package DAO.Configuracao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Configuracao.ConfigLatex;

public class ConfigLatexDAO extends DAO<ConfigLatex>
{
	private static final long serialVersionUID = 1L;

	public ConfigLatexDAO()
	{
		super(ConfigLatex.class);
	}

	public ConfigLatex buscar()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigLatex> query = builder.createQuery(ConfigLatex.class);
		Root<ConfigLatex> fromConfigLatex = query.from(ConfigLatex.class);

		Predicate predicate = builder.and();

		TypedQuery<ConfigLatex> typedQuery = em.createQuery(query.select(fromConfigLatex).where(predicate));
		List<ConfigLatex> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
}
