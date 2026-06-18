package matematica.intermediario.sistemaequacoes.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoSubtituicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirY1(true);

		String resultadoCorreto = "" + sistema.tres.valor;
		String resolucao = ResolucaoSubtituicao.substituicaoX(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(z\\) pelo método da substituição.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
