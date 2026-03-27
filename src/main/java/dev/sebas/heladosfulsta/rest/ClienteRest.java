package dev.sebas.heladosfulsta.rest;

import dev.sebas.heladosfulsta.entities.Cliente;
import dev.sebas.heladosfulsta.services.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/clientes")
public class ClienteRest {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(required = false, defaultValue = "") String criterio,
                         Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clientes;

        clientes = clienteService.findAll(pageable);


        model.addAttribute("activePage", "listar");
        model.addAttribute("menuListar", "clientes");
        model.addAttribute("clientes", clientes.getContent());
        model.addAttribute("totalElements", clientes.getTotalElements());
        model.addAttribute("currentPage", clientes.getNumber());
        model.addAttribute("totalPages", clientes.getTotalPages());
        model.addAttribute("criterio", criterio);
        return "Clientes/listarClientes";
    }

    @GetMapping("/listar/{id}")
    public String listarById(@PathVariable Integer id, Model model) {
        model.addAttribute("activePage", "listar");
        model.addAttribute("menuListar", "clientes");
        model.addAttribute("cliente", clienteService.findById(id).orElse(null));
        return "Clientes/listarClientes";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("activePage", "formulario");
        model.addAttribute("menuListar", "clientesForm");
        model.addAttribute("objCliente", new Cliente());
        return "Clientes/crearCliente";
    }

    @PostMapping("/crear")
    public String guardar(@Valid @ModelAttribute("objCliente") Cliente objCliente,
                          Model model, SessionStatus status) {
        try {
            clienteService.save(objCliente);
            status.setComplete();
            return "redirect:/clientes/listar";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el cliente: " + e.getMessage());
            return "Clientes/crearCliente";
        }
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarActualizar(@PathVariable Integer id, Model model) {
        model.addAttribute("activePage", "formulario");
        model.addAttribute("menuListar", "clientesForm");
        model.addAttribute("objCliente", clienteService.findById(id).orElse(null));
        return "Clientes/actualizarCliente";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @ModelAttribute("objCliente") Cliente objCliente) {
        objCliente.setCodCliente(id);
        clienteService.save(objCliente);
        return "redirect:/clientes/listar";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable @Min(1) Integer id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/listar";
    }
}