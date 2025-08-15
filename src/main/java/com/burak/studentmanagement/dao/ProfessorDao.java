package com.burak.studentmanagement.dao;

import java.util.List;

import com.burak.studentmanagement.entity.Professor;

public interface ProfessorDao {
	
	public Professor findByProfessorName(String theProfessorName);
	
	public Professor findByProfessorId(int id);
	
	public void save(Professor professor);
	
	public List<Professor> findAllProfessores();
	
	public void deleteProfessorById(int id);
	
}
