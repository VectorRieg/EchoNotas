package com.br.EchoNotas.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Table;


@Entity
@Table(name="aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome",nullable = false)
    private String nome;

    @Column(name="sobrenome",nullable = false)
    private String sobrenome;

    @Column(name="matricula",nullable = false, unique = true)
    private String matricula;

    @Column(name="senha",nullable = false)
    private String senha;

    @Column(name="email",nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name="nivel_usuario",nullable = false)
    private NivelUsuario nivelUsuario;

    public Aluno() {}

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getMatricula() { return matricula; }
    public String getSenha() { return senha; }
    public String getEmail() { return email; }
    public NivelUsuario getNivelUsuario() { return nivelUsuario; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setEmail(String email) { this.email = email; }
    public void setNivelUsuario( NivelUsuario nivelUsuario) { this.nivelUsuario = nivelUsuario; }


}