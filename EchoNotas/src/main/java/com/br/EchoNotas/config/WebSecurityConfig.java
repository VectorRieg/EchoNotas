package com.br.EchoNotas.config;

import com.br.EchoNotas.service.AlunoService;
import com.br.EchoNotas.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CorsConfig corsConfig;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN") //user with student or teacher role cannot access url starting with admin
                .antMatchers("/aluno/**").hasRole("ALUNO")
                .antMatchers("/professor/**").hasRole("PROFESSOR")
                .and()
                .formLogin()
                .loginPage("/Login") //custom login page is generated in LoginController
                .loginProcessingUrl("/authenticateTheUser") //authenticateTheUser is automatically done by spring boot
                .successHandler(corsConfig) //after login, user is redirected to home page depending on the role.
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied"); //simple access denied mapping defined in LoginController in case of user
        //tries to access a page without the proper authority

    }

    //needed for admin password encoding for security purposes
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuarios/**").permitAll() // libera temporariamente os endpoints
                        .anyRequest().authenticated()
                )
                .httpBasic(); // autenticação básica temporária

        return http.build();
    }
}
