package matematica.intermediario.sistemaequacoes.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoComparacao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirX1X2(true);

		String resultadoCorreto = "" + sistema.tres.valor;
		String resolucao = ResolucaoComparacao.comparacaoY(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(z\\) pelo método da comparação.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
