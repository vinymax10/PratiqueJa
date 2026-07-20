package bean.pdf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.pdf.TipoPdf;
import service.pdf.ListaExerciciosPdfService;
import service.pdf.ListaExerciciosPdfService.ResultadoLote;

@Named
@ViewScoped
public class PdfExercicioBean extends PdfListaBeanBase
{
	@Inject
	private ListaExerciciosPdfService listaExerciciosPdfService;

	public PdfExercicioBean()
	{
		super("PDF de Lista de Exercícios", "/administracao/conteudo/pdf/exercicio/");
	}

	@Override
	protected TipoPdf getTipoPdf()
	{
		return TipoPdf.ListaExercicios;
	}

	@Override
	protected ResumoLote gerarLote()
	{
		ResultadoLote r = listaExerciciosPdfService.gerarTodos();
		return new ResumoLote(r.getGerados(), r.getIgnorados(), r.getErros());
	}

	@Override
	protected String motivoIgnorado()
	{
		return "sem exercício padrão";
	}
}
