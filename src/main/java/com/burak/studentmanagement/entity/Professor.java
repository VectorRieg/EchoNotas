package com.burak.studentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="professor", schema = "echo_notas")
public class Professor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="first_name")
	private String primeiroNome;
	
	@Column(name="last_name")
	private String ultimoNome;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToMany(mappedBy= "professor", fetch=FetchType.EAGER)
	private List<Curso> cursos;
	
	public Professor() {
		
	}

	public Professor(int id, String userName, String senha, String primeiroNome, String ultimoNome, String email, Role role,
					 List<Curso> cursos) {
		this.id = id;
		this.userName = userName;
		this.senha = senha;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.role = role;
		this.cursos = cursos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	public void addCurso(Curso curso) {
		if(cursos == null) {
			cursos = new ArrayList<>();
		}
		
		cursos.add(curso);
	}
	
}
