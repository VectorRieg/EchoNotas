package com.burak.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burak.studentmanagement.dao.DetalhesAlunoCursoDao;
import com.burak.studentmanagement.entity.DetalhesAlunoCurso;

@Service
public class DetalhesAlunoCursoServiceImpl implements DetalhesAlunoCursoService {
	
	@Autowired
	private DetalhesAlunoCursoDao detalhesAlunoCursoDao;
	
	@Override
	@Transactional
	public List<DetalhesAlunoCurso> findByAlunoId(int id) {
		return detalhesAlunoCursoDao.findByAlunoId(id);
	}

	@Override
	@Transactional
	public DetalhesAlunoCurso findByAlunoAndCursoId(int alunoId, int cursoId) {
		return detalhesAlunoCursoDao.findByAlunoAndCursoId(alunoId, cursoId);
	}

	@Override
	@Transactional
	public void deleteByAlunoAndCursoId(int alunoId, int cursoId) {
		detalhesAlunoCursoDao.deleteByAlunoAndCursoId(alunoId, cursoId);
	}

	@Override
	@Transactional
	public void save(DetalhesAlunoCurso detalhesAlunoCurso) {
		detalhesAlunoCursoDao.save(detalhesAlunoCurso);
		
	}

	@Override
	@Transactional
	public void deleteByAlunoId(int id) {
		detalhesAlunoCursoDao.deleteByAlunoId(id);
	}

}
