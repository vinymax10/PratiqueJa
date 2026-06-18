package matematica.intermediario.sistemaequacoes.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoAdicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirX1(true);

		String resultadoCorreto = "" + sistema.tres.valor;
		String resolucao = ResolucaoAdicao.adicaoY(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(z\\) pelo método da adição.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
