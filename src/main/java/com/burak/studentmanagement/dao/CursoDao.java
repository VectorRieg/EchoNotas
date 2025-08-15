package com.burak.studentmanagement.dao;

import java.util.List;

import com.burak.studentmanagement.entity.Curso;

public interface CursoDao {
	
	public void saveCurso(Curso curso);
	
	public List<Curso> findAllCursos();
	
	public Curso findCursoById(int id);
	
	public void deleteCursoById(int id);
}
