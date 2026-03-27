package dev.sebas.heladosfulsta.services;

import dev.sebas.heladosfulsta.entities.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface iVentaService {

    void save(Venta venta);
    void deleteById(Integer id);
    Optional<Venta> findById(Integer id);
    List<Venta> findAll();
    Page<Venta> findAll(Pageable pageable);
}