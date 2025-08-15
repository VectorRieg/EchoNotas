package com.burak.studentmanagement.service;

import java.util.List;

import com.burak.studentmanagement.entity.Professor;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.burak.studentmanagement.user.UserDto;

public interface ProfessorService extends UserDetailsService {
	
	public Professor findByProfessorName(String userName);
	
	public Professor findByProfessorId(int id);

	public void save(UserDto userDto);
	
	public void save(Professor professor);
	
	public List<Professor> findAllProfessores();
	
	public void deleteProfessorById(int id);
}
