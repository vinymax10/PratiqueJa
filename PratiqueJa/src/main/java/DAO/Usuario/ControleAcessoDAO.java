package DAO.Usuario;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Bean.Usuario.Filtro.FiltroControleAcesso;
import DAO.DAO;
import Modelo.Usuario.ControleAcesso;
import Modelo.Usuario.Usuario;

public class ControleAcessoDAO extends DAO<ControleAcesso>
{
	private static final long serialVersionUID = 1L;

	public ControleAcessoDAO()
	{
		super(ControleAcesso.class);
	}

	public ControleAcesso controleAcessoHoje(Usuario usuario)
	{
		em.clear();
		
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ControleAcesso> query = builder.createQuery(ControleAcesso.class);
		Root<ControleAcesso> fromControleAcesso = query.from(ControleAcesso.class);
		
		Predicate predicate = builder.and();
		
		LocalDate hoje=LocalDate.now();
		predicate = builder.and(predicate, builder.equal(fromControleAcesso.get("data"), hoje));
		
		predicate = builder.and(predicate, builder.equal(fromControleAcesso.get("usuario").get("id"), usuario.getId()));

		TypedQuery<ControleAcesso> typedQuery = em.createQuery(query.select(fromControleAcesso)
		.where(predicate));
		List<ControleAcesso> list = typedQuery.getResultList();
		
		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
	
	public List<ControleAcesso> buscar(FiltroControleAcesso filtroControleAcesso)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ControleAcesso> query = builder.createQuery(ControleAcesso.class);
		Root<ControleAcesso> fromAcesso = query.from(ControleAcesso.class);

		Predicate predicate = builder.and();

		if(filtroControleAcesso.getNomeUsuario() != null && !filtroControleAcesso.getNomeUsuario().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromAcesso.<Usuario>get("usuario").get("nome"), "%" + filtroControleAcesso.getNomeUsuario() + "%"));
		}

		if(filtroControleAcesso.getInicio() != null)
		{
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromAcesso.get("data"), filtroControleAcesso.getInicio()));
		}

		if(filtroControleAcesso.getTermino() != null)
		{
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromAcesso.get("data"), filtroControleAcesso.getTermino()));
		}

		TypedQuery<ControleAcesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicate));
		List<ControleAcesso> list = typedQuery.getResultList();

		return list;
	}
	
	public List<ControleAcesso> listaControleAcesso(Usuario usuario, LocalDate inicio, LocalDate fim)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ControleAcesso> query = builder.createQuery(ControleAcesso.class);
		Root<ControleAcesso> fromControleAcesso = query.from(ControleAcesso.class);

		Predicate predicate = builder.and();

		if(usuario != null)
		{
			predicate = builder.and(predicate, builder.equal(fromControleAcesso.get("usuario").get("id"), usuario.getId()));

			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromControleAcesso.get("data"), fim));

			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromControleAcesso.get("data"), inicio));
		}

		TypedQuery<ControleAcesso> typedQuery = em.createQuery(query.select(fromControleAcesso).where(predicate));
		List<ControleAcesso> list = typedQuery.getResultList();

		return list;
	}
}
