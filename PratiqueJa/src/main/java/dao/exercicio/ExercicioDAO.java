package dao.exercicio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import filtro.exercicio.FiltroExercicio;
import modelo.academico.Assunto;
import modelo.exercicio.Exercicio;

public class ExercicioDAO extends DAO<Exercicio>
{
	private static final long serialVersionUID = 1L;

	public ExercicioDAO()
	{
		super(Exercicio.class);
	}

	public List<Exercicio> buscar(FiltroExercicio filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> from = query.from(Exercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getAssunto() != null)
			predicates.add(builder.equal(from.get("assunto").get("id"), filtro.getAssunto().getId()));

		if(filtro.getNivel() != null)
			predicates.add(builder.equal(from.get("nivel"), filtro.getNivel()));

		if(filtro.getGlobal() != null)
			predicates.add(builder.equal(from.get("global"), filtro.getGlobal().booleanValue()));

		if(filtro.getVisibilidade() != null)
			predicates.add(builder.equal(from.get("visibilidade"), filtro.getVisibilidade()));

		if(filtro.getTexto() != null && !filtro.getTexto().isBlank())
			predicates.add(builder.like(builder.lower(from.join("paragrafos", JoinType.LEFT).get("texto")),
				"%" + filtro.getTexto().toLowerCase() + "%"));

		TypedQuery<Exercicio> typedQuery = em.createQuery(
			query.select(from).where(predicates.toArray(new Predicate[0])).distinct(true));

		return typedQuery.getResultList();
	}

	public List<Exercicio> buscarPorAssunto(Assunto assunto)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> from = query.from(Exercicio.class);

		query.select(from).where(
			builder.equal(from.get("assunto").get("id"), assunto.getId()),
			builder.equal(from.get("global"), true));

		return em.createQuery(query).getResultList();
	}

	public long contarExercicios(Assunto assunto)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Exercicio> from = query.from(Exercicio.class);

		query.select(builder.count(from)).where(
			builder.equal(from.get("assunto").get("id"), assunto.getId()),
			builder.equal(from.get("global"), true));

		return em.createQuery(query).getSingleResult();
	}

	public List<Exercicio> buscarGlobais()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> from = query.from(Exercicio.class);

		query.select(from).where(builder.equal(from.get("global"), true));

		return em.createQuery(query).getResultList();
	}
}
