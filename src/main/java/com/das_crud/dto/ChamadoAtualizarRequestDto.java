package com.das_crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChamadoAtualizarRequestDto(
        @NotBlank @Size(max = 120) String titulo,
        @NotBlank @Size(max = 1000) String descricao
) {}
