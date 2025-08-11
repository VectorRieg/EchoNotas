package com.br.EchoNotas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoAvaliacao tipoAv;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(length = 1000)
    private String comentarios;

    public Nota() {}

    public Nota(Double valor, LocalDate data, TipoAvaliacao tipoAv, Aluno aluno, Curso curso) {
        this.valor = valor;
        this.data = data;
        this.tipoAv = tipoAv;
        this.aluno = aluno;
        this.curso = curso;
    }

    public Long getId() { return id; }
    public Double getValor() {return valor; }
    public LocalDate getData() { return data; }
    public TipoAvaliacao getTipoAv() { return tipoAv; }
    public Aluno getAluno() { return aluno; }
    public Curso getCurso() { return curso; }
    public Professor getProfessor() { return professor; }
    public String getComentarios() { return comentarios; }

    public void setId(Long id) {this.id = id;}
    public void setValor(Double valor) {this.valor = valor;}
    public void setData(LocalDate data) {this.data = data;}
    public void setTipoAv(TipoAvaliacao tipoAv) {this.tipoAv = tipoAv;}
    public void setAluno(Aluno aluno) {this.aluno = aluno;}
    public void setCurso(Curso curso) {this.curso = curso;}
    public void setProfessor(Professor professor) {this.professor = professor;}
    public void setComentarios(String comentarios) {this.comentarios = comentarios;}
}