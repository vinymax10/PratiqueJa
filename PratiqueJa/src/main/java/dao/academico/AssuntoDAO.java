package dao.academico;

import java.util.ArrayList;
import java.util.List;

import filtro.academico.FiltroAssunto;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Assunto;
import modelo.academico.Modulo;
import modelo.exercicio.ExercicioPadrao;
import modelo.questao.Questao;
import dao.DAO;

public class AssuntoDAO extends DAO<Assunto>
{
	private static final long serialVersionUID = 1L;

	public AssuntoDAO()
	{
		super(Assunto.class);
	}

	public List<Assunto> buscar(FiltroAssunto filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNomeAssunto() != null && !filtro.getNomeAssunto().isBlank())
		{
			predicates.add(builder.like(fromAssunto.get("nome"), "%" + filtro.getNomeAssunto() + "%"));
		}

		if(filtro.getChave() != null && !filtro.getChave().isBlank())
		{
			predicates.add(builder.like(fromAssunto.get("chave"), "%" + filtro.getChave() + "%"));
		}

		if(filtro.getModulo() != null)
		{
			predicates.add(builder.equal(fromAssunto.get("modulo"), filtro.getModulo()));
		}

		if(filtro.getHabilidado() != null)
		{
			predicates.add(builder.equal(fromAssunto.get("habilidado"), filtro.getHabilidado()));
		}

		if(filtro.getShowAula() != null)
		{
			predicates.add(builder.equal(fromAssunto.get("showAula"), filtro.getShowAula()));
		}

		if(filtro.getShowAnotacao() != null)
		{
			predicates.add(builder.equal(fromAssunto.get("showAnotacao"), filtro.getShowAnotacao()));
		}

		if(filtro.getShowExercicio() != null)
		{
			predicates.add(builder.equal(fromAssunto.get("showExercicio"), filtro.getShowExercicio()));
		}

		if(filtro.getShowQuestao() != null)
		{
			predicates.add(builder.equal(fromAssunto.get("showQuestao"), filtro.getShowQuestao()));
		}

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssunto.get("modulo")),
		builder.asc(fromAssunto.get("ordem"))));

		List<Assunto> list = typedQuery.getResultList();

		return list;
	}

	public Assunto assunto(String chave)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromAssunto.get("habilidado"), true));

		predicates.add(builder.equal(fromAssunto.get("chave"), chave));

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssunto.get("modulo")),
		builder.asc(fromAssunto.get("ordem"))));

		List<Assunto> list = typedQuery.getResultList();

		return list.isEmpty() ? null : list.get(0);
	}

	public List<Assunto> todos()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromAssunto.get("habilidado"), true));

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssunto.get("modulo")),
		builder.asc(fromAssunto.get("ordem"))));

		List<Assunto> list = typedQuery.getResultList();

		return list;
	}

	public List<Assunto> buscar(Modulo modulo)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromAssunto.get("habilidado"), true));

		if(modulo != null)
		{
			predicates.add(builder.equal(fromAssunto.get("modulo"), modulo));
		}

		TypedQuery<Assunto> typedQuery = em
		.createQuery(query.select(fromAssunto).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromAssunto.get("modulo")),
		builder.asc(fromAssunto.get("ordem"))));

		List<Assunto> list = typedQuery.getResultList();

		return list;
	}

	public int contarQuestoes(Assunto assunto)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		query.select(builder.count(fromQuestao))
			.where(builder.isMember(assunto, fromQuestao.get("assuntos")));

		return em.createQuery(query).getSingleResult().intValue();
	}

	public List<ExercicioPadrao> getExerciciosPadrao(Assunto assunto)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicioPadrao = query.from(ExercicioPadrao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(assunto != null)
		{
			predicates.add(builder.equal(fromExercicioPadrao.get("assunto").get("id"), assunto.getId()));
		}

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicioPadrao).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromExercicioPadrao.get("nivel"))));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list;
	}

}
