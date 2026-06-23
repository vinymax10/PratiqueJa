package matematica.basico.planocartesiano.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int px = 1 + rand.nextInt(8);
		int py = 1 + rand.nextInt(8);
		while (py == px) py = 1 + rand.nextInt(8);
		if (rand.nextBoolean()) px = -px;
		if (rand.nextBoolean()) py = -py;

		boolean pedirEixoX = rand.nextBoolean();
		int resposta = pedirEixoX ? Math.abs(py) : Math.abs(px);
		int coordOutra = pedirEixoX ? Math.abs(px) : Math.abs(py);
		int coordUsada = pedirEixoX ? py : px;
		String eixo = pedirEixoX ? "x" : "y";
		String nomeCoordenada = pedirEixoX ? "ordenada" : "abscissa";

		addParagrafo("Qual é a distância do ponto \\( P(" + px + ",\\;" + py + ") \\) ao eixo \\(" + eixo + "\\)?");

		String correta = "" + resposta;
		List<String> alts = new ArrayList<>();
		if (coordOutra != resposta) alts.add("" + coordOutra); // erro clássico: usar a coordenada errada
		for (int d = 1; alts.size() < 3; d++)
		{
			String a = "" + (resposta + d);
			if (!a.equals(correta) && !alts.contains(a)) alts.add(a);
			if (alts.size() < 3)
			{
				int v = resposta - d;
				if (v > 0)
				{
					String b = "" + v;
					if (!b.equals(correta) && !alts.contains(b)) alts.add(b);
				}
			}
		}
		embaralharEAdicionarAlternativas(correta, alts);

		if (pedirEixoX)
			addResolucao("A distância de um ponto a um eixo coordenado é medida perpendicularmente até ele. A distância ao eixo \\(x\\) é o módulo da ordenada \\(|y|\\), pois a ordenada indica o afastamento vertical.");
		else
			addResolucao("A distância de um ponto a um eixo coordenado é medida perpendicularmente até ele. A distância ao eixo \\(y\\) é o módulo da abscissa \\(|x|\\), pois a abscissa indica o afastamento horizontal.");
		addResolucao("Portanto: \\(d = |" + coordUsada + "| = " + resposta + "\\)");
	}
}
