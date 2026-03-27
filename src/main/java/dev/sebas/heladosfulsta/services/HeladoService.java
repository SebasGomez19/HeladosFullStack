package dev.sebas.heladosfulsta.services;

import dev.sebas.heladosfulsta.entities.Helado;
import dev.sebas.heladosfulsta.repositories.HeladosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeladoService implements iHeladoService {

    @Autowired
    private HeladosRepository heladoRepository;

    @Override
    public void save(Helado helado) {
        heladoRepository.save(helado);
    }

    @Override
    public void deleteById(Integer id) {
        heladoRepository.deleteById(id);
    }

    @Override
    public Optional<Helado> findById(Integer id) {
        return heladoRepository.findById(id);
    }

    @Override
    public List<Helado> findAll() {
        return heladoRepository.findAll();
    }

    @Override
    public Page<Helado> findAll(Pageable pageable) {
        return heladoRepository.findAll(pageable);
    }

    public void eliminarHelado(Integer id) {
        try {
            heladoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("No se puede eliminar el Helado debido a que esta asociado a una venta");
        }
    }

    public List<Helado> encontrarActivos() {
        return heladoRepository.findHeladosActivos();
    }
}