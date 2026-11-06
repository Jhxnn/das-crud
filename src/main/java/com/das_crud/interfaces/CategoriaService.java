package com.das_crud.interfaces;

import com.das_crud.dto.CategoriaRequestDto;
import com.das_crud.dto.CategoriaResponseDto;

import java.util.List;

public interface CategoriaService {
    CategoriaResponseDto criar(CategoriaRequestDto request);

    List<CategoriaResponseDto> listar();
}
