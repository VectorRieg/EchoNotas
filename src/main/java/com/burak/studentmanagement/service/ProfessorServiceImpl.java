package com.burak.studentmanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.burak.studentmanagement.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burak.studentmanagement.dao.RoleDao;
import com.burak.studentmanagement.dao.ProfessorDao;
import com.burak.studentmanagement.entity.Role;
import com.burak.studentmanagement.user.UserDto;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorDao professorDao;
	
	@Autowired 
	private RoleDao roleDao;
	
	
	@Override
	@Transactional
	public Professor findByProfessorName(String professorName) {
		return professorDao.findByProfessorName(professorName);
	}

	@Override
	@Transactional
	public void save(UserDto userDto) {
		Professor professor = new Professor();
		professor.setUserName(userDto.getUserName());
		professor.setSenha(new BCryptPasswordEncoder().encode(userDto.getSenha()));
		professor.setPrimeiroNome(userDto.getPrimeiroNome());
		professor.setUltimoNome(userDto.getUltimoNome());
		professor.setEmail(userDto.getEmail());
		professor.setRole(userDto.getRole());
		
		professorDao.save(professor);
	}
	
	@Override
	@Transactional
	public void save(Professor professor) {
		professorDao.save(professor);
	}
	
	
	@Override
	@Transactional
	public List<Professor> findAllProfessores() {
		return professorDao.findAllProfessores();
	}
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Professor professor = professorDao.findByProfessorName(username);
		if (professor == null) {
			throw new UsernameNotFoundException("Senha ou Nome inválido.");
		}
		Collection<Role> role = new ArrayList<>();
		role.add(professor.getRole());
		return new org.springframework.security.core.userdetails.User(professor.getUserName(), professor.getSenha(),
				mapRolesToAuthorities(role));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Professor findByProfessorId(int id) {
		return professorDao.findByProfessorId(id);
	}

	@Override
	@Transactional
	public void deleteProfessorById(int id) {
		professorDao.deleteProfessorById(id);
	}

	

	

}
