package dao.exercicio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.ResultadoExercicio;
import modelo.usuario.Usuario;
import filtro.exercicio.FiltroResultadoExercicio;

public class ResultadoExercicioDAO extends DAO<ResultadoExercicio>
{
	private static final long serialVersionUID = 1L;

	public ResultadoExercicioDAO()
	{
		super(ResultadoExercicio.class);
	}

	public List<ResultadoExercicio> listar(Usuario usuario)
	{

		TypedQuery<ResultadoExercicio> query = em.createQuery("Select e from ResultadoExercicio e WHERE e.usuario.id = : usuarioId ORDER BY e.realizacao DESC",
		ResultadoExercicio.class);

		query.setParameter("usuarioId", usuario.getId());

		List<ResultadoExercicio> list = query.getResultList();

		return list;
	}

	public List<ResultadoExercicio> exerciciosRealizados100(ExercicioPadrao exercicio, Usuario usuario)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoExercicio> query = builder.createQuery(ResultadoExercicio.class);
		Root<ResultadoExercicio> fromResultadoExercicio = query.from(ResultadoExercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.greaterThanOrEqualTo(fromResultadoExercicio.<Double>get("nota"), 1d));

		if(exercicio != null)
		{
			predicates.add(builder.equal(fromResultadoExercicio.<ExercicioPadrao>get("exercicio").get("id"), exercicio.getId()));
		}

		if(usuario != null)
		{
			predicates.add(builder.equal(fromResultadoExercicio.<Usuario>get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<ResultadoExercicio> typedQuery = em.createQuery(query.select(fromResultadoExercicio).where(predicates.toArray(new Predicate[0])));
		List<ResultadoExercicio> list = typedQuery.getResultList();

		return list;
	}

	public List<ResultadoExercicio> buscar(FiltroResultadoExercicio filtroResultadoExercicio)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoExercicio> query = builder.createQuery(ResultadoExercicio.class);
		Root<ResultadoExercicio> fromResultadoExercicio = query.from(ResultadoExercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroResultadoExercicio.getUsuario() != null)
		{
			predicates.add(builder.equal(fromResultadoExercicio.get("usuario").get("id"), filtroResultadoExercicio.getUsuario().getId()));
		}
		
		if(filtroResultadoExercicio.getNomeUsuario() != null && !filtroResultadoExercicio.getNomeUsuario().isBlank())
		{

			predicates.add(builder.like(fromResultadoExercicio.<Usuario>get("usuario").get("nome"), "%" + filtroResultadoExercicio.getNomeUsuario() + "%"));
		}

		if(filtroResultadoExercicio.getAssuntoCurso() != null)
		{
			predicates.add(builder.equal(fromResultadoExercicio.get("exercicio").get("assuntoCurso").get("id"), filtroResultadoExercicio.getAssuntoCurso().getId()));
		}

		if(filtroResultadoExercicio.getModulo() != null)
		{
			predicates.add(builder.equal(fromResultadoExercicio.get("exercicio").get("assuntoCurso").get("modulo"), filtroResultadoExercicio.getModulo()));
		}

		if(filtroResultadoExercicio.getNivel() != null)
		{
			predicates.add(builder.equal(fromResultadoExercicio.get("exercicio").get("nivel"), filtroResultadoExercicio.getNivel()));
		}

		if(filtroResultadoExercicio.getNomeExercicio() != null && !filtroResultadoExercicio.getNomeExercicio().isBlank())
		{
			predicates.add(builder.like(fromResultadoExercicio.get("exercicio").get("nome"), "%" + filtroResultadoExercicio.getNomeExercicio() + "%"));
		}

		if(filtroResultadoExercicio.getEnunciado() != null && !filtroResultadoExercicio.getEnunciado().isBlank())
		{
			predicates.add(builder.like(fromResultadoExercicio.get("exercicio").get("enunciado"), "%" + filtroResultadoExercicio.getEnunciado() + "%"));
		}

		if(filtroResultadoExercicio.getDescricao() != null && !filtroResultadoExercicio.getDescricao().isBlank())
		{
			predicates.add(builder.like(fromResultadoExercicio.get("exercicio").get("descricao"), "%" + filtroResultadoExercicio.getDescricao() + "%"));
		}

		if(filtroResultadoExercicio.getTipoExercicio() != null)
		{
			predicates.add(builder.equal(fromResultadoExercicio.get("exercicio").get("tipoExercicio"), filtroResultadoExercicio.getTipoExercicio()));
		}

		if(filtroResultadoExercicio.getInicioRealizacao() != null)
		{
			predicates.add(builder.greaterThanOrEqualTo(fromResultadoExercicio.get("realizacao"), filtroResultadoExercicio.getInicioRealizacao()));
		}

		if(filtroResultadoExercicio.getTerminoRealizacao() != null)
		{
			predicates.add(builder.lessThanOrEqualTo(fromResultadoExercicio.get("realizacao"), filtroResultadoExercicio.getTerminoRealizacao()));
		}

		TypedQuery<ResultadoExercicio> typedQuery = em.createQuery(query.select(fromResultadoExercicio).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.desc(fromResultadoExercicio.get("realizacao"))));
		List<ResultadoExercicio> list = typedQuery.getResultList();

		return list;
	}
}
