package com.burak.studentmanagement.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;

import com.burak.studentmanagement.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.burak.studentmanagement.entity.Curso;
import com.burak.studentmanagement.service.DetalhesAtividadeService;
import com.burak.studentmanagement.service.AtividadeService;
import com.burak.studentmanagement.service.CursoService;
import com.burak.studentmanagement.service.DetalhesNotaService;
import com.burak.studentmanagement.service.DetalhesAlunoCursoService;
import com.burak.studentmanagement.service.ProfessorService;


@RestController
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private DetalhesAlunoCursoService detalhesAlunoCursoService;
	
	@Autowired
	private DetalhesAtividadeService detalhesAtividadeService;
	
	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private DetalhesNotaService detalhesNotaService;

	@GetMapping("/{professorId}/cursos")
	@ResponseBody
	public String exibirCursosDoProfessor(@PathVariable("professorId") int professorId, Model theModel) {
		Professor professor = professorService.findByProfessorId(professorId);
		List<Curso> cursos = professor.getCursos();
		
		theModel.addAttribute("professor", professor);
		theModel.addAttribute("cursos", cursos);
		return "professor/cursos-professor";
	}
	
	@GetMapping("/{professorId}/cursos/{cursoId}")
	@ResponseBody
	public String mostrarDetalhesDeCursosDoProfessor(@PathVariable("professorId") int professorId, @PathVariable("cursoId") int cursoId, Model theModel) {
		Professor professor = professorService.findByProfessorId(professorId);
		Curso curso = cursoService.findCursoById(cursoId);
		List<Curso> cursos = professor.getCursos();
		List<Aluno> alunos = curso.getAlunos();
		
		if(alunos.size() != 0) {
			List<Atividade> atividades = detalhesAlunoCursoService.findByAlunoAndCursoId(alunos.get(0).getId(), cursoId).getAtividades();
			for(Atividade atividade : atividades) {
				int diasFaltando = findDayDifference(atividade);
				atividade.setDaysRemaining(diasFaltando);
				atividadeService.save(atividade);
			}
			if(atividades.size() == 0) {
				atividades = null;
			}
			List<DetalhesNota> detalhesNotaList = new ArrayList<>();
			for(Aluno aluno : alunos) {
				detalhesNotaList.add(detalhesAlunoCursoService.findByAlunoAndCursoId(aluno.getId(), cursoId).getDetalhesNota());
			}
			HashMap<List<Aluno>, List<DetalhesNota>> notaAlunoList = new HashMap<>();
			notaAlunoList.put(alunos, detalhesNotaList);
			theModel.addAttribute("notaAlunoList", notaAlunoList);
			theModel.addAttribute("atividades", atividades);
		} 
		
		theModel.addAttribute("professor", professor);
		theModel.addAttribute("curso", curso);
		theModel.addAttribute("cursos", cursos);
		theModel.addAttribute("alunos", alunos);
		
		return "professor/detalhes-cursos-professor";
	}
	
	
	@GetMapping("/{professorId}/cursos/{cursoId}/editarNotas")
	@ResponseBody
	public String editarNotasForm(@PathVariable("professorId") int professorId, @PathVariable("cursoId") int cursoId, Model theModel) {
		Professor professor = professorService.findByProfessorId(professorId);
		Curso curso = cursoService.findCursoById(cursoId);
		List<Curso> cursos = professor.getCursos();
		List<Aluno> alunos = curso.getAlunos();
		
		List<DetalhesNota> detalhesNotaList = new ArrayList<>();
		for(Aluno aluno : alunos) {
			detalhesNotaList.add(detalhesAlunoCursoService.findByAlunoAndCursoId(aluno.getId(), cursoId).getDetalhesNota());
		}
		
		HashMap<List<Aluno>, List<DetalhesNota>> notaAlunoList = new HashMap<>();
		notaAlunoList.put(alunos, detalhesNotaList);
		
		theModel.addAttribute("notaAlunoList", notaAlunoList);
		theModel.addAttribute("curso", curso);
		theModel.addAttribute("cursos", cursos);
		theModel.addAttribute("professor", professor);
		theModel.addAttribute("alunos", alunos);
		theModel.addAttribute("detalhesNotaList", detalhesNotaList);
		
		return "professor/editar-notas-form";
	}
	
	
	@PostMapping("/{professorId}/cursos/{cursoId}/editarNotas/save/{detalhesNotaId}")
	public String modificarNotas(@ModelAttribute DetalhesNota detalhesNota,
								 @PathVariable("professorId") int professorId, @PathVariable("cursoId") int cursoId,
								 @PathVariable("detalhesNotaId") int detalhesNotaId) throws Exception {
		
		Professor professor = professorService.findByProfessorId(professorId);
		Curso curso = cursoService.findCursoById(cursoId);
		//DetalhesAlunoCurso detalhesAlunoCurso = detalhesAlunoCursoService.findByAlunoAndCursoId(detalhesNotaId, cursoId);
		DetalhesAlunoCurso detalhesAlunoCurso = detalhesNotaService.findById(detalhesNotaId).getStudentCourseDetails();
		detalhesAlunoCurso.setDetalhesNota(detalhesNota);
		detalhesAlunoCursoService.save(detalhesAlunoCurso);
		detalhesNotaService.deleteById(detalhesNotaId);
		//detalhesNotaService.save(detalhesNota);
		
	    return "redirect:/professor/" + professorId + "/cursos/" + cursoId;
	}

	@GetMapping("/{professorId}/cursos/{cursoId}/atividades/{atividadeId}")
	public String exibirDetalhesDeAtividade(@PathVariable("professorId") int professorId, @PathVariable("cursoId") int cursoId,
											@PathVariable("atividadeId") int atividadeId, Model theModel) {
		Professor professor = professorService.findByProfessorId(professorId);
		Curso curso = cursoService.findCursoById(cursoId);
		List<Aluno> alunos = curso.getAlunos();
		List<Curso> cursos = professor.getCursos();
		//
		
		List<Atividade> atividades = new ArrayList<>();
		List<DetalhesAlunoCurso> detalhesAlunoCurso = new ArrayList<>();
		List<DetalhesAtividade> detalhesAlunoCursoAtividade = new ArrayList<>();
		List<String> StatusAtividade = new ArrayList<>();
		
		for(Aluno aluno : alunos) {
			DetalhesAtividade detalheAlunoCursoAtividade = detalhesAtividadeService.
					findByAtividadeAndDetalhesAlunoCursoId(atividadeId, detalhesAlunoCursoService.findByAlunoAndCursoId(aluno.getId(), cursoId).getId());
			detalhesAlunoCursoAtividade.add(detalheAlunoCursoAtividade);
			if(detalheAlunoCursoAtividade.getIsDone() == 0) {
				StatusAtividade.add("incompleto");
			} else {
				StatusAtividade.add("completo");
			}
		}
				
		HashMap<List<Aluno>, List<String>> list = new HashMap<>();
		list.put(alunos, StatusAtividade);
		
		theModel.addAttribute("list", list);
		theModel.addAttribute("detalhesAtividade", detalhesAlunoCursoAtividade);
		theModel.addAttribute("alunos", alunos);
		theModel.addAttribute("cursos", cursos);
		theModel.addAttribute("professor", professor);
		
		return "professor/professor-atividade-status";
	}

	@GetMapping("/{professorId}/cursos/{cursoId}/atividades/{atividadeId}/delete")
	public String deleteAtividade(@PathVariable("professorId") int professorId, @PathVariable("cursoId") int cursoId,
								  @PathVariable("atividadeId") int atividadeId) {
		atividadeService.deleteAtividadeById(atividadeId);
		
		return "redirect:/professor/" + professorId + "/cursos/" + cursoId;
	}
	
	@GetMapping("/{professorId}/cursos/{cursoId}/addNewAtividade")
	public String addNewAtividade(@PathVariable("professorId") int professorId, @PathVariable("cursoId") int cursoId, Model theModel) {
		Atividade atividade = new Atividade();
		Professor professor = professorService.findByProfessorId(professorId);
		List<Curso> cursos = professor.getCursos();
		
		theModel.addAttribute("atividade", atividade);
		theModel.addAttribute("professor", professor);
		theModel.addAttribute("curso", cursoService.findCursoById(cursoId));
		theModel.addAttribute("cursos", cursos);
		
		return "professor/atividade-form";
	}
	
	@PostMapping("/{professorId}/cursos/{cursoId}/addNewAtividade/save")
	public String saveAtividade(@Valid @ModelAttribute("atividade") Atividade atividade, BindingResult theBindingResult,
								 @PathVariable("professorId") int professorId, @PathVariable("cursoId") int cursoId, Model theModel) {
		
		Professor professor = professorService.findByProfessorId(professorId);
		List<Curso> cursos = professor.getCursos();
		
		if (theBindingResult.hasErrors()) {
			theModel.addAttribute("professor", professor);
			theModel.addAttribute("cursos", cursos);
			theModel.addAttribute("curso", cursoService.findCursoById(cursoId));
			return "professor/atividade-form";
		}
		
		atividade.setDaysRemaining(findDayDifference(atividade));
		atividadeService.save(atividade);
		
		Curso curso = cursoService.findCursoById(cursoId);
		List<Aluno> alunos = curso.getAlunos();
		
		for(Aluno aluno : alunos) {
			DetalhesAlunoCurso detalhesAlunoCurso = detalhesAlunoCursoService.findByAlunoAndCursoId(aluno.getId(), cursoId);
			DetalhesAtividade detalhesAtividade = new DetalhesAtividade();
			detalhesAtividade.setAtividadeId(atividade.getId());
			detalhesAtividade.setDetalhesAlunoCursoId(detalhesAlunoCurso.getId());
			detalhesAtividade.setIsDone(0);
			detalhesAtividadeService.save(detalhesAtividade);
		}
		
		
		theModel.addAttribute("professor", professor);
		
		return "redirect:/professor/" + professorId + "/cursos/" + cursoId;
	}
	
	
	
	
	private int findDayDifference(Atividade atividade) {
		String dateString = atividade.getDueDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			LocalDate dueDate = LocalDate.parse(dateString, dtf);
			LocalDate today = LocalDate.now();
			int dayDiff = (int) Duration.between(today.atStartOfDay(), dueDate.atStartOfDay()).toDays();
			
			return dayDiff;	
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return -1;
	}
	
}




