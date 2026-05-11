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
import modelo.assuntocurso.AssuntoCurso;
import modelo.questao.Alternativa;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import modelo.questao.ResultadoQuestao;
import modelo.questao.TipoFiltro;
import modelo.questao.configuracao.Assunto;
import modelo.usuario.Usuario;
import web.session.Sessao;

public class QuestaoDAO extends DAO<Questao>
{
	private static final long serialVersionUID = 1L;

	public QuestaoDAO()
	{
		super(Questao.class);
	}

	public List<Questao> buscaAssuntoCurso(AssuntoCurso assuntoCurso, TipoFiltro tipoFiltro, Usuario usuario)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Questao> query = builder.createQuery(Questao.class);
		Root<Questao> fromQuestao = query.from(Questao.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromQuestao.get("revisada"), true));
		predicates.add(builder.notEqual(fromQuestao.get("resolucao"),""));

		if(assuntoCurso != null)
		{
			predicates.add(builder.equal(fromQuestao.get("assuntoCurso").get("id"), assuntoCurso.getId()));
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
//		fromQuestao.fetch("assuntoCurso");
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
		
		if(filtroQuestao.getAno() != null)
		{
			predicates.add(builder.equal(fromQuestao.get("ano").get("id"), filtroQuestao.getAno().getId()));
		}

		if(filtroQuestao.getBanca() != null)
		{
			predicates.add(builder.equal(fromQuestao.get("banca").get("id"), filtroQuestao.getBanca().getId()));
		}

		if(filtroQuestao.getOrgao() != null)
		{
			predicates.add(builder.equal(fromQuestao.get("orgao").get("id"), filtroQuestao.getOrgao().getId()));
		}

		if(filtroQuestao.getDisciplina() != null)
		{
			predicates.add(builder.equal(fromQuestao.get("disciplina").get("id"), filtroQuestao.getDisciplina().getId()));
		}

		if(filtroQuestao.getAssuntoCurso() != null)
		{
			predicates.add(builder.equal(fromQuestao.get("assuntoCurso").get("id"), filtroQuestao.getAssuntoCurso().getId()));
		}

		if(filtroQuestao.getDificuldade() != null)
		{
			predicates.add(builder.equal(fromQuestao.get("dificuldade"), filtroQuestao.getDificuldade()));
		}

		if(filtroQuestao.getChave()!=null&&!filtroQuestao.getChave().isBlank())
		{
			predicates.add(builder.like(fromQuestao.<String>get("chave"), "%" + filtroQuestao.getChave() + "%"));
		}

		if(filtroQuestao.getOrdemInsercao() != 0)
		{
			predicates.add(builder.equal(fromQuestao.get("ordemInsercao"), filtroQuestao.getOrdemInsercao()));
		}

		if(filtroQuestao.getId() != 0)
		{
			predicates.add(builder.equal(fromQuestao.<Long>get("id"), filtroQuestao.getId()));
		}

		if(filtroQuestao.getAssunto() != null)
		{
			Join<Questao, Assunto> assuntoJoin = fromQuestao.join("assuntos");
			predicates.add(builder.equal(assuntoJoin.get("id"), filtroQuestao.getAssunto().getId()));
		}

		if(filtroQuestao.getRevisada() != null)
			predicates.add(builder.equal(fromQuestao.get("revisada"), filtroQuestao.getRevisada().booleanValue()));

		if(filtroQuestao.getResolucaoLatex() != null)
		{
			if(filtroQuestao.getResolucaoLatex().booleanValue())
				predicates.add(builder.notEqual(fromQuestao.get("resolucao"),""));
			else
				predicates.add(builder.isNull(fromQuestao.get("resolucao")));
		}
		
		Usuario usuario = (Usuario) Sessao.get("UsuarioLogado");
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
		
		if(list.size()>0)
			return list.get(0);
		
		return null;
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
