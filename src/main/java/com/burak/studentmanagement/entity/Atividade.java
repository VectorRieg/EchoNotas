package com.burak.studentmanagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="atividade", schema = "echo_notas")
public class Atividade implements Comparable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="is required") 
	@Size(min=1, message="is required")
	@Column(name="name")
	private String name;
	
	@NotBlank(message="is required") 
	@Size(min=1, message="is required")
	@Column(name="descricao")
	private String descricao;
	
	@NotEmpty(message="is required")
	@Column(name="due_date")
	private String dueDate;
	
	@Column(name="dias_faltando")
	private int diasFaltando;
	
	
	@ManyToMany
	@JoinTable(name="detalhes_atividade",
				joinColumns = @JoinColumn(name="atividade_id"),
				inverseJoinColumns = @JoinColumn(name="student_course_details_id"))
	private List<DetalhesAlunoCurso> detalhesCurso;
	
	public Atividade() {
		
	}

	public Atividade(int id, String name, String descricao, String dueDate, int diasFaltando,
					 List<DetalhesAlunoCurso> detalhesCurso) {
		this.id = id;
		this.name = name;
		this.descricao = descricao;
		this.dueDate = dueDate;
		this.diasFaltando = diasFaltando;
		this.detalhesCurso = detalhesCurso;
	}







	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return descricao;
	}

	public void setDescription(String descricao) {
		this.descricao = descricao;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getDaysRemaining() {
		return diasFaltando;
	}

	public void setDaysRemaining(int diasFaltando) {
		this.diasFaltando = diasFaltando;
	}


	public List<DetalhesAlunoCurso> getCourseDetails() {
		return detalhesCurso;
	}




	public void setCourseDetails(List<DetalhesAlunoCurso> detalhesCurso) {
		this.detalhesCurso = detalhesCurso;
	}




	@Override
	public int compareTo(Object o) {
		Atividade comAss = (Atividade) o;
		if(this.diasFaltando != comAss.getDaysRemaining()) {
			return this.diasFaltando - comAss.getDaysRemaining();
		} else {
			return this.name.compareTo(comAss.getName());
		}
	}

	
	
	
	
}




