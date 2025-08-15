package com.burak.studentmanagement.config;

import com.burak.studentmanagement.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.burak.studentmanagement.service.AlunoService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//student and professor login credentials are stored in mysql db, while admin username and senha defined below as in-memory
		
		auth
        	.userDetailsService(alunoService)
        	.passwordEncoder(passwordEncoder());
		auth
        	.userDetailsService(professorService)
        	.passwordEncoder(passwordEncoder());
		
		auth.inMemoryAuthentication()  //admin senha username
        .withUser("admin")
        .password(passwordEncoder().encode("1"))
        .roles("ADMIN");
	
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").authenticated()
			.antMatchers("/admin/**").hasRole("ADMIN") //user with student or professor role cannot access url starting with admin
			.antMatchers("/aluno/**").hasRole("ALUNO")
			.antMatchers("/professor/**").hasRole("PROFESSOR")
			.and()
			.formLogin()
				.loginPage("/exibirLogin") //custom login page is generated in LoginController
				.loginProcessingUrl("/authenticateTheUser") //authenticateTheUser is automatically done by spring boot
				.successHandler(customAuthenticationSuccessHandler) //after login, user is redirected to home page depending on the role.
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/acesso-negado"); //simple access denied mapping defined in LoginController in case of user
		                                                             //tries to access a page without the proper authority
				
	}
	
	
	//needed for admin senha encoding for security purposes
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}


