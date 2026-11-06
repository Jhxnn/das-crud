package com.das_crud.interfaces;

import com.das_crud.dto.ChamadoAtualizarRequestDto;
import com.das_crud.dto.ChamadoRequestDto;
import com.das_crud.dto.ChamadoResponseDto;
import com.das_crud.dto.ChamadoStatusRequestDto;

import java.util.List;

public interface ChamadoService {
    ChamadoResponseDto abrir(ChamadoRequestDto request);

    List<ChamadoResponseDto> listar();

    ChamadoResponseDto buscarPorId(Long id);

    ChamadoResponseDto atualizar(Long id, ChamadoAtualizarRequestDto request);

    ChamadoResponseDto alterarStatus(Long id, ChamadoStatusRequestDto request);

    void excluir(Long id);
}
