package com.burak.studentmanagement.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burak.studentmanagement.entity.DetalhesAtividade;

@Repository
public class DetalhesAtividadeDaoImpl implements DetalhesAtividadeDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public DetalhesAtividade findByAtividadeAndDetalhesAlunoCursoId(int atividadeId, int detalhesAlunoCursoId) {
		Session session = entityManager.unwrap(Session.class);
		Query<DetalhesAtividade> query = session
				.createQuery("from detalhes_atividade where atividadeId=:atividadeId AND detalhesAlunoCursoId=:detailsId",
				DetalhesAtividade.class);
		query.setParameter("atividadeId", atividadeId);
		query.setParameter("detailsId", detalhesAlunoCursoId);
		
		
		DetalhesAtividade s = query.getSingleResult();
		
		return s;
		
		
		
	}

	@Override
	public void save(DetalhesAtividade detalhesAlunoCursoAtividade) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(detalhesAlunoCursoAtividade);
	}

}
