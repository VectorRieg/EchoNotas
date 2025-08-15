package com.burak.studentmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.burak.studentmanagement.entity.*;
import com.burak.studentmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.burak.studentmanagement.entity.DetalhesNota;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private DetalhesAlunoCursoService detalhesAlunoCursoService;
	
	@Autowired
	private DetalhesNotaService detalhesNotaService;
	
	private int professorDeleteErrorValue;
	
	@GetMapping("/painelAdmin")
	public String exibirPainelAdmin() {
		
		return "admin/painel-admin";
	}
	
	@GetMapping("/adminInfo")
	public String exibirAdminInfo(Model theModel) {
		int cursoSize = cursoService.findAllCursos().size();
		theModel.addAttribute("cursoSize", cursoSize);
		int alunoSize = alunoService.findAllAlunos().size();
		theModel.addAttribute("alunoSize", alunoSize);
		int professorSize = professorService.findAllProfessores().size();
		theModel.addAttribute("professorSize", professorSize);
		return "admin/admin-info";
	}
	
	@GetMapping("/alunos")
	public String exibirListaDeAlunos(Model theModel) {
		theModel.addAttribute("alunos", alunoService.findAllAlunos());
		
		return "admin/list-alunos";
	}
	
	@RequestMapping("/alunos/delete")
	public String deleteAlunos(@RequestParam("alunoId") int alunoId) {
		List<DetalhesAlunoCurso> list = detalhesAlunoCursoService.findByAlunoId(alunoId);
		for(DetalhesAlunoCurso scd : list) { //deleting the aluno notas before deleting the aluno
			int notaId = scd.getDetalhesNota().getId();
			detalhesAlunoCursoService.deleteByAlunoId(alunoId);
			detalhesNotaService.deleteById(notaId);
		}
		alunoService.deleteById(alunoId);
		
		return "redirect:/admin/alunos";
	}
	
	@GetMapping("/alunos/{alunoId}/curso")
	public String editCursosFromAluno(@PathVariable("alunoId") int alunoId, Model theModel) {
		Aluno aluno = alunoService.findByAlunoId(alunoId);
		List<Curso> cursos = aluno.getCursos();
		
		theModel.addAttribute("aluno", aluno);
		theModel.addAttribute("cursos", cursos);
		
		return "admin/aluno-curso-list";
	}
	
	@GetMapping("/alunos/{alunoId}/addCurso")
	public String addCursoForAluno(@PathVariable("alunoId") int alunoId, Model theModel) {
		Aluno aluno = alunoService.findByAlunoId(alunoId);
		List<Curso> cursos = cursoService.findAllCursos();
		
		for(int i = 0; i < cursos.size(); i++) { //finding the cursos that the current aluno has not enrolled yet
			if(aluno.getCursos().contains(cursos.get(i))) {
				cursos.remove(i);
				i--;
			}
		}
		theModel.addAttribute("aluno", aluno);
		theModel.addAttribute("cursos", cursos); //unenrolled cursos are displayed as drop-down list
		theModel.addAttribute("listSize", cursos.size());
		return "admin/add-curso";
	}
	
	@RequestMapping("/aluno/{alunoId}/addCurso/save")
	public String saveCursoForAluno(@PathVariable("alunoId") int alunoId, @RequestParam("cursoId") int cursoId) {
		
		DetalhesAlunoCurso sc = new DetalhesAlunoCurso(alunoId, cursoId, new ArrayList<Atividade>() ,new DetalhesNota());
		detalhesAlunoCursoService.save(sc);
			
		return "redirect:/admin/aluno/" + alunoId + "/cursos";
	}
	
	
	@GetMapping("/aluno/{alunoId}/cursos/delete/{cursoId}")
	public String deleteCursoFromAluno(@PathVariable("alunoId") int alunoId, @PathVariable("cursoId") int cursoId) {
		DetalhesAlunoCurso scd = detalhesAlunoCursoService.findByAlunoAndCursoId(alunoId, cursoId);
		int notaId = scd.getDetalhesNota().getId();
		
		//operations for removing the aluno from the curso
		detalhesAlunoCursoService.deleteByAlunoAndCursoId(alunoId, cursoId);
		detalhesNotaService.deleteById(notaId);
		
		return "redirect:/admin/aluno/" + alunoId + "/cursos";
	}
	
	
	@GetMapping("/professor")
	public String mostrarListaDeProfessores(Model theModel) {
		theModel.addAttribute("professores", professorService.findAllProfessores());
		theModel.addAttribute("error", professorDeleteErrorValue);
		professorDeleteErrorValue = 0; //0 means the professor has not any assigned cursos, 1 means it has
		return "admin/list-professores";
	}
	
	@GetMapping("/teachers/delete")
	public String deleteProfessor(@RequestParam("professorId") int professorId) {
		Professor professor = professorService.findByProfessorId(professorId);
		if(professor.getCursos().size() == 0) { //if the professor has cursos assigned, the professor cannot be deleted
			professorService.deleteProfessorById(professorId);
			professorDeleteErrorValue = 0;
		} else {
			professorDeleteErrorValue = 1;
		}
		
		return "redirect:/admin/professores";
	}
	
	
	@GetMapping("/addCurso")
	public String addCurso(Model theModel) {
		//add curso form has a select professor field where all teachers registered are showed as drop-down list
		List<Professor> professores = professorService.findAllProfessores();
		
		theModel.addAttribute("curso", new Curso());
		theModel.addAttribute("teachers", professores);
		
		return "admin/curso-form";
	}
	
	@PostMapping("/saveCurso")
	public String saveCurso(@Valid @ModelAttribute("curso") Curso theCurso,
			BindingResult theBindingResult, @RequestParam("professorId") int professorId, Model theModel) {
		
		if (theBindingResult.hasErrors()) { //curso form has data validation rules. If fields are not properly filled out, form is showed again
			List<Professor> professores = professorService.findAllProfessores();
			theModel.addAttribute("profesores", professores);
			return "admin/curso-form";
		}
		
		theCurso.setProfessor(professorService.findByProfessorId(professorId)); //setTeacher method also sets the professor's curso as this
		cursoService.save(theCurso);
		
		return "redirect:/admin/painel-admin";
	}
	
	@GetMapping("/cursos")
	public String exibirCurso(Model theModel) {
		theModel.addAttribute("cursos", cursoService.findAllCursos());
		
		return "admin/curso-list";
	}

	@GetMapping("/cursos/delete")
	public String deleteCurso(@RequestParam("cursoId") int cursoId) {
		Curso curso = cursoService.findCursoById(cursoId);
		List<Aluno> alunos = curso.getAlunos();
		
		for(Aluno aluno : alunos) {
			DetalhesAlunoCurso scd = detalhesAlunoCursoService.findByAlunoAndCursoId(aluno.getId(), cursoId);
			int notaId = scd.getDetalhesNota().getId();
			detalhesAlunoCursoService.deleteByAlunoAndCursoId(aluno.getId(), cursoId);
			detalhesNotaService.deleteById(notaId);
		}
		
		cursoService.deleteCursoById(cursoId);
		return "redirect:/admin/cursos";
	}
	
	@GetMapping("/cursos/{cursoId}/alunos")
	public String exibirAlunos(@PathVariable("cursoId") int cursoId, Model theModel) {
		Curso curso = cursoService.findCursoById(cursoId);
		List<Aluno> alunos = curso.getAlunos();
		Professor professor = curso.getProfessor();
		theModel.addAttribute("alunos", alunos);
		theModel.addAttribute("curso", curso);
		theModel.addAttribute("professor", professor);
		return "admin/list-curso-aluno";
	}
	
	@GetMapping("/cursos/{cursoId}/alunos/delete")
	public String deleteAlunoDeCurso(@PathVariable("cursoId") int cursoId, @RequestParam("alunoId") int alunoId) {
		DetalhesAlunoCurso scd = detalhesAlunoCursoService.findByAlunoAndCursoId(alunoId, cursoId);
		int notaId = scd.getDetalhesNota().getId();
		
		detalhesAlunoCursoService.deleteByAlunoAndCursoId(alunoId, cursoId);
		detalhesNotaService.deleteById(notaId);
		
		return "redirect:/admin/cursos/" + cursoId + "/alunos";
	}
	
	@GetMapping("/cursos/{cursoId}/alunos/addAluno")
	public String addAlunoNoCurso(@PathVariable("cursoId") int cursoId, Model theModel) {
		Curso curso = cursoService.findCursoById(cursoId);
		List<Aluno> alunos = alunoService.findAllAlunos();
		
		for(int i = 0; i < alunos.size(); i++) {
			if(curso.getAlunos().contains(alunos.get(i))) {
				alunos.remove(alunos.get(i));
				i--;
			}
		}
		theModel.addAttribute("alunos", alunos); //all alunos who are not enrolled to the current curso yet
		theModel.addAttribute("curso", curso);
		theModel.addAttribute("listSize", alunos.size());
		return "admin/add-aluno";
		
	}
	
	@RequestMapping("/cursos/{cursoId}/alunos/addAluno/save")
	public String saveAlunoNoCurso(@RequestParam("alunoId") int alunoId, @PathVariable("cursoId") int cursoId) {
		
		DetalhesAlunoCurso sc = new DetalhesAlunoCurso(alunoId, cursoId, new ArrayList<Atividade>() ,new DetalhesNota());
		detalhesAlunoCursoService.save(sc);
		
		return "redirect:/admin/cursos/" + cursoId + "/alunos";
	}
}
