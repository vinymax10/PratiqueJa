package DAO.Questao;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

import Bean.Questao.FiltroQuestao;
import DAO.DAO;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Questao.Alternativa;
import Modelo.Questao.Paragrafo;
import Modelo.Questao.Questao;
import Modelo.Questao.ResultadoQuestao;
import Modelo.Questao.Configuracao.Assunto;
import Modelo.Questao.Enum.TipoFiltro;
import Modelo.Usuario.Usuario;
import Session.SessionContext;

public class QuestaoDAO extends DAO<Questao>
{
	private static final long serialVersionUID = 1L;

	public QuestaoDAO()
	{
		super(Questao.class);
	}

	public List<Questao> buscaAssuntoCurso(AssuntoCurso assuntoCurso, TipoFiltro tipoFiltro, Usuario usuario)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Questao> query = builder.createQuery(Questao.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromQuestao.get("revisada"), true));
		predicate = builder.and(predicate, builder.notEqual(fromQuestao.get("resolucao"),""));

		if(assuntoCurso != null)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("assuntoCurso").get("id"), assuntoCurso.getId()));
		}

		if(tipoFiltro != null && usuario != null)
		{

			if(tipoFiltro == TipoFiltro.Repondidas)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
//				predicate=builder.and(predicate,fromQuestao.in(resultadoQuestaoJoin));

			}
			else if(tipoFiltro == TipoFiltro.NaoRespondidas)
			{
				Subquery<Questao> questaoSubquery = query.subquery(Questao.class);
				Root<Questao> questaoFromSubquery = questaoSubquery.from(Questao.class);
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = questaoFromSubquery.join("resultadosQuestao");

				questaoSubquery.select(questaoFromSubquery).where(builder.and(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()),
				builder.equal(resultadoQuestaoJoin.get("questao").get("id"), questaoFromSubquery.get("id"))));

				predicate = builder.and(predicate, builder.not(fromQuestao.in(questaoSubquery)));
			}
			else if(tipoFiltro == TipoFiltro.Aceitei)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("acertou"), true));
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
			}
			else if(tipoFiltro == TipoFiltro.Errei)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("acertou"), false));
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
			}
		}

