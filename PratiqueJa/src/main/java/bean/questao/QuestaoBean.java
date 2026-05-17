package bean.questao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import bean.PaiBean;
import bean.download.Diretorio;
import bean.exercicio.ConfigDownload;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.questao.AlternativaDAO;
import dao.questao.QuestaoDAO;
import dao.questao.ResultadoQuestaoDAO;
import dao.usuario.UsuarioDAO;
import exceptions.RelacaoException;
import filtro.questao.FiltroQuestao;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.AssuntoCurso;
import modelo.auditoria.TipoEvento;
import modelo.questao.Alternativa;
import modelo.questao.Dificuldade;
import modelo.questao.Questao;
import modelo.questao.ResultadoQuestao;
import modelo.questao.TipoFiltro;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Usuario;
import pdf.questao.GerarLatexQuestao;
import service.configuracao.DiretorioService;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.enums.IndexAxis;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
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

	public QuestaoBean()
	{
		super(Questao.class, "Questão");
		urlCadastro = "/administracao/questao/form.xhtml";
		urlLista    = "/administracao/questao/list.xhtml";
	}

	@PostConstruct
	public void postConstruct()
	{
		if(tabState.hasState(FiltroQuestao.class))
			filtro = tabState.getState(FiltroQuestao.class);
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
			validar(!permissao.isPodeRemover(),Mensagem.messagePermissaoNegada());
			podeRemover(entidade);
			if(auditoriasAtivas.contains(TipoEvento.EXCLUSAO))
				auditoriaService.registrarExclusao(classe, entidade.getId(), entidade);
			
			if(lista.contains(questao))
				lista.remove(entidade);
			
			getListaTudo().remove(entidade);
			entidadeDAO.remover(entidade);
			if(ClasseAux.possuiAtributo(classe, "ordem"))
				onRowReorder(null);
			
			personalizarRemover();
			
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, nome + " removido(a) com sucesso.");
			Navegacao.redirect(urlLista);
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o(a) " + nome);
		}
		return "";
	}

	/** Alias para compatibilidade com views existentes. */
	public FiltroQuestao getFiltroQuestao() { return filtro; }
	public void setFiltroQuestao(FiltroQuestao f) { this.filtro = f; }

	/** Para views de aluno: carrega questões de um assunto com filtros de revisada/resolução. */
	public List<Questao> buscaQuestao(AssuntoCurso assuntoCurso)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		questoes = entidadeDAO.buscaAssuntoCurso(assuntoCurso, tipoFiltro, usuario);
		atualizarOverview();
		return questoes;
	}

	/** Para views de aluno: filtro com revisada=true forçado. */
	public void filtrarEstudante()
	{
		filtro.setRevisada(true);
		filtro.setResolucaoLatex(true);
		questoes = entidadeDAO.filtrar(filtro);
		atualizarOverview();
	}

	public void toogleResolucaoComentada(Questao questao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(!questao.isShowResolucaoComentada() && controleAcessoBean.podeResolucaoQuestao())
			{
				questao.toogleResolucaoComentada();
				if(!questao.isJaMostrouResolucaoComentada())
					controleAcessoBean.registrarResolucaoQuestao();
			}
			else if(questao.isShowResolucaoComentada())
				questao.toogleResolucaoComentada();
			else
				controleAcessoBean.showUpgrade("Limite diário de acesso as resoluções de questões foi excedido."
				+ "\nPor favor faça o upgrade de sua conta.");

			if(!questao.isJaMostrouResolucaoComentada())
				questao.setJaMostrouResolucaoComentada(true);
		}
	}

	public StreamedContent download(boolean massa)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		usuario = usuarioDAO.carrega(usuario.getId());
		configDownload.setUsuario(usuario);

		Diretorio diretorio = diretorioService.criarDiretorio();

		GerarLatexQuestao gerarLatex = new GerarLatexQuestao(diretorio);
		gerarLatex.gerarPDFQuestoes(questoes, configDownload);
		gerarLatex.gerar();

		File initialFile = new File(diretorio.getEnderecoPdf());
		InputStream inStream;
		StreamedContent file = null;
		try
		{
			inStream = new FileInputStream(initialFile);
			file = DefaultStreamedContent.builder().name("Questoes.pdf")
			.contentType("aplication/pdf").stream(() -> inStream).build();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		diretorioService.freeDiretorio(diretorio);
		controleAcessoBean.registrarDownloadQuestao(massa);
		return file;
	}

	public void podeFazerDownloadAssunto()
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(controleAcessoBean.podeFazerDownloadQuestao())
				PrimeFaces.current().executeScript("PF('downloadQuestaoWidget').show()");
			else
				controleAcessoBean.showUpgrade("Limite diário de downloads de questões foi excedido."
				+ "\nPor favor faça o upgrade de sua conta.");
		}
	}

	public void podeFazerDownloadMassa()
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(questoes.size() > 0)
			{
				if(controleAcessoBean.podeFazerDownloadMassa())
					PrimeFaces.current().executeScript("PF('downloadQuestaoWidget').show()");
				else
					controleAcessoBean.showUpgrade("Este recurso está disponível somente para os perfis Prata ou Ouro."
					+ "\nPor favor faça o upgrade de sua conta.");
			}
			else
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Nenhuma questão carregada. "
				+ "Por favor utilize o filtro para carregar as questões.");
		}
	}

	public String estaCorreta(Questao questao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(controleAcessoBean.podeFazerQuestao())
			{
				if(questao.getAlternativaEscolhida() != null)
				{
					computarAlternativaEscolhida(questao.getAlternativaEscolhida());
					salvarResultadoQuestao(questao);

					if(questao.getAlternativaEscolhida().isCorreta())
						Mensagem.send("msg", FacesMessage.SEVERITY_INFO, "Resposta correta.");
					else
					{
						Alternativa correta = entidadeDAO.getAlternativaCorreta(questao);
						if(correta != null)
							Mensagem.send("msg", FacesMessage.SEVERITY_ERROR,
							"Resposta errada. A alternativa correta é a letra " + correta.getLetra());
					}

					if(!questao.isJaFezQuestao())
					{
						controleAcessoBean.registrarQuestaoFeita();
						questao.setJaFezQuestao(true);
					}
				}
				else
					Mensagem.send("msg", FacesMessage.SEVERITY_ERROR, "Por favor escolha uma alternativa.");
			}
			else
				controleAcessoBean.showUpgrade("Limite diário para resolver as questões foi excedido."
				+ "\nPor favor faça o upgrade de sua conta.");
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

	public String createBarModel(Questao questao)
	{
		BarChart barModel = new BarChart();
		BarData data = new BarData();

		BarDataset barDataSet = new BarDataset();
		barDataSet.setLabel("Estatística");

		List<Number> values = new ArrayList<>();
		List<String> bgColor = new ArrayList<>();
		List<String> borderColor = new ArrayList<>();
		List<String> labels = new ArrayList<>();

		bgColor.add("rgba(255, 99, 132, 0.2)");
		bgColor.add("rgba(255, 159, 64, 0.2)");
		bgColor.add("rgba(255, 205, 86, 0.2)");
		bgColor.add("rgba(75, 192, 192, 0.2)");
		bgColor.add("rgba(54, 162, 235, 0.2)");

		borderColor.add("rgb(255, 99, 132)");
		borderColor.add("rgb(255, 159, 64)");
		borderColor.add("rgb(255, 205, 86)");
		borderColor.add("rgb(75, 192, 192)");
		borderColor.add("rgb(54, 162, 235)");

		int total = 0;
		for(Alternativa alternativa : questao.getAlternativas())
			total += alternativa.getQtnEscolhida();

		for(Alternativa alternativa : questao.getAlternativas())
		{
			values.add(total == 0 ? 0 : 100 * alternativa.getQtnEscolhida() / total);
			labels.add(alternativa.getLetra());
		}

		barDataSet.setData(values);
		barDataSet.setBackgroundColor(bgColor);
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addDataset(barDataSet);
		data.setLabels(labels);
		barModel.setData(data);

		BarOptions options = new BarOptions();
		options.setResponsive(true)
		       .setMaintainAspectRatio(false)
		       .setIndexAxis(IndexAxis.X)
		       .setPlugins(new Plugins()
		               .setTitle(new Title()
		                       .setDisplay(true)
		                       .setText("Q" + questao.getId())));

		barModel.setOptions(options);
		return barModel.toJson();
	}
}
