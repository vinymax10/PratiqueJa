package dao.questao.configuracao;

import java.util.List;

import dao.DAO;
import filtro.FiltroOrgao;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.questao.configuracao.Orgao;

public class OrgaoDAO extends DAO<Orgao>
{
	private static final long serialVersionUID = 1L;

	public OrgaoDAO()
	{
		super(Orgao.class);
	}

	public List<Orgao> buscar(FiltroOrgao filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = builder.createQuery(Orgao.class);
		Root<Orgao> fromOrgao = query.from(Orgao.class);

		Predicate predicate = builder.and();

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicate = builder.and(predicate, builder.like(
				builder.upper(fromOrgao.get("nome")), "%" + filtro.getNome().toUpperCase() + "%"));

		if(filtro.getSigla() != null && !filtro.getSigla().isBlank())
			predicate = builder.and(predicate, builder.like(
				builder.upper(fromOrgao.get("sigla")), "%" + filtro.getSigla().toUpperCase() + "%"));

		if(filtro.getAtivo() != null)
			predicate = builder.and(predicate, builder.equal(
				fromOrgao.get("ativo"), filtro.getAtivo()));

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicate).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Orgao> procurarParecido(Orgao orgao)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = builder.createQuery(Orgao.class);
		Root<Orgao> fromOrgao = query.from(Orgao.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.notEqual(fromOrgao.get("id"), orgao.getId()));
		predicate = builder.and(predicate, builder.or(
			builder.like(fromOrgao.<String>get("nome"), "%" + orgao.getNome() + "%"),
			builder.like(fromOrgao.<String>get("sigla"), "%" + orgao.getSigla() + "%")));

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicate).distinct(true));
		return typedQuery.getResultList();
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
			builder.or(
				builder.like(fromOrgao.<String>get("nome"), "%" + nome + "%"),
				builder.like(fromOrgao.<String>get("sigla"), "%" + nome + "%")));
		}

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicate).distinct(true));
		return typedQuery.getResultList();
	}
}
