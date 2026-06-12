package matematica.avancado.pa.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a1Val = 1 + rand.nextInt(15);
		int rVal = 1 + rand.nextInt(10);
		Racional a1 = new Racional(a1Val);
		Racional r = new Racional(rVal);

		int i = 1 + rand.nextInt(3);
		int j = i + 2 + rand.nextInt(4);

		int aiVal = a1Val + (i - 1) * rVal;
		int ajVal = a1Val + (j - 1) * rVal;
		int diff = ajVal - aiVal;
		int denom = j - i;

		addParagrafo("Determine a razão \\(r\\) da PA sabendo que:");
		addParagrafo("\\(a_{" + i + "} = " + aiVal + " \\quad \\text{e} \\quad a_{" + j + "} = " + ajVal + "\\)");
		gerarAlternativas("" + rVal);

		String res = "Usando a fórmula do termo geral \\(a_j = a_i + (j - i) \\cdot r\\): \\(\\\\\\)";
		res += "\\(" + ajVal + " = " + aiVal + " + (" + j + " - " + i + ") \\cdot r \\\\";
		res += ajVal + " = " + aiVal + " + " + denom + "r \\\\";
		res += denom + "r = " + diff + " \\\\";
		res += "r = \\dfrac{" + diff + "}{" + denom + "} = \\mathbf{" + rVal + "}\\)";
		setResolucao(res);
	}
}
