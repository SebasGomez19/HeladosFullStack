package dev.sebas.heladosfulsta.services;

import dev.sebas.heladosfulsta.entities.Venta;
import dev.sebas.heladosfulsta.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements iVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public void save(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public void deleteById(Integer id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public Optional<Venta> findById(Integer id) {
        return ventaRepository.findById(id);
    }

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Page<Venta> findAll(Pageable pageable) {
        return ventaRepository.findAll(pageable);
    }
}