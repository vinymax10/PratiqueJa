package DAO.Questao.Configuracao;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Questao.Configuracao.Orgao;

public class OrgaoDAO extends DAO<Orgao>
{
	private static final long serialVersionUID = 1L;

	public OrgaoDAO()
	{
		super(Orgao.class);
	}

	public List<Orgao> filtrar(String nomeFiltro, String siglaFiltro)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = builder.createQuery(Orgao.class);
		Root<Orgao> fromOrgao = query.from(Orgao.class);

		Predicate predicate = builder.and();

		if(!nomeFiltro.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromOrgao.<String>get("nome"), "%" + nomeFiltro + "%"));
		}

		if(!siglaFiltro.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromOrgao.<String>get("sigla"), "%" + siglaFiltro + "%"));
		}

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicate).distinct(true));
		List<Orgao> list = typedQuery.getResultList();

		return list;
	}

	public List<Orgao> procurarParecido(Orgao orgao)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = builder.createQuery(Orgao.class);
		Root<Orgao> fromOrgao = query.from(Orgao.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.notEqual(fromOrgao.get("id"), orgao.getId()));
		predicate = builder.and(predicate, builder.or(builder.like(fromOrgao.<String>get("nome"), "%" + orgao.getNome() + "%"),
		builder.like(fromOrgao.<String>get("sigla"), "%" + orgao.getSigla() + "%")));

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicate).distinct(true));
		List<Orgao> list = typedQuery.getResultList();

		return list;
	}

	public List<Orgao> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = builder.createQuery(Orgao.class);
		Root<Orgao> fromOrgao = query.from(Orgao.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate,
			builder.or(builder.like(fromOrgao.<String>get("nome"), "%" + nome + "%"), builder.like(fromOrgao.<String>get("sigla"), "%" + nome + "%")));
		}

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicate).distinct(true));
		List<Orgao> list = typedQuery.getResultList();

		return list;
	}

}
