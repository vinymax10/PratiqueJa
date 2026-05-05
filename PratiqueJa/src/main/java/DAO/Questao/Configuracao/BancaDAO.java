package DAO.Questao.Configuracao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Questao.Configuracao.Banca;

public class BancaDAO extends DAO<Banca>
{
	private static final long serialVersionUID = 1L;

	public BancaDAO()
	{
		super(Banca.class);
	}

	public List<Banca> filtrar(String nomeFiltro, String siglaFiltro)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Banca> query = builder.createQuery(Banca.class);
		Root<Banca> fromBanca = query.from(Banca.class);

		Predicate predicate = builder.and();

		if(!nomeFiltro.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromBanca.<String>get("nome"), "%" + nomeFiltro + "%"));
		}

		if(!siglaFiltro.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromBanca.<String>get("sigla"), "%" + siglaFiltro + "%"));
		}

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicate).distinct(true));
		List<Banca> list = typedQuery.getResultList();

		return list;
	}

	public List<Banca> procurarParecido(Banca banca)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Banca> query = builder.createQuery(Banca.class);
		Root<Banca> fromBanca = query.from(Banca.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.notEqual(fromBanca.get("id"), banca.getId()));
		predicate = builder.and(predicate, builder.or(builder.like(fromBanca.<String>get("nome"), "%" + banca.getNome() + "%"),
		builder.like(fromBanca.<String>get("sigla"), "%" + banca.getSigla() + "%")));

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicate).distinct(true));
		List<Banca> list = typedQuery.getResultList();

		return list;
	}

	public List<Banca> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Banca> query = builder.createQuery(Banca.class);
		Root<Banca> fromBanca = query.from(Banca.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate,
			builder.or(builder.like(fromBanca.<String>get("nome"), "%" + nome + "%"), builder.like(fromBanca.<String>get("sigla"), "%" + nome + "%")));
		}

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicate).distinct(true));
		List<Banca> list = typedQuery.getResultList();

		return list;
	}

}
