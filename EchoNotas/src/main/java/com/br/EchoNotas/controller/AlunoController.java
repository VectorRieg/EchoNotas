package com.br.EchoNotas.controller;

import com.br.EchoNotas.entity.Aluno;

import com.br.EchoNotas.entity.Curso;
import com.br.EchoNotas.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Aluno> findByMatricula(@PathVariable String matricula) {
        return alunoService.findByMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Aluno>> findByNomeIgnoreCaseAndSobrenomeIgnoreCase(
            @RequestParam String nome,
            @RequestParam String sobrenome) {
        List<Aluno> alunos = alunoService.findByNomeIgnoreCaseAndSobrenomeIgnoreCase(nome, sobrenome);
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Aluno>> findByCurso(@PathVariable Long cursoId) {
        Curso curso = new Curso();
        curso.setId(cursoId);
        List<Aluno> alunos = alunoService.findByCurso(curso);
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
    }

}
