package com.das_crud.service;

import com.das_crud.dto.CategoriaRequestDto;
import com.das_crud.dto.CategoriaResponseDto;
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
        if (request.nome() == null || request.nome().isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(request.nome().trim());

        return paraResponse(repository.save(categoria));
    }

    @Override
    public List<CategoriaResponseDto> listar() {
        return repository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    private CategoriaResponseDto paraResponse(Categoria categoria) {
        return new CategoriaResponseDto(categoria.getId(), categoria.getNome());
    }
}
