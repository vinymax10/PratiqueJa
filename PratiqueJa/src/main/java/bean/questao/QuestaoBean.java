package bean.questao;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import bean.PaiBean;
import bean.exercicio.ConfigDownload;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.questao.AlternativaDAO;
import dao.questao.QuestaoDAO;
import dao.questao.ResultadoQuestaoDAO;
import dao.usuario.UsuarioDAO;
import filtro.questao.FiltroQuestao;
import infra.DadosGrafico;
import infra.GraficoPeriodo;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.Ano;
import modelo.academico.Assunto;
import modelo.academico.Banca;
import modelo.academico.Orgao;
import modelo.auditoria.TipoEvento;
import modelo.pdf.Visibilidade;
import modelo.questao.Alternativa;
import modelo.questao.Dificuldade;
import modelo.questao.Questao;
import modelo.questao.ResultadoQuestao;
import modelo.questao.TipoFiltro;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Usuario;
import service.configuracao.DiretorioService;
import util.ClasseAux;
import web.session.Sessao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class QuestaoBean extends PaiBean<Questao, QuestaoDAO, PermissaoPadrao<Questao>>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private FiltroQuestao filtro;

	@Inject
	private DiretorioService diretorioService;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private ConfigDownload configDownload;

	@Inject
	private ResultadoQuestaoDAO resultadoQuestaoDAO;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Inject
	private AlternativaDAO alternativaDAO;

	private TipoFiltro tipoFiltro;

	// Overview pagination para views de aluno
	private int index;
	private List<Questao> questoes = new ArrayList<>();
	private List<Questao> questoesOverview = new ArrayList<>();
	private int janela = 20;
	private int inicio;
	private int fim;

	/**
	 * Seleção combinada de situação: ACERTEI | ERREI | NAO_RESPONDIDA | null=Todas
	 */
	private String situacao;

	public QuestaoBean()
	{
		super(Questao.class, "Questão");
		urlCadastro = "/administracao/conteudo/questao/gerenciar/form.xhtml";
		urlLista = "/administracao/conteudo/questao/gerenciar/list.xhtml";
	}

	@PostConstruct
	public void postConstruct()
	{
		if(tabState.hasState(FiltroQuestao.class))
			filtro = tabState.getState(FiltroQuestao.class);
		filtrar();
	}

	public void filtrar()
	{
		this.lista = entidadeDAO.filtrar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	public String remover(Questao questao)
	{
		try
		{
//			validar(!permissao.isPodeRemover(), Mensagem.messagePermissaoNegada());
			if(auditoriasAtivas.contains(TipoEvento.EXCLUSAO))
				auditoriaService.registrarExclusao(classe, questao.getId(), questao);

			if(lista != null)
				lista.remove(questao);

			getListaTudo().remove(questao);
			entidadeDAO.remover(questao);
			if(ClasseAux.possuiAtributo(classe, "ordem"))
				onRowReorder(null);

			personalizarRemover();

			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, nome + " removido(a) com sucesso.");
			Navegacao.redirect(urlLista);
		}
//		catch(RelacaoException e)
//		{
//			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
//		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o(a) " + nome);
		}
		return "";
	}

	/** Alias para compatibilidade com views existentes. */
	public FiltroQuestao getFiltroQuestao()
	{
		return filtro;
	}

	public void setFiltroQuestao(FiltroQuestao f)
	{
		this.filtro = f;
	}

	/**
	 * Para views de aluno: carrega questões de um assunto com filtros de
	 * revisada/resolução.
	 */
	public List<Questao> buscaQuestao(Assunto assunto)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		questoes = entidadeDAO.buscaAssunto(assunto, tipoFiltro, usuario);
		atualizarOverview();
		return questoes;
	}

	/** Para views de aluno: filtro com revisada=true forçado. */
	public void filtrarEstudante()
	{
		if("ACERTEI".equals(situacao))
		{
			filtro.setAcertei(true);
			filtro.setRespondida(null);
		}
		else if("ERREI".equals(situacao))
		{
			filtro.setAcertei(false);
			filtro.setRespondida(null);
		}
		else if("NAO_RESPONDIDA".equals(situacao))
		{
			filtro.setAcertei(null);
			filtro.setRespondida(false);
		}
		else
		{
			filtro.setAcertei(null);
			filtro.setRespondida(null);
		}
		filtro.setRevisada(true);
		filtro.setResolucaoLatex(true);
		questoes = entidadeDAO.filtrar(filtro);
		atualizarOverview();
	}

	public void filtrarPorAssunto(Assunto assunto)
	{
		if(assunto != null && !filtro.getAssuntos().contains(assunto))
			filtro.getAssuntos().add(assunto);
		filtrarEstudante();
	}

	public void limparFiltroPorAssunto(Assunto assunto)
	{
		filtro.limpar();
		situacao = null;
		if(assunto != null)
			filtro.getAssuntos().add(assunto);
		filtrarEstudante();
	}

	public void limparFiltroEstudante()
	{
		filtro.limpar();
		situacao = null;
		filtrarEstudante();
	}

	public void removerAssunto(Assunto ac)
	{
		filtro.getAssuntos().remove(ac);
		filtrarEstudante();
	}

	public void removerBanca(Banca b)
	{
		filtro.getBancas().remove(b);
		filtrarEstudante();
	}

	public void removerOrgao(Orgao o)
	{
		filtro.getOrgaos().remove(o);
		filtrarEstudante();
	}

	public void removerAno(Ano a)
	{
		filtro.getAnos().remove(a);
		filtrarEstudante();
	}

	public void removerDificuldade(Dificuldade d)
	{
		filtro.getDificuldades().remove(d);
		filtrarEstudante();
	}

	public void removerSituacao()
	{
		situacao = null;
		filtrarEstudante();
	}

	public boolean temFiltrosAtivos()
	{
		return (filtro.getAssuntos() != null && !filtro.getAssuntos().isEmpty()) || (filtro.getBancas() != null && !filtro.getBancas().isEmpty())
		|| (filtro.getOrgaos() != null && !filtro.getOrgaos().isEmpty()) || (filtro.getAnos() != null && !filtro.getAnos().isEmpty())
		|| (filtro.getDificuldades() != null && !filtro.getDificuldades().isEmpty()) || (situacao != null && !situacao.isBlank());
	}

	public String getSituacaoLabel()
	{
		if("ACERTEI".equals(situacao))
			return "Acertei";
		if("ERREI".equals(situacao))
			return "Errei";
		if("NAO_RESPONDIDA".equals(situacao))
			return "Não respondida";
		return "";
	}

	public void toogleResolucaoComentada(Questao questao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(questao.isShowResolucaoComentada())
			{
				questao.toogleResolucaoComentada();
				return;
			}

			if(questao.getVisibilidade() == Visibilidade.Premium && !controleAcessoBean.podeAcessarPremium())
			{
				PrimeFaces.current().executeScript("PF('dialogPremiumQuestao').show()");
				return;
			}

			questao.toogleResolucaoComentada();
		}
	}

	public void toogleEstatistica(Questao questao)
	{
		if(controleAcessoBean.verificaEstaLogado())
			questao.toogleEstatistica();
	}

	public void podeFazerDownloadAssunto()
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(controleAcessoBean.podeFazerDownload())
				PrimeFaces.current().executeScript("PF('downloadQuestaoWidget').show()");
			else
				controleAcessoBean.showUpgrade("Limite mensal de páginas baixadas foi excedido." + "\nPor favor faça o upgrade de sua conta.");
		}
	}

	public void podeFazerDownloadMassa()
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(questoes.size() > 0)
			{
				if(controleAcessoBean.podeFazerDownload())
					PrimeFaces.current().executeScript("PF('downloadQuestaoWidget').show()");
				else
					controleAcessoBean.showUpgrade("Limite mensal de páginas baixadas foi excedido." + "\nPor favor faça o upgrade de sua conta.");
			}
			else
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Nenhuma questão carregada. " + "Por favor utilize o filtro para carregar as questões.");
		}
	}

	public String estaCorreta(Questao questao)
	{
		questao.setFeedbackAcertou(null);
		questao.setFeedbackLetraCorreta(null);
		questao.setFeedbackSemSelecao(false);

		if(controleAcessoBean.verificaEstaLogado())
		{
			if(controleAcessoBean.podeFazerQuestao())
			{
				if(questao.getAlternativaEscolhida() != null)
				{
					computarAlternativaEscolhida(questao.getAlternativaEscolhida());
					salvarResultadoQuestao(questao);

					boolean acertou = questao.getAlternativaEscolhida().isCorreta();
					questao.setFeedbackAcertou(acertou);

					if(!acertou)
					{
						Alternativa correta = entidadeDAO.getAlternativaCorreta(questao);
						if(correta != null)
							questao.setFeedbackLetraCorreta(correta.getLetra());
					}

					if(!questao.isJaFezQuestao())
					{
						controleAcessoBean.registrarQuestaoFeita();
						questao.setJaFezQuestao(true);
					}
				}
				else
					questao.setFeedbackSemSelecao(true);
			}
			else
				controleAcessoBean.showUpgrade("Limite mensal para resolver as questões foi excedido." + "\nPor favor faça o upgrade de sua conta.");
		}
		return "";
	}

	private void salvarResultadoQuestao(Questao questao)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		ResultadoQuestao resultadoQuestao = entidadeDAO.getResultadoQuestaos(questao, usuario);

		if(resultadoQuestao == null)
			resultadoQuestao = new ResultadoQuestao();

		resultadoQuestao.setUsuario(usuario);
		resultadoQuestao.setQuestao(questao);
		resultadoQuestao.setAcertou(questao.getAlternativaEscolhida().isCorreta());
		resultadoQuestaoDAO.salvar(resultadoQuestao);
	}

	private void computarAlternativaEscolhida(Alternativa alternativa)
	{
		alternativa.incrementaQtnEscolhida();
		alternativaDAO.salvar(alternativa);
	}

	public void nextOverview()
	{
		if(((index + 1) * janela) < questoes.size())
			index++;
		atualizarOverview();
	}

	public void backOverview()
	{
		if(index > 0)
			index--;
		atualizarOverview();
	}

	public void firstOverview()
	{
		index = 0;
		atualizarOverview();
	}

	public void lastOverview()
	{
		index = Math.max(0, (questoes.size() - 1) / janela);
		atualizarOverview();
	}

	/** Mantido por compatibilidade com GestaoQuestaoBean. */
	public void setQuestoes()
	{
		atualizarOverview();
	}

	private void atualizarOverview()
	{
		questoesOverview = new ArrayList<>();
		inicio = index * janela;
		fim = Math.min(inicio + janela, questoes.size());
		for(int i = inicio; i < fim; i++)
			questoesOverview.add(questoes.get(i));
	}

	public Dificuldade[] getDificuldadeValues()
	{
		return Dificuldade.values();
	}

	public Visibilidade[] getVisibilidadeValues()
	{
		return Visibilidade.values();
	}

	public String createBarModel(Questao questao)
	{
		int total = 0;
		for(Alternativa a : questao.getAlternativas())
			total += a.getQtnEscolhida();

		List<String> labels = new ArrayList<>();
		List<Number> values = new ArrayList<>();
		for(Alternativa a : questao.getAlternativas())
		{
			labels.add(a.getLetra());
			values.add(total == 0 ? 0 : 100 * a.getQtnEscolhida() / total);
		}

		DadosGrafico dados = new DadosGrafico();
		dados.setTitulo("Escolha das alternativas");
		dados.setTituloEixoX("Alternativas");
		dados.setTituloEixoY("Porcentagem");
		dados.setIndexAxis("x");
		dados.setLabels(labels);
		dados.setValues(values);

		return GraficoPeriodo.criarGraficoBarras(dados);
	}
}
