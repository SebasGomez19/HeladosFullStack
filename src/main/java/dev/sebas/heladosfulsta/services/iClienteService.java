package dev.sebas.heladosfulsta.services;

import dev.sebas.heladosfulsta.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface iClienteService {

    void save(Cliente cliente);
    void deleteById(Integer id);
    Optional<Cliente> findById(Integer id);
    List<Cliente> findAll();
    Page<Cliente> findAll(Pageable pageable);
}