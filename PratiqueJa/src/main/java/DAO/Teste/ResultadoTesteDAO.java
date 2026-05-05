package DAO.Teste;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Bean.Teste.Filtro.FiltroResultadoTeste;
import DAO.DAO;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Teste.ResultadoTeste;
import Modelo.Usuario.Usuario;

public class ResultadoTesteDAO extends DAO<ResultadoTeste>
{
	private static final long serialVersionUID = 1L;

	public ResultadoTesteDAO()
	{
		super(ResultadoTeste.class);
	}

	public Double melhorResultado(AssuntoCurso assuntoCurso, Usuario usuario)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Double> query = builder.createQuery(Double.class);
		Root<ResultadoTeste> fromResultadoTeste = query.from(ResultadoTeste.class);

		Predicate predicate = builder.and();
		
		if(assuntoCurso!=null)
		{
			predicate = builder.and(predicate, 
			builder.equal(fromResultadoTeste.get("testePadrao").get("assuntoCurso").get("id"), assuntoCurso.getId()));
		}
		
		if(usuario!=null)
		{
			predicate = builder.and(predicate, 
			builder.equal(fromResultadoTeste.get("usuario").get("id"), usuario.getId()));
		}
		
		query.select(builder.max(fromResultadoTeste.<Double>get("nota"))).where(predicate);
		List<Double> list = em.createQuery(query).getResultList();
		double maiorNota=0;

		if(list!=null&&list.size()>0&&list.get(0)!=null)
			maiorNota= list.get(0);
		
		return maiorNota;
	}

	public List<ResultadoTeste> buscar(FiltroResultadoTeste filtroResultadoTeste)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoTeste> query = builder.createQuery(ResultadoTeste.class);
		Root<ResultadoTeste> fromResultadoTeste = query.from(ResultadoTeste.class);

		Predicate predicate = builder.and();

		if(filtroResultadoTeste.getUsuario() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromResultadoTeste.get("usuario").get("id"), filtroResultadoTeste.getUsuario().getId()));
		}
		
		if(filtroResultadoTeste.getNomeUsuario() != null && !filtroResultadoTeste.getNomeUsuario().equals(""))
		{

			predicate = builder.and(predicate,
			builder.like(fromResultadoTeste.<Usuario>get("usuario").get("nome"), "%" + filtroResultadoTeste.getNomeUsuario() + "%"));
		}

		if(filtroResultadoTeste.getAssuntoCurso() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromResultadoTeste.get("testePadrao").get("assuntoCurso").get("id"), filtroResultadoTeste.getAssuntoCurso().getId()));
		}

		if(filtroResultadoTeste.getModulo() != null)
		{
			predicate = builder.and(predicate,
			builder.equal(fromResultadoTeste.get("testePadrao").get("assuntoCurso").get("modulo"), filtroResultadoTeste.getModulo()));
		}

		if(filtroResultadoTeste.getInicioRealizacao() != null)
		{
			predicate = builder.and(predicate,
			builder.greaterThanOrEqualTo(fromResultadoTeste.get("realizacao"), filtroResultadoTeste.getInicioRealizacao()));
		}

		if(filtroResultadoTeste.getTerminoRealizacao() != null)
		{
			predicate = builder.and(predicate,
			builder.lessThanOrEqualTo(fromResultadoTeste.get("realizacao"), filtroResultadoTeste.getTerminoRealizacao()));
		}

		TypedQuery<ResultadoTeste> typedQuery = em.createQuery(query.select(fromResultadoTeste).where(predicate)
		.orderBy(builder.desc(fromResultadoTeste.get("realizacao"))));
		List<ResultadoTeste> list = typedQuery.getResultList();

		return list;
	}
}
