package dao;

import java.util.List;

import bean.assuntocurso.FiltroAssuntoCurso;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.assuntocurso.AssuntoCurso;
import modelo.assuntocurso.Modulo;
import modelo.exercicio.ExercicioPadrao;

public class AssuntoCursoDAO extends DAO<AssuntoCurso>
{
	private static final long serialVersionUID = 1L;

	public AssuntoCursoDAO()
	{
		super(AssuntoCurso.class);
	}

	public List<AssuntoCurso> buscar(FiltroAssuntoCurso filtroAssuntoCurso)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		Predicate predicate = builder.and();

		if(!filtroAssuntoCurso.getNomeAssuntoCurso().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromAssuntoCurso.get("nome"), "%" + filtroAssuntoCurso.getNomeAssuntoCurso() + "%"));
		}

		if(!filtroAssuntoCurso.getChave().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromAssuntoCurso.get("chave"), "%" + filtroAssuntoCurso.getChave() + "%"));
		}

		if(filtroAssuntoCurso.getModulo() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("modulo"), filtroAssuntoCurso.getModulo()));
		}

		if(filtroAssuntoCurso.getHabilidado() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("habilidado"), filtroAssuntoCurso.getHabilidado()));
		}
		
		if(filtroAssuntoCurso.getShowAula() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("showAula"), filtroAssuntoCurso.getShowAula()));
		}

		if(filtroAssuntoCurso.getShowAnotacao() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("showAnotacao"), filtroAssuntoCurso.getShowAnotacao()));
		}

		if(filtroAssuntoCurso.getShowExercicio() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("showExercicio"), filtroAssuntoCurso.getShowExercicio()));
		}

		if(filtroAssuntoCurso.getShowQuestao() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("showQuestao"), filtroAssuntoCurso.getShowQuestao()));
		}

		if(filtroAssuntoCurso.getIndice() != 0)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("indice"), filtroAssuntoCurso.getIndice()));
		}

		TypedQuery<AssuntoCurso> typedQuery = em.createQuery(query.select(fromAssuntoCurso).where(predicate)
		.orderBy(builder.asc(fromAssuntoCurso.get("indice"))));
		
		List<AssuntoCurso> list = typedQuery.getResultList();

		return list;
	}
	
	public AssuntoCurso assunto(String chave)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		Predicate predicate = builder.and();
		predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("habilidado"), true));

		predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("chave"), chave));

		TypedQuery<AssuntoCurso> typedQuery = em.createQuery(query.select(fromAssuntoCurso).where(predicate)
		.orderBy(builder.asc(fromAssuntoCurso.get("indice"))));
		
		List<AssuntoCurso> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
	
	public List<AssuntoCurso> todos()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		Predicate predicate = builder.and();
		predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("habilidado"), true));

		TypedQuery<AssuntoCurso> typedQuery = em.createQuery(query.select(fromAssuntoCurso).where(predicate)
		.orderBy(builder.asc(fromAssuntoCurso.get("indice"))));
		
		List<AssuntoCurso> list = typedQuery.getResultList();

		return list;
	}

	public List<AssuntoCurso> buscar(Modulo modulo)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AssuntoCurso> query = builder.createQuery(AssuntoCurso.class);
		Root<AssuntoCurso> fromAssuntoCurso = query.from(AssuntoCurso.class);

		Predicate predicate = builder.and();
		predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("habilidado"), true));

		if(modulo != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAssuntoCurso.get("modulo"), modulo));
		}

		TypedQuery<AssuntoCurso> typedQuery = em
		.createQuery(query.select(fromAssuntoCurso).where(predicate).distinct(true).orderBy(builder.asc(fromAssuntoCurso.get("indice"))));
		List<AssuntoCurso> list = typedQuery.getResultList();

		return list;
	}

	public List<ExercicioPadrao> getExerciciosPadrao(AssuntoCurso assuntoCurso)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicioPadrao = query.from(ExercicioPadrao.class);

		Predicate predicate = builder.and();

		if(assuntoCurso != null)
		{
			predicate = builder.and(predicate, builder.equal(fromExercicioPadrao.get("assuntoCurso").get("id"), assuntoCurso.getId()));
		}

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicioPadrao).where(predicate)
		.orderBy(builder.asc(fromExercicioPadrao.get("nivel"))));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list;
	}

}
