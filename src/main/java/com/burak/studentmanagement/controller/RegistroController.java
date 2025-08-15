package com.burak.studentmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.burak.studentmanagement.dao.RoleDao;
import com.burak.studentmanagement.entity.Role;
import com.burak.studentmanagement.service.AlunoService;
import com.burak.studentmanagement.service.ProfessorService;
import com.burak.studentmanagement.user.UserDto;

@Controller
@RequestMapping("/registro")
public class RegistroController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private RoleDao roleDao;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	

	@GetMapping("/mostrarRegistroForm")
	public String mostrarRegistroForm(Model theModel) {
		theModel.addAttribute("userDto", new UserDto());		
		return "registro/registro-form";
	}
	
	
	@PostMapping("/processarRegistroForm")
	public String processarRegistroForm(@Valid @ModelAttribute("userDto") UserDto user,
										  BindingResult theBindingResult, @RequestParam(value="role") String roleName, Model theModel) {
		if (theBindingResult.hasErrors()) {
			return "registro/registro-form";
		}
		
		if(roleName.equals("ROLE_STUDENT")) {
			String userName = user.getUserName();
			
			//if username already exists in db
			if(alunoService.findByAlunoName(userName) != null) {
				theModel.addAttribute("userDto", new UserDto());
				theModel.addAttribute("erro", "Esse nome já existe!");
				return "registro/registro-form";
			}
					
			Role role = roleDao.findRoleByName(roleName);
			user.setRole(role);
			alunoService.save(user); //save() method converts UserDto to Aluno and saves it in db
		} else { //professor role
			
			String userName = user.getUserName();
			
			//if username already exists in db
			if(professorService.findByProfessorName(userName) != null) {
				theModel.addAttribute("userDto", new UserDto());
				theModel.addAttribute("erro", "Esse nome já existe!");
				return "registro/registro-form";
			}
					
			Role role = roleDao.findRoleByName(roleName);
			user.setRole(role);
			professorService.save(user);
		}
		
		
		return "login/login-form";
	}
}
