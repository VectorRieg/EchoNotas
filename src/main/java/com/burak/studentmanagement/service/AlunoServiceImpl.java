package com.burak.studentmanagement.service;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.burak.studentmanagement.entity.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burak.studentmanagement.dao.RoleDao;
import com.burak.studentmanagement.dao.AlunoDao;
import com.burak.studentmanagement.entity.Role;
import com.burak.studentmanagement.user.UserDto;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	private AlunoDao alunoDao;
	
	@Autowired 
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public Aluno findByAlunoName(String alunoName) {
		return alunoDao.findByAlunoName(alunoName);
	}

	@Override
	@Transactional
	public Aluno findByAlunoId(int id) {
		return alunoDao.findByAlunoId(id);
	}

	@Override
	public Aluno update(int id, Aluno aluno) {
		return alunoDao.update(id, aluno);
	}

	@Override
	public Aluno patch(int id, Aluno partialAluno) {
		return alunoDao.patch(id, partialAluno);
	}


	@Override
	@Transactional
	public void save(UserDto userDto) {
		Aluno aluno = new Aluno();
		aluno.setUserName(userDto.getUserName());
		aluno.setSenha(new BCryptPasswordEncoder().encode(userDto.getSenha()));
		aluno.setPrimeiroNome(userDto.getPrimeiroNome());
		aluno.setUltimoNome(userDto.getUltimoNome());
		aluno.setEmail(userDto.getEmail());
		aluno.setRole(userDto.getRole());
		
		alunoDao.save(aluno);
	}
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Aluno aluno = alunoDao.findByAlunoName(username);
		if (aluno == null) {
			throw new UsernameNotFoundException("Invalid username or senha.");
		}
		Collection<Role> role = new ArrayList<>();
		role.add(aluno.getRole());
		return new org.springframework.security.core.userdetails.User(aluno.getUserName(), aluno.getSenha(),
				mapRolesToAuthorities(role));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<Aluno> findAllAlunos() {
		return alunoDao.findAllAlunos();
	}

	@Override
	@Transactional
	public Aluno save(Aluno aluno) {
		alunoDao.save(aluno);

		return aluno;
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		alunoDao.deleteById(id);
	}

}
