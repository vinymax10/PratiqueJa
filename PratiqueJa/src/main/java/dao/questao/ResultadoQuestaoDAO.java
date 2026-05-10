package dao.questao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.questao.Questao;
import modelo.questao.ResultadoQuestao;
import modelo.usuario.Usuario;

public class ResultadoQuestaoDAO extends DAO<ResultadoQuestao>
{
	private static final long serialVersionUID = 1L;

	public ResultadoQuestaoDAO()
	{
		super(ResultadoQuestao.class);
	}

	public List<ResultadoQuestao> questoesRealizados(Questao questao, Usuario usuario)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoQuestao> query = builder.createQuery(ResultadoQuestao.class);
		Root<ResultadoQuestao> fromResultadoQuestao = query.from(ResultadoQuestao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(questao != null)
		{
			predicates.add(builder.equal(fromResultadoQuestao.<Questao>get("questao").get("id"), questao.getId()));
		}

		if(usuario != null)
		{
			predicates.add(builder.equal(fromResultadoQuestao.<Usuario>get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<ResultadoQuestao> typedQuery = em.createQuery(query.select(fromResultadoQuestao).where(predicates.toArray(new Predicate[0])));
		List<ResultadoQuestao> list = typedQuery.getResultList();

		return list;
	}

}
