package com.burak.studentmanagement.service;

import java.util.List;

import com.burak.studentmanagement.entity.Curso;

public interface CursoService {
	
	public void save(Curso curso);
	
	public List<Curso> findAllCursos();
	
	public Curso findCursoById(int id);
	
	public void deleteCursoById(int id);
}
