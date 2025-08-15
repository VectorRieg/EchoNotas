package com.burak.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burak.studentmanagement.entity.DetalhesAlunoCurso;

@Repository
public class DetalhesAlunoCursoDaoImpl implements DetalhesAlunoCursoDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<DetalhesAlunoCurso> findByAlunoId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<DetalhesAlunoCurso> query = session.createQuery("from detalhes_aluno_curso where alunoId=:id", DetalhesAlunoCurso.class);
		query.setParameter("id", id);
		
		try {
			return query.getResultList();
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public DetalhesAlunoCurso findByAlunoAndCursoId(int alunoId, int cursoId) {
		Session session = entityManager.unwrap(Session.class);
		Query<DetalhesAlunoCurso> query = session
				.createQuery("from detalhes_aluno_curso where alunoId=:alunoId and cursoId=:cursoId", DetalhesAlunoCurso.class);
		query.setParameter("alunoId", alunoId);
		query.setParameter("cursoId", cursoId);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public void deleteByAlunoAndCursoId(int alunoId, int cursoId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete detalhes_aluno_curso where alunoId=:alunoId and cursoId=:cursoId");
		query.setParameter("alunoId", alunoId);
		query.setParameter("cursoId", cursoId);
		query.executeUpdate();
		
	}

	@Override
	public void save(DetalhesAlunoCurso detalhesAlunoCurso) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(detalhesAlunoCurso);
	}

	@Override
	public void deleteByAlunoId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete detalhes_aluno_curso where alunoId=:alunoId");
		query.setParameter("alunoId", id);
		query.executeUpdate();
	}

}
