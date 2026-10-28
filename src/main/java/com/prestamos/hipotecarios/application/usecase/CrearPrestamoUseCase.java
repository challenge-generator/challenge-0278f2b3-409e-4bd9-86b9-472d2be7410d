package com.prestamos.hipotecarios.application.usecase;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CrearPrestamoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CrearPrestamoUseCase.class);
    private static final BigDecimal MONTO_MINIMO = new BigDecimal("10000.00");
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("5000000.00");
    private static final BigDecimal TASA_MINIMA = new BigDecimal("0.05");
    private static final BigDecimal TASA_MAXIMA = new BigDecimal("0.25");
    private static final Integer PLAZO_MINIMO = 12;
    private static final Integer PLAZO_MAXIMO = 360;

    private final PrestamoRepository prestamoRepository;

    public CrearPrestamoUseCase(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Transactional
    public Prestamo ejecutar(UUID clienteId, BigDecimal monto, BigDecimal tasaInteres, 
                             Integer plazoMeses, LocalDate fechaInicio) {
        logger.info("Iniciando proceso de creación de préstamo para cliente: {}", clienteId);
        
        validarParametros(clienteId, monto, tasaInteres, plazoMeses, fechaInicio);
        
        Prestamo prestamo = new Prestamo(clienteId, monto, tasaInteres, plazoMeses, fechaInicio);
        
        if (!puedeSerAprobado(monto, tasaInteres, plazoMeses)) {
            logger.warn("Préstamo no aprobado para cliente {}: no cumple criterios de riesgo", clienteId);
            throw new IllegalArgumentException("El préstamo no cumple los criterios de aprobación");
        }
        
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        logger.info("Préstamo creado exitosamente con ID: {} para cliente: {}", 
                   prestamoGuardado.getId(), clienteId);
        
        return prestamoGuardado;
    }

    private void validarParametros(UUID clienteId, BigDecimal monto, BigDecimal tasaInteres, 
                                   Integer plazoMeses, LocalDate fechaInicio) {
        if (clienteId == null) {
            logger.error("Error de validación: el ID del cliente no puede ser nulo");
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }
        
        if (monto == null || monto.compareTo(MONTO_MINIMO) < 0 || monto.compareTo(MONTO_MAXIMO) > 0) {
            logger.error("Error de validación: monto {} fuera de rango permitido [{}, {}]", 
                        monto, MONTO_MINIMO, MONTO_MAXIMO);
            throw new IllegalArgumentException(
                String.format("El monto debe estar entre %s y %s", MONTO_MINIMO, MONTO_MAXIMO));
        }
        
        if (tasaInteres == null || tasaInteres.compareTo(TASA_MINIMA) < 0 || 
            tasaInteres.compareTo(TASA_MAXIMA) > 0) {
            logger.error("Error de validación: tasa de interés {} fuera de rango permitido [{}, {}]", 
                        tasaInteres, TASA_MINIMA, TASA_MAXIMA);
            throw new IllegalArgumentException(
                String.format("La tasa de interés debe estar entre %s y %s", TASA_MINIMA, TASA_MAXIMA));
        }
        
        if (plazoMeses == null || plazoMeses < PLAZO_MINIMO || plazoMeses > PLAZO_MAXIMO) {
            logger.error("Error de validación: plazo {} meses fuera de rango permitido [{}, {}]", 
                        plazoMeses, PLAZO_MINIMO, PLAZO_MAXIMO);
            throw new IllegalArgumentException(
                String.format("El plazo debe estar entre %d y %d meses", PLAZO_MINIMO, PLAZO_MAXIMO));
        }
        
        if (fechaInicio == null || fechaInicio.isBefore(LocalDate.now())) {
            logger.error("Error de validación: fecha de inicio {} inválida", fechaInicio);
            throw new IllegalArgumentException("La fecha de inicio debe ser hoy o una fecha futura");
        }
        
        logger.debug("Todos los parámetros validados correctamente para cliente: {}", clienteId);
    }

    private boolean puedeSerAprobado(BigDecimal monto, BigDecimal tasaInteres, Integer plazoMeses) {
        BigDecimal cuotaMensual = monto.multiply(tasaInteres)
            .divide(BigDecimal.valueOf(12), 2, java.math.RoundingMode.HALF_UP)
            .multiply(BigDecimal.ONE.add(tasaInteres.divide(BigDecimal.valueOf(12), 10, 
                java.math.RoundingMode.HALF_UP)).pow(plazoMeses))
            .divide(BigDecimal.ONE.add(tasaInteres.divide(BigDecimal.valueOf(12), 10, 
                java.math.RoundingMode.HALF_UP)).pow(plazoMeses).subtract(BigDecimal.ONE), 
                2, java.math.RoundingMode.HALF_UP);
        
        BigDecimal ingresoEstimado = monto.divide(BigDecimal.valueOf(plazoMeses), 2, 
            java.math.RoundingMode.HALF_UP);
        
        return cuotaMensual.compareTo(ingresoEstimado.multiply(new BigDecimal("0.35"))) < 0;
    }

    public Optional<Prestamo> buscarPorId(UUID id) {
        logger.info("Buscando préstamo con ID: {}", id);
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if (prestamo.isPresent()) {
            logger.debug("Préstamo encontrado: {}", id);
        } else {
            logger.warn("No se encontró préstamo con ID: {}", id);
        }
        return prestamo;
    }

    public List<Prestamo> buscarPorCliente(UUID clienteId) {
        logger.info("Buscando préstamos para cliente: {}", clienteId);
        List<Prestamo> prestamos = prestamoRepository.findByClienteId(clienteId);
        logger.debug("Se encontraron {} préstamos para cliente: {}", prestamos.size(), clienteId);
        return prestamos;
    }

    public List<Prestamo> buscarPorEstado(String estado) {
        logger.info("Buscando préstamos con estado: {}", estado);
        List<Prestamo> prestamos = prestamoRepository.findByEstado(estado);
        logger.debug("Se encontraron {} préstamos con estado: {}", prestamos.size(), estado);
        return prestamos;
    }

    public void eliminarPrestamo(UUID id) {
        logger.info("Eliminando préstamo con ID: {}", id);
        if (prestamoRepository.findById(id).isEmpty()) {
            logger.error("No se puede eliminar: préstamo con ID {} no existe", id);
            throw new IllegalArgumentException("El préstamo no existe");
        }
        prestamoRepository.deleteById(id);
        logger.info("Préstamo eliminado exitosamente: {}", id);
    }
}