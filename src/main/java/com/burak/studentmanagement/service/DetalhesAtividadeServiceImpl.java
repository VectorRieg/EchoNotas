package com.burak.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burak.studentmanagement.dao.DetalhesAtividadeDao;
import com.burak.studentmanagement.entity.DetalhesAtividade;

@Service
public class DetalhesAtividadeServiceImpl implements DetalhesAtividadeService {
	
	@Autowired
	private DetalhesAtividadeDao studentCourseDetalhesAtividadeDao;
	
	@Override
	@Transactional
	public DetalhesAtividade findByAtividadeAndDetalhesAlunoCursoId(int atividadeId,
																	   int detalhesAlunoCursoId) {
		return studentCourseDetalhesAtividadeDao.findByAtividadeAndDetalhesAlunoCursoId(atividadeId, detalhesAlunoCursoId);
		 
	}

	@Override
	@Transactional
	public void save(DetalhesAtividade studentCourseDetalhesAtividade) {
		studentCourseDetalhesAtividadeDao.save(studentCourseDetalhesAtividade);
	}

}
