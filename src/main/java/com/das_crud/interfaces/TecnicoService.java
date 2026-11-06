package com.das_crud.interfaces;

import com.das_crud.dto.TecnicoRequestDto;
import com.das_crud.dto.TecnicoResponseDto;

import java.util.List;

public interface TecnicoService {
    TecnicoResponseDto criar(TecnicoRequestDto request);

    List<TecnicoResponseDto> listar();

    TecnicoResponseDto buscarPorId(Long id);
}
