package com.burak.studentmanagement.service;

import java.util.List;

import com.burak.studentmanagement.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burak.studentmanagement.dao.CursoDao;

@Service
public class CursoServiceImpl implements CursoService {
	
	@Autowired
	private CursoDao cursoDao;
	
	@Override
	@Transactional
	public void save(Curso curso) {
		cursoDao.saveCurso(curso);
	}

	@Override
	@Transactional
	public List<Curso> findAllCursos() {
		return cursoDao.findAllCursos();
	}

	@Override
	@Transactional
	public Curso findCursoById(int id) {
		return cursoDao.findCursoById(id);
	}

	@Override
	@Transactional
	public void deleteCursoById(int id) {
		cursoDao.deleteCursoById(id);
	}

}
