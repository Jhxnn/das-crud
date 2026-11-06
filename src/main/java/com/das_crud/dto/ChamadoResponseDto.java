package com.das_crud.dto;

import com.das_crud.model.StatusChamado;

import java.time.LocalDateTime;

public record ChamadoResponseDto(
        Long id,
        String titulo,
        String descricao,
        StatusChamado status,
        LocalDateTime criadoEm
) {}
