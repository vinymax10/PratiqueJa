package DAO.Configuracao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
