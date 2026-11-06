package com.das_crud.controller;

import com.das_crud.dto.CategoriaRequestDto;
import com.das_crud.interfaces.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaViewController {

    private final CategoriaService service;

    public CategoriaViewController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", service.listar());
        return "categorias/lista";
    }

    @GetMapping("/nova")
    public String novo(Model model) {
        model.addAttribute("request", new CategoriaRequestDto(""));
        return "categorias/formulario";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("request") CategoriaRequestDto request,
                        BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "categorias/formulario";
        }

        try {
            service.criar(request);
        } catch (IllegalStateException ex) {
            resultado.rejectValue("nome", "duplicada", ex.getMessage());
            return "categorias/formulario";
        }

        return "redirect:/categorias";
    }
}
