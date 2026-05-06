package bean.questao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.xmlbeans.impl.common.Levenshtein;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ReorderEvent;
import org.primefaces.model.file.UploadedFile;

import bean.exercicio.ConfigDownload;
import bean.questao.configuracao.AnoBean;
import bean.questao.configuracao.AssuntoBean;
import bean.questao.configuracao.BancaBean;
import bean.questao.configuracao.DisciplinaBean;
import bean.questao.configuracao.OrgaoBean;
import dao.questao.QuestaoDAO;
import infra.Mensagem;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.questao.Alternativa;
import modelo.questao.Dificuldade;
import modelo.questao.ImagemFile;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import modelo.questao.configuracao.Ano;
import modelo.questao.configuracao.Assunto;
import modelo.questao.configuracao.Banca;
import modelo.questao.configuracao.Disciplina;
import modelo.questao.configuracao.Orgao;
import scraping.QuestaoQ;
import session.SessionContext;

@Named
@ViewScoped
public class GestaoQuestaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Questão";

	@Inject
	private QuestaoDAO questaoDAO;

	private List<Questao> questoes = new ArrayList<Questao>();

	private Questao questao;

	private boolean cadastro = false;

	private int activeIndex;

	private UploadedFile uploadedFile;

	@Inject
	private AnoBean anoBean;

	@Inject
	private BancaBean bancaBean;

	@Inject
	private OrgaoBean orgaoBean;

	@Inject
	private DisciplinaBean disciplinaBean;

	@Inject
	private AssuntoBean assuntoBean;

	private int inicioCarregamento;

	private int fimCarregamento;

