package dao.exercicio;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.exercicio.FiltroResultadoExercicio;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Assunto;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.ResultadoExercicio;
import modelo.usuario.Usuario;

public class ResultadoExercicioDAO extends DAO<ResultadoExercicio>
{
	private static final long serialVersionUID = 1L;

	public ResultadoExercicioDAO()
	{
		super(ResultadoExercicio.class);
	}

	public List<ResultadoExercicio> listar(Usuario usuario)
	{
		TypedQuery<ResultadoExercicio> query = em.createQuery(
			"Select e from ResultadoExercicio e WHERE e.usuario.id = :usuarioId ORDER BY e.realizacao DESC",
			ResultadoExercicio.class);

		query.setParameter("usuarioId", usuario.getId());

		return query.getResultList();
	}

	/** Resultado já existente do usuário para o exercício (para upsert), ou null se ainda não respondeu. */
	public ResultadoExercicio buscar(Exercicio exercicio, Usuario usuario)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoExercicio> query = builder.createQuery(ResultadoExercicio.class);
		Root<ResultadoExercicio> from = query.from(ResultadoExercicio.class);

		query.select(from).where(
			builder.equal(from.get("exercicio").get("id"), exercicio.getId()),
			builder.equal(from.get("usuario").get("id"), usuario.getId()));

		List<ResultadoExercicio> lista = em.createQuery(query).getResultList();
		return lista.isEmpty() ? null : lista.get(0);
	}

	/** Quantos exercícios do assunto o usuário já acertou (nota >= 1). */
	public int contarCorretos(Assunto assunto, Usuario usuario)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ResultadoExercicio> from = query.from(ResultadoExercicio.class);

		query.select(builder.count(from)).where(
			builder.equal(from.get("usuario").get("id"), usuario.getId()),
			builder.equal(from.get("exercicio").get("assunto").get("id"), assunto.getId()),
			builder.greaterThanOrEqualTo(from.<Double>get("nota"), 1d));

		return em.createQuery(query).getSingleResult().intValue();
	}

	public List<ResultadoExercicio> exerciciosRealizados100(ExercicioPadrao exercicio, Usuario usuario)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoExercicio> query = builder.createQuery(ResultadoExercicio.class);
		Root<ResultadoExercicio> from = query.from(ResultadoExercicio.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.greaterThanOrEqualTo(from.<Double>get("nota"), 1d));

		if(exercicio != null)
			predicates.add(builder.equal(from.<ExercicioPadrao>get("exercicioPadrao").get("id"), exercicio.getId()));

		if(usuario != null)
			predicates.add(builder.equal(from.<Usuario>get("usuario").get("id"), usuario.getId()));

		return em.createQuery(query.select(from).where(predicates.toArray(new Predicate[0]))).getResultList();
	}

	public List<ResultadoExercicio> buscar(FiltroResultadoExercicio filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoExercicio> query = builder.createQuery(ResultadoExercicio.class);
		Root<ResultadoExercicio> from = query.from(ResultadoExercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getUsuario() != null)
			predicates.add(builder.equal(from.get("usuario").get("id"), filtro.getUsuario().getId()));

		if(filtro.getNomeUsuario() != null && !filtro.getNomeUsuario().isBlank())
			predicates.add(builder.like(from.<Usuario>get("usuario").get("nome"), "%" + filtro.getNomeUsuario() + "%"));

		if(filtro.getAssunto() != null)
			predicates.add(builder.equal(from.get("exercicioPadrao").get("assunto").get("id"), filtro.getAssunto().getId()));

		if(filtro.getModulo() != null)
			predicates.add(builder.equal(from.get("exercicioPadrao").get("assunto").get("modulo"), filtro.getModulo()));

		if(filtro.getNivel() != null)
			predicates.add(builder.equal(from.get("exercicioPadrao").get("nivel"), filtro.getNivel()));

		if(filtro.getNomeEnunciadoDescricao() != null && !filtro.getNomeEnunciadoDescricao().isBlank())
		{
			String like = "%" + filtro.getNomeEnunciadoDescricao() + "%";
			predicates.add(builder.or(
				builder.like(from.get("exercicioPadrao").get("nome"), like),
				builder.like(from.get("exercicioPadrao").get("enunciado"), like),
				builder.like(from.get("exercicioPadrao").get("descricao"), like)
			));
		}

		if(filtro.getPeriodo() != null && filtro.getPeriodo().size() == 2)
		{
			if(filtro.getPeriodo().get(0) != null)
				predicates.add(builder.greaterThanOrEqualTo(from.get("realizacao"), filtro.getPeriodo().get(0)));

			if(filtro.getPeriodo().get(1) != null)
				predicates.add(builder.lessThanOrEqualTo(from.get("realizacao"), filtro.getPeriodo().get(1)));
		}

		return em.createQuery(
			query.select(from)
			.where(predicates.toArray(new Predicate[0]))
			.orderBy(builder.desc(from.get("realizacao")))
		).getResultList();
	}
}
