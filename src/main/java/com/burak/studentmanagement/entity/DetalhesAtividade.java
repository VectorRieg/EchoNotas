package com.burak.studentmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalhes_atividade", schema = "echo_notas")
public class DetalhesAtividade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="atividade_id")
	private int atividadeId;
	
	@Column(name="student_course_details_id")
	private int detalhesAlunoCursoId;
	
	@Column(name="is_done")
	private int isDone; 
	
	public DetalhesAtividade() {
		
	}
	
	
	public DetalhesAtividade(int id, int atividadeId, int detalhesAlunoCursoId, int isDone) {
		this.id = id;
		this.atividadeId = atividadeId;
		this.detalhesAlunoCursoId = detalhesAlunoCursoId;
		this.isDone = isDone;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAtividadeId() {
		return atividadeId;
	}


	public void setAtividadeId(int atividadeId) {
		this.atividadeId = atividadeId;
	}


	public int getDetalhesAlunoCursoId() {
		return detalhesAlunoCursoId;
	}


	public void setDetalhesAlunoCursoId(int detalhesAlunoCursoId) {
		this.detalhesAlunoCursoId = detalhesAlunoCursoId;
	}


	public int getIsDone() {
		return isDone;
	}


	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}


	public void add(DetalhesAtividade detalhesAlunoCursoAtividade) {
	}
}



