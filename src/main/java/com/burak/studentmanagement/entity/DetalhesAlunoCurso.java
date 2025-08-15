package com.burak.studentmanagement.entity;

import java.util.ArrayList;
import java.util.Collections;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="detalhes_aluno_curso", schema = "echo_notas")
public class DetalhesAlunoCurso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="aluno_id")
	private int alunoId;
	
	@Column(name="curso_id")
	private int cursoId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="detalhes_atividade",
				joinColumns = @JoinColumn(name="detalhes_aluno_curso_id"),
				inverseJoinColumns = @JoinColumn(name="atividade_id"))
	private List<Atividade> atividades;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)  
	@JoinColumn(name="detalhes_nota_id")
	private DetalhesNota detalhesNota;
	
	public DetalhesAlunoCurso() {
	}
	
	public DetalhesAlunoCurso(int alunoId, int cursoId, List<Atividade> atividades, DetalhesNota detalhesNota) {
		this.alunoId = alunoId;
		this.cursoId = cursoId;
		this.atividades = atividades;
		this.detalhesNota = detalhesNota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAlunoId() {
		return alunoId;
	}
	
	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}
	
	public int getCursoId() {
		return cursoId;
	}
	
	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}
	
	public List<Atividade> getAtividadesByOrder() {
		Collections.sort(atividades);
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	public void addAtividade(Atividade atividade) {
		if(atividades == null) {
			atividades = new ArrayList<>();
		}
		atividades.add(atividade);
	}
	
	public Atividade getAtividadeById(int atividadeId) {
		for(Atividade a : atividades) {
			if(a.getId() == atividadeId) {
				return a;
			}
		}
		
		return null;
	}
	
	public DetalhesNota getDetalhesNota() {
		return detalhesNota;
	}
	
	public void setDetalhesNota(DetalhesNota detalhesNota) {
		this.detalhesNota = detalhesNota;
	}
	
}


