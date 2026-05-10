package dao.configuracao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.configuracao.ConfigLatex;

public class ConfigLatexDAO extends DAO<ConfigLatex>
{
	private static final long serialVersionUID = 1L;

	public ConfigLatexDAO()
	{
		super(ConfigLatex.class);
	}

	public ConfigLatex buscar()
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigLatex> query = builder.createQuery(ConfigLatex.class);
		Root<ConfigLatex> fromConfigLatex = query.from(ConfigLatex.class);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<ConfigLatex> typedQuery = em.createQuery(query.select(fromConfigLatex).where(predicates.toArray(new Predicate[0])));
		List<ConfigLatex> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
}
