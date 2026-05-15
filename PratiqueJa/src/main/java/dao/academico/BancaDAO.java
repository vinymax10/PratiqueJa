package dao.academico;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.academico.FiltroBanca;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Banca;

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

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicates.add(builder.like(
				builder.upper(fromBanca.get("nome")), "%" + filtro.getNome().toUpperCase() + "%"));

		if(filtro.getSigla() != null && !filtro.getSigla().isBlank())
			predicates.add(builder.like(
				builder.upper(fromBanca.get("sigla")), "%" + filtro.getSigla().toUpperCase() + "%"));

		if(filtro.getAtivo() != null)
			predicates.add(builder.equal(
				fromBanca.get("ativo"), filtro.getAtivo()));

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Banca> procurarParecido(Banca banca)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Banca> query = builder.createQuery(Banca.class);
		Root<Banca> fromBanca = query.from(Banca.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.notEqual(fromBanca.get("id"), banca.getId()));
		predicates.add(builder.or(
			builder.like(fromBanca.<String>get("nome"), "%" + banca.getNome() + "%"),
			builder.like(fromBanca.<String>get("sigla"), "%" + banca.getSigla() + "%")));

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Banca> filtrar(String nome)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Banca> query = builder.createQuery(Banca.class);
		Root<Banca> fromBanca = query.from(Banca.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.or(
				builder.like(fromBanca.<String>get("nome"), "%" + nome + "%"),
				builder.like(fromBanca.<String>get("sigla"), "%" + nome + "%")));
		}

		TypedQuery<Banca> typedQuery = em.createQuery(query.select(fromBanca).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}
}
