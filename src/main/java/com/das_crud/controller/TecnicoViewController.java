package com.das_crud.controller;

import com.das_crud.interfaces.TecnicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tecnicos")
public class TecnicoViewController {

    private final TecnicoService service;

    public TecnicoViewController(TecnicoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tecnicos", service.listar());
        return "tecnicos/lista";
    }
}