//  ---------Filtro---------

	@Inject
	private FiltroQuestao filtroQuestao;
	private long idQuestao;
	
	@Inject
	private ConfigDownload configDownload;
	
	@Inject
	private QuestaoBean questaoBean;
	
	public String cadastrar()
	{
		activeIndex = 0;
		cadastro = true;
		questao = new Questao();
		questaoDAO.salvar(questao);
		Navegacao.redirectPF("/gestaoQuestao/questao?questao="+questao.getId()+"&tab="+0,"_blank");
		return "";
	}

	public String adicionar()
	{
		try
		{
			questaoDAO.salvar(questao);
			questoes.add(questao);
			cadastro = false;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionada com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar a " + nome);
		}

		return "";
	}

	public String salvar()
	{
		try
		{
			questao=questaoDAO.salvar(questao);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salva com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a " + nome);
		}
		return "";
	}

	public String remover()
	{
		try
		{
			questaoDAO.remover(questao);
			questoes.remove(questao);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
		}
		return "";
	}

	public String remover(Questao questao)
	{
		try
		{
			questaoDAO.remover(questao);
			questoes.remove(questao);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
		}
		return "";
	}

	@PostConstruct
	public void init()
	{
		if(idQuestao!=0)
		{
			SessionContext.getInstance().setAttribute("id", null);
			this.questao = questaoDAO.getQuestao(idQuestao);
			cadastro = false;
		}
	}
	
	public void arrumar()
	{
//		filtroQuestao.limpar();
//		this.questoes = questaoDAO.filtrar(filtroQuestao);
//		for(Questao questao : questoes)
//		{
//			if(questao.getResolucaoComentadaFile()!=null)
//			{
//				questao.setResolucaoComentadaFile(null);
//				questaoDAO.salvar(questao);
//				
//				System.out.println("questao: "+questao.getChave());
//			}
//		}
	}

	public void carregar(Questao questao)
	{
		this.questao = questao;
		activeIndex = 3;
		cadastro = false;
		PrimeFaces.current().executeScript("window.open('/gestaoQuestao/questao?questao="+questao.getId()+"&tab="+3+"', '_blank')");
	}
	
	public void filtrar()
	{
		this.questoes = questaoDAO.filtrar(filtroQuestao);
		
		questaoBean.setQuestoes(questoes);
		questaoBean.setIndex(0);
		questaoBean.setQuestoes();
	}

	public void inserirPonto()
	{
		for(Alternativa alternativa : questao.getAlternativas())
		{
			alternativa.setTexto(alternativa.getTexto().concat("."));
		}
	}

	public void adicionarQuestoesSalvas()
	{
		for(int index = inicioCarregamento; index < fimCarregamento; index++)
		{
			QuestaoQ questaoQ = deSerializar("" + index);

			questao = new Questao();
			questao.setRevisada(false);
			questao.setOrdemInsercao(index);
			questao.setChave(questaoQ.getId());
			questao.setProva(questaoQ.getProva());
			questao.setDificuldade(Dificuldade.Facil);

			List<Ano> anos = anoBean.getEntidadeDAO().filtrar(questaoQ.getAno().trim());
			if(anos.size() > 0)
			{
				Ano ano = anos.get(0);
				questao.setAno(ano);
			}
			else
			{
				Ano ano = new Ano();
				ano.setNome(questaoQ.getAno().trim());
				anoBean.getEntidadeDAO().salvar(ano);
				anoBean.getLista().add(ano);
				questao.setAno(ano);
			}

			List<Banca> bancas = bancaBean.getEntidadeDAO().filtrar(questaoQ.getBanca().trim());
			if(bancas.size() > 0)
			{
				Banca bestBanca = null;
				if(bancas.size() > 1)
				{
					int distMin = Integer.MAX_VALUE;
					int dist;
					for(Banca banca : bancas)
					{
						dist = Levenshtein.distance(banca.getSigla(), questaoQ.getBanca().trim());
						if(dist < distMin)
						{
							distMin = dist;
							bestBanca = banca;
						}
					}
				}
				else
					bestBanca = bancas.get(0);

				questao.setBanca(bestBanca);
			}
			else
			{
				Banca banca = new Banca();
				banca.setNome(questaoQ.getBanca().trim());
				banca.setSigla(questaoQ.getOrgao().trim());
				bancaBean.getEntidadeDAO().salvar(banca);
				bancaBean.getLista().add(banca);
				questao.setBanca(banca);
			}

			List<Orgao> orgaos = orgaoBean.getEntidadeDAO().filtrar(questaoQ.getOrgao().trim());
			if(orgaos.size() > 0)
			{
				Orgao bestOrgao = null;
				if(orgaos.size() > 1)
				{
					int distMin = Integer.MAX_VALUE;
					int dist;
					for(Orgao orgao : orgaos)
					{
						dist = Levenshtein.distance(orgao.getSigla(), questaoQ.getOrgao().trim());
						if(dist < distMin)
						{
							distMin = dist;
							bestOrgao = orgao;
						}
					}
				}
				else
					bestOrgao = orgaos.get(0);

				questao.setOrgao(bestOrgao);
			}
			else
			{
				Orgao orgao = new Orgao();
				orgao.setNome(questaoQ.getOrgao().trim());
				orgao.setSigla(questaoQ.getOrgao().trim());
				orgaoBean.getEntidadeDAO().salvar(orgao);
				orgaoBean.getLista().add(orgao);
				questao.setOrgao(orgao);
			}

			Disciplina disciplina = null;
			List<Disciplina> disciplinas = disciplinaBean.getDisciplinaDAO().filtrar(questaoQ.getDisciplina().trim());
			if(disciplinas.size() > 0)
			{
				disciplina = disciplinas.get(0);
				questao.setDisciplina(disciplina);
			}

			for(String str : questaoQ.getAssuntos())
			{
				str = str.trim();
				if(!str.equals(""))
				{
					List<Assunto> assuntos = assuntoBean.getEntidadeDAO().filtrar(str);
					if(assuntos.size() > 0)
					{
						Assunto bestAssunto = null;
						if(assuntos.size() > 1)
						{
							int distMin = Integer.MAX_VALUE;
							int dist;
							for(Assunto assunto : assuntos)
							{
								dist = Levenshtein.distance(assunto.getNome(), str);
								if(dist < distMin)
								{
									distMin = dist;
									bestAssunto = assunto;
								}
							}
						}
						else
							bestAssunto = assuntos.get(0);

						questao.getAssuntos().add(bestAssunto);
					}
					else
					{
						Assunto assunto = new Assunto();
						assunto.setDisciplina(disciplina);
						assunto.setNome(str);
						disciplina.getAssuntos().add(assunto);
						assuntoBean.getEntidadeDAO().salvar(assunto);
						disciplinaBean.getDisciplinaDAO().salvar(disciplina);
						questao.getAssuntos().add(assunto);
					}
				}
			}

			questaoDAO.salvar(questao);

			for(scraping.Paragrafo paragrafoQ : questaoQ.getParagrafos())
			{
				modelo.questao.Paragrafo paragrafo = new Paragrafo();
				paragrafo.setQuestao(questao);
				if(paragrafoQ.getTexto() != null)
				{
					paragrafo.setTexto(paragrafoQ.getTexto());
					paragrafo.setOrdem(questao.getParagrafos().size());
					questao.getParagrafos().add(paragrafo);
				}
				else if(paragrafoQ.getImagem() != null && !paragrafoQ.getImagem().equals(""))
				{
					SerialBlob serialBlob = getImage(paragrafoQ.getImagem());
					ImagemFile imagemFile = new ImagemFile();

					imagemFile.setFile(serialBlob);
					imagemFile.setEndImagem(paragrafoQ.getImagem());

					paragrafo.setImagemFile(imagemFile);

					paragrafo.setOrdem(questao.getParagrafos().size());
					questao.getParagrafos().add(paragrafo);
				}
			}

			questaoDAO.salvar(questao);

			for(scraping.Alternativa alternativaQ : questaoQ.getAlternativas())
			{
				modelo.questao.Alternativa alternativa = new Alternativa();
				alternativa.setQuestao(questao);
				if(alternativaQ.getTexto() != null && !alternativaQ.getTexto().equals(""))
				{
					String str = alternativaQ.getTexto();
					if(str.charAt(str.length() - 1) == '.')
						str = str.substring(0, str.length() - 1);

					if(str.charAt(str.length() - 1) == ';')
						str = str.substring(0, str.length() - 1);

					alternativa.setTexto(str);
					alternativa.setCorreta(alternativaQ.isCorreta());
					alternativa.setOrdem(questao.getAlternativas().size());
					questao.getAlternativas().add(alternativa);
				}
			}

			questaoDAO.salvar(questao);
		}
		FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Carregamento realizado com sucesso.", ""));
	}

	public QuestaoQ deSerializar(String nome)
	{
		QuestaoQ questao = null;
		try
		{
			FileInputStream fin = new FileInputStream("C://QuestoesQ//" + nome + ".ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			questao = (QuestaoQ) ois.readObject();
			ois.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return questao;
	}

	private SerialBlob getImage(String endImagem)
	{
		SerialBlob serialBlob = null;
		try
		{
			BufferedImage bi = ImageIO.read(new File("C://" + endImagem));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			String termos[] = endImagem.split("\\.");
			String extensao = termos[termos.length - 1];
			ImageIO.write(bi, extensao, baos);
			byte[] bytes = baos.toByteArray();
			serialBlob = new SerialBlob(bytes);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return serialBlob;
	}

	public void reorderParagrafo(ReorderEvent event)
	{
		int index = 0;
		for(Paragrafo paragrafo : questao.getParagrafos())
			paragrafo.setOrdem(index++);

		questaoDAO.salvar(questao);
	}

	public void reorderAlternativa(ReorderEvent event)
	{
		int index = 0;
		for(Alternativa alternativa : questao.getAlternativas())
			alternativa.setOrdem(index++);

		questaoDAO.salvar(questao);
	}

	public QuestaoDAO getQuestaoDAO()
	{
		return questaoDAO;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Questao> getQuestoes()
	{
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes)
	{
		this.questoes = questoes;
	}

	public Questao getQuestao()
	{
		return questao;
	}

	public void setQuestao(Questao questao)
	{
		this.questao = questao;
	}

	public boolean isCadastro()
	{
		return cadastro;
	}

	public void setCadastro(boolean cadastro)
	{
		this.cadastro = cadastro;
	}

	public UploadedFile getUploadedFile()
	{
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile)
	{
		this.uploadedFile = uploadedFile;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
		if(questao!=null)
			FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
			.add("history.pushState({}, null, 'questao?questao=" + questao.getId() + "&tab=" + activeIndex + "');");
	}

	public int getInicioCarregamento()
	{
		return inicioCarregamento;
	}

	public void setInicioCarregamento(int inicioCarregamento)
	{
		this.inicioCarregamento = inicioCarregamento;
	}

	public int getFimCarregamento()
	{
		return fimCarregamento;
	}

	public void setFimCarregamento(int fimCarregamento)
	{
		this.fimCarregamento = fimCarregamento;
	}

	public FiltroQuestao getFiltroQuestao()
	{
		return filtroQuestao;
	}

	public void setFiltroQuestao(FiltroQuestao filtroQuestao)
	{
		this.filtroQuestao = filtroQuestao;
	}

	public long getIdQuestao()
	{
		return idQuestao;
	}

	public void setIdQuestao(long idQuestao)
	{
		this.idQuestao = idQuestao;
	}

	public ConfigDownload getConfigDownload()
	{
		return configDownload;
	}

	public void setConfigDownload(ConfigDownload configDownload)
	{
		this.configDownload = configDownload;
	}

}