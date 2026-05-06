package dao;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import infra.Log;
import modelo.Entidade;
import modelo.configuracao.Opcao;

public abstract class DAO<T extends Entidade> implements Serializable,DAOInterface<T>
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
//		System.out.println("salvando entidade: " + entidade);
		em.persist(entidade);
	}
	
	@Transactional
	public T salvar(T entidade)
	{
		Log.escrever("salvou entidade: "+entidade);
		if(entidade.getId() == null)
			em.persist(entidade);
		else
			return em.merge(entidade);
		return null;
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
		Log.escrever("removido com sucesso: "+entidade);
	}

	@Transactional
	public T refresh(T entidade)
	{
		if(!em.contains(entidade))
			entidade = em.find(classe, entidade.getId());

		em.refresh(entidade);
		System.out.println("depois do refresh no DAO: " + entidade);
		return entidade;
	}

	public T carrega(Serializable id)
	{
		T entidade = em.find(classe, id);
		return entidade;
	}

	public void destacar(Object entidade)
	{
		em.detach(entidade);
	}

	public void clear()
	{
		em.clear();
	}

	public boolean contains(Opcao entidade)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		Root<T> from = query.from(classe);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(from.get("nome"), entidade.getNome()));

		if(entidade.getId() != null)
		{
			predicate = builder.and(predicate, builder.notEqual(from.get("id"), entidade.getId()));
		}

		TypedQuery<T> typedQuery = em.createQuery(query.select(from).where(predicate));
		List<T> list = typedQuery.getResultList();

		if(list.size() > 0)
			return true;

		return false;
	}

	public List<T> listaTudo()
	{
		em.clear();
		String hql = "Select e from " + classe.getName() + " e";
		@SuppressWarnings("unchecked")
		List<T> list = em.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<T> listaTudoOpcao()
	{
		em.clear();
		String hql = "Select e from " + classe.getName() + " e ORDER BY e.nome ASC";
		List<T> list = em.createQuery(hql).getResultList();
		return list;
	}

}