//		fromQuestao.fetch("assuntos", JoinType.LEFT);
//		fromQuestao.fetch("ano");
//		fromQuestao.fetch("disciplina");
//		fromQuestao.fetch("banca");
//		fromQuestao.fetch("orgao");
//		fromQuestao.fetch("assuntoCurso");
//		
		TypedQuery<Questao> typedQuery = em
		.createQuery(query.select(fromQuestao).where(predicate).distinct(true)
		.orderBy(builder.asc(fromQuestao.get("ordemInsercao"))));

		List<Questao> list = typedQuery.getResultList();

		return list;
	}

	public List<Questao> filtrar(FiltroQuestao filtroQuestao)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Questao> query = builder.createQuery(Questao.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		Predicate predicate = builder.and();

		if(filtroQuestao.getConteudo() != null && !filtroQuestao.getConteudo().equals(""))
		{
			Join<Questao, Paragrafo> paragrafoJoin = fromQuestao.join("paragrafos");
			predicate = builder.and(predicate, builder.like(paragrafoJoin.get("texto"), "%" + filtroQuestao.getConteudo()+"%"));
		}
		
		if(filtroQuestao.getAno() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("ano").get("id"), filtroQuestao.getAno().getId()));
		}

		if(filtroQuestao.getBanca() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("banca").get("id"), filtroQuestao.getBanca().getId()));
		}

		if(filtroQuestao.getOrgao() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("orgao").get("id"), filtroQuestao.getOrgao().getId()));
		}

		if(filtroQuestao.getDisciplina() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("disciplina").get("id"), filtroQuestao.getDisciplina().getId()));
		}

		if(filtroQuestao.getAssuntoCurso() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("assuntoCurso").get("id"), filtroQuestao.getAssuntoCurso().getId()));
		}

		if(filtroQuestao.getDificuldade() != null)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("dificuldade"), filtroQuestao.getDificuldade()));
		}

		if(filtroQuestao.getChave()!=null&&!filtroQuestao.getChave().equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromQuestao.<String>get("chave"), "%" + filtroQuestao.getChave() + "%"));
		}

		if(filtroQuestao.getOrdemInsercao() != 0)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("ordemInsercao"), filtroQuestao.getOrdemInsercao()));
		}

		if(filtroQuestao.getId() != 0)
		{
			predicate = builder.and(predicate, builder.equal(fromQuestao.<Long>get("id"), filtroQuestao.getId()));
		}

		if(filtroQuestao.getAssunto() != null)
		{
			Join<Questao, Assunto> assuntoJoin = fromQuestao.join("assuntos");
			predicate = builder.and(predicate, builder.equal(assuntoJoin.get("id"), filtroQuestao.getAssunto().getId()));
		}

		if(filtroQuestao.getRevisada() != null)
			predicate = builder.and(predicate, builder.equal(fromQuestao.get("revisada"), filtroQuestao.getRevisada().booleanValue()));

		if(filtroQuestao.getResolucaoLatex() != null)
		{
			if(filtroQuestao.getResolucaoLatex().booleanValue())
				predicate = builder.and(predicate, builder.notEqual(fromQuestao.get("resolucao"),""));
			else
				predicate = builder.and(predicate, builder.isNull(fromQuestao.get("resolucao")));
		}
		
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		if(usuario!=null)
		{
			if(filtroQuestao.getRespondida() != null)
			{
				
				if(filtroQuestao.getRespondida().booleanValue())
				{
					Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
					predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
					predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));

				}
				else
				{
					Subquery<Questao> questaoSubquery = query.subquery(Questao.class);
					Root<Questao> questaoFromSubquery = questaoSubquery.from(Questao.class);
					Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = questaoFromSubquery.join("resultadosQuestao");

					questaoSubquery.select(questaoFromSubquery).where(builder.and(builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()),
					builder.equal(resultadoQuestaoJoin.get("questao").get("id"), questaoFromSubquery.get("id"))));

					predicate = builder.and(predicate, builder.not(fromQuestao.in(questaoSubquery)));
				}
			}
			
			if(filtroQuestao.getAcertei() != null)
			{
				Join<Questao, ResultadoQuestao> resultadoQuestaoJoin = fromQuestao.join("resultadosQuestao");
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("acertou"), filtroQuestao.getAcertei().booleanValue()));
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("usuario").get("id"), usuario.getId()));
				predicate = builder.and(predicate, builder.equal(resultadoQuestaoJoin.get("questao").get("id"), fromQuestao.get("id")));
			}
		}

		TypedQuery<Questao> typedQuery = em
		.createQuery(query.select(fromQuestao).where(predicate).distinct(true).orderBy(builder.asc(fromQuestao.get("ordemInsercao"))));

		List<Questao> list = typedQuery.getResultList();

		return list;
	}
	
	public Questao getQuestao(long idQuestao)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Questao> query = builder.createQuery(Questao.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromQuestao.get("id"), idQuestao));

		TypedQuery<Questao> typedQuery = em
		.createQuery(query.select(fromQuestao).where(predicate).distinct(true).orderBy(builder.asc(fromQuestao.get("ordemInsercao"))));

		List<Questao> list = typedQuery.getResultList();
		
		if(list.size()>0)
			return list.get(0);
		
		return null;
	}

	public Alternativa getAlternativaCorreta(Questao questao)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Alternativa> query = builder.createQuery(Alternativa.class);
		Root<Alternativa> fromAlternativa = query.from(Alternativa.class);

		Predicate predicate = builder.and();

		if(questao != null)
		{
			predicate = builder.and(predicate, builder.equal(fromAlternativa.get("questao").get("id"), questao.getId()));
			predicate = builder.and(predicate, builder.equal(fromAlternativa.get("correta"), true));
		}

		TypedQuery<Alternativa> typedQuery = em.createQuery(query.select(fromAlternativa).where(predicate));

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
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResultadoQuestao> query = builder.createQuery(ResultadoQuestao.class);
		Root<ResultadoQuestao> fromResultadoQuestao = query.from(ResultadoQuestao.class);

		Predicate predicate = builder.and();

		if(questao != null)
		{
			predicate = builder.and(predicate, builder.equal(fromResultadoQuestao.get("questao").get("id"), questao.getId()));
			predicate = builder.and(predicate, builder.equal(fromResultadoQuestao.get("usuario").get("id"), usuario.getId()));
		}

		TypedQuery<ResultadoQuestao> typedQuery = em.createQuery(query.select(fromResultadoQuestao).where(predicate));
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
