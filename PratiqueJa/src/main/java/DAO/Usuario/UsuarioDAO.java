package DAO.Usuario;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import Bean.Usuario.Filtro.FiltroUsuario;
import DAO.DAO;
import Modelo.Usuario.Acesso;
import Modelo.Usuario.Usuario;

public class UsuarioDAO extends DAO<Usuario>
{
	private static final long serialVersionUID = 1L;

	public UsuarioDAO()
	{
		super(Usuario.class);
	}

	public Usuario getUsuario(String email)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		Predicate predicate = builder.and();

		Usuario usuario = null;
		if(!email.equals(""))
		{
			predicate = builder.and(predicate, builder.equal(fromUsuario.get("email"), email));
			TypedQuery<Usuario> typedQuery = em.createQuery(query.select(fromUsuario).where(predicate).distinct(true));
			try
			{
				usuario = typedQuery.getSingleResult();
			}
			catch(NoResultException e)
			{
				return null;
			}
		}
		return usuario;
	}

	public Usuario getUsuarioGoogle(String sub)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		Predicate predicate = builder.and();

		Usuario usuario = null;
		if(!sub.equals(""))
		{
			predicate = builder.and(predicate, builder.equal(fromUsuario.get("subGoogle"), sub));
			TypedQuery<Usuario> typedQuery = em.createQuery(query.select(fromUsuario).where(predicate).distinct(true));
			try
			{
				usuario = typedQuery.getSingleResult();
			}
			catch(NoResultException e)
			{
				return null;
			}
		}
		return usuario;
	}

	public List<Usuario> buscar(FiltroUsuario filtroUsuario)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		Predicate predicate = builder.and();

		if(filtroUsuario.getNome()!=null && !filtroUsuario.getNome().equals(""))
			predicate = builder.and(predicate, builder.like(fromUsuario.get("nome"), "%" + filtroUsuario.getNome() + "%"));

		if(filtroUsuario.getEmail()!=null && !filtroUsuario.getEmail().equals(""))
			predicate = builder.and(predicate, builder.like(fromUsuario.get("email"), "%" + filtroUsuario.getEmail() + "%"));

		if(filtroUsuario.getTurma() !=null)
			predicate = builder.and(predicate, builder.equal(fromUsuario.get("turma").get("id"), filtroUsuario.getTurma().getId() ));
		
		if(filtroUsuario.getPerfil() !=null)
			predicate = builder.and(predicate, builder.equal(fromUsuario.get("perfil"), filtroUsuario.getPerfil()));

		if(filtroUsuario.getCriador() !=null)
			predicate = builder.and(predicate, builder.equal(fromUsuario.get("criador"), filtroUsuario.getCriador() ));
	
		if(filtroUsuario.getRecebeSpam() !=null)
			predicate = builder.and(predicate, builder.equal(fromUsuario.get("recebeSpam"), filtroUsuario.getRecebeSpam() ));
		
		TypedQuery<Usuario> typedQuery = em.createQuery(query.select(fromUsuario).where(predicate).distinct(true));
		List<Usuario> list = typedQuery.getResultList();

		return list;
	}

	public List<Acesso> listaAcessos(Usuario usuario, LocalDate inicio, LocalDate fim)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		Predicate predicate = builder.and();

		if(usuario != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAcesso.get("usuario").get("id"), usuario.getId()));

			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromAcesso.get("data"), fim));

			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromAcesso.get("data"), inicio));
		}

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicate).distinct(true));
		List<Acesso> list = typedQuery.getResultList();

		return list;
	}

}
