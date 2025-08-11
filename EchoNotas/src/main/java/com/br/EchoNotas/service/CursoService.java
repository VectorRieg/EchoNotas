package com.br.EchoNotas.service;

import com.br.EchoNotas.entity.Curso;
import com.br.EchoNotas.entity.Professor;
import com.br.EchoNotas.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private CursoRepository cursoRepository;

    public Curso save(Curso Curso){
        return cursoRepository.save(Curso);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

}