package dao.teste;

import java.util.ArrayList;
import java.util.List;

import filtro.teste.FiltroResultadoTeste;
import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.assuntocurso.AssuntoCurso;
import modelo.teste.ResultadoTeste;
import modelo.usuario.Usuario;

public class ResultadoTesteDAO extends DAO<ResultadoTeste>
{
	private static final long serialVersionUID = 1L;

	public ResultadoTesteDAO()
	{
		super(ResultadoTeste.class);
	}

	public Double melhorResultado(AssuntoCurso assuntoCurso, Usuario usuario)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Double> query = builder.createQuery(Double.class);
		Root<ResultadoTeste> fromResultadoTeste = query.from(ResultadoTeste.class);

		List<Predicate> predicates = new ArrayList<>();
		
		if(assuntoCurso!=null)
		{
			predicates.add(builder.equal(fromResultadoTeste.get("testePadrao").get("assuntoCurso").get("id"), assuntoCurso.getId()));
		}
		
		if(usuario!=null)
		{
			predicates.add(builder.equal(fromResultadoTeste.get("usuario").get("id"), usuario.getId()));
		}
		
		query.select(builder.max(fromResultadoTeste.<Double>get("nota"))).where(predicates.toArray(new Predicate[0]));
		List<Double> list = em.createQuery(query).getResultList();
		double maiorNota=0;

		if(list!=null&&list.size()>0&&list.get(0)!=null)
			maiorNota= list.get(0);
		
		return maiorNota;
	}

	public List<ResultadoTeste> buscar(FiltroResultadoTeste filtroResultadoTeste)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoTeste> query = builder.createQuery(ResultadoTeste.class);
		Root<ResultadoTeste> fromResultadoTeste = query.from(ResultadoTeste.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroResultadoTeste.getUsuario() != null)
		{
			predicates.add(builder.equal(fromResultadoTeste.get("usuario").get("id"), filtroResultadoTeste.getUsuario().getId()));
		}
		
		if(filtroResultadoTeste.getNomeUsuario() != null && !filtroResultadoTeste.getNomeUsuario().isBlank())
		{

			predicates.add(builder.like(fromResultadoTeste.<Usuario>get("usuario").get("nome"), "%" + filtroResultadoTeste.getNomeUsuario() + "%"));
		}

		if(filtroResultadoTeste.getAssuntoCurso() != null)
		{
			predicates.add(builder.equal(fromResultadoTeste.get("testePadrao").get("assuntoCurso").get("id"), filtroResultadoTeste.getAssuntoCurso().getId()));
		}

		if(filtroResultadoTeste.getModulo() != null)
		{
			predicates.add(builder.equal(fromResultadoTeste.get("testePadrao").get("assuntoCurso").get("modulo"), filtroResultadoTeste.getModulo()));
		}

		if(filtroResultadoTeste.getInicioRealizacao() != null)
		{
			predicates.add(builder.greaterThanOrEqualTo(fromResultadoTeste.get("realizacao"), filtroResultadoTeste.getInicioRealizacao()));
		}

		if(filtroResultadoTeste.getTerminoRealizacao() != null)
		{
			predicates.add(builder.lessThanOrEqualTo(fromResultadoTeste.get("realizacao"), filtroResultadoTeste.getTerminoRealizacao()));
		}

		TypedQuery<ResultadoTeste> typedQuery = em.createQuery(query.select(fromResultadoTeste).where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.desc(fromResultadoTeste.get("realizacao"))));
		List<ResultadoTeste> list = typedQuery.getResultList();

		return list;
	}
}
