package com.burak.studentmanagement.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burak.studentmanagement.entity.Atividade;

@Repository
public class AtividadeDaoImpl implements AtividadeDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(Atividade atividade) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(atividade);
	}

	@Override
	public void deleteAtividadeById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete Atividade where id=:atividadeId");
		query.setParameter("atividadeId", id);
		query.executeUpdate();
	}

}
