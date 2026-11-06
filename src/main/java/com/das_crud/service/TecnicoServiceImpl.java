package com.das_crud.service;

import com.das_crud.dto.TecnicoRequestDto;
import com.das_crud.dto.TecnicoResponseDto;
import com.das_crud.exception.RecursoNaoEncontradoException;
import com.das_crud.interfaces.TecnicoService;
import com.das_crud.model.Tecnico;
import com.das_crud.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository repository;

    public TecnicoServiceImpl(TecnicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public TecnicoResponseDto criar(TecnicoRequestDto request) {
        String email = request.email().trim();

        if (repository.existsByEmailIgnoreCase(email)) {
            throw new IllegalStateException("E-mail '" + email + "' já cadastrado");
        }

        Tecnico tecnico = new Tecnico();
        tecnico.setNome(request.nome().trim());
        tecnico.setEmail(email);

        return paraResponse(repository.save(tecnico));
    }

    @Override
    public List<TecnicoResponseDto> listar() {
        return repository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    @Override
    public TecnicoResponseDto buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::paraResponse)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Técnico " + id + " não encontrado"));
    }

    private TecnicoResponseDto paraResponse(Tecnico tecnico) {
        return new TecnicoResponseDto(tecnico.getId(), tecnico.getNome(), tecnico.getEmail());
    }
}
