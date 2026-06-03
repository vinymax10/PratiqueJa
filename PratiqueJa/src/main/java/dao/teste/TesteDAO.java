package dao.teste;

import java.time.LocalDate;
import java.util.ArrayList;
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
import filtro.teste.FiltroTeste;

public class TesteDAO extends DAO<Teste>
{
	private static final long serialVersionUID = 1L;

	public TesteDAO()
	{
		super(Teste.class);
	}

	public List<Teste> meusTestes(Usuario usuario, Boolean realizado)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		List<Predicate> predicates = new ArrayList<>();

		if(realizado != null)
			predicates.add(builder.equal(fromTeste.get("realizado"), realizado.booleanValue()));

		if(usuario != null)
			predicates.add(builder.equal(fromTeste.<Usuario>get("usuario").get("id"), usuario.getId()));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}

	public Long numeroMeusTestes(Usuario usuario, Boolean realizado)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		List<Predicate> predicates = new ArrayList<>();

		if(realizado != null)
			predicates.add(builder.equal(fromTeste.get("realizado"), realizado.booleanValue()));

		if(usuario != null)
			predicates.add(builder.equal(fromTeste.<Usuario>get("usuario").get("id"), usuario.getId()));

		query.select(builder.count(fromTeste)).where(predicates.toArray(new Predicate[0]));
		Long result = em.createQuery(query).getSingleResult();

		return result;
	}

	public List<Teste> buscar(FiltroTeste filtroTeste)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroTeste.getRealizado() != null)
			predicates.add(builder.equal(fromTeste.get("realizado"), filtroTeste.getRealizado().booleanValue()));

		if(filtroTeste.getAssunto() != null)
		{
			predicates.add(builder.equal(fromTeste.get("testePadrao").get("assunto").get("id"), filtroTeste.getAssunto().getId()));
		}
		
		if(filtroTeste.getTestePadrao() != null)
			predicates.add(builder.equal(fromTeste.<TestePadrao>get("testePadrao").get("id"), filtroTeste.getTestePadrao().getId()));

		if(filtroTeste.getNome() != null && !filtroTeste.getNome().isBlank())
			predicates.add(builder.like(fromTeste.<Usuario>get("usuario").get("nome"), "%"+filtroTeste.getNome()+"%"));

		if(filtroTeste.getPeriodo() != null && !filtroTeste.getPeriodo().isEmpty())
		{
			predicates.add(builder.greaterThanOrEqualTo(fromTeste.get("realizacao"), filtroTeste.getPeriodo().get(0)));
			predicates.add(builder.lessThanOrEqualTo(fromTeste.get("realizacao"), filtroTeste.getPeriodo().get(1)));
		}

		if(filtroTeste.getNota() != null)
			predicates.add(builder.equal(fromTeste.get("nota"), filtroTeste.getNota()));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicates.toArray(new Predicate[0])));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Teste> testesRealizados(int diasRemoverTesteRealizado)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromTeste.get("realizado"), true));

		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverTesteRealizado);
		
		predicates.add(builder.lessThanOrEqualTo(fromTeste.get("realizacao"), limite));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicates.toArray(new Predicate[0])));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Teste> testesNaoRealizados(int diasRemoverTesteNaoRealizado)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> fromTeste = query.from(Teste.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromTeste.get("realizado"), false));
		predicates.add(builder.equal(fromTeste.get("tempo"), 0));
		predicates.add(builder.equal(fromTeste.get("repetirAtePassar"), false));
		
		LocalDate hoje=LocalDate.now();
		LocalDate limite = hoje.minusDays(diasRemoverTesteNaoRealizado);
		
		predicates.add(builder.lessThanOrEqualTo(fromTeste.get("realizacao"), limite));

		TypedQuery<Teste> typedQuery = em.createQuery(query.select(fromTeste).where(predicates.toArray(new Predicate[0])));
		List<Teste> list = typedQuery.getResultList();

		return list;
	}

}
