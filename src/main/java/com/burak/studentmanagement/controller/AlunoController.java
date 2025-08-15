package com.burak.studentmanagement.controller;

import com.burak.studentmanagement.entity.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.burak.studentmanagement.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable int id) {
		Aluno aluno = alunoService.findByAlunoId(id);
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
		Aluno savedAluno = alunoService.save(aluno);
		return ResponseEntity.ok(savedAluno);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aluno> updateAluno(@PathVariable int id, @RequestBody Aluno aluno) {
		Aluno existingAluno = alunoService.findByAlunoId(id);
		if (existingAluno == null) {
			return ResponseEntity.notFound().build();
		}
		aluno.setId(id);
		Aluno updatedAluno = alunoService.save(aluno);
		return ResponseEntity.ok(updatedAluno);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Aluno> patchAluno(@PathVariable int id, @RequestBody Aluno partialAluno) {
		Aluno existingAluno = alunoService.findByAlunoId(id);
		if (existingAluno == null) {
			return ResponseEntity.notFound().build();
		}
		if (partialAluno.getPrimeiroNome() != null) {
			existingAluno.setPrimeiroNome(partialAluno.getPrimeiroNome());
		}
		if (partialAluno.getUltimoNome() != null) {
			existingAluno.setUltimoNome(partialAluno.getUltimoNome());
		}	
		Aluno updatedAluno = alunoService.save(existingAluno);
		return ResponseEntity.ok(updatedAluno);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAluno(@PathVariable int id) {
		Aluno existingAluno = alunoService.findByAlunoId(id);
		if (existingAluno == null) {
			return ResponseEntity.notFound().build();
		}
		alunoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}