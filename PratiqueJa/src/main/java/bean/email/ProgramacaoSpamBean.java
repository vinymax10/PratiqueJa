package bean.email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.primefaces.event.ReorderEvent;

import bean.academico.AssuntoBean;
import bean.util.Mensagem;
import dao.email.ProgramacaoSpamDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.academico.Assunto;
import modelo.email.ProgramacaoSpam;
import service.publicacao.EnvioPostService;

@Data
@Named
@ViewScoped
public class ProgramacaoSpamBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramacaoSpamDAO programacaoSpamDAO;

	@Inject
	private ConfigSpamBean configSpamBean;

	private ProgramacaoSpam programacaoSpam;

	private String nome = "Programação de Spam";

	@Inject
	private AssuntoBean assuntoBean;

	private int quantidade = 1;

	private int ordem;

	@Inject
	private EnvioPostService envioPostService;

	public String cadastrar()
	{
		programacaoSpam = new ProgramacaoSpam();
		return "";
	}

	public String adicionar()
	{
		ProgramacaoSpam programacaoSpamNovo;
		for(int i = 0; i < quantidade; i++)
		{
			programacaoSpamNovo = programacaoSpam.clone();
			programacaoSpamNovo.setConfigSpam(configSpamBean.getConfigSpam());
			programacaoSpamNovo.setOrdem(ordem + i);
			programacaoSpamDAO.salvar(programacaoSpamNovo);
			configSpamBean.getConfigSpam().getProgramacoesSpam().add(ordem + i, programacaoSpamNovo);
			organizarOrdem();
		}

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");

		return "";
	}

	private void organizarOrdem()
	{
		List<ProgramacaoSpam> programacoesSpam = configSpamBean.getConfigSpam().getProgramacoesSpam();
		for(int i = 0; i < programacoesSpam.size(); i++)
		{
			ProgramacaoSpam ps = programacoesSpam.get(i);
			ps.setOrdem(i);
			ps.updateData();
			programacaoSpamDAO.salvar(ps);
		}
	}

	public void onRowReorder(ReorderEvent event)
	{
		organizarOrdem();
	}

	public String salvar()
	{
		try
		{
			programacaoSpam = programacaoSpamDAO.salvar(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().remove(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().add(programacaoSpam.getOrdem(), programacaoSpam);

			organizarOrdem();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String remover(ProgramacaoSpam programacaoSpam)
	{
		try
		{
			configSpamBean.getConfigSpam().getProgramacoesSpam().remove(programacaoSpam);

			programacaoSpamDAO.remover(programacaoSpam);
			organizarOrdem();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}

	public String programacaoDefault()
	{
		removerTodos();
		List<Assunto> assuntos = assuntoBean.getListaAtivas();
		for(int i = 0; i < assuntos.size(); i++)
		{
			Assunto assunto = assuntos.get(i);
			programacaoSpam = new ProgramacaoSpam();
			programacaoSpam.setOrdem(i);
			programacaoSpam.setConfigSpam(configSpamBean.getConfigSpam());
			programacaoSpam.updateData();
			programacaoSpam.setAssunto(assunto);
			programacaoSpamDAO.salvar(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().add(i, programacaoSpam);
		}

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Programação Default realizada com sucesso.");

		return "";
	}

	private void removerTodos()
	{
		while(configSpamBean.getConfigSpam().getProgramacoesSpam().size() > 0)
		{
			programacaoSpam = configSpamBean.getConfigSpam().getProgramacoesSpam().get(0);
			programacaoSpamDAO.remover(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().remove(0);
		}
	}

	public String removerTodosAcao()
	{
		removerTodos();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Remoção de todas as Programaões realizada com sucesso.");

		return "";
	}

	public ProgramacaoSpam programacaoSpamDefault()
	{
		ProgramacaoSpam ps = new ProgramacaoSpam();
		ps.setConfigSpam(configSpamBean.getConfigSpam());
		ps.setData(LocalDate.now());
		return ps;
	}

	public void gerarConteudo(Assunto assunto)
	{
		ProgramacaoSpam ps = programacaoSpamDefault();
		ps.setAssunto(assunto);
		programacaoSpamDAO.salvar(ps);
//		envioPostService.acorda();
	}
}
