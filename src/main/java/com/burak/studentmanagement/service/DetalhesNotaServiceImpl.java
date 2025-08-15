package com.burak.studentmanagement.service;

import com.burak.studentmanagement.entity.DetalhesNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burak.studentmanagement.dao.DetalhesNotaDao;

@Service
public class DetalhesNotaServiceImpl implements DetalhesNotaService {

	@Autowired
	private DetalhesNotaDao detalhesNotaDao;
	
	@Override
	@Transactional
	public void save(DetalhesNota detalhesNota) {
		detalhesNotaDao.save(detalhesNota);
	}

	@Override
	@Transactional
	public DetalhesNota findById(int id) {
		return detalhesNotaDao.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		detalhesNotaDao.deleteById(id);
	}

}
