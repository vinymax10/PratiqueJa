package matematica.intermediario.sistemaequacoes.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoSubtituicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirY1(false);

		int x = sistema.x;
		int y = sistema.y;
		String resultadoCorreto = "\\(x=" + x + ",\\;y=" + y + "\\)";
		String[] resolucao = ResolucaoSubtituicao.substituicaoXY(sistema);
		String texto = sistema.latex();

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(x=" + (x + 1) + ",\\;y=" + y + "\\)");
		distratores.add("\\(x=" + x + ",\\;y=" + (y + 1) + "\\)");
		distratores.add("\\(x=" + y + ",\\;y=" + x + "\\)");

		addParagrafo("Encontre a solução \\((x,\\,y)\\) pelo método da substituição.");
		addParagrafo("\\(" + texto + "\\)");
		embaralharEAdicionarAlternativas(resultadoCorreto, distratores);
		for(String passo : resolucao) addResolucao(passo);
	}
}
