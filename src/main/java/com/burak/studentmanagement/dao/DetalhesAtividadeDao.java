package com.burak.studentmanagement.dao;

import com.burak.studentmanagement.entity.DetalhesAtividade;

public interface DetalhesAtividadeDao {
	
	public DetalhesAtividade findByAtividadeAndDetalhesAlunoCursoId(int atividadeId, int detalhesAlunoCursoId);
	
	public void save(DetalhesAtividade detalhesAlunoCursoAtividade);
}
