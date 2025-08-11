package com.br.EchoNotas.repository;


import com.br.EchoNotas.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Métodos CRUD herdados do JpaRepository
}
