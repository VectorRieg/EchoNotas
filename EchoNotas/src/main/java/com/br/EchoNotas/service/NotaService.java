package com.br.EchoNotas.service;

import com.br.EchoNotas.entity.Nota;
import com.br.EchoNotas.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private NotaRepository notaRepository;

    public Nota save(Nota Nota){
        return notaRepository.save(Nota);
    }

    public List<Nota> findAll() {
        return notaRepository.findAll();
    }

    public Optional<Nota> findById(Long id) {
        return notaRepository.findById(id);
    }

    public void delete(Long id) {
        notaRepository.deleteById(id);
    }
}
