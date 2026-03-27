package dev.sebas.heladosfulsta.services;

import dev.sebas.heladosfulsta.entities.Helado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface iHeladoService {

    void save(Helado helado);
    void deleteById(Integer id);
    Optional<Helado> findById(Integer id);
    List<Helado> findAll();
    Page<Helado> findAll(Pageable pageable);
}