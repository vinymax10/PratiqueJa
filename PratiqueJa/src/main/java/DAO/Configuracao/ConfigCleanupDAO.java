package DAO.Configuracao;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Configuracao.ConfigCleanup;

public class ConfigCleanupDAO extends DAO<ConfigCleanup>
{
	private static final long serialVersionUID = 1L;

	public ConfigCleanupDAO()
	{
		super(ConfigCleanup.class);
	}

	public ConfigCleanup buscar()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigCleanup> query = builder.createQuery(ConfigCleanup.class);
		Root<ConfigCleanup> fromConfigCleanup = query.from(ConfigCleanup.class);

		Predicate predicate = builder.and();

		TypedQuery<ConfigCleanup> typedQuery = em.createQuery(query.select(fromConfigCleanup).where(predicate));
		List<ConfigCleanup> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
}
