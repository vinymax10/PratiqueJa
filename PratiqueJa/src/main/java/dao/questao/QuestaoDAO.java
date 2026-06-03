package dao.questao;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.questao.FiltroQuestao;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import modelo.academico.Assunto;
import modelo.questao.Alternativa;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import modelo.questao.ResultadoQuestao;
import modelo.questao.TipoFiltro;
import modelo.usuario.Usuario;
import web.session.Sessao;

public class QuestaoDAO extends DAO<Questao>
{
	private static final long serialVersionUID = 1L;

	public QuestaoDAO()
	{
		super(Questao.class);
	}

	public List<Questao> buscaAssunto(Assunto assunto, TipoFiltro tipoFiltro, Usuario usuario)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Questao> query = builder.createQuery(Questao.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromQuestao.get("revisada"), true));
		predicates.add(builder.notEqual(fromQuestao.get("resolucao"),""));

		if(assunto != null)
		{
			Join<Questao, Assunto> assuntoJoin = fromQuestao.join("assuntos");
			predicates.add(builder.equal(assuntoJoin.get("id"), assunto.getId()));
		}

		if(tipoFiltro != null && usuario != null)
		{

			if(tipoFiltro == TipoFiltro.Repondidas)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicates.add(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicates.add(builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
//				predicate=builder.and(predicate,fromQuestao.in(resultadoQuestaoJoin));

			}
			else if(tipoFiltro == TipoFiltro.NaoRespondidas)
			{
				Subquery<Questao> questaoSubquery = query.subquery(Questao.class);
				Root<Questao> questaoFromSubquery = questaoSubquery.from(Questao.class);
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = questaoFromSubquery.join("resultadosQuestao");

				questaoSubquery.select(questaoFromSubquery).where(builder.and(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()),
				builder.equal(resultadoQuestaoJoin.get("questao").get("id"), questaoFromSubquery.get("id"))));

				predicates.add(builder.not(fromQuestao.in(questaoSubquery)));
			}
			else if(tipoFiltro == TipoFiltro.Aceitei)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicates.add(builder.equal(resultadoQuestaoJoin.get("acertou"), true));
				predicates.add(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicates.add(builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
			}
			else if(tipoFiltro == TipoFiltro.Errei)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicates.add(builder.equal(resultadoQuestaoJoin.get("acertou"), false));
				predicates.add(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicates.add(builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
			}
		}

//		fromQuestao.fetch("assuntos", JoinType.LEFT);
//		fromQuestao.fetch("ano");
//		fromQuestao.fetch("disciplina");
//		fromQuestao.fetch("banca");
//		fromQuestao.fetch("orgao");
//		fromQuestao.fetch("assunto");
//		
		TypedQuery<Questao> typedQuery = em
		.createQuery(query.select(fromQuestao).where(predicates.toArray(new Predicate[0])).distinct(true)
		.orderBy(builder.asc(fromQuestao.get("ordemInsercao"))));

		List<Questao> list = typedQuery.getResultList();

		return list;
	}

	public List<Questao> filtrar(FiltroQuestao filtroQuestao)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Questao> query = builder.createQuery(Questao.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroQuestao.getConteudo() != null && !filtroQuestao.getConteudo().isBlank())
		{
			Join<Questao, Paragrafo> paragrafoJoin = fromQuestao.join("paragrafos");
			predicates.add(builder.like(paragrafoJoin.get("texto"), "%" + filtroQuestao.getConteudo()+"%"));
		}
		
		if(filtroQuestao.getAnos() != null && !filtroQuestao.getAnos().isEmpty())
			predicates.add(fromQuestao.get("ano").in(filtroQuestao.getAnos()));
		else if(filtroQuestao.getAno() != null)
			predicates.add(builder.equal(fromQuestao.get("ano").get("id"), filtroQuestao.getAno().getId()));

		if(filtroQuestao.getBancas() != null && !filtroQuestao.getBancas().isEmpty())
			predicates.add(fromQuestao.get("banca").in(filtroQuestao.getBancas()));
		else if(filtroQuestao.getBanca() != null)
			predicates.add(builder.equal(fromQuestao.get("banca").get("id"), filtroQuestao.getBanca().getId()));

		if(filtroQuestao.getOrgaos() != null && !filtroQuestao.getOrgaos().isEmpty())
			predicates.add(fromQuestao.get("orgao").in(filtroQuestao.getOrgaos()));
		else if(filtroQuestao.getOrgao() != null)
			predicates.add(builder.equal(fromQuestao.get("orgao").get("id"), filtroQuestao.getOrgao().getId()));

		if(filtroQuestao.getDisciplina() != null)
			predicates.add(builder.equal(fromQuestao.get("disciplina").get("id"), filtroQuestao.getDisciplina().getId()));

		if((filtroQuestao.getAssuntos() != null && !filtroQuestao.getAssuntos().isEmpty()) || filtroQuestao.getAssunto() != null)
		{
			Join<Questao, Assunto> assuntoJoin = fromQuestao.join("assuntos");
			if(filtroQuestao.getAssuntos() != null && !filtroQuestao.getAssuntos().isEmpty())
				predicates.add(assuntoJoin.in(filtroQuestao.getAssuntos()));
			else
				predicates.add(builder.equal(assuntoJoin.get("id"), filtroQuestao.getAssunto().getId()));
		}

		if(filtroQuestao.getDificuldades() != null && !filtroQuestao.getDificuldades().isEmpty())
			predicates.add(fromQuestao.get("dificuldade").in(filtroQuestao.getDificuldades()));
		else if(filtroQuestao.getDificuldade() != null)
			predicates.add(builder.equal(fromQuestao.get("dificuldade"), filtroQuestao.getDificuldade()));

		if(filtroQuestao.getChave()!=null&&!filtroQuestao.getChave().isBlank())
		{
			predicates.add(builder.like(fromQuestao.<String>get("chave"), "%" + filtroQuestao.getChave() + "%"));
		}

		if(filtroQuestao.getOrdemInsercao() != 0)
		{
			predicates.add(builder.equal(fromQuestao.get("ordemInsercao"), filtroQuestao.getOrdemInsercao()));
		}

		if(filtroQuestao.getId() != null && filtroQuestao.getId() != 0)
		{
			predicates.add(builder.equal(fromQuestao.<Long>get("id"), filtroQuestao.getId()));
		}

		if(filtroQuestao.getRevisada() != null)
			predicates.add(builder.equal(fromQuestao.get("revisada"), filtroQuestao.getRevisada().booleanValue()));

		if(filtroQuestao.getMalFormulada() != null)
			predicates.add(builder.equal(fromQuestao.get("malFormulada"), filtroQuestao.getMalFormulada().booleanValue()));

		if(filtroQuestao.getResolucaoLatex() != null)
		{
			if(filtroQuestao.getResolucaoLatex().booleanValue())
				predicates.add(builder.and(
					builder.isNotNull(fromQuestao.get("resolucao")),
					builder.notEqual(builder.trim(fromQuestao.<String>get("resolucao")), "")));
			else
				predicates.add(builder.or(
					builder.isNull(fromQuestao.get("resolucao")),
					builder.equal(builder.trim(fromQuestao.<String>get("resolucao")), "")));
		}
		
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario!=null)
		{
			if(filtroQuestao.getRespondida() != null)
			{
				
				if(filtroQuestao.getRespondida().booleanValue())
				{
					Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
					predicates.add(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
					predicates.add(builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));

				}
				else
				{
					Subquery<Questao> questaoSubquery = query.subquery(Questao.class);
					Root<Questao> questaoFromSubquery = questaoSubquery.from(Questao.class);
					Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = questaoFromSubquery.join("resultadosQuestao");

					questaoSubquery.select(questaoFromSubquery).where(builder.and(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()),
					builder.equal(resultadoQuestaoJoin.get("questao").get("id"), questaoFromSubquery.get("id"))));

					predicates.add(builder.not(fromQuestao.in(questaoSubquery)));
				}
			}
			
			if(filtroQuestao.getAcertei() != null)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicates.add(builder.equal(resultadoQuestaoJoin.get("acertou"), filtroQuestao.getAcertei().booleanValue()));
				predicates.add(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicates.add(builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
			}
		}

		TypedQuery<Questao> typedQuery = em
		.createQuery(query.select(fromQuestao).where(predicates.toArray(new Predicate[0])).distinct(true).orderBy(builder.asc(fromQuestao.get("ordemInsercao"))));

		List<Questao> list = typedQuery.getResultList();

		return list;
	}
	
	public Questao getQuestao(long idQuestao)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Questao> query = builder.createQuery(Questao.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromQuestao.get("id"), idQuestao));

		TypedQuery<Questao> typedQuery = em
		.createQuery(query.select(fromQuestao).where(predicates.toArray(new Predicate[0])).distinct(true).orderBy(builder.asc(fromQuestao.get("ordemInsercao"))));

		List<Questao> list = typedQuery.getResultList();
		
		return list.isEmpty() ? null : list.get(0);
	}

	public Alternativa getAlternativaCorreta(Questao questao)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Alternativa> query = builder.createQuery(Alternativa.class);
		Root<Alternativa> fromAlternativa = query.from(Alternativa.class);

		List<Predicate> predicates = new ArrayList<>();

		if(questao != null)
		{
			predicates.add(builder.equal(fromAlternativa.get("questao").get("id"), questao.getId()));
			predicates.add(builder.equal(fromAlternativa.get("correta"), true));
		}

		TypedQuery<Alternativa> typedQuery = em.createQuery(query.select(fromAlternativa).where(predicates.toArray(new Predicate[0])));

		Alternativa alternativa;
		try
		{
			alternativa = typedQuery.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
		return alternativa;
	}

	public ResultadoQuestao getResultadoQuestaos(Questao questao, Usuario usuario)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoQuestao> query = builder.createQuery(ResultadoQuestao.class);
		Root<ResultadoQuestao> fromResultadoQuestao = query.from(ResultadoQuestao.class);

		List<Predicate> predicates = new ArrayList<>();

		if(questao != null)
		{
			predicates.add(builder.equal(fromResultadoQuestao.get("questao").get("id"), questao.getId()));
			predicates.add(builder.equal(fromResultadoQuestao.get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<ResultadoQuestao> typedQuery = em.createQuery(query.select(fromResultadoQuestao).where(predicates.toArray(new Predicate[0])));
		ResultadoQuestao resultadoQuestao;
		try
		{
			resultadoQuestao = typedQuery.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
		return resultadoQuestao;
	}

}
