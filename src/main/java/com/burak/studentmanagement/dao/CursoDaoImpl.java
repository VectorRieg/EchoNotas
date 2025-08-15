package com.burak.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.burak.studentmanagement.entity.Curso;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDaoImpl implements CursoDao {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public void saveCurso(Curso curso) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(curso);
	}


	@Override
	public List<Curso> findAllCursos() {
		Session session = entityManager.unwrap(Session.class);
		List<Curso> cursos = session.createQuery("from Curso", Curso.class).getResultList();
		return cursos;
	}


	@Override
	public Curso findCursoById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Curso> query = session.createQuery("from Curso where id=:cursoId", Curso.class);
		query.setParameter("cursoId", id);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}


	@Override
	public void deleteCursoById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete Curso where id=:cursoId");
		query.setParameter("cursoId", id);
		query.executeUpdate();
	}

}
