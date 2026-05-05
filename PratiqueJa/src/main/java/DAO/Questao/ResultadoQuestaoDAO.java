package DAO.Questao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Questao.Questao;
import Modelo.Questao.ResultadoQuestao;
import Modelo.Usuario.Usuario;

public class ResultadoQuestaoDAO extends DAO<ResultadoQuestao>
{
	private static final long serialVersionUID = 1L;

	public ResultadoQuestaoDAO()
	{
		super(ResultadoQuestao.class);
	}

	public List<ResultadoQuestao> questoesRealizados(Questao questao, Usuario usuario)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoQuestao> query = builder.createQuery(ResultadoQuestao.class);
		Root<ResultadoQuestao> fromResultadoQuestao = query.from(ResultadoQuestao.class);

		Predicate predicate = builder.and();

		if(questao != null)
		{
			predicate = builder.and(predicate, builder.equal(fromResultadoQuestao.<Questao>get("questao").get("id"), questao.getId()));
		}

		if(usuario != null)
		{
			predicate = builder.and(predicate, builder.equal(fromResultadoQuestao.<Usuario>get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<ResultadoQuestao> typedQuery = em.createQuery(query.select(fromResultadoQuestao).where(predicate));
		List<ResultadoQuestao> list = typedQuery.getResultList();

		return list;
	}

}
