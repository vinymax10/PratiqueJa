package dao.seguranca;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.seguranca.Acesso;
import modelo.seguranca.StatusAcesso;
import modelo.usuario.Usuario;
import filtro.seguranca.FiltroAcesso;

public class AcessoDAO extends DAO<Acesso>
{
	private static final long serialVersionUID = 1L;

	public AcessoDAO()
	{
		super(Acesso.class);
	}

	public Acesso lastAcesso(String idSessao)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		List<Predicate> predicates = new ArrayList<>();

		Acesso acesso = null;

		predicates.add(builder.equal(fromAcesso.get("status"), StatusAcesso.ATIVO));
		predicates.add(builder.equal(fromAcesso.get("idSessao"), idSessao));

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicates.toArray(new Predicate[0])));
		try
		{
			List<Acesso> listaAcesso = typedQuery.getResultList();
			if(listaAcesso.size() > 0)
				acesso = typedQuery.getResultList().get(0);
		}
		catch(NoResultException e)
		{
			return null;
		}
		return acesso;
	}

	public List<Acesso> buscar(FiltroAcesso filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNomeUsuario() != null && !filtro.getNomeUsuario().isBlank())
		{
			predicates.add(builder.like(fromAcesso.<Usuario>get("usuario").get("nome"), "%" + filtro.getNomeUsuario() + "%"));
		}

		if(filtro.getIdSessao() != null && !filtro.getIdSessao().isBlank())
		{
			predicates.add(builder.equal(fromAcesso.get("idSessao"), filtro.getIdSessao()));
		}

		if(filtro.getPeriodo() != null && !filtro.getPeriodo().isEmpty())
		{
			LocalDateTime inicioPeriodo = filtro.getPeriodo().get(0).atStartOfDay();
			LocalDateTime terminoPeriodo = filtro.getPeriodo().size() > 1
				? filtro.getPeriodo().get(1).atTime(23, 59, 59)
				: filtro.getPeriodo().get(0).atTime(23, 59, 59);

			Predicate inicioNoPeriodo = builder.between(fromAcesso.get("inicio"), inicioPeriodo, terminoPeriodo);
			Predicate terminoNoPeriodo = builder.between(fromAcesso.get("termino"), inicioPeriodo, terminoPeriodo);

			predicates.add(builder.or(inicioNoPeriodo, terminoNoPeriodo));
		}

		if(filtro.getMinutosMinimo() != null)
		{
			predicates.add(builder.greaterThanOrEqualTo(fromAcesso.get("duracao"), (long) filtro.getMinutosMinimo() * 60));
		}

		if(filtro.getMinutosMaximo() != null)
		{
			predicates.add(builder.lessThanOrEqualTo(fromAcesso.get("duracao"), (long) filtro.getMinutosMaximo() * 60));
		}

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicates.toArray(new Predicate[0])));

		return typedQuery.getResultList();
	}

}
