package com.das_crud.service;

import com.das_crud.dto.ChamadoAtualizarRequestDto;
import com.das_crud.dto.ChamadoRequestDto;
import com.das_crud.dto.ChamadoResponseDto;
import com.das_crud.dto.ChamadoStatusRequestDto;
import com.das_crud.exception.RecursoNaoEncontradoException;
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

    @Override
    public ChamadoResponseDto buscarPorId(Long id) {
        return paraResponse(buscarEntidade(id));
    }

    @Override
    public ChamadoResponseDto atualizar(Long id, ChamadoAtualizarRequestDto request) {
        Chamado chamado = buscarEntidade(id);
        chamado.setTitulo(request.titulo());
        chamado.setDescricao(request.descricao());

        return paraResponse(repository.save(chamado));
    }

    @Override
    public ChamadoResponseDto alterarStatus(Long id, ChamadoStatusRequestDto request) {
        Chamado chamado = buscarEntidade(id);

        if (chamado.getStatus() == StatusChamado.CONCLUIDO) {
            throw new IllegalStateException("Chamado concluído não pode mudar de status");
        }

        chamado.setStatus(request.status());
        return paraResponse(repository.save(chamado));
    }

    @Override
    public void excluir(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Chamado buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Chamado " + id + " não encontrado"));
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
