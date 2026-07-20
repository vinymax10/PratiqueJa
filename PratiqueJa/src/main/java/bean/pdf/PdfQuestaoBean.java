package bean.pdf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.pdf.TipoPdf;
import service.pdf.ListaQuestoesPdfService;
import service.pdf.ListaQuestoesPdfService.ResultadoLote;

@Named
@ViewScoped
public class PdfQuestaoBean extends PdfListaBeanBase
{
	@Inject
	private ListaQuestoesPdfService listaQuestoesPdfService;

	public PdfQuestaoBean()
	{
		super("PDF de Lista de Questões", "/administracao/conteudo/pdf/questao/");
	}

	@Override
	protected TipoPdf getTipoPdf()
	{
		return TipoPdf.ListaQuestoes;
	}

	@Override
	protected ResumoLote gerarLote()
	{
		ResultadoLote r = listaQuestoesPdfService.gerarTodos();
		return new ResumoLote(r.getGerados(), r.getIgnorados(), r.getErros());
	}

	@Override
	protected String motivoIgnorado()
	{
		return "sem questões";
	}
}
