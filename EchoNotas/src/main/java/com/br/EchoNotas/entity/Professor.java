package com.br.EchoNotas.entity;

import jakarta.persistence.*;

@Entity
@Table(name="professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome",nullable = false)
    private String nome;

    @Column(name="sobrenome",nullable = false)
    private String sobrenome;

    @Column(name="senha",nullable = false)
    private String senha;

    @Column(name="email",nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name="nivelUsuario",nullable = false)
    private NivelUsuario nivelUsuario;

    // Construtor padrão
    public Professor() {}

    // Construtor com parâmetros
    public Professor(String nome, String sobrenome, String email, String senha, NivelUsuario nivelUsuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.nivelUsuario = nivelUsuario;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getSenha() { return senha; }
    public String getEmail() { return email; }
    public NivelUsuario getNivelUsuario() { return nivelUsuario; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setEmail(String email) { this.email = email; }
    public void setNivelUsuario( NivelUsuario nivelUsuario) { this.nivelUsuario = nivelUsuario; }

}
