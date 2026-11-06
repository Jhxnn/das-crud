package com.das_crud.interfaces;

import com.das_crud.dto.ChamadoRequestDto;
import com.das_crud.dto.ChamadoResponseDto;

import java.util.List;

public interface ChamadoService {
    ChamadoResponseDto abrir(ChamadoRequestDto request);

    List<ChamadoResponseDto> listar();
}
