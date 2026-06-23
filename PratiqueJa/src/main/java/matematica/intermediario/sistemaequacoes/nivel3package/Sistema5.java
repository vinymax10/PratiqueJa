package matematica.intermediario.sistemaequacoes.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoComparacao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirY1Y2(true);

		String resultadoCorreto = "" + sistema.tres.valor;
		String[] resolucao = ResolucaoComparacao.comparacaoX(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(z\\) pelo método da comparação.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		for(String passo : resolucao) addResolucao(passo);
	}
}
