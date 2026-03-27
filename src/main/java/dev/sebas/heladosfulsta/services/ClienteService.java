package dev.sebas.heladosfulsta.services;

import dev.sebas.heladosfulsta.entities.Cliente;
import dev.sebas.heladosfulsta.repositories.ClienteRepository;
import dev.sebas.heladosfulsta.services.iClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements iClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

}