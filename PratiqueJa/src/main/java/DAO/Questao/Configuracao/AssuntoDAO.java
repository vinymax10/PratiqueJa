package DAO.Questao.Configuracao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Questao.Configuracao.Assunto;

public class AssuntoDAO extends DAO<Assunto>
{
	private static final long serialVersionUID = 1L;

	public AssuntoDAO()
	{
		super(Assunto.class);
	}

	public List<Assunto> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromAssunto.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicate).distinct(true));
		List<Assunto> list = typedQuery.getResultList();

		return list;
	}
}
