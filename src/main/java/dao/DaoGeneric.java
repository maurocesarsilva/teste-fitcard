package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import util.Util;

public class DaoGeneric<E> {

	public E updateMerge(E entidade) {
		EntityManager entityManager = Util.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade);
		transaction.commit();
		entityManager.close();

		return entidadeSalva;
	}

	public void deletarPorId(E entidade) throws Exception {
		EntityManager entityManager = Util.getEntityManager();
		Object id = Util.getPrimaryKey(entidade);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager
				.createNativeQuery(
						"delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id=" + id)
				.executeUpdate();

		transaction.commit();
		entityManager.close();
	}

	public List<E> listar(Class<E> entidade) {
		EntityManager entityManager = Util.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		transaction.commit();
		entityManager.close();

		return lista;
	}
	
	
}
