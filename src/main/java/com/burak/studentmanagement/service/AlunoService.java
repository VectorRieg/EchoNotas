package com.burak.studentmanagement.service;

import java.util.List;

import com.burak.studentmanagement.entity.Aluno;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.burak.studentmanagement.user.UserDto;

public interface AlunoService extends UserDetailsService {
	
	Aluno findByAlunoName(String userName);

	void save(UserDto userDto);
	
	Aluno save(Aluno aluno);
	
	Aluno findByAlunoId(int id);

	Aluno update(int id, Aluno aluno);

	Aluno patch(int id, Aluno partialAluno);

	List<Aluno> findAllAlunos();

	void deleteById(int id);

}
