package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;

import java.util.ArrayList;
import java.util.List;

public class PotenciaNegativo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(4);
		int n = 2 + rand.nextInt(3);
		int absResult = (int) Math.pow(a, n);
		boolean par = (n % 2 == 0);
		int resultado = par ? absResult : -absResult;

		boolean tipoDistincao = rand.nextBoolean() && par;

		if (tipoDistincao)
		{
			addParagrafo("Qual é o valor de \\((-" + a + ")^{" + n + "}\\)? (Atenção: o sinal está dentro do parêntese.)");

			List<String> distratores = new ArrayList<>();
			distratores.add("\\(-" + absResult + "\\) — pois a base é negativa");
			distratores.add("\\(-" + (absResult + 1) + "\\)");
			distratores.add("\\(+" + (absResult - 1) + "\\)");
			embaralharEAdicionarAlternativas("\\(+" + absResult + "\\) — pois o expoente é par", distratores);

			String expandido = "Expandindo: \\((-" + a + ")^{" + n + "} = ";
			for (int i = 0; i < n; i++) { if (i > 0) expandido += " \\times "; expandido += "(-" + a + ")"; }
			expandido += "\\)";
			addResolucao("Como o expoente \\(" + n + "\\) é par, o resultado é \\(\\mathbf{positivo}\\).");
			addResolucao(expandido);
			addResolucao("\\(" + n + "\\) fatores negativos (par) → \\(+" + a + "^{" + n + "} = \\mathbf{+" + absResult + "}\\)");
			addResolucao("Atenção: \\(-" + a + "^{" + n + "} = -" + absResult + "\\) (o \\(-\\) fora do parêntese não eleva junto).");
		}
		else
		{
			addParagrafo("Calcule \\((-" + a + ")^{" + n + "}\\).");
			gerarAlternativasInteirasComNegativos(resultado);

			String expandido = "\\((-" + a + ")^{" + n + "} = ";
			for (int i = 0; i < n; i++) { if (i > 0) expandido += " \\times "; expandido += "(-" + a + ")"; }
			expandido += "\\)";
			addResolucao("Expandimos a potência como produto de \\(" + n + "\\) fatores:");
			addResolucao(expandido);
			addResolucao("Há \\(" + n + "\\) fatores negativos "
				+ (par ? "(par) → resultado \\(\\mathbf{positivo}\\)." : "(ímpar) → resultado \\(\\mathbf{negativo}\\)."));
			addResolucao("\\(" + a + "^{" + n + "} = " + absResult + " \\to \\mathbf{" + (par ? "+" : "-") + absResult + "}\\)");
		}
	}
}
