package dao.exercicio;

import java.util.List;

import bean.download.SetDownload;
import bean.exercicio.filtro.FiltroExercicioPadrao;
import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.assuntocurso.AssuntoCurso;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.exercicio.TipoExercicio;

public class ExercicioPadraoDAO extends DAO<ExercicioPadrao>
{
	private static final long serialVersionUID = 1L;

	public ExercicioPadraoDAO()
	{
		super(ExercicioPadrao.class);
	}

	public List<ExercicioPadrao> listaTudo()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		Predicate predicate = builder.and();

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate).distinct(true));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list;
	}

	public List<ExercicioPadrao> minhasExercicios(AssuntoCurso assuntoCurso)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		Predicate predicate = builder.and();

		if(assuntoCurso != null)
		{
			predicate = builder.and(predicate, builder.equal(fromExercicio.<AssuntoCurso>get("assuntoCurso").get("id"), assuntoCurso.getId()));
		}

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate).distinct(true));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list;
	}

	public List<ExercicioPadrao> buscar(FiltroExercicioPadrao filtroExercicio)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		Predicate predicate = builder.and();

		if(filtroExercicio.getQuantidade()!=0)
		{
			predicate = builder.and(predicate, 
			builder.equal(fromExercicio.get("quantidade"), filtroExercicio.getQuantidade()));
		}
		
		if(filtroExercicio.getMostrarResolucao()!=null)
		{
			predicate = builder.and(predicate, 
			builder.equal(fromExercicio.get("mostrarResolucao"), filtroExercicio.getMostrarResolucao()));
		}
		
		if(filtroExercicio.getNome() != null && !filtroExercicio.getNome().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromExercicio.<String>get("nome"), "%" + filtroExercicio.getNome() + "%"));
		}
		
		if(filtroExercicio.getAssuntoCurso() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromExercicio.<AssuntoCurso>get("assuntoCurso").get("id"), filtroExercicio.getAssuntoCurso().getId()));
		}

		if(filtroExercicio.getModulo() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromExercicio.<AssuntoCurso>get("assuntoCurso").get("modulo"), filtroExercicio.getModulo()));
		}

		if(filtroExercicio.getNivel() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromExercicio.<Nivel>get("nivel"), filtroExercicio.getNivel()));
		}

		if(filtroExercicio.getEnunciado() != null && !filtroExercicio.getEnunciado().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromExercicio.<String>get("enunciado"), "%" + filtroExercicio.getEnunciado() + "%"));
		}

		if(filtroExercicio.getDescricao() != null && !filtroExercicio.getDescricao().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromExercicio.<String>get("descricao"), "%" + filtroExercicio.getDescricao() + "%"));
		}

		if(filtroExercicio.getTipoExercicio() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromExercicio.<TipoExercicio>get("tipoExercicio"), filtroExercicio.getTipoExercicio()));
		}

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate).distinct(true));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list;
	}
	
	public List<ExercicioPadrao> buscar(SetDownload setDownload)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		Predicate predicate = builder.and();

		if(setDownload.getQuantidadeNivel1() == 0)
			predicate = builder.and(predicate, builder.notEqual(fromExercicio.<Nivel>get("nivel"), Nivel.Nivel1));
		
		if(setDownload.getQuantidadeNivel2() == 0)
			predicate = builder.and(predicate, builder.notEqual(fromExercicio.<Nivel>get("nivel"), Nivel.Nivel2));

		if(setDownload.getQuantidadeNivel3() == 0)
			predicate = builder.and(predicate, builder.notEqual(fromExercicio.<Nivel>get("nivel"), Nivel.Nivel3));
		
		predicate = builder.and(predicate, builder.and(fromExercicio.get("assuntoCurso").in(setDownload.getAssuntosCurso())));
		
		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate).distinct(true));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		return list;
	}
	
	public ExercicioPadrao buscar(AssuntoCurso assuntoCurso, Nivel nivel)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExercicioPadrao> query = builder.createQuery(ExercicioPadrao.class);
		Root<ExercicioPadrao> fromExercicio = query.from(ExercicioPadrao.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromExercicio.<Nivel>get("nivel"), nivel));
		predicate = builder.and(predicate, builder.equal(fromExercicio.get("assuntoCurso").get("id"), assuntoCurso.getId()));

		TypedQuery<ExercicioPadrao> typedQuery = em.createQuery(query.select(fromExercicio).where(predicate).distinct(true));
		List<ExercicioPadrao> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}

}
