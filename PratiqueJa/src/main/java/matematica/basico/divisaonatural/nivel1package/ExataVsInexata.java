package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

import java.util.ArrayList;
import java.util.List;

public class ExataVsInexata extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(8);
		boolean exata = rand.nextBoolean();

		int a;
		if (exata)
			a = b * (2 + rand.nextInt(8));
		else
		{
			a = b * (2 + rand.nextInt(8)) + 1 + rand.nextInt(b - 1);
		}

		int q = a / b;
		int r = a % b;

		addParagrafo("A divisão \\(" + a + " \\div " + b + "\\) é exata ou inexata?");

		List<String> distratores = new ArrayList<>();
		String correta;
		if (exata)
		{
			correta = "Exata, pois o resto é zero";
			distratores.add("Inexata, pois o resto é diferente de zero");
			distratores.add("Exata, pois o dividendo é maior que o divisor");
			distratores.add("Inexata, pois o quociente não é inteiro");
		}
		else
		{
			correta = "Inexata, pois o resto é " + r;
			distratores.add("Exata, pois o resto é zero");
			distratores.add("Exata, pois " + a + " é múltiplo de " + b);
			distratores.add("Inexata, pois o dividendo é ímpar");
		}

		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Calculamos: \\(" + a + " \\div " + b + " = " + q;
		if (exata)
		{
			res += "\\) com resto \\(0\\). \\(\\\\\\)";
			res += "Como o resto é \\(0\\), a divisão é \\(\\mathbf{exata}\\). \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
		}
		else
		{
			res += "\\) com resto \\(" + r + "\\). \\(\\\\\\)";
			res += "Como o resto é diferente de \\(0\\), a divisão é \\(\\mathbf{inexata}\\). \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)";
		}
		setResolucao(res);
	}
}
