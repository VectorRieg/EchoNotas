package com.br.EchoNotas.service;

import com.br.EchoNotas.entity.Aluno;
import com.br.EchoNotas.entity.Curso;
import com.br.EchoNotas.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private AlunoRepository alunoRepository;

    public Optional<Aluno> findByMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    public List<Aluno> findByNomeIgnoreCaseAndSobrenomeIgnoreCase(String nome, String sobrenome) {
        return alunoRepository.findByNomeIgnoreCaseAndSobrenomeIgnoreCase(nome, sobrenome);
    }

    public List<Aluno> findByCurso(Curso curso) {
        return alunoRepository.findByCurso(curso);
    }

    @Transactional
    public Aluno save( Aluno aluno ){
        return alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        alunoRepository.deleteById(id);
    }

}
