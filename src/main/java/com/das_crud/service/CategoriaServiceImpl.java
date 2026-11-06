package com.das_crud.service;

import com.das_crud.dto.CategoriaRequestDto;
import com.das_crud.dto.CategoriaResponseDto;
import com.das_crud.exception.RecursoNaoEncontradoException;
import com.das_crud.interfaces.CategoriaService;
import com.das_crud.model.Categoria;
import com.das_crud.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoriaResponseDto criar(CategoriaRequestDto request) {
        String nome = nomeValido(request);

        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalStateException("Categoria '" + nome + "' já cadastrada");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        return paraResponse(repository.save(categoria));
    }

    @Override
    public List<CategoriaResponseDto> listar() {
        return repository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    @Override
    public CategoriaResponseDto buscarPorId(Long id) {
        return paraResponse(buscarEntidade(id));
    }

    @Override
    public CategoriaResponseDto atualizar(Long id, CategoriaRequestDto request) {
        Categoria categoria = buscarEntidade(id);
        String nome = nomeValido(request);

        if (repository.existsByNomeIgnoreCaseAndIdNot(nome, id)) {
            throw new IllegalStateException("Categoria '" + nome + "' já cadastrada");
        }

        categoria.setNome(nome);
        return paraResponse(repository.save(categoria));
    }

    @Override
    public void excluir(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private String nomeValido(CategoriaRequestDto request) {
        if (request.nome() == null || request.nome().isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório");
        }
        return request.nome().trim();
    }

    private Categoria buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria " + id + " não encontrada"));
    }

    private CategoriaResponseDto paraResponse(Categoria categoria) {
        return new CategoriaResponseDto(categoria.getId(), categoria.getNome());
    }
}
