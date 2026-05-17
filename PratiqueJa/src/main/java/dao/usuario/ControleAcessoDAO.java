package dao.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.usuario.ControleAcesso;
import modelo.usuario.Usuario;
import filtro.usuario.FiltroControleAcesso;

public class ControleAcessoDAO extends DAO<ControleAcesso>
{
	private static final long serialVersionUID = 1L;

	public ControleAcessoDAO()
	{
		super(ControleAcesso.class);
	}

	public ControleAcesso controleAcessoMes(Usuario usuario)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ControleAcesso> query = builder.createQuery(ControleAcesso.class);
		Root<ControleAcesso> fromControleAcesso = query.from(ControleAcesso.class);

		List<Predicate> predicates = new ArrayList<>();

		LocalDate primeiroDiaMes = LocalDate.now().withDayOfMonth(1);
		predicates.add(builder.equal(fromControleAcesso.get("data"), primeiroDiaMes));
		predicates.add(builder.equal(fromControleAcesso.get("usuario").get("id"), usuario.getId()));

		TypedQuery<ControleAcesso> typedQuery = em.createQuery(query.select(fromControleAcesso)
		.where(predicates.toArray(new Predicate[0])));
		List<ControleAcesso> list = typedQuery.getResultList();

		return list.isEmpty() ? null : list.get(0);
	}
	
	public List<ControleAcesso> buscar(FiltroControleAcesso filtro)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ControleAcesso> query = builder.createQuery(ControleAcesso.class);
		Root<ControleAcesso> fromAcesso = query.from(ControleAcesso.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNomeUsuario() != null && !filtro.getNomeUsuario().isBlank())
		{
			predicates.add(builder.like(fromAcesso.<Usuario>get("usuario").get("nome"), "%" + filtro.getNomeUsuario() + "%"));
		}

		if(filtro.getPeriodo() != null && !filtro.getPeriodo().isEmpty())
		{
			predicates.add(builder.greaterThanOrEqualTo(
				fromAcesso.get("data"), filtro.getPeriodo().get(0)));

			if(filtro.getPeriodo().size() > 1)
				predicates.add(builder.lessThanOrEqualTo(
					fromAcesso.get("data"), filtro.getPeriodo().get(1)));
		}

		TypedQuery<ControleAcesso> typedQuery = em.createQuery(query.select(fromAcesso).where(predicates.toArray(new Predicate[0])));
		List<ControleAcesso> list = typedQuery.getResultList();

		return list;
	}
	
	public List<ControleAcesso> listaControleAcesso(Usuario usuario, LocalDate inicio, LocalDate fim)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ControleAcesso> query = builder.createQuery(ControleAcesso.class);
		Root<ControleAcesso> fromControleAcesso = query.from(ControleAcesso.class);

		List<Predicate> predicates = new ArrayList<>();

		if(usuario != null)
		{
			predicates.add(builder.equal(fromControleAcesso.get("usuario").get("id"), usuario.getId()));

			predicates.add(builder.lessThanOrEqualTo(fromControleAcesso.get("data"), fim));

			predicates.add(builder.greaterThanOrEqualTo(fromControleAcesso.get("data"), inicio));
		}

		TypedQuery<ControleAcesso> typedQuery = em.createQuery(query.select(fromControleAcesso).where(predicates.toArray(new Predicate[0])));
		List<ControleAcesso> list = typedQuery.getResultList();

		return list;
	}
}
