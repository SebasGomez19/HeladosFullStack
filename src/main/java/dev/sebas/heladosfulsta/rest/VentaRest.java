package dev.sebas.heladosfulsta.rest;

import dev.sebas.heladosfulsta.entities.Cliente;
import dev.sebas.heladosfulsta.entities.Helado;
import dev.sebas.heladosfulsta.entities.Venta;
import dev.sebas.heladosfulsta.services.ClienteService;
import dev.sebas.heladosfulsta.services.HeladoService;
import dev.sebas.heladosfulsta.services.VentaService;
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

import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaRest {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HeladoService heladoService;

    private void cargarCombos(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        List<Helado> helados = heladoService.encontrarActivos();
        model.addAttribute("arrClientes", clientes);
        model.addAttribute("arrHelados", helados);
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Venta> ventas = ventaService.findAll(pageable);

        model.addAttribute("activePage", "listar");
        model.addAttribute("menuListar", "ventas");
        model.addAttribute("ventas", ventas.getContent());
        model.addAttribute("totalElements", ventas.getTotalElements());
        model.addAttribute("currentPage", ventas.getNumber());
        model.addAttribute("totalPages", ventas.getTotalPages());
        return "Ventas/listarVentas";
    }

    @GetMapping("/listar/{id}")
    public String listarById(@PathVariable Integer id, Model model) {
        model.addAttribute("activePage", "listar");
        model.addAttribute("menuListar", "ventas");
        model.addAttribute("venta", ventaService.findById(id).orElse(null));
        return "Ventas/listarVentas";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("activePage", "formulario");
        model.addAttribute("menuListar", "ventasForm");
        model.addAttribute("objVenta", new Venta());
        cargarCombos(model);
        return "Ventas/crearVenta";
    }

    @PostMapping("/crear")
    public String guardar(@Valid @ModelAttribute("objVenta") Venta objVenta,
                          Model model, SessionStatus status) {
        try {
            ventaService.save(objVenta);
            status.setComplete();
            return "redirect:/ventas/listar";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la venta: " + e.getMessage());
            cargarCombos(model);
            return "Ventas/crearVenta";
        }
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarActualizar(@PathVariable Integer id, Model model) {
        model.addAttribute("activePage", "formulario");
        model.addAttribute("menuListar", "ventasForm");
        model.addAttribute("objVenta", ventaService.findById(id).orElse(null));
        cargarCombos(model);
        return "Ventas/actualizarVenta";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @ModelAttribute("objVenta") Venta objVenta) {
        objVenta.setCodVenta(id);
        ventaService.save(objVenta);
        return "redirect:/ventas/listar";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable @Min(1) Integer id) {
        ventaService.deleteById(id);
        return "redirect:/ventas/listar";
    }
}