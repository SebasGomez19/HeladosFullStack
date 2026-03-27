package dev.sebas.heladosfulsta.repositories;

import dev.sebas.heladosfulsta.entities.Helado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeladosRepository extends JpaRepository<Helado, Integer> {

    @Query("SELECT h FROM Helado h WHERE h.estado = 1")
    List<Helado> findHeladosActivos();
}
