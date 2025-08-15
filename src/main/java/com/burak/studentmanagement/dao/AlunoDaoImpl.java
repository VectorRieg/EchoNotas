package com.burak.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.burak.studentmanagement.entity.Aluno;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AlunoDaoImpl implements AlunoDao {
	
	@Autowired
	private EntityManager entityManager;
		
	@Override
	public Aluno findByAlunoName(String theAlunoName) {
		Session session = entityManager.unwrap(Session.class);
		Query<Aluno> query = session.createQuery("from Student where userName=:user", Aluno.class);
		query.setParameter("user", theAlunoName);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
		
	}
	
	@Override
	public Aluno findByAlunoId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Aluno> query = session.createQuery("from Student where id=:theId", Aluno.class);
		query.setParameter("theId", id);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
		
	}

	@Override
	public void save(Aluno aluno) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(aluno);

	}

	@Override
	public Aluno patch(int id, Aluno partialAluno) {
		Aluno existingAluno = findByAlunoId(partialAluno.getId());
		if (existingAluno == null) {
			return null; // ou lance exceção, se preferir
		}

		// Atualiza campos somente se não forem nulos no partialStudent
		if (partialAluno.getPrimeiroNome() != null) {
			existingAluno.setPrimeiroNome(partialAluno.getPrimeiroNome());
		}
		if (partialAluno.getUltimoNome() != null) {
			existingAluno.setUltimoNome(partialAluno.getUltimoNome());
		}
		if (partialAluno.getEmail() != null) {
			existingAluno.setEmail(partialAluno.getEmail());
		}
		// Adicione outros campos conforme necessidade

		return entityManager.merge(existingAluno);
	}

	@Override
	public List<Aluno> findAllAlunos() {
		Session session = entityManager.unwrap(Session.class);
		List<Aluno> alunos = session.createQuery("from Aluno", Aluno.class).getResultList();
		return alunos;
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete Aluno where id=:alunoId");
		query.setParameter("alunoId", id);
		query.executeUpdate();
	}

	@Override
	public Aluno update(int id, Aluno aluno) {
		return null;
	}

}
