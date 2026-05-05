package DAO.Exercicio;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Bean.Exercicio.Filtro.FiltroResultadoExercicio;
import DAO.DAO;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Exercicio.ResultadoExercicio;
import Modelo.Usuario.Usuario;

public class ResultadoExercicioDAO extends DAO<ResultadoExercicio>
{
	private static final long serialVersionUID = 1L;

	public ResultadoExercicioDAO()
	{
		super(ResultadoExercicio.class);
	}

	public List<ResultadoExercicio> listar(Usuario usuario)
	{
		em.clear();

		TypedQuery<ResultadoExercicio> query = em.createQuery("Select e from ResultadoExercicio e WHERE e.usuario.id = : usuarioId ORDER BY e.realizacao DESC",
		ResultadoExercicio.class);

		query.setParameter("usuarioId", usuario.getId());

		List<ResultadoExercicio> list = query.getResultList();

		return list;
	}

	public List<ResultadoExercicio> exerciciosRealizados100(ExercicioPadrao exercicio, Usuario usuario)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoExercicio> query = builder.createQuery(ResultadoExercicio.class);
		Root<ResultadoExercicio> fromResultadoExercicio = query.from(ResultadoExercicio.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, 
		builder.greaterThanOrEqualTo(fromResultadoExercicio.<Double>get("nota"), 1d));

		if(exercicio != null)
		{
			predicate = builder.and(predicate, 
			builder.equal(fromResultadoExercicio.<ExercicioPadrao>get("exercicio").get("id"), exercicio.getId()));
		}

		if(usuario != null)
		{
			predicate = builder.and(predicate, 
			builder.equal(fromResultadoExercicio.<Usuario>get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<ResultadoExercicio> typedQuery = em.createQuery(query.select(fromResultadoExercicio).where(predicate));
		List<ResultadoExercicio> list = typedQuery.getResultList();

		return list;
	}

	public List<ResultadoExercicio> buscar(FiltroResultadoExercicio filtroResultadoExercicio)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoExercicio> query = builder.createQuery(ResultadoExercicio.class);
		Root<ResultadoExercicio> fromResultadoExercicio = query.from(ResultadoExercicio.class);

		Predicate predicate = builder.and();

		if(filtroResultadoExercicio.getUsuario() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromResultadoExercicio.get("usuario").get("id"), filtroResultadoExercicio.getUsuario().getId()));
		}
		
		if(filtroResultadoExercicio.getNomeUsuario() != null && !filtroResultadoExercicio.getNomeUsuario().equals(""))
		{

			predicate = builder.and(predicate,
			builder.like(fromResultadoExercicio.<Usuario>get("usuario").get("nome"), "%" + filtroResultadoExercicio.getNomeUsuario() + "%"));
		}

		if(filtroResultadoExercicio.getAssuntoCurso() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromResultadoExercicio.get("exercicio").get("assuntoCurso").get("id"), filtroResultadoExercicio.getAssuntoCurso().getId()));
		}

		if(filtroResultadoExercicio.getModulo() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromResultadoExercicio.get("exercicio").get("assuntoCurso").get("modulo"), filtroResultadoExercicio.getModulo()));
		}

		if(filtroResultadoExercicio.getNivel() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromResultadoExercicio.get("exercicio").get("nivel"), filtroResultadoExercicio.getNivel()));
		}

		if(filtroResultadoExercicio.getNomeExercicio() != null && !filtroResultadoExercicio.getNomeExercicio().equals(""))
		{
			predicate = builder.and(predicate,
			builder.like(fromResultadoExercicio.get("exercicio").get("nome"), "%" + filtroResultadoExercicio.getNomeExercicio() + "%"));
		}

		if(filtroResultadoExercicio.getEnunciado() != null && !filtroResultadoExercicio.getEnunciado().equals(""))
		{
			predicate = builder.and(predicate,
			builder.like(fromResultadoExercicio.get("exercicio").get("enunciado"), "%" + filtroResultadoExercicio.getEnunciado() + "%"));
		}

		if(filtroResultadoExercicio.getDescricao() != null && !filtroResultadoExercicio.getDescricao().equals(""))
		{
			predicate = builder.and(predicate,
			builder.like(fromResultadoExercicio.get("exercicio").get("descricao"), "%" + filtroResultadoExercicio.getDescricao() + "%"));
		}

		if(filtroResultadoExercicio.getTipoExercicio() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromResultadoExercicio.get("exercicio").get("tipoExercicio"), filtroResultadoExercicio.getTipoExercicio()));
		}

		if(filtroResultadoExercicio.getInicioRealizacao() != null)
		{
			predicate = builder.and(predicate,
			builder.greaterThanOrEqualTo(fromResultadoExercicio.get("realizacao"), filtroResultadoExercicio.getInicioRealizacao()));
		}

		if(filtroResultadoExercicio.getTerminoRealizacao() != null)
		{
			predicate = builder.and(predicate,
			builder.lessThanOrEqualTo(fromResultadoExercicio.get("realizacao"), filtroResultadoExercicio.getTerminoRealizacao()));
		}

		TypedQuery<ResultadoExercicio> typedQuery = em.createQuery(query.select(fromResultadoExercicio).where(predicate)
		.orderBy(builder.desc(fromResultadoExercicio.get("realizacao"))));
		List<ResultadoExercicio> list = typedQuery.getResultList();

		return list;
	}
}
