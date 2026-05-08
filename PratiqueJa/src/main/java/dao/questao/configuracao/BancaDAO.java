package dao.questao.configuracao;

import java.util.List;

import dao.DAO;
import filtro.FiltroBanca;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.questao.configuracao.Banca;

public class BancaDAO extends DAO<Banca>
{
	private static final long serialVersionUID = 1L;

	public BancaDAO()
	{
		super(Banca.class);
	}

	public List<Banca> buscar(FiltroBanca filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Banca> query = builder.createQuery(Banca.class);
		Root<Banca> fromBanca = query.from(Banca.class);

		Predicate predicate = builder.and();

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicate = builder.and(predicate, builder.like(
				builder.upper(fromBanca.get("nome")), "%" + filtro.getNome().toUpperCase() + "%"));

		if(filtro.getSigla() != null && !filtro.getSigla().isBlank())
			predicate = builder.and(predicate, builder.like(
				builder.upper(fromBanca.get("sigla")), "%" + filtro.getSigla().toUpperCase() + "%"));

		if(filtro.getAtivo() != null)
			predicate = builder.and(predicate, builder.equal(
				fromBanca.get("ativo"), filtro.getAtivo()));

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicate).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Banca> procurarParecido(Banca banca)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Banca> query = builder.createQuery(Banca.class);
		Root<Banca> fromBanca = query.from(Banca.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.notEqual(fromBanca.get("id"), banca.getId()));
		predicate = builder.and(predicate, builder.or(
			builder.like(fromBanca.<String>get("nome"), "%" + banca.getNome() + "%"),
			builder.like(fromBanca.<String>get("sigla"), "%" + banca.getSigla() + "%")));

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicate).distinct(true));
		return typedQuery.getResultList();
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
			builder.or(
				builder.like(fromBanca.<String>get("nome"), "%" + nome + "%"),
				builder.like(fromBanca.<String>get("sigla"), "%" + nome + "%")));
		}

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicate).distinct(true));
		return typedQuery.getResultList();
	}
}
