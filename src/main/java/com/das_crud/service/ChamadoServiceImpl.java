package com.das_crud.service;

import com.das_crud.dto.ChamadoRequestDto;
import com.das_crud.dto.ChamadoResponseDto;
import com.das_crud.interfaces.ChamadoService;
import com.das_crud.model.Chamado;
import com.das_crud.model.StatusChamado;
import com.das_crud.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoServiceImpl(ChamadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChamadoResponseDto abrir(ChamadoRequestDto request) {
        Chamado chamado = new Chamado();
        chamado.setTitulo(request.titulo());
        chamado.setDescricao(request.descricao());
        chamado.setStatus(StatusChamado.ABERTO);

        return paraResponse(repository.save(chamado));
    }

    @Override
    public List<ChamadoResponseDto> listar() {
        return repository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    private ChamadoResponseDto paraResponse(Chamado chamado) {
        return new ChamadoResponseDto(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getStatus(),
                chamado.getCriadoEm()
        );
    }
}
