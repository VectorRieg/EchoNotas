package com.burak.studentmanagement.service;

import com.burak.studentmanagement.entity.DetalhesNota;

public interface DetalhesNotaService {
	
	public void save(DetalhesNota detalhesNota);
	
	public DetalhesNota findById(int id);
	
	public void deleteById(int id);
}
