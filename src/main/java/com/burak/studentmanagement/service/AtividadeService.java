package com.burak.studentmanagement.service;

import com.burak.studentmanagement.entity.Atividade;

public interface AtividadeService {
	
	public void save(Atividade atividade);
	
	public void deleteAtividadeById(int id);
}
