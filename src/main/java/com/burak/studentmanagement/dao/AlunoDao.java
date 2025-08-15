package com.burak.studentmanagement.dao;

import java.util.List;

import com.burak.studentmanagement.entity.Aluno;

public interface AlunoDao {
	
	Aluno findByAlunoName (String theAlunoName);
	
	public void save(Aluno aluno);

	Aluno patch(int id, Aluno partialAluno);

	Aluno findByAlunoId(int id);
	
	public List<Aluno> findAllAlunos();
	
	public void deleteById(int id);

	Aluno update(int id, Aluno aluno);
}
