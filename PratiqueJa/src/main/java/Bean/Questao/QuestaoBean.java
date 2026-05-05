package Bean.Questao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import Bean.Download.Diretorio;
import Bean.Download.PoolNomesBean;
import Bean.Exercicio.ConfigDownload;
import Bean.Usuario.ControleAcessoBean;
import DAO.Questao.AlternativaDAO;
import DAO.Questao.QuestaoDAO;
import DAO.Questao.ResultadoQuestaoDAO;
import DAO.Usuario.UsuarioDAO;
import Infra.Mensagem;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Questao.Alternativa;
import Modelo.Questao.Questao;
import Modelo.Questao.ResultadoQuestao;
import Modelo.Questao.Enum.TipoFiltro;
import Modelo.Usuario.Usuario;
import Pdf.latex.GerarLatexQuestao;
import Session.SessionContext;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.enums.IndexAxis;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianScaleOptions;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianTickOptions;

@Named
@ViewScoped
public class QuestaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private QuestaoDAO questaoDAO;

	private List<Questao> questoes = new ArrayList<Questao>();

	@Inject
	private PoolNomesBean poolNomesBean;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private FiltroQuestao filtroQuestao;
	
	@Inject
	private ConfigDownload configDownload;
	
	@Inject
	private ResultadoQuestaoDAO resultadoQuestaoDAO;
	
//  --------------Overview-------------
	private int index;
	private List<Questao> questoesOverview = new ArrayList<Questao>();
	private int janela = 20;
	private int inicio;
	private int fim;

	@Inject
	private ControleAcessoBean controleAcessoBean;
	
	@Inject AlternativaDAO alternativaDAO;
	
	private TipoFiltro tipoFiltro;

	public void filtrar()
	{
		filtroQuestao.setRevisada(true);
		filtroQuestao.setResolucaoLatex(true);

		this.questoes = questaoDAO.filtrar(filtroQuestao);
		setQuestoes();
	}
	
	public List<Questao> buscaQuestao(AssuntoCurso assuntoCurso)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");

		questoes = questaoDAO.buscaAssuntoCurso(assuntoCurso, tipoFiltro, usuario);
		setQuestoes();
		return questoes;
	}
	
	public void toogleResolucaoComentada(Questao questao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(!questao.isShowResolucaoComentada()&&controleAcessoBean.podeResolucaoQuestao())
			{
				questao.toogleResolucaoComentada();
				if(!questao.isJaMostrouResolucaoComentada())
					controleAcessoBean.registrarResolucaoQuestao();
			}
			else if(questao.isShowResolucaoComentada())
				questao.toogleResolucaoComentada();
			else
			{
				controleAcessoBean.showUpgrade("Limite diário de acesso as resoluções de questões foi excedido."
				+ "\nPor favor faça o upgrade de sua conta.");
			}
			
			if(!questao.isJaMostrouResolucaoComentada())
				questao.setJaMostrouResolucaoComentada(true);
		}
	}
	
	public StreamedContent download(boolean massa)
	{
		System.out.println(configDownload);
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		usuario = usuarioDAO.carrega(usuario.getId());
		configDownload.setUsuario(usuario);
		
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		
		GerarLatexQuestao gerarLatex=new GerarLatexQuestao(diretorio);
		gerarLatex.gerarPDFQuestoes(questoes, configDownload);
		gerarLatex.gerar();
		
		File initialFile = new File(diretorio.getEnderecoPdf());
	    InputStream inStream;
	    StreamedContent file=null;
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
		
		poolNomesBean.freeDiretorio(diretorio);
		
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
			if(questoes.size()>0)
			{
				if(controleAcessoBean.podeFazerDownloadMassa())
					PrimeFaces.current().executeScript("PF('downloadQuestaoWidget').show()");
				else
					controleAcessoBean.showUpgrade("Este recurso está disponível somente para os perfis Prata ou Ouro."
					+ "\nPor favor faça o upgrade de sua conta.");
			}
			else
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Nenhuma questão carregada", 
				"Por favor utilize o filtro para carregar as questões.");
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
					computarAlternaticaEscolhida(questao.getAlternativaEscolhida());
					salvarResultadoQuestao(questao);
					
					if(questao.getAlternativaEscolhida().isCorreta())
						Mensagem.send("msg", FacesMessage.SEVERITY_INFO, "Resposta correta.", "");
					else
					{
						Alternativa correta = questaoDAO.getAlternativaCorreta(questao);
						if(correta != null)
							Mensagem.send("msg", FacesMessage.SEVERITY_ERROR, 
							"Resposta errada. A aternativa correta é a letra " + correta.getLetra(), "");
					}

					if(!questao.isJaFezQuestao())
					{
						controleAcessoBean.registrarQuestaoFeita();
						questao.setJaFezQuestao(true);
					}
				}
				else
				{
					Mensagem.send("msg", FacesMessage.SEVERITY_ERROR, "Por favor escolha uma alternativa.", "");
				}
			}
			else
			{
				controleAcessoBean.showUpgrade("Limite diário para resolver as questões foi excedido."
				+ "\nPor favor faça o upgrade de sua conta.");
			}
		}

		return "";
	}
	
	private void salvarResultadoQuestao(Questao questao)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		ResultadoQuestao resultadoQuestao = questaoDAO.getResultadoQuestaos(questao, usuario);

		if(resultadoQuestao == null)
			resultadoQuestao = new ResultadoQuestao();

		resultadoQuestao.setUsuario(usuario);
		resultadoQuestao.setQuestao(questao);
		
		resultadoQuestao.setAcertou(questao.getAlternativaEscolhida().isCorreta());
		resultadoQuestaoDAO.salvar(resultadoQuestao);
	}
	
	private void computarAlternaticaEscolhida(Alternativa alternativa)
	{
		alternativa.incrementaQtnEscolhida();
		alternativaDAO.salvar(alternativa);
	}
	
	public void nextOverview()
	{
		if(((index + 1) * janela) < questoes.size())
			index++;

		setQuestoes();
	}

	public void backOverview()
	{
		if(index > 0)
			index--;

		setQuestoes();
	}

	public void firstOverview()
	{
		index = 0;

		setQuestoes();
	}

	public void lastOverview()
	{
		index = (int) ((questoes.size() - 1) / janela);
		index = Math.max(index, 0);

		setQuestoes();
	}

	public void setQuestoes()
	{
		questoesOverview = new ArrayList<Questao>();
		inicio = index * janela;
		fim = Math.min(inicio + janela, questoes.size());

		for(int i = inicio; i < fim; i++)
			questoesOverview.add(questoes.get(i));
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
			if(total == 0)
				values.add(0);
			else
				values.add(100 * alternativa.getQtnEscolhida() / total);

			labels.add(alternativa.getLetra());
		}
		barDataSet.setData(values);
		barDataSet.setBackgroundColor(bgColor);
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addDataset(barDataSet);

		data.setLabels(labels);
		barModel.setData(data);

		// Options
		BarOptions options = new BarOptions();
		
		options.setResponsive(true)
        .setMaintainAspectRatio(false)
        .setIndexAxis(IndexAxis.X)
