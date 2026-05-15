package dao.academico;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.academico.FiltroOrgao;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Orgao;

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

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicates.add(builder.like(
				builder.upper(fromOrgao.get("nome")), "%" + filtro.getNome().toUpperCase() + "%"));

		if(filtro.getSigla() != null && !filtro.getSigla().isBlank())
			predicates.add(builder.like(
				builder.upper(fromOrgao.get("sigla")), "%" + filtro.getSigla().toUpperCase() + "%"));

		if(filtro.getAtivo() != null)
			predicates.add(builder.equal(
				fromOrgao.get("ativo"), filtro.getAtivo()));

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Orgao> procurarParecido(Orgao orgao)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = builder.createQuery(Orgao.class);
		Root<Orgao> fromOrgao = query.from(Orgao.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.notEqual(fromOrgao.get("id"), orgao.getId()));
		predicates.add(builder.or(
			builder.like(fromOrgao.<String>get("nome"), "%" + orgao.getNome() + "%"),
			builder.like(fromOrgao.<String>get("sigla"), "%" + orgao.getSigla() + "%")));

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Orgao> filtrar(String nome)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = builder.createQuery(Orgao.class);
		Root<Orgao> fromOrgao = query.from(Orgao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.or(
				builder.like(fromOrgao.<String>get("nome"), "%" + nome + "%"),
				builder.like(fromOrgao.<String>get("sigla"), "%" + nome + "%")));
		}

		TypedQuery<Orgao> typedQuery = em.createQuery(query.select(fromOrgao).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}
}
