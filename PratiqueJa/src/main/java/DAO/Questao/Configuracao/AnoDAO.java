package DAO.Questao.Configuracao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Questao.Configuracao.Ano;

public class AnoDAO extends DAO<Ano>
{
	private static final long serialVersionUID = 1L;

	public AnoDAO()
	{
		super(Ano.class);
	}

	public List<Ano> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ano> query = builder.createQuery(Ano.class);
		Root<Ano> fromAno = query.from(Ano.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromAno.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Ano> typedQuery = em.createQuery(query.select(fromAno).where(predicate).distinct(true));
		List<Ano> list = typedQuery.getResultList();

		return list;
	}

}
