package matematica.intermediario.sistemaequacoes.nivel1package;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoAdicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirY1(false);

		String resultadoCorreto = "" + sistema.x;
		String resolucao = ResolucaoAdicao.adicaoX(sistema);
		String texto = sistema.latex();

		addParagrafo("Encontre \\(x\\) pelo método da adição.");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
