package bean.pdf;

import java.io.Serializable;

import bean.util.Mensagem;
import infra.Navegacao;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.academico.Assunto;
import modelo.exercicio.Nivel;
import modelo.pdf.Visibilidade;
import service.pdf.ListaExerciciosPdfException;
import service.pdf.ListaExerciciosPdfService;
import service.pdf.ListaExerciciosPdfService.ResultadoLote;

@Data
@Named
@ViewScoped
public class GerarListaExerciciosBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Assunto assunto;
	private Nivel nivel;
	private boolean comAlternativas = true;
	private Visibilidade visibilidade;

	@Inject
	private ListaExerciciosPdfService listaExerciciosPdfService;

	public void gerar()
	{
		if(assunto == null || nivel == null || visibilidade == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Preencha todos os campos obrigatórios.");
			return;
		}

		try
		{
			listaExerciciosPdfService.gerar(assunto, nivel, visibilidade, comAlternativas);
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "PDF gerado com sucesso.");
			Navegacao.redirect("/administracao/conteudo/pdf/exercicio/list.xhtml");
		}
		catch(ListaExerciciosPdfException e)
		{
			Mensagem.send("growl", e.isErro() ? FacesMessage.SEVERITY_ERROR : FacesMessage.SEVERITY_WARN, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Erro ao gerar PDF: " + e.getMessage());
		}
	}

	public void gerarTodos()
	{
		try
		{
			ResultadoLote resultado = listaExerciciosPdfService.gerarTodos();

			String msg = resultado.getGerados() + " PDF(s) gerado(s)";
			if(resultado.getIgnorados() > 0)
				msg += ", " + resultado.getIgnorados() + " ignorado(s) (sem exercício padrão)";
			if(resultado.getErros() > 0)
				msg += ", " + resultado.getErros() + " erro(s)";
			msg += ".";

			FacesMessage.Severity severity = resultado.getErros() > 0 ? FacesMessage.SEVERITY_WARN : FacesMessage.SEVERITY_INFO;
			Mensagem.send("growl", severity, msg);
		}
		catch(ListaExerciciosPdfException e)
		{
			Mensagem.send("growl", e.isErro() ? FacesMessage.SEVERITY_ERROR : FacesMessage.SEVERITY_WARN, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Erro ao gerar PDFs: " + e.getMessage());
		}
	}
}
