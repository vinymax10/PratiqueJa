package dao.exercicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.usuario.Usuario;
import filtro.exercicio.FiltroExercicio;

public class ExercicioDAO extends DAO<Exercicio>
{
	private static final long serialVersionUID = 1L;

	public ExercicioDAO()
	{
		super(Exercicio.class);
	}

	public Long numeroMeusExercicios(Usuario usuario, Boolean realizado)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if(realizado != null)
			predicates.add(builder.equal(fromExercicio.get("realizado"), realizado.booleanValue()));

		if(usuario != null)
			predicates.add(builder.equal(fromExercicio.<Usuario>get("usuario").get("id"), usuario.getId()));

		query.select(builder.count(fromExercicio)).where(predicates.toArray(new Predicate[0]));
		Long result = em.createQuery(query).getSingleResult();

		return result;
	}
	
	public List<Exercicio> meusExercicios(Usuario usuario, Boolean realizada)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if(realizada != null)
			predicates.add(builder.equal(fromExercicio.get("realizado"), realizada.booleanValue()));

		if(usuario != null)
		{
			predicates.add(builder.equal(fromExercicio.<Usuario>get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.desc(fromExercicio.get("realizacao"))));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}

	public List<Exercicio> buscar(FiltroExercicio filtro)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getRealizada() != null)
			predicates.add(builder.equal(fromExercicio.get("realizado"), filtro.getRealizada().booleanValue()));

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicates.add(builder.like(fromExercicio.get("usuario").get("nome"), "%" + filtro.getNome() + "%"));

		if(filtro.getExercicioPadrao() != null)
			predicates.add(builder.equal(fromExercicio.<ExercicioPadrao>get("exercicioPadrao").get("id"), filtro.getExercicioPadrao().getId()));

		if(filtro.getPeriodo() != null && !filtro.getPeriodo().isEmpty())
		{
			predicates.add(builder.greaterThanOrEqualTo(fromExercicio.get("realizacao"), filtro.getPeriodo().get(0)));
			predicates.add(builder.lessThanOrEqualTo(fromExercicio.get("realizacao"), filtro.getPeriodo().get(1)));
		}

		if(filtro.getInicioPrazo() != null)
			predicates.add(builder.greaterThanOrEqualTo(fromExercicio.get("prazo"), filtro.getInicioPrazo()));

		if(filtro.getTerminoPrazo() != null)
			predicates.add(builder.lessThanOrEqualTo(fromExercicio.get("prazo"), filtro.getTerminoPrazo()));

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Exercicio> exerciciosRealizados(int diasRemoverExercicioRealizado)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromExercicio.get("realizado"), true));

		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverExercicioRealizado);
		
		predicates.add(builder.lessThanOrEqualTo(fromExercicio.get("realizacao"), limite));

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicates.toArray(new Predicate[0])));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Exercicio> exerciciosNaoRealizados(int diasRemoverExercicioNaoRealizado)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromExercicio.get("realizado"), false));

		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverExercicioNaoRealizado);
		
		predicates.add(builder.lessThanOrEqualTo(fromExercicio.get("prazo"), limite));

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicates.toArray(new Predicate[0])));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}
	
}
