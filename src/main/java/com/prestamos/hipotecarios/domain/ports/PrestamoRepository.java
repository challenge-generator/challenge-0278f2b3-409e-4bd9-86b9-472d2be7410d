package com.prestamos.hipotecarios.domain.ports;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(UUID id);
    List<Prestamo> findByClienteId(UUID clienteId);
    List<Prestamo> findByEstado(String estado);
    void deleteById(UUID id);
}