package com.burak.studentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="aluno", schema = "echo_notas")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String userName;

	@Column(name = "senha")
	private String senha;

	@Column(name = "primeiro_nome")
	private String primeiroNome;

	@Column(name = "ultimo_nome")
	private String ultimoNome;

	@Column(name = "email")
	private String email;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "detalhes_aluno_curso",
			joinColumns = @JoinColumn(name = "aluno_id"),
			inverseJoinColumns = @JoinColumn(name = "curso_id"))
	private List<Curso> cursos;

	public Aluno() {

	}


	public Aluno(int id, String userName, String senha, String primeiroNome, String ultimoNome, String email,
				 Role role, List<Curso> cursos) {
		this.id = id;
		this.userName = userName;
		this.senha = senha;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.role = role;
		this.cursos = cursos;
	}


	public void addCurso(Curso curso) {
		if (cursos == null) {
			cursos = new ArrayList<>();
		}
		cursos.add(curso);
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
		this.senha = this.senha;
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

	public void setCurso(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void removeCurso(Curso curso) {
		cursos.remove(curso);
	}

	public boolean equals(Object comparedObject) {
		if (this == comparedObject) {
			return true;
		}

		if (!(comparedObject instanceof Aluno)) {
			return false;
		}

		Aluno comparedAluno = (Aluno) comparedObject;

		if (this.id == comparedAluno.id) {
			return true;
		}

		return false;
	}

}