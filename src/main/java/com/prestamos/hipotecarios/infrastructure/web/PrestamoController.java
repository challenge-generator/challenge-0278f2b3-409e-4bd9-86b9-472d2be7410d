package com.prestamos.hipotecarios.infrastructure.web;

import com.prestamos.hipotecarios.application.usecase.CrearPrestamoUseCase;
import com.prestamos.hipotecarios.domain.model.Prestamo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoController.class);

    private final CrearPrestamoUseCase crearPrestamoUseCase;

    public PrestamoController(CrearPrestamoUseCase crearPrestamoUseCase) {
        this.crearPrestamoUseCase = crearPrestamoUseCase;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> crearPrestamo(@Valid @RequestBody PrestamoRequest request) {
        logger.info("Recibida solicitud de creación de préstamo para cliente: {}", request.clienteId());
        
        Prestamo prestamo = crearPrestamoUseCase.ejecutar(
            request.clienteId(),
            request.monto(),
            request.tasaInteres(),
            request.plazoMeses(),
            request.fechaInicio()
        );
        
        logger.info("Préstamo creado exitosamente con ID: {}", prestamo.getId());
        PrestamoResponse response = PrestamoResponse.fromDomain(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> obtenerPrestamo(@PathVariable UUID id) {
        logger.info("Consultando préstamo con ID: {}", id);
        
        return crearPrestamoUseCase.buscarPorId(id)
            .map(prestamo -> {
                logger.debug("Préstamo encontrado: {}", id);
                return ResponseEntity.ok(PrestamoResponse.fromDomain(prestamo));
            })
            .orElseGet(() -> {
                logger.warn("Préstamo no encontrado: {}", id);
                return ResponseEntity.notFound().build();
            });
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PrestamoResponse>> obtenerPrestamosPorCliente(@PathVariable UUID clienteId) {
        logger.info("Consultando préstamos para cliente: {}", clienteId);
        
        List<PrestamoResponse> prestamos = crearPrestamoUseCase.buscarPorCliente(clienteId)
            .stream()
            .map(PrestamoResponse::fromDomain)
            .toList();
        
        logger.debug("Se encontraron {} préstamos para cliente: {}", prestamos.size(), clienteId);
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> obtenerPrestamosPorEstado(@RequestParam String estado) {
        logger.info("Consultando préstamos con estado: {}", estado);
        
        List<PrestamoResponse> prestamos = crearPrestamoUseCase.buscarPorEstado(estado)
            .stream()
            .map(PrestamoResponse::fromDomain)
            .toList();
        
        logger.debug("Se encontraron {} préstamos con estado: {}", prestamos.size(), estado);
        return ResponseEntity.ok(prestamos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable UUID id) {
        logger.info("Solicitud de eliminación de préstamo: {}", id);
        
        try {
            crearPrestamoUseCase.eliminarPrestamo(id);
            logger.info("Préstamo eliminado exitosamente: {}", id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            logger.error("Error al eliminar préstamo {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    public record PrestamoRequest(
        @NotNull(message = "El ID del cliente es obligatorio")
        UUID clienteId,
        
        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser positivo")
        BigDecimal monto,
        
        @NotNull(message = "La tasa de interés es obligatoria")
        @Positive(message = "La tasa de interés debe ser positiva")
        BigDecimal tasaInteres,
        
        @NotNull(message = "El plazo en meses es obligatorio")
        @Positive(message = "El plazo debe ser positivo")
        @Size(min = 12, max = 360, message = "El plazo debe estar entre 12 y 360 meses")
        Integer plazoMeses,
        
        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio
    ) {}

    public record PrestamoResponse(
        UUID id,
        UUID clienteId,
        BigDecimal monto,
        BigDecimal tasaInteres,
        Integer plazoMeses,
        LocalDate fechaInicio,
        String estado,
        BigDecimal cuotaMensual
    ) {
        public static PrestamoResponse fromDomain(Prestamo prestamo) {
            return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getClienteId(),
                prestamo.getMonto(),
                prestamo.getTasaInteres(),
                prestamo.getPlazoMeses(),
                prestamo.getFechaInicio(),
                prestamo.getEstado(),
                prestamo.calcularCuotaMensual()
            );
        }
    }
}