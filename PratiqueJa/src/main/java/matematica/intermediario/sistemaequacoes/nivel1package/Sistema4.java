package matematica.intermediario.sistemaequacoes.nivel1package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoSubtituicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirX1(false);

		String resultadoCorreto = "" + sistema.y;
		String resolucao = ResolucaoSubtituicao.substituicaoY(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(y\\) pelo método da substituição.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
