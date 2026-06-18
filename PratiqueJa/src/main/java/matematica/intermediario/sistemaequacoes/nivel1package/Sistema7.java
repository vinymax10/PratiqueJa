package matematica.intermediario.sistemaequacoes.nivel1package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoSubtituicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirY1(false);

		String resultadoCorreto = "" + sistema.x;
		String resolucao = ResolucaoSubtituicao.substituicaoX(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(x\\) pelo método da substituição.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
