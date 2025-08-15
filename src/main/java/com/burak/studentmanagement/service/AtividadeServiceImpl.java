package com.burak.studentmanagement.service;

import com.burak.studentmanagement.entity.Atividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burak.studentmanagement.dao.AtividadeDao;

@Service
public class AtividadeServiceImpl implements AtividadeService {
	
	@Autowired
	private AtividadeDao atividadeDao;
	
	@Override
	@Transactional
	public void save(Atividade atividade) {
		atividadeDao.save(atividade);
	}

	@Override
	@Transactional
	public void deleteAtividadeById(int id) {
		atividadeDao.deleteAtividadeById(id);
	}

}
