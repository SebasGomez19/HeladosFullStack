package dev.sebas.heladosfulsta.rest;

import dev.sebas.heladosfulsta.entities.Helado;
import dev.sebas.heladosfulsta.services.HeladoService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/helados")
public class HeladoRest {

    @Autowired
    private HeladoService heladoService;

    @GetMapping("/listar")
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(required = false, defaultValue = "") String criterio,
                         Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Helado> helados;

        helados = heladoService.findAll(pageable);


        model.addAttribute("activePage", "listar");
        model.addAttribute("menuListar", "helados");
        model.addAttribute("helados", helados.getContent());
        model.addAttribute("totalElements", helados.getTotalElements());
        model.addAttribute("currentPage", helados.getNumber());
        model.addAttribute("totalPages", helados.getTotalPages());
        model.addAttribute("criterio", criterio);
        return "Helados/listarHelados";
    }

    @GetMapping("/listar/{id}")
    public String listarById(@PathVariable Integer id, Model model) {
        model.addAttribute("activePage", "listar");
        model.addAttribute("menuListar", "helados");
        model.addAttribute("helado", heladoService.findById(id).orElse(null));
        return "Helados/listarHelados";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("activePage", "formulario");
        model.addAttribute("menuListar", "heladosForm");
        model.addAttribute("objHelado", new Helado());
        return "Helados/crearHelado";
    }

    @PostMapping("/crear")
    public String guardar(@Valid @ModelAttribute("objHelado") Helado objHelado,
                          Model model, SessionStatus status) {
        try {
            heladoService.save(objHelado);
            status.setComplete();
            return "redirect:/helados/listar";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el helado: " + e.getMessage());
            return "Helados/crearHelado";
        }
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarActualizar(@PathVariable Integer id, Model model) {
        model.addAttribute("activePage", "formulario");
        model.addAttribute("menuListar", "heladosForm");
        model.addAttribute("objHelado", heladoService.findById(id).orElse(null));
        return "Helados/actualizarHelado";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @ModelAttribute("objHelado") Helado objHelado) {
        objHelado.setCodHelado(id);
        heladoService.save(objHelado);
        return "redirect:/helados/listar";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable @Min(1) Integer id, RedirectAttributes redirectAttributes) {
        try {
            heladoService.eliminarHelado(id);
            redirectAttributes.addFlashAttribute("message", "Helado eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/helados/listar";
    }
}