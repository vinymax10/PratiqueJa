package matematica.intermediario.expressaoalgebrica.nivel3package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 3 + rand.nextInt(3);
		Racional[] coeficientes = new Racional[size];

		for(int i = 0; i < size; i++)
		{
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
			if(rand.nextBoolean())
				coeficientes[i].numerador *= -1;
		}

		String variavel = "" + (char) (97 + rand.nextInt(26));

		int numX = 1 + rand.nextInt(3);
		int posX[] = new int[numX];
		for(int i = 0; i < posX.length; i++)
			posX[i] = (i * 2) + rand.nextInt(2);

		String texto = "";
		if(posicaoX(0, posX))
		{
			if(coeficientes[0].numerador != 1 && coeficientes[0].numerador != -1)
				texto += "" + coeficientes[0];
			else if(coeficientes[0].numerador == -1)
				texto += "-";

			texto += variavel;
		}
		else
			texto += "" + coeficientes[0];

		for(int i = 1; i < size; i++)
		{
			if(coeficientes[i].numerador >= 0)
				texto += "+";

			if(posicaoX(i, posX))
			{
				if(coeficientes[i].numerador != 1 && coeficientes[i].numerador != -1)
					texto += "" + coeficientes[i];
				else if(coeficientes[i].numerador == -1)
					texto += "-";

				texto += variavel;
			}
			else
				texto += "" + coeficientes[i];
		}

		Racional x = new Racional(0);
		Racional naoX = new Racional(0);

		for(int i = 0; i < size; i++)
		{
			if(posicaoX(i, posX))
				x = x.add(coeficientes[i]);
			else
				naoX = naoX.add(coeficientes[i]);
		}

		String resultadoCorreto = "";
		if(x.numerador != 0)
		{
			if(x.numerador == 1)
				resultadoCorreto += variavel;
			else if(x.numerador == -1)
				resultadoCorreto += "-" + variavel;
			else
				resultadoCorreto += x + variavel;
		}

		if(naoX.numerador > 0 && x.numerador != 0)
			resultadoCorreto += "+";

		if(naoX.numerador != 0 || x.numerador == 0)
			resultadoCorreto += naoX;

		addParagrafo("Simplifique a expressão:");
		addParagrafo("\\(" + texto + "\\)");

		if(x.numerador == 0)
		{
			gerarAlternativas(resultadoCorreto);
		}
		else
		{
			Set<String> usados = new HashSet<>();
			usados.add(resultadoCorreto);
			List<String> distratores = new ArrayList<>();
			int[] deltas = {1, -1, 2, -2, 3, -3, 4, -4};
			for(int dx : deltas)
			{
				if(distratores.size() >= 3) break;
				long cx = x.numerador + dx;
				if(cx == 0) continue;
				String alt = buildResultado(cx, naoX.numerador, variavel);
				if(usados.add(alt)) distratores.add(alt);
			}
			for(int dc : deltas)
			{
				if(distratores.size() >= 3) break;
				long cy = naoX.numerador + dc;
				String alt = buildResultado(x.numerador, cy, variavel);
				if(usados.add(alt)) distratores.add(alt);
			}
			List<String> distrLatex = new ArrayList<>();
			for(String d : distratores)
				distrLatex.add("\\(" + d + "\\)");
			embaralharEAdicionarAlternativas("\\(" + resultadoCorreto + "\\)", distrLatex);
		}

		StringBuilder termosVar = new StringBuilder();
		StringBuilder termosCte = new StringBuilder();
		for(int i = 0; i < size; i++)
		{
			long c = coeficientes[i].numerador;
			if(posicaoX(i, posX))
			{
				if(termosVar.length() > 0 && c > 0) termosVar.append("+");
				if(c == 1) termosVar.append(variavel);
				else if(c == -1) termosVar.append("-").append(variavel);
				else termosVar.append(c).append(variavel);
			}
			else
			{
				if(termosCte.length() > 0 && c > 0) termosCte.append("+");
				termosCte.append(c);
			}
		}

		String res = "Agrupamos os termos com \\(" + variavel + "\\) e as constantes: \\(\\\\\\)";
		if(termosVar.length() > 0)
			res += "Termos em \\(" + variavel + "\\): \\(" + termosVar + "\\) \\(\\\\\\)";
		if(termosCte.length() > 0)
			res += "Constantes: \\(" + termosCte + "\\\\";
		res += "" + resultadoCorreto + "\\)";
		setResolucao(res);
	}

	private String buildResultado(long cx, long cte, String var)
	{
		StringBuilder sb = new StringBuilder();
		if(cx != 0)
		{
			if(cx == 1)       sb.append(var);
			else if(cx == -1) sb.append("-").append(var);
			else              sb.append(cx).append(var);
		}
		if(cte > 0 && sb.length() > 0) sb.append("+").append(cte);
		else if(cte != 0)              sb.append(cte);
		if(sb.length() == 0)           sb.append("0");
		return sb.toString();
	}

	private boolean posicaoX(int index, int posX[])
	{
		for(int i = 0; i < posX.length; i++)
		{
			if(posX[i] == index)
				return true;
		}
		return false;
	}
}
