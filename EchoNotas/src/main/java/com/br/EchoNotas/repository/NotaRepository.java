package com.br.EchoNotas.repository;

import com.br.EchoNotas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    // Métodos CRUD herdados do JpaRepository
}
