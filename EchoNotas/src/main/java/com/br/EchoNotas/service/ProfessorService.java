package com.br.EchoNotas.service;

import com.br.EchoNotas.entity.Aluno;
import com.br.EchoNotas.entity.Professor;
import com.br.EchoNotas.repository.AlunoRepository;
import com.br.EchoNotas.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private  ProfessorRepository professorRepository;

    public Professor save(Professor Professor){
        return professorRepository.save(Professor);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    public void delete(Long id) {
        professorRepository.deleteById(id);
    }
}
