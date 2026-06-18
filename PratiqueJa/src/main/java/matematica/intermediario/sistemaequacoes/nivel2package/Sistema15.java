package matematica.intermediario.sistemaequacoes.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoComparacao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirY1Y2(false);

		String resultadoCorreto = "" + sistema.x;
		String resolucao = ResolucaoComparacao.comparacaoX(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(x\\) pelo método da comparação.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
