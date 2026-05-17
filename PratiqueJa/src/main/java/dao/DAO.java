package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.ClasseAux;
import filtro.configuracao.FiltroConfig;
import infra.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import modelo.Entidade;

public abstract class DAO<T extends Entidade> implements Serializable, DAOInterface<T>
{
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "PratiqueJa")
	protected EntityManager em;

	private Class<T> classe;

	public DAO(Class<T> classe)
	{
		this.classe = classe;
	}

	@Transactional
	public void adicionar(T entidade)
	{
		em.persist(entidade);
	}

	@Transactional
	public T salvar(T entidade)
	{
		Log.escrever("salvou entidade: " + entidade);
		if(entidade.getId() == null)
		{
			em.persist(entidade);
			return entidade;
		}
		return em.merge(entidade);
	}

	@Transactional
	public void salvarSL(T entidade)
	{
		if(entidade.getId() == null)
			em.persist(entidade);
		else
			em.merge(entidade);
	}

	@Transactional
	public void remover(T entidade)
	{
		if(!em.contains(entidade))
			entidade = em.find(classe, entidade.getId());

		em.remove(entidade);
		Log.escrever("removido com sucesso: " + entidade);
	}

	@Transactional
	public T refresh(T entidade)
	{
		if(!em.contains(entidade))
			entidade = em.find(classe, entidade.getId());

		em.refresh(entidade);
		return entidade;
	}

	public T carrega(Serializable id)
	{
		return em.find(classe, id);
	}

	public void destacar(Object entidade)
	{
		em.detach(entidade);
	}

	public void clear()
	{
	}

	public List<T> buscar(FiltroConfig filtroConfig)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		Root<T> fromT = query.from(classe);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroConfig.getNome() != null && !filtroConfig.getNome().isBlank())
			predicates.add(builder.like(
				fromT.get("nome"), "%" + filtroConfig.getNome() + "%"));

		if(filtroConfig.getAtivo() != null)
			predicates.add(builder.equal(
				fromT.get("ativo"), filtroConfig.getAtivo().booleanValue()));

		TypedQuery<T> typedQuery = em
			.createQuery(query.select(fromT)
			.where(predicates.toArray(new Predicate[0]))
			.orderBy(builder.asc(fromT.get("ordem"))));

		return typedQuery.getResultList();
	}

	public List<T> listarTudo()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		Root<T> fromT = query.from(classe);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<T> typedQuery;
		if(ClasseAux.possuiAtributo(classe, "ordem"))
		{
			typedQuery = em
				.createQuery(query.select(fromT)
				.where(predicates.toArray(new Predicate[0]))
				.orderBy(builder.asc(fromT.get("ordem"))));
		}
		else
			typedQuery = em
				.createQuery(query.select(fromT)
				.where(predicates.toArray(new Predicate[0])));

		return typedQuery.getResultList();
	}

	public List<T> listarOpcoesAtivas()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		Root<T> fromT = query.from(classe);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromT.get("ativo"), true));

		TypedQuery<T> typedQuery;
		if(ClasseAux.possuiAtributo(classe, "ordem"))
		{
			typedQuery = em
				.createQuery(query.select(fromT)
				.where(predicates.toArray(new Predicate[0]))
				.orderBy(builder.asc(fromT.get("ordem"))));
		}
		else
			typedQuery = em
				.createQuery(query.select(fromT)
				.where(predicates.toArray(new Predicate[0])));

		return typedQuery.getResultList();
	}
}
