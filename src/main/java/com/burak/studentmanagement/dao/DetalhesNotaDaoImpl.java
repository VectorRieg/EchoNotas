package com.burak.studentmanagement.dao;

import javax.persistence.EntityManager;

import com.burak.studentmanagement.entity.DetalhesNota;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetalhesNotaDaoImpl implements DetalhesNotaDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(DetalhesNota detalhesNota) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(detalhesNota);
	}

	@Override
	public DetalhesNota findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<DetalhesNota> query = session.createQuery("from detalhes_nota where id=:notaId", DetalhesNota.class);
		query.setParameter("notaId", id);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public void deleteById(int detalhesNotaId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete detalhes_nota where id=:detalhesNotaId");
		query.setParameter("detalhesNotaId", detalhesNotaId);
		query.executeUpdate();
	}

}
