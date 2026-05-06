package dao.teste;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.teste.Teste;
import modelo.teste.TestePadrao;
import modelo.usuario.Usuario;
import bean.teste.filtro.FiltroTeste;

public class TesteDAO extends DAO<Teste>
{
	private static final long serialVersionUID = 1L;

	public TesteDAO()
	{
		super(Teste.class);
	}

	public List<Teste> meusTestes(Usuario usuario, Boolean realizado)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		Predicate predicate = builder.and();

		if(realizado != null)
			predicate = builder.and(predicate, builder.equal(fromTeste.get("realizado"), realizado.booleanValue()));

		if(usuario != null)
			predicate = builder.and(predicate,
			builder.equal(fromTeste.<Usuario>get("usuario").get("id"), usuario.getId()));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicate).distinct(true));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}

	public Long numeroMeusTestes(Usuario usuario, Boolean realizado)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		Predicate predicate = builder.and();

		if(realizado != null)
			predicate = builder.and(predicate, builder.equal(fromTeste.get("realizado"), realizado.booleanValue()));

		if(usuario != null)
			predicate = builder.and(predicate,
			builder.equal(fromTeste.<Usuario>get("usuario").get("id"), usuario.getId()));

		query.select(builder.count(fromTeste)).where(predicate);
		Long result = em.createQuery(query).getSingleResult();

		return result;
	}

	public List<Teste> buscar(FiltroTeste filtroTeste)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		Predicate predicate = builder.and();

		if(filtroTeste.getRealizado() != null)
			predicate = builder.and(predicate,
			builder.equal(fromTeste.get("realizado"), filtroTeste.getRealizado().booleanValue()));

		if(filtroTeste.getAssuntoCurso() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromTeste.get("testePadrao").get("assuntoCurso").get("id"), filtroTeste.getAssuntoCurso().getId()));
		}
		
		if(filtroTeste.getTestePadrao() != null)
			predicate = builder.and(predicate,
			builder.equal(fromTeste.<TestePadrao>get("testePadrao").get("id"), filtroTeste.getTestePadrao().getId()));

		if(filtroTeste.getNome() != null && !filtroTeste.getNome().equals(""))
			predicate = builder.and(predicate,
			builder.like(fromTeste.<Usuario>get("usuario").get("nome"), "%"+filtroTeste.getNome()+"%"));

		if(filtroTeste.getInicioRealizacao() != null)
			predicate = builder.and(predicate,
			builder.greaterThanOrEqualTo(fromTeste.get("realizacao"), filtroTeste.getInicioRealizacao()));

		if(filtroTeste.getTerminoRealizacao() != null)
			predicate = builder.and(predicate,
			builder.lessThanOrEqualTo(fromTeste.get("realizacao"), filtroTeste.getTerminoRealizacao()));

		if(filtroTeste.getNota() != null)
			predicate = builder.and(predicate, builder.equal(fromTeste.get("nota"), filtroTeste.getNota()));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicate));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Teste> testesRealizados(int diasRemoverTesteRealizado)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromTeste.get("realizado"), true));

		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverTesteRealizado);
		
		predicate = builder.and(predicate,
			builder.lessThanOrEqualTo(fromTeste.get("realizacao"), limite));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicate));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Teste> testesNaoRealizados(int diasRemoverTesteNaoRealizado)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromTeste.get("realizado"), false));
		predicate = builder.and(predicate, builder.equal(fromTeste.get("tempo"), 0));
		predicate = builder.and(predicate, builder.equal(fromTeste.get("repetirAtePassar"), false));
		
		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverTesteNaoRealizado);
		
		predicate = builder.and(predicate,
			builder.lessThanOrEqualTo(fromTeste.get("realizacao"), limite));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicate));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}

}
