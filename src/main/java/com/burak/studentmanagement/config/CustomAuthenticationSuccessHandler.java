package com.burak.studentmanagement.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.burak.studentmanagement.entity.Professor;
import com.burak.studentmanagement.service.AlunoService;
import com.burak.studentmanagement.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.burak.studentmanagement.entity.Aluno;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
		
		String role = auth.getAuthorities().iterator().next().toString();
		
		//redirecting the user to proper url depending on the authority
		if(role.equals("ROLE_ALUNO")) {
			String userName = auth.getName();
			Aluno theAluno = alunoService.findByAlunoName(userName);
			int userId = theAluno.getId();
			HttpSession session = request.getSession();
			session.setAttribute("user", theAluno);
			response.sendRedirect(request.getContextPath() + "/aluno/" + userId + "/cursos");
			
		} else if(role.equals("ROLE_PROFESSOR")) {
			String userName = auth.getName();
			Professor theProfessor = professorService.findByProfessorName(userName);
			int userId = theProfessor.getId();
			HttpSession session = request.getSession();
			session.setAttribute("user", theProfessor);
			response.sendRedirect(request.getContextPath() + "/professor/" + userId + "/cursos");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/painelAdmin");
		}

	}

}

