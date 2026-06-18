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
import modelo.pdf.VisibilidadePdf;
import modelo.questao.Dificuldade;
import pdf.questao.TipoGabarito;
import service.pdf.ListaQuestoesPdfException;
import service.pdf.ListaQuestoesPdfService;
import service.pdf.ListaQuestoesPdfService.ResultadoLote;

@Data
@Named
@ViewScoped
public class GerarListaQuestoesBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Assunto assunto;
	private Dificuldade dificuldade;
	private VisibilidadePdf visibilidade;
	private TipoGabarito tipoGabarito = TipoGabarito.COMPLETO;

	@Inject
	private ListaQuestoesPdfService listaQuestoesPdfService;

	public void gerar()
	{
		if(assunto == null || visibilidade == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Preencha todos os campos obrigatórios.");
			return;
		}

		try
		{
			listaQuestoesPdfService.gerar(assunto, dificuldade, visibilidade, true, tipoGabarito);
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "PDF gerado com sucesso.");
			Navegacao.redirect("/administracao/conteudo/pdf/questao/list.xhtml");
		}
		catch(ListaQuestoesPdfException e)
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
			ResultadoLote resultado = listaQuestoesPdfService.gerarTodos(true, tipoGabarito);

			String msg = resultado.getGerados() + " PDF(s) gerado(s)";
			if(resultado.getIgnorados() > 0)
				msg += ", " + resultado.getIgnorados() + " ignorado(s) (questões insuficientes)";
			if(resultado.getErros() > 0)
				msg += ", " + resultado.getErros() + " erro(s)";
			msg += ".";

			FacesMessage.Severity severity = resultado.getErros() > 0 ? FacesMessage.SEVERITY_WARN : FacesMessage.SEVERITY_INFO;
			Mensagem.send("growl", severity, msg);
		}
		catch(ListaQuestoesPdfException e)
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
