package dao.seguranca;

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
import filtro.usuario.FiltroAcesso;

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

	public List<Acesso> buscar(FiltroAcesso filtroAcesso)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroAcesso.getNomeUsuario() != null && !filtroAcesso.getNomeUsuario().isBlank())
		{
			predicates.add(builder.like(fromAcesso.<Usuario>get("usuario").get("nome"), "%" + filtroAcesso.getNomeUsuario() + "%"));
		}

		if(filtroAcesso.getIdSessao() != null && !filtroAcesso.getIdSessao().isBlank())
		{
			predicates.add(builder.equal(fromAcesso.get("idSessao"), filtroAcesso.getIdSessao()));
		}

		if(filtroAcesso.getInicio() != null)
		{
			predicates.add(builder.greaterThanOrEqualTo(fromAcesso.get("data"), filtroAcesso.getInicio()));
		}

		if(filtroAcesso.getTermino() != null)
		{
			predicates.add(builder.lessThanOrEqualTo(fromAcesso.get("data"), filtroAcesso.getTermino()));
		}

		if(filtroAcesso.getMinutosMinimo() != 0)
		{
			predicates.add(builder.greaterThanOrEqualTo(fromAcesso.get("minutos"), filtroAcesso.getMinutosMinimo()));
		}

		if(filtroAcesso.getMinutosMaximo() != 0)
		{
			predicates.add(builder.lessThanOrEqualTo(fromAcesso.get("minutos"), filtroAcesso.getMinutosMaximo()));
		}

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicates.toArray(new Predicate[0])));
		List<Acesso> list = typedQuery.getResultList();

		return list;
	}

}
