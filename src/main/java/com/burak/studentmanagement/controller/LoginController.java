package com.burak.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/mostrarLogin")
	public String mostrarLogin() {
		return "login/login-form";
	}
	
	//authenticateTheUser is automatically done by spring boot
	
	@GetMapping("/acesso-negado")
	public String mostrarAcessoNegado() {
		return "acesso-negado";
	}
}
