package dao.configuracao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.configuracao.ConfigControleAcesso;

public class ConfigControleAcessoDAO extends DAO<ConfigControleAcesso>
{
	private static final long serialVersionUID = 1L;

	public ConfigControleAcessoDAO()
	{
		super(ConfigControleAcesso.class);
	}

	public ConfigControleAcesso buscar()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigControleAcesso> query = builder.createQuery(ConfigControleAcesso.class);
		Root<ConfigControleAcesso> fromConfig = query.from(ConfigControleAcesso.class);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<ConfigControleAcesso> typedQuery = em.createQuery(
			query.select(fromConfig).where(predicates.toArray(new Predicate[0])));
		List<ConfigControleAcesso> list = typedQuery.getResultList();

		if(list.size() > 0)
			return list.get(0);

		return null;
	}
}
