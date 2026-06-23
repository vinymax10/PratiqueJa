package matematica.intermediario.sistemaequacoes.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoComparacao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirX1X2(false);

		String resultadoCorreto = "" + sistema.y;
		String[] resolucao = ResolucaoComparacao.comparacaoY(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(y\\) pelo método da comparação.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		for(String passo : resolucao) addResolucao(passo);
	}
}
