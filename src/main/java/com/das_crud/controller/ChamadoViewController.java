package com.das_crud.controller;

import com.das_crud.dto.ChamadoRequestDto;
import com.das_crud.interfaces.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chamados")
public class ChamadoViewController {

    private final ChamadoService service;

    public ChamadoViewController(ChamadoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("chamados", service.listar());
        return "chamados/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("request", new ChamadoRequestDto("", ""));
        return "chamados/formulario";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("request") ChamadoRequestDto request,
                        BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "chamados/formulario";
        }

        service.abrir(request);
        return "redirect:/chamados";
    }
}
