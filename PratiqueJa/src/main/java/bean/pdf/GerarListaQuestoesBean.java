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
import modelo.pdf.Visibilidade;
import modelo.questao.Dificuldade;
import pdf.questao.TipoGabarito;
import service.pdf.ListaQuestoesPdfException;
import service.pdf.ListaQuestoesPdfService;

@Data
@Named
@ViewScoped
public class GerarListaQuestoesBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Assunto assunto;
	private Dificuldade dificuldade;
	private Visibilidade visibilidade;
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
}
