package com.das_crud.controller;

import com.das_crud.dto.CategoriaRequestDto;
import com.das_crud.dto.CategoriaResponseDto;
import com.das_crud.interfaces.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDto criar(@Valid @RequestBody CategoriaRequestDto request) {
        return service.criar(request);
    }

    @GetMapping
    public List<CategoriaResponseDto> listar() {
        return service.listar();
    }
}
