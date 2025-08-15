package com.burak.studentmanagement.dao;

import com.burak.studentmanagement.entity.DetalhesNota;

public interface DetalhesNotaDao {
	
	public void save(DetalhesNota detalhesNota);
	
	public DetalhesNota findById(int id);
	
	public void deleteById(int id);
}
