package dao.exercicio;

import java.util.ArrayList;
import java.util.List;

import bean.download.SetDownload;
import filtro.exercicio.FiltroExercicioPadrao;
import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Assunto;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;

public class ExercicioPadraoDAO extends DAO<ExercicioPadrao>
{
	private static final long serialVersionUID = 1L;

	public ExercicioPadraoDAO()
	{
		super(ExercicioPadrao.class);
	}

	public List<ExercicioPadrao> buscar(FiltroExercicioPadrao filtroExercicio)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroExercicio.getQuantidade() != 0)
		{
			predicates.add(builder.equal(fromExercicio.get("quantidade"), filtroExercicio.getQuantidade()));
		}

		if(filtroExercicio.getMostrarResolucao() != null)
		{
			predicates.add(builder.equal(fromExercicio.get("mostrarResolucao"), filtroExercicio.getMostrarResolucao()));
		}

		if(filtroExercicio.getNomeEnunciadoDescricao() != null && !filtroExercicio.getNomeEnunciadoDescricao().isBlank())
		{
			String like = "%" + filtroExercicio.getNomeEnunciadoDescricao() + "%";
			predicates.add(builder.or(
				builder.like(fromExercicio.<String>get("nome"), like),
				builder.like(fromExercicio.<String>get("enunciado"), like),
				builder.like(fromExercicio.<String>get("descricao"), like)
			));
		}

		if(filtroExercicio.getAssunto() != null)
		{
			predicates.add(builder.equal(fromExercicio.<Assunto>get("assunto").get("id"), filtroExercicio.getAssunto().getId()));
		}

		if(filtroExercicio.getModulo() != null)
		{
			predicates.add(builder.equal(fromExercicio.<Assunto>get("assunto").get("modulo"), filtroExercicio.getModulo()));
		}

		if(filtroExercicio.getNivel() != null)
		{
			predicates.add(builder.equal(fromExercicio.<Nivel>get("nivel"), filtroExercicio.getNivel()));
		}

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(
			query.select(fromExercicio)
			.where(predicates.toArray(new Predicate[0]))
			.orderBy(
				builder.asc(fromExercicio.get("assunto").get("modulo")),
				builder.asc(fromExercicio.get("assunto").get("ordem")),
				builder.asc(fromExercicio.get("nivel"))
			)
		);

		return typedQuery.getResultList();
	}

	public List<ExercicioPadrao> buscar(SetDownload setDownload)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(setDownload.getQuantidadeNivel1() == 0)
			predicates.add(builder.notEqual(fromExercicio.<Nivel>get("nivel"), Nivel.Nivel1));

		if(setDownload.getQuantidadeNivel2() == 0)
			predicates.add(builder.notEqual(fromExercicio.<Nivel>get("nivel"), Nivel.Nivel2));

		if(setDownload.getQuantidadeNivel3() == 0)
			predicates.add(builder.notEqual(fromExercicio.<Nivel>get("nivel"), Nivel.Nivel3));

		predicates.add(builder.and(fromExercicio.get("assunto").in(setDownload.getAssuntos())));

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(
			query.select(fromExercicio)
			.where(predicates.toArray(new Predicate[0]))
			.distinct(true)
		);

		return typedQuery.getResultList();
	}

	public ExercicioPadrao buscar(Assunto assunto, Nivel nivel)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromExercicio.<Nivel>get("nivel"), nivel));
		predicates.add(builder.equal(fromExercicio.get("assunto").get("id"), assunto.getId()));

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(
			query.select(fromExercicio)
			.where(predicates.toArray(new Predicate[0]))
			.distinct(true)
		);

		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list.isEmpty() ? null : list.get(0);
	}

}
