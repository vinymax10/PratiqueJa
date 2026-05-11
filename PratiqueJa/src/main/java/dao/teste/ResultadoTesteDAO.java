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

		if(assuntoCurso != null)
			predicates.add(builder.equal(fromResultadoTeste.get("testePadrao").get("assuntoCurso").get("id"), assuntoCurso.getId()));

		if(usuario != null)
			predicates.add(builder.equal(fromResultadoTeste.get("usuario").get("id"), usuario.getId()));

		query.select(builder.max(fromResultadoTeste.<Double>get("nota"))).where(predicates.toArray(new Predicate[0]));
		List<Double> list = em.createQuery(query).getResultList();

		if(list != null && list.size() > 0 && list.get(0) != null)
			return list.get(0);

		return 0d;
	}

	public List<ResultadoTeste> buscar(FiltroResultadoTeste filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoTeste> query = builder.createQuery(ResultadoTeste.class);
		Root<ResultadoTeste> from = query.from(ResultadoTeste.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getUsuario() != null)
			predicates.add(builder.equal(from.get("usuario").get("id"), filtro.getUsuario().getId()));

		if(filtro.getNomeUsuario() != null && !filtro.getNomeUsuario().isBlank())
			predicates.add(builder.like(from.<Usuario>get("usuario").get("nome"), "%" + filtro.getNomeUsuario() + "%"));

		if(filtro.getTestePadrao() != null)
			predicates.add(builder.equal(from.get("testePadrao").get("id"), filtro.getTestePadrao().getId()));

		if(filtro.getAssuntoCurso() != null)
			predicates.add(builder.equal(from.get("testePadrao").get("assuntoCurso").get("id"), filtro.getAssuntoCurso().getId()));

		if(filtro.getModulo() != null)
			predicates.add(builder.equal(from.get("testePadrao").get("assuntoCurso").get("modulo"), filtro.getModulo()));

		if(filtro.getPeriodo() != null && filtro.getPeriodo().size() == 2)
		{
			if(filtro.getPeriodo().get(0) != null)
				predicates.add(builder.greaterThanOrEqualTo(from.get("realizacao"), filtro.getPeriodo().get(0)));

			if(filtro.getPeriodo().get(1) != null)
				predicates.add(builder.lessThanOrEqualTo(from.get("realizacao"), filtro.getPeriodo().get(1)));
		}

		return em.createQuery(
			query.select(from)
			.where(predicates.toArray(new Predicate[0]))
			.orderBy(builder.desc(from.get("realizacao")))
		).getResultList();
	}
}
