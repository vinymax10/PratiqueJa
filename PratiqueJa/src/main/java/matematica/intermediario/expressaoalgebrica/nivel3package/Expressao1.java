package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao1 extends GeradorExercicio
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
		gerarAlternativas(resultadoCorreto);
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
