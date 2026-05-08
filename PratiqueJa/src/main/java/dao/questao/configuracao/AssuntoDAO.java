package dao.questao.configuracao;

import java.util.List;

import dao.DAO;
import filtro.FiltroAssunto;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.questao.configuracao.Assunto;

public class AssuntoDAO extends DAO<Assunto>
{
	private static final long serialVersionUID = 1L;

	public AssuntoDAO()
	{
		super(Assunto.class);
	}

	public List<Assunto> buscar(FiltroAssunto filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		Predicate predicate = builder.and();

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicate = builder.and(predicate, builder.like(
				builder.upper(fromAssunto.get("nome")), "%" + filtro.getNome().toUpperCase() + "%"));

		if(filtro.getDisciplina() != null)
			predicate = builder.and(predicate, builder.equal(
				fromAssunto.get("disciplina"), filtro.getDisciplina()));

		if(filtro.getAtivo() != null)
			predicate = builder.and(predicate, builder.equal(
				fromAssunto.get("ativo"), filtro.getAtivo()));

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicate).distinct(true));
		return typedQuery.getResultList();
	}
	
	public List<Assunto> filtrar(String nome)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromAssunto.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicate).distinct(true));
		List<Assunto> list = typedQuery.getResultList();

		return list;
	}
}
