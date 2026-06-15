package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;

import java.util.ArrayList;
import java.util.List;

public class IdentificarSinal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9);
		int b = 1 + rand.nextInt(9);
		boolean aNeg = rand.nextBoolean();
		boolean bNeg = rand.nextBoolean();
		boolean sameSign = (aNeg == bNeg);
		int product = a * b;

		String aStr = aNeg ? "(-" + a + ")" : "(+" + a + ")";
		String bStr = bNeg ? "(-" + b + ")" : "(+" + b + ")";

		addParagrafo("Calcule: \\(" + aStr + " \\times " + bStr + "\\)");

		String posRes = "\\(+" + product + "\\)";
		String negRes = "\\(-" + product + "\\)";
		String correta, explicacao;

		List<String> distratores = new ArrayList<>();
		if (sameSign)
		{
			correta = posRes + " — sinais iguais produzem resultado positivo";
			distratores.add(negRes + " — sinais diferentes produzem resultado negativo");
			distratores.add(posRes + " — sinais diferentes produzem resultado positivo");
			distratores.add(negRes + " — sinais iguais produzem resultado negativo");
			explicacao = "Sinais iguais: o produto é \\(\\mathbf{positivo}\\).";
		}
		else
		{
			correta = negRes + " — sinais diferentes produzem resultado negativo";
			distratores.add(posRes + " — sinais iguais produzem resultado positivo");
			distratores.add(negRes + " — sinais iguais produzem resultado negativo");
			distratores.add(posRes + " — sinais diferentes produzem resultado positivo");
			explicacao = "Sinais diferentes: o produto é \\(\\mathbf{negativo}\\).";
		}

		embaralharEAdicionarAlternativas(correta, distratores);

		String sinal = sameSign ? "+" : "-";
		String res = explicacao + " \\(\\\\\\)";
		res += "\\(|" + a + "| \\times |" + b + "| = " + product + "\\) \\(\\\\\\)";
		res += "\\(" + aStr + " \\times " + bStr + " = \\mathbf{" + sinal + product + "}\\)";
		setResolucao(res);
	}
}
