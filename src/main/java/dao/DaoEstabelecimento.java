package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedNativeQueries;

import model.CadEstabelecimento;
import util.Util;

public class DaoEstabelecimento<E> {
	
	public List<E> listarPorRazao(Class<E> entidade, String razao) {
		EntityManager entityManager = Util.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<E> lista = entityManager.createQuery("from CadEstabelecimento  where razaos like '%"+razao+"%'").getResultList();
		
		transaction.commit();
		entityManager.close();

		return lista;
	}
	
	public List<E> listarPorCnpj(Class<E> entidade, String cnpj) {
		EntityManager entityManager = Util.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<E> lista = entityManager.createQuery("from " + entidade.getName()+ " where cnpj like '"+cnpj+"'").getResultList();
		transaction.commit();
		entityManager.close();

		return lista;
	}

}
