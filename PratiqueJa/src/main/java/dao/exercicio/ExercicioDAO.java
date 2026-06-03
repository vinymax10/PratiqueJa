package dao.exercicio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import filtro.exercicio.FiltroExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;

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
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getExercicioPadrao() != null)
			predicates.add(builder.equal(fromExercicio.<ExercicioPadrao>get("exercicioPadrao").get("id"), filtro.getExercicioPadrao().getId()));

		if(filtro.getGlobal() != null)
			predicates.add(builder.equal(fromExercicio.get("global"), filtro.getGlobal().booleanValue()));

		if(filtro.getEnunciado() != null && !filtro.getEnunciado().isBlank())
			predicates.add(builder.like(builder.lower(fromExercicio.get("enunciado")), "%" + filtro.getEnunciado().toLowerCase() + "%"));

		TypedQuery<Exercicio> typedQuery = em.createQuery(
			query.select(fromExercicio).where(predicates.toArray(new Predicate[0])).distinct(true));

		return typedQuery.getResultList();
	}

	public List<Exercicio> buscarGlobais()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		query.select(fromExercicio)
			.where(builder.equal(fromExercicio.get("global"), true));

		return em.createQuery(query).getResultList();
	}
}
