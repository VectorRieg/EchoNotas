package com.br.EchoNotas.repository;

import com.br.EchoNotas.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Métodos CRUD herdados do JpaRepository
}
