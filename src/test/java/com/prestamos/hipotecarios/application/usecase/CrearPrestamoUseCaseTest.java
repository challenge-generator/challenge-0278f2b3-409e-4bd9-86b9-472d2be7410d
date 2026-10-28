package com.prestamos.hipotecarios.application.usecase;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para CrearPrestamoUseCase")
class CrearPrestamoUseCaseTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private CrearPrestamoUseCase crearPrestamoUseCase;

    private UUID clienteId;
    private BigDecimal monto;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;
    private LocalDate fechaInicio;

    @BeforeEach
    void setUp() {
        clienteId = UUID.randomUUID();
        monto = new BigDecimal("250000.00");
        tasaInteres = new BigDecimal("0.065");
        plazoMeses = 240;
        fechaInicio = LocalDate.now();
    }

    @Test
    @DisplayName("Debe crear un préstamo exitosamente cuando los datos son válidos")
    void debeCrearPrestamoExitosamente() {
        Prestamo prestamoGuardado = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );
        prestamoGuardado.esActivo();

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamoGuardado);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isPresent(), "El préstamo debe ser creado exitosamente");
        assertEquals(clienteId, resultado.get().getClienteId(), "El ID del cliente debe coincidir");
        assertEquals(monto, resultado.get().getMonto(), "El monto debe coincidir");
        assertEquals(tasaInteres, resultado.get().getTasaInteres(), "La tasa de interés debe coincidir");
        assertEquals(plazoMeses, resultado.get().getPlazoMeses(), "El plazo en meses debe coincidir");
        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe calcular correctamente la cuota mensual del préstamo")
    void debeCalcularCuotaMensualCorrectamente() {
        Prestamo prestamo = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        BigDecimal cuotaMensual = resultado.get().calcularCuotaMensual();
        assertNotNull(cuotaMensual, "La cuota mensual no debe ser nula");
        assertTrue(cuotaMensual.compareTo(BigDecimal.ZERO) > 0, "La cuota mensual debe ser mayor a cero");
    }

    @Test
    @DisplayName("Debe validar que el préstamo puede ser aprobado con datos válidos")
    void debeValidarAprobacionDelPrestamo() {
        Prestamo prestamo = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.get().puedeSerAprobado(), "El préstamo debe poder ser aprobado");
    }

    @Test
    @DisplayName("Debe marcar el préstamo como activo después de ser creado")
    void debeMarcarPrestamoComoActivo() {
        Prestamo prestamo = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.get().esActivo(), "El préstamo debe estar activo");
    }

    @Test
    @DisplayName("Debe rechazar monto cero o negativo")
    void debeRechazarMontoCeroONegativo() {
        BigDecimal montoInvalido = BigDecimal.ZERO;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, montoInvalido, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo con monto cero");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar tasa de interés negativa")
    void debeRechazarTasaInteresNegativa() {
        BigDecimal tasaNegativa = new BigDecimal("-0.05");

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaNegativa, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo con tasa negativa");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar plazo menor a un mes")
    void debeRechazarPlazoMenorAMes() {
        Integer plazoInvalido = 0;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoInvalido, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo con plazo cero");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar cliente nulo")
    void debeRechazarClienteNulo() {
        UUID clienteNulo = null;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteNulo, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo sin cliente");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar fecha de inicio nula")
    void debeRechazarFechaInicioNula() {
        LocalDate fechaNula = null;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaNula
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo sin fecha de inicio");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }
}