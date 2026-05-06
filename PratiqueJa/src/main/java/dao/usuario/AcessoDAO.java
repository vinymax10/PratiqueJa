package dao.usuario;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.usuario.Acesso;
import modelo.usuario.Usuario;
import bean.usuario.filtro.FiltroAcesso;

public class AcessoDAO extends DAO<Acesso>
{
	private static final long serialVersionUID = 1L;

	public AcessoDAO()
	{
		super(Acesso.class);
	}

	public Acesso lastAcesso(String idSessao)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		Predicate predicate = builder.and();

		Acesso acesso = null;

		predicate = builder.and(predicate, builder.equal(fromAcesso.get("finalizado"), false));
		predicate = builder.and(predicate, builder.equal(fromAcesso.get("idSessao"), idSessao));

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicate));
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
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		Predicate predicate = builder.and();

		if(filtroAcesso.getNomeUsuario() != null && !filtroAcesso.getNomeUsuario().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromAcesso.<Usuario>get("usuario").get("nome"), "%" + filtroAcesso.getNomeUsuario() + "%"));
		}

		if(filtroAcesso.getIdSessao() != null && !filtroAcesso.getIdSessao().equals(""))
		{
			predicate = builder.and(predicate, builder.equal(fromAcesso.get("idSessao"), filtroAcesso.getIdSessao()));
		}

		if(filtroAcesso.getFinalizado() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAcesso.get("finalizado"), filtroAcesso.getFinalizado().booleanValue()));
		}

		if(filtroAcesso.getInicio() != null)
		{
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromAcesso.get("data"), filtroAcesso.getInicio()));
		}

		if(filtroAcesso.getTermino() != null)
		{
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromAcesso.get("data"), filtroAcesso.getTermino()));
		}

		if(filtroAcesso.getMinutosMinimo() != 0)
		{
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromAcesso.get("minutos"), filtroAcesso.getMinutosMinimo()));
		}

		if(filtroAcesso.getMinutosMaximo() != 0)
		{
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromAcesso.get("minutos"), filtroAcesso.getMinutosMaximo()));
		}

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicate));
		List<Acesso> list = typedQuery.getResultList();

		return list;
	}

	public List<Acesso> acessosAtivos()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromAcesso.get("finalizado"), false));

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicate));
		List<Acesso> listaUsuario = typedQuery.getResultList();

		return listaUsuario;
	}

}
