package ar.com.fluxit.errorManager.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.fluxit.errorManager.model.ExceptionDescriptor;


@Repository
@Transactional()
public class ExceptionDescriptorRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public ExceptionDescriptor getExceptionDescriptor(String className) {
		try {
			return (ExceptionDescriptor) entityManager
					.createQuery("from ExceptionDescriptor where className = ?")
					.setParameter(1, className).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	public void update(ExceptionDescriptor exceptionDescriptor) {
		entityManager.persist(exceptionDescriptor);

	}

}
