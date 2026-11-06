package com.das_crud.controller;

import com.das_crud.dto.TecnicoRequestDto;
import com.das_crud.dto.TecnicoResponseDto;
import com.das_crud.interfaces.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoController {

    private final TecnicoService service;

    public TecnicoController(TecnicoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TecnicoResponseDto criar(@Valid @RequestBody TecnicoRequestDto request) {
        return service.criar(request);
    }

    @GetMapping
    public List<TecnicoResponseDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TecnicoResponseDto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
