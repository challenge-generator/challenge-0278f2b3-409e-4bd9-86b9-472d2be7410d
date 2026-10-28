package com.prestamos.hipotecarios.infrastructure.persistence;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestamos", indexes = {
    @Index(name = "idx_prestamo_cliente_id", columnList = "cliente_id"),
    @Index(name = "idx_prestamo_estado", columnList = "estado"),
    @Index(name = "idx_prestamo_fecha_inicio", columnList = "fecha_inicio")
})
public class PrestamoEntity {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoEntity.class);

    @Id
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "cliente_id", nullable = false, columnDefinition = "UUID")
    private UUID clienteId;

    @Column(name = "monto", nullable = false, precision = 19, scale = 2)
    private BigDecimal monto;

    @Column(name = "tasa_interes", nullable = false, precision = 5, scale = 4)
    private BigDecimal tasaInteres;

    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "cuota_mensual", precision = 19, scale = 2)
    private BigDecimal cuotaMensual;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    public PrestamoEntity() {
    }

    public static PrestamoEntity fromDomain(Prestamo prestamo) {
        logger.debug("Convirtiendo dominio Prestamo a entidad: {}", prestamo.getId());
        PrestamoEntity entity = new PrestamoEntity();
        entity.setId(prestamo.getId());
        entity.setClienteId(prestamo.getClienteId());
        entity.setMonto(prestamo.getMonto());
        entity.setTasaInteres(prestamo.getTasaInteres());
        entity.setPlazoMeses(prestamo.getPlazoMeses());
        entity.setFechaInicio(prestamo.getFechaInicio());
        entity.setEstado(prestamo.getEstado());
        entity.setCuotaMensual(prestamo.calcularCuotaMensual());
        entity.setFechaCreacion(LocalDate.now());
        entity.setFechaActualizacion(LocalDate.now());
        logger.debug("Entidad creada exitosamente para préstamo: {}", entity.getId());
        return entity;
    }

    public Prestamo toDomain() {
        logger.debug("Convirtiendo entidad a dominio Prestamo: {}", this.id);
        Prestamo prestamo = new Prestamo(
            this.clienteId,
            this.monto,
            this.tasaInteres,
            this.plazoMeses,
            this.fechaInicio
        );
        logger.debug("Dominio creado exitosamente para préstamo: {}", this.id);
        return prestamo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(BigDecimal cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "PrestamoEntity{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", monto=" + monto +
                ", tasaInteres=" + tasaInteres +
                ", plazoMeses=" + plazoMeses +
                ", fechaInicio=" + fechaInicio +
                ", estado='" + estado + '\'' +
                ", cuotaMensual=" + cuotaMensual +
                '}';
    }
}