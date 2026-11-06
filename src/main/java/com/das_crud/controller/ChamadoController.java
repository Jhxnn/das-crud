package com.das_crud.controller;

import com.das_crud.dto.ChamadoAtualizarRequestDto;
import com.das_crud.dto.ChamadoRequestDto;
import com.das_crud.dto.ChamadoResponseDto;
import com.das_crud.dto.ChamadoStatusRequestDto;
import com.das_crud.interfaces.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChamadoResponseDto criar(@Valid @RequestBody ChamadoRequestDto request) {
        return service.abrir(request);
    }

    @GetMapping
    public List<ChamadoResponseDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ChamadoResponseDto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ChamadoResponseDto atualizar(@PathVariable Long id,
                                        @Valid @RequestBody ChamadoAtualizarRequestDto request) {
        return service.atualizar(id, request);
    }

    @PatchMapping("/{id}/status")
    public ChamadoResponseDto alterarStatus(@PathVariable Long id,
                                            @Valid @RequestBody ChamadoStatusRequestDto request) {
        return service.alterarStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
