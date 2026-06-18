package dao.configuracao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.configuracao.Config;

public class ConfigDAO extends DAO<Config>
{
	private static final long serialVersionUID = 1L;

	public ConfigDAO()
	{
		super(Config.class);
	}

	public Config buscar()
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Config> query = builder.createQuery(Config.class);
		Root<Config> fromConfig = query.from(Config.class);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<Config> typedQuery = em.createQuery(query.select(fromConfig).where(predicates.toArray(new Predicate[0])));
		List<Config> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
}