//        .setScales(new Scales()
//    		.addScale(Scales.ScaleAxis.Y, new CartesianScaleOptions()
//                .setStacked(false)
//                .setTicks(new CartesianTickOptions()
//                        .setAutoSkip(true)
//                        .setMirror(true)))
//    		.addScale(Scales.ScaleAxis.X, new CategoryScaleOptions()
//			.setTitle(new AbstractCartesianScaleOptions.Title().setText("Test"))
//        	)
//        )
        .setPlugins(new Plugins()
                .setTitle(new Title()
                        .setDisplay(true)
                        .setText("Q" + questao.getId())));
        
//		Title title = new Title();
//		title.setDisplay(true);
//		title.setText("Q" + questao.getId());
//		options.setTitle(title);
//
//		Legend legend = new Legend();
//		legend.setDisplay(true);
//		legend.setPosition("top");
//		LegendLabel legendLabels = new LegendLabel();
//		legendLabels.setFontStyle("bold");
//		legendLabels.setFontColor("#2980B9");
//		legendLabels.setFontSize(24);
//		legend.setLabels(legendLabels);
//		options.setLegend(legend);
//
//		// disable animation
//		Animation animation = new Animation();
//		animation.setDuration(0);
//		options.setAnimation(animation);

		barModel.setOptions(options);
		
		
		return barModel.toJson();
	}
	
	public List<Questao> getQuestoes()
	{
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes)
	{
		this.questoes = questoes;
	}

	public FiltroQuestao getFiltroQuestao()
	{
		return filtroQuestao;
	}

	public void setFiltroQuestao(FiltroQuestao filtroQuestao)
	{
		this.filtroQuestao = filtroQuestao;
	}

	public int getInicio()
	{
		return inicio;
	}

	public void setInicio(int inicio)
	{
		this.inicio = inicio;
	}

	public int getFim()
	{
		return fim;
	}

	public void setFim(int fim)
	{
		this.fim = fim;
	}

	public List<Questao> getQuestoesOverview()
	{
		return questoesOverview;
	}

	public void setQuestoesOverview(List<Questao> questoesOverview)
	{
		this.questoesOverview = questoesOverview;
	}

	public ConfigDownload getConfigDownload()
	{
		return configDownload;
	}

	public void setConfigDownload(ConfigDownload configDownload)
	{
		this.configDownload = configDownload;
	}

	public TipoFiltro getTipoFiltro()
	{
		return tipoFiltro;
	}

	public void setTipoFiltro(TipoFiltro tipoFiltro)
	{
		this.tipoFiltro = tipoFiltro;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}
	
}