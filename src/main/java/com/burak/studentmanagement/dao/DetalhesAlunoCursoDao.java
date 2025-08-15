package com.burak.studentmanagement.dao;

import java.util.List;

import com.burak.studentmanagement.entity.DetalhesAlunoCurso;

public interface DetalhesAlunoCursoDao {
	
	public List<DetalhesAlunoCurso> findByAlunoId(int id);
	
	public DetalhesAlunoCurso findByAlunoAndCursoId(int alunoId, int cursoId);
	
	public void deleteByAlunoId(int id);
	
	public void deleteByAlunoAndCursoId(int alunoId, int cursoId);
	
	public void save(DetalhesAlunoCurso detalhesAlunoCurso);
}
