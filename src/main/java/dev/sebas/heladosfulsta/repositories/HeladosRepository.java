package dev.sebas.heladosfulsta.repositories;

import dev.sebas.heladosfulsta.entities.Helado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeladosRepository extends JpaRepository<Helado, Integer> {
}
