package com.burak.studentmanagement.service;

import com.burak.studentmanagement.entity.DetalhesAtividade;

public interface DetalhesAtividadeService {
	
	public DetalhesAtividade findByAtividadeAndDetalhesAlunoCursoId(int atividadeId, int detalhesAlunoCursoId);
	
	public void save(DetalhesAtividade studentCourseDetalhesAtividade);
}
