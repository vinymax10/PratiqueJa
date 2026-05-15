package dao.academico;

import java.util.ArrayList;
import java.util.List;

import filtro.academico.FiltroAssuntoCurso;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.AssuntoCurso;
import modelo.academico.Modulo;
import modelo.exercicio.ExercicioPadrao;
import dao.DAO;

public class AssuntoCursoDAO extends DAO<AssuntoCurso>
{
	private static final long serialVersionUID = 1L;

	public AssuntoCursoDAO()
	{
		super(AssuntoCurso.class);
	}

	public List<AssuntoCurso> buscar(FiltroAssuntoCurso filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNomeAssuntoCurso() != null && !filtro.getNomeAssuntoCurso().isBlank())
		{
			predicates.add(builder.like(fromAssuntoCurso.get("nome"), "%" + filtro.getNomeAssuntoCurso() + "%"));
		}

		if(filtro.getChave() != null && !filtro.getChave().isBlank())
		{
			predicates.add(builder.like(fromAssuntoCurso.get("chave"), "%" + filtro.getChave() + "%"));
		}

		if(filtro.getModulo() != null)
		{
			predicates.add(builder.equal(fromAssuntoCurso.get("modulo"), filtro.getModulo()));
		}

		if(filtro.getHabilidado() != null)
		{
			predicates.add(builder.equal(fromAssuntoCurso.get("habilidado"), filtro.getHabilidado()));
		}

		if(filtro.getShowAula() != null)
		{
			predicates.add(builder.equal(fromAssuntoCurso.get("showAula"), filtro.getShowAula()));
		}

		if(filtro.getShowAnotacao() != null)
		{
			predicates.add(builder.equal(fromAssuntoCurso.get("showAnotacao"), filtro.getShowAnotacao()));
		}

		if(filtro.getShowExercicio() != null)
		{
			predicates.add(builder.equal(fromAssuntoCurso.get("showExercicio"), filtro.getShowExercicio()));
		}

		if(filtro.getShowQuestao() != null)
		{
			predicates.add(builder.equal(fromAssuntoCurso.get("showQuestao"), filtro.getShowQuestao()));
		}

		TypedQuery<AssuntoCurso> typedQuery = em.createQuery(query.select(fromAssuntoCurso).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssuntoCurso.get("modulo")),
		builder.asc(fromAssuntoCurso.get("ordem"))));

		List<AssuntoCurso> list = typedQuery.getResultList();

		return list;
	}

	public AssuntoCurso assunto(String chave)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromAssuntoCurso.get("habilidado"), true));

		predicates.add(builder.equal(fromAssuntoCurso.get("chave"), chave));

		TypedQuery<AssuntoCurso> typedQuery = em.createQuery(query.select(fromAssuntoCurso).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssuntoCurso.get("modulo")),
		builder.asc(fromAssuntoCurso.get("ordem"))));

		List<AssuntoCurso> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);

		return null;
	}

	public List<AssuntoCurso> todos()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromAssuntoCurso.get("habilidado"), true));

		TypedQuery<AssuntoCurso> typedQuery = em.createQuery(query.select(fromAssuntoCurso).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssuntoCurso.get("modulo")),
		builder.asc(fromAssuntoCurso.get("ordem"))));

		List<AssuntoCurso> list = typedQuery.getResultList();

		return list;
	}

	public List<AssuntoCurso> buscar(Modulo modulo)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromAssuntoCurso.get("habilidado"), true));

		if(modulo != null)
		{
			predicates.add(builder.equal(fromAssuntoCurso.get("modulo"), modulo));
		}

		TypedQuery<AssuntoCurso> typedQuery = em
		.createQuery(query.select(fromAssuntoCurso).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssuntoCurso.get("modulo")),
		builder.asc(fromAssuntoCurso.get("ordem"))));

		List<AssuntoCurso> list = typedQuery.getResultList();

		return list;
	}

	public List<ExercicioPadrao> getExerciciosPadrao(AssuntoCurso assuntoCurso)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicioPadrao = query.from(ExercicioPadrao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(assuntoCurso != null)
		{
			predicates.add(builder.equal(fromExercicioPadrao.get("assuntoCurso").get("id"), assuntoCurso.getId()));
		}

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicioPadrao).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromExercicioPadrao.get("nivel"))));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list;
	}

}
