package com.burak.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.burak.studentmanagement.entity.Professor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessorDaoImpl implements ProfessorDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Professor findByProfessorName(String theTeacherName) {
		Session session = entityManager.unwrap(Session.class);
		Query<Professor> query = session.createQuery("from Professor where userName=:user", Professor.class);
		query.setParameter("user", theTeacherName);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}
	
	@Override
	public Professor findByProfessorId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Professor> query = session.createQuery("from Professor where id=:professorId", Professor.class);
		query.setParameter("professorId", id);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public void save(Professor professor) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(professor);
	}

	@Override
	public List<Professor> findAllProfessores() {
		Session session = entityManager.unwrap(Session.class);
		List<Professor> professores = session.createQuery("from Professor", Professor.class).getResultList();
		return professores;
	}

	@Override
	public void deleteProfessorById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete Professor where id=:professorId");
		query.setParameter("professorId", id);
		query.executeUpdate();
	}

	

}
