package DAO.Teste;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Bean.Teste.Filtro.FiltroTestePadrao;
import DAO.DAO;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Teste.TestePadrao;
import Modelo.Usuario.Usuario;

public class TestePadraoDAO extends DAO<TestePadrao>
{
	private static final long serialVersionUID = 1L;

	public TestePadraoDAO()
	{
		super(TestePadrao.class);
	}

	public List<TestePadrao> buscar(FiltroTestePadrao filtroTestePadrao)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TestePadrao> query = builder.createQuery(TestePadrao.class);
		Root<TestePadrao> fromTestePadrao = query.from(TestePadrao.class);

		Predicate predicate = builder.and();

		if(filtroTestePadrao.getNome() != null && !filtroTestePadrao.getNome().equals(""))
			predicate = builder.and(predicate,
			builder.like(fromTestePadrao.get("nome"), "%" + filtroTestePadrao.getNome() + "%"));
		
		if(filtroTestePadrao.getAssuntoCurso() != null)
			predicate = builder.and(predicate,
			builder.equal(fromTestePadrao.<Usuario>get("assuntoCurso").get("id"), filtroTestePadrao.getAssuntoCurso().getId()));
		
		if(filtroTestePadrao.getNotaMinima() != null)
			predicate = builder.and(predicate,
			builder.equal(fromTestePadrao.<Usuario>get("notaMinima"), filtroTestePadrao.getNotaMinima()));

		if(filtroTestePadrao.getDuracao() != null)
			predicate = builder.and(predicate, builder.equal(fromTestePadrao.<ExercicioPadrao>get("duracao"),
			filtroTestePadrao.getDuracao()));

		TypedQuery<TestePadrao> typedQuery = em
		.createQuery(query.select(fromTestePadrao).where(predicate).distinct(true));
		List<TestePadrao> list = typedQuery.getResultList();

		return list;
	}
	
	public List<TestePadrao> listaTodos()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TestePadrao> query = builder.createQuery(TestePadrao.class);
		Root<TestePadrao> fromTestePadrao = query.from(TestePadrao.class);

		Predicate predicate = builder.and();

		TypedQuery<TestePadrao> typedQuery = em
		.createQuery(query.select(fromTestePadrao).where(predicate).distinct(true));
		List<TestePadrao> list = typedQuery.getResultList();

		return list;
	}

}
