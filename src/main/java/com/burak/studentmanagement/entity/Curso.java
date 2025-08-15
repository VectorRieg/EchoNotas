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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course", schema = "echo_notas")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotBlank(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name="codigo")
	private String codigo;
	
	@NotBlank(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name="name")
	private String name;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name = "teacher_id")
	private Professor professor;
	
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(name="student_course_details",
				joinColumns = @JoinColumn(name="course_id"),
				inverseJoinColumns = @JoinColumn(name="student_id"))			
	private List<Aluno> alunos;
	
	
	
	

	public Curso() {
		
	}


	public Curso(int id, String codigo, String name, Professor professor, List<Aluno> alunos) {
		this.id = id;
		this.codigo = codigo;
		this.name = name;
		this.professor = professor;
		this.alunos = alunos;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public String getCode() {
		return codigo;
	}


	public void setCode(String codigo) {
		this.codigo = codigo;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




	


	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
		professor.addCurso(this);
	}



	public List<Aluno> getAlunos() {
		return alunos;
	}


	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public int alunoListSize() {
		return alunos.size();
	}
	
	public void addAluno(Aluno aluno) {
		if(alunos == null) {
			alunos = new ArrayList<>();
		}
		alunos.add(aluno);
	}
	
	public void removeAluno(Aluno aluno) {
		if(alunos.contains(aluno)) {
			alunos.remove(aluno);
		}
	}
	
	public boolean equals(Object comparedObject) {
	    if (this == comparedObject) {
	        return true;
	    }

	   if (!(comparedObject instanceof Curso)) {
	        return false;
	    }

	    Curso comparedCurso = (Curso) comparedObject;

	    if (this.id == comparedCurso.id) {
	        return true;
	    }

	    return false;
	}


	public void add(Curso curso) {
	}

	public void remove(Curso curso) {
	}
}