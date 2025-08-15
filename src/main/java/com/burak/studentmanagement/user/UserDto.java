package com.burak.studentmanagement.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.burak.studentmanagement.entity.Role;


public class UserDto {
	
	@NotBlank(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;
	
	@NotBlank(message = "is required")
	@Size(min = 1, message = "is required")
	private String senha;
	
	@NotBlank(message = "is required")
	@Size(min = 1, message = "is required")
	private String primeiroNome;
	
	@NotBlank(message = "is required")
	@Size(min = 1, message = "is required")
	private String ultimoNome;
	
	@NotBlank(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	private Role role;
	
	public UserDto() {
		
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

	
	
	
}
