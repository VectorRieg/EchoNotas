package com.br.EchoNotas.repository;

import com.br.EchoNotas.entity.Aluno;
import com.br.EchoNotas.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByMatricula(String matricula);

    List<Aluno> findByNomeIgnoreCaseAndSobrenomeIgnoreCase(String nome, String sobrenome);

    List<Aluno> findByCurso(Curso curso);

}
