package com.prestamos.hipotecarios.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prestamo {
    private UUID id;

    @NotNull(message = "El cliente es obligatorio")
    private UUID clienteId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private BigDecimal monto;

    @NotNull(message = "La tasa de interés es obligatoria")
    @Positive(message = "La tasa de interés debe ser positiva")
    private BigDecimal tasaInteres;

    @NotNull(message = "El plazo en meses es obligatorio")
    @Positive(message = "El plazo en meses debe ser positivo")
    private Integer plazoMeses;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "El estado es obligatorio")
    @Size(min = 3, max = 20, message = "El estado debe tener entre 3 y 20 caracteres")
    private String estado;

    public Prestamo() {
        this.id = UUID.randomUUID();
        this.estado = "PENDIENTE";
    }

    public Prestamo(UUID clienteId, BigDecimal monto, BigDecimal tasaInteres, Integer plazoMeses, LocalDate fechaInicio) {
        this();
        this.clienteId = clienteId;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.plazoMeses = plazoMeses;
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal calcularCuotaMensual() {
        BigDecimal tasaMensual = tasaInteres.divide(new BigDecimal("12"), 10, BigDecimal.ROUND_HALF_UP)
                .divide(new BigDecimal("100"), 10, BigDecimal.ROUND_HALF_UP);
        BigDecimal plazo = new BigDecimal(plazoMeses);
        BigDecimal factor = tasaMensual.add(BigDecimal.ONE).pow(plazoMeses);
        return monto.multiply(tasaMensual).multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
    }

    public boolean esActivo() {
        return "ACTIVO".equals(estado);
    }

    public boolean puedeSerAprobado() {
        return "PENDIENTE".equals(estado);
    }
}