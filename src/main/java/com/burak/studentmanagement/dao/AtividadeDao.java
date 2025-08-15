package com.burak.studentmanagement.dao;

import com.burak.studentmanagement.entity.Atividade;

public interface AtividadeDao {
	
	public void save(Atividade atividade);
	
	public void deleteAtividadeById(int id);
}
