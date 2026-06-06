package matematica.intermediario.sistemaequacoes.nivel1package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoAdicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirX1(false);

		String resultadoCorreto = "" + sistema.y;
		String resolucao = ResolucaoAdicao.adicaoY(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(y\\) pelo método da adição.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
