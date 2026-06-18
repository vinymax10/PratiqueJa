package matematica.intermediario.potenciacao.nivel1package;

import java.util.Arrays;

import matematica.GeradorExercicio;

public class Potenciacao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] bases = {2, 3, 4, 5};
		int a = bases[rand.nextInt(bases.length)];
		int p = 2 + rand.nextInt(3); // 2..4
		boolean par = (p % 2 == 0);
		int resultado = par ? (int) Math.pow(a, p) : -(int) Math.pow(a, p);

		addParagrafo("Calcule:");
		addParagrafo("\\((-" + a + ")^{" + p + "}\\)");
		embaralharEAdicionarAlternativas(
			"" + resultado,
			Arrays.asList(
				"" + (-resultado),
				"" + (int) Math.pow(a, p - 1),
				"" + ((int) Math.pow(a + 1, p) * (par ? 1 : -1))
			)
		);

		StringBuilder sb = new StringBuilder();
		sb.append("\\((-").append(a).append(")^{").append(p).append("} = ");
		for (int i = 0; i < p; i++)
		{
			if (i > 0) sb.append(" \\cdot ");
			sb.append("(-").append(a).append(")");
		}
		sb.append("\\)");
		sb.append("\\(\\\\\\)");
		sb.append("Expoente ").append(par ? "par" : "ímpar")
		  .append(" → sinal ").append(par ? "positivo" : "negativo").append(".");
		sb.append("\\(\\\\\\)");
		sb.append("\\((-").append(a).append(")^{").append(p).append("} = \\mathbf{").append(resultado).append("}\\)");
		setResolucao(sb.toString());
	}
}
