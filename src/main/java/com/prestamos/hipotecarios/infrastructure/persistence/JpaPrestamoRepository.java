package com.prestamos.hipotecarios.infrastructure.persistence;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaPrestamoRepository implements PrestamoRepository {
    private final SpringDataPrestamoRepository repository;

    public JpaPrestamoRepository(SpringDataPrestamoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        PrestamoEntity entity = PrestamoEntity.fromDomain(prestamo);
        PrestamoEntity savedEntity = repository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Prestamo> findById(UUID id) {
        return repository.findById(id).map(PrestamoEntity::toDomain);
    }

    @Override
    public List<Prestamo> findByClienteId(UUID clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(PrestamoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> findByEstado(String estado) {
        return repository.findByEstado(estado).stream()
                .map(PrestamoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}