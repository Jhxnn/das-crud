package com.das_crud.dto;

import com.das_crud.model.StatusChamado;
import jakarta.validation.constraints.NotNull;

public record ChamadoStatusRequestDto(
        @NotNull StatusChamado status
) {}
