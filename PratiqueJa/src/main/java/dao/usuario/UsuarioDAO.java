package dao.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import dao.DAO;
import modelo.seguranca.Acesso;
import modelo.usuario.Usuario;
import filtro.usuario.FiltroUsuario;

public class UsuarioDAO extends DAO<Usuario>
{
	private static final long serialVersionUID = 1L;

	public UsuarioDAO()
	{
		super(Usuario.class);
	}

	public Usuario getUsuario(String email, String sub)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		List<Predicate> predicates = new ArrayList<>();
		
		Predicate pEntidade1 = null;
		Predicate pEntidade2 = null;

		if(email != null && !email.isBlank())
			pEntidade1 = builder.equal(fromUsuario.get("email"), email);

		if(sub != null && !sub.isBlank())
			pEntidade2 = builder.equal(fromUsuario.get("subGoogle"), sub);

		if(pEntidade1 != null && pEntidade2 != null)
			predicates.add(builder.or(pEntidade1, pEntidade2));
		else if(pEntidade1 != null)
			predicates.add(pEntidade1);
		else if(pEntidade2 != null)
			predicates.add(pEntidade2);
		
		TypedQuery<Usuario> typedQuery = em
		.createQuery(query.select(fromUsuario)
		.where(predicates.toArray(new Predicate[0])));
		List<Usuario> list = typedQuery.getResultList();

		if(list.size()==1)
			return list.get(0);
		
		return null;
	}

	public Usuario getUsuarioGoogle(String sub)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		List<Predicate> predicates = new ArrayList<>();

		Usuario usuario = null;
		if(sub != null && !sub.isBlank())
		{
			predicates.add(builder.equal(fromUsuario.get("subGoogle"), sub));
			TypedQuery<Usuario> typedQuery = em.createQuery(query.select(fromUsuario).where(predicates.toArray(new Predicate[0])).distinct(true));
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

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroUsuario.getNome()!=null && !filtroUsuario.getNome().isBlank())
			predicates.add(builder.like(fromUsuario.get("nome"), "%" + filtroUsuario.getNome() + "%"));

		if(filtroUsuario.getEmail()!=null && !filtroUsuario.getEmail().isBlank())
			predicates.add(builder.like(fromUsuario.get("email"), "%" + filtroUsuario.getEmail() + "%"));

		if(filtroUsuario.getTurma() !=null)
			predicates.add(builder.equal(fromUsuario.get("turma").get("id"), filtroUsuario.getTurma().getId() ));
		
		if(filtroUsuario.getPerfil() !=null)
			predicates.add(builder.equal(fromUsuario.get("perfil"), filtroUsuario.getPerfil()));

		if(filtroUsuario.getCriador() !=null)
			predicates.add(builder.equal(fromUsuario.get("criador"), filtroUsuario.getCriador() ));

		TypedQuery<Usuario> typedQuery = em.createQuery(query.select(fromUsuario).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Usuario> list = typedQuery.getResultList();

		return list;
	}

	/** Usuários com plano de avaliações (assinantes), para o processamento mensal do rollover. */
	public List<Usuario> listarComPerfilAvaliacao()
	{
		return em.createQuery(
			"SELECT u FROM Usuario u WHERE u.perfilAvaliacao IS NOT NULL", Usuario.class)
			.getResultList();
	}

	/** Grava o crédito de rollover do mês e o marcador de continuidade da assinatura. */
	@Transactional
	public void atualizarRollover(Long usuarioId, int credito, LocalDate mesProcessado)
	{
		Usuario usuario = em.find(Usuario.class, usuarioId);
		if(usuario != null)
		{
			usuario.setCreditoRollover(credito);
			usuario.setMesRolloverProcessado(mesProcessado);
		}
	}

	/** Usuários criadores de conteúdo (com config de posts), para o rollover mensal de créditos de post. */
	public List<Usuario> listarCriadores()
	{
		return em.createQuery(
			"SELECT u FROM Usuario u JOIN FETCH u.configPost WHERE u.configPost IS NOT NULL", Usuario.class)
			.getResultList();
	}

	/** Grava o crédito de rollover de post do mês e o marcador de continuidade. */
	@Transactional
	public void atualizarRolloverPost(Long usuarioId, int credito, LocalDate mesProcessado)
	{
		Usuario usuario = em.find(Usuario.class, usuarioId);
		if(usuario != null)
		{
			usuario.setCreditoRolloverPost(credito);
			usuario.setMesRolloverPostProcessado(mesProcessado);
		}
	}

	public List<Acesso> listaAcessos(Usuario usuario, LocalDate inicio, LocalDate fim)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
		Root<Acesso> fromAcesso = query.from(Acesso.class);

		List<Predicate> predicates = new ArrayList<>();

		if(usuario != null)
		{
			predicates.add(builder.equal(fromAcesso.get("usuario").get("id"), usuario.getId()));

			predicates.add(builder.greaterThanOrEqualTo(
				fromAcesso.<LocalDateTime>get("inicio"), inicio.atStartOfDay()));

			predicates.add(builder.lessThanOrEqualTo(
				fromAcesso.<LocalDateTime>get("inicio"), fim.atTime(23, 59, 59)));
		}

		TypedQuery<Acesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Acesso> list = typedQuery.getResultList();

		return list;
	}

}
