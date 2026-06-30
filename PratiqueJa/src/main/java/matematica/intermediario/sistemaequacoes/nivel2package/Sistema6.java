package matematica.intermediario.sistemaequacoes.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.sistemaequacoes.ResolucaoAdicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;

public class Sistema6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		SistemaEquacoes sistema = new SistemaEquacoes();
		sistema.construirY1(false);

		int x = sistema.x;
		int y = sistema.y;
		String resultadoCorreto = "\\(x=" + x + ",\\;y=" + y + "\\)";
		String[] resolucao = ResolucaoAdicao.adicaoXY(sistema);
		String texto = sistema.latex();

		// Quando x==y, distrator (y,x) == (x,y) == correta; deslocar ambos em 1
		String d3 = (x == y)
			? "\\(x=" + (x + 1) + ",\\;y=" + (y + 1) + "\\)"
			: "\\(x=" + y + ",\\;y=" + x + "\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(x=" + (x + 1) + ",\\;y=" + y + "\\)");
		distratores.add("\\(x=" + x + ",\\;y=" + (y + 1) + "\\)");
		distratores.add(d3);

		addParagrafo("Encontre a solução \\((x,\\,y)\\) pelo método da adição.");
		addParagrafo("\\(" + texto + "\\)");
		embaralharEAdicionarAlternativas(resultadoCorreto, distratores);
		for(String passo : resolucao) addResolucao(passo);
	}
}
