package dao.exercicio;

import java.time.LocalDate;
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
import bean.exercicio.filtro.FiltroExercicio;

public class ExercicioDAO extends DAO<Exercicio>
{
	private static final long serialVersionUID = 1L;

	public ExercicioDAO()
	{
		super(Exercicio.class);
	}

	public Long numeroMeusExercicios(Usuario usuario, Boolean realizado)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		Predicate predicate = builder.and();

		if(realizado != null)
			predicate = builder.and(predicate, builder.equal(fromExercicio.get("realizado"), realizado.booleanValue()));

		if(usuario != null)
			predicate = builder.and(predicate, builder.equal(fromExercicio.<Usuario>get("usuario").get("id"), usuario.getId()));

		query.select(builder.count(fromExercicio)).where(predicate);
		Long result = em.createQuery(query).getSingleResult();

		return result;
	}
	
	public List<Exercicio> meusExercicios(Usuario usuario, Boolean realizada)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		Predicate predicate = builder.and();

		if(realizada != null)
			predicate = builder.and(predicate, builder.equal(fromExercicio.get("realizado"), realizada.booleanValue()));

		if(usuario != null)
		{
			predicate = builder.and(predicate, builder.equal(fromExercicio.<Usuario>get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate)
		.orderBy(builder.desc(fromExercicio.get("realizacao"))));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}

	public List<Exercicio> buscar(FiltroExercicio filtroExercicio)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		Predicate predicate = builder.and();

		if(filtroExercicio.getRealizada() != null)
			predicate = builder.and(predicate, builder.equal(fromExercicio.get("realizado"), filtroExercicio.getRealizada().booleanValue()));

		if(filtroExercicio.getUsuario() != null)
			predicate = builder.and(predicate, builder.equal(fromExercicio.<Usuario>get("usuario").get("id"), filtroExercicio.getUsuario().getId()));

		if(filtroExercicio.getExercicioPadrao() != null)
			predicate = builder.and(predicate, builder.equal(fromExercicio.<ExercicioPadrao>get("exercicioPadrao").get("id"), filtroExercicio.getExercicioPadrao().getId()));

		if(filtroExercicio.getInicioRealizacao() != null)
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromExercicio.get("realizacao"), filtroExercicio.getInicioRealizacao()));

		if(filtroExercicio.getTerminoRealizacao() != null)
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromExercicio.get("realizacao"), filtroExercicio.getTerminoRealizacao()));

		if(filtroExercicio.getInicioPrazo() != null)
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromExercicio.get("prazo"), filtroExercicio.getInicioPrazo()));

		if(filtroExercicio.getTerminoPrazo() != null)
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromExercicio.get("prazo"), filtroExercicio.getTerminoPrazo()));

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate).distinct(true));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Exercicio> exerciciosRealizados(int diasRemoverExercicioRealizado)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromExercicio.get("realizado"), true));

		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverExercicioRealizado);
		
		predicate = builder.and(predicate,
			builder.lessThanOrEqualTo(fromExercicio.get("realizacao"), limite));

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Exercicio> exerciciosNaoRealizados(int diasRemoverExercicioNaoRealizado)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = builder.createQuery(Exercicio.class);
		Root<Exercicio> fromExercicio = query.from(Exercicio.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromExercicio.get("realizado"), false));

		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverExercicioNaoRealizado);
		
		predicate = builder.and(predicate,
			builder.lessThanOrEqualTo(fromExercicio.get("prazo"), limite));

		TypedQuery<Exercicio> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate));
		List<Exercicio> list = typedQuery.getResultList();

		return list;
	}
	
}
