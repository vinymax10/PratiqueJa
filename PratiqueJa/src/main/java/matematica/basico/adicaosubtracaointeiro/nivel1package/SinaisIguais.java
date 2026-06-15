package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.adicaosubtracaointeiro.ResolucaoASInteiro;

public class SinaisIguais extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean ambosNegativos = rand.nextBoolean();
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		if (ambosNegativos) { a = -a; b = -b; }

		int resultado = a + b;

		String aStr = a > 0 ? "(+" + a + ")" : "(" + a + ")";
		String bStr = b > 0 ? "(+" + b + ")" : "(" + b + ")";

		int tipo = rand.nextInt(2);
		if (tipo == 0)
		{
			addParagrafo("Calcule: \\(" + aStr + " + " + bStr + "\\)");
		}
		else if (ambosNegativos)
		{
			addParagrafo("A temperatura era \\(" + a + "\\,°C\\) e caiu mais \\(" + Math.abs(b) + "\\,°C\\). Qual é a temperatura final?");
		}
		else
		{
			addParagrafo("Um time marcou \\(" + a + "\\) pontos no 1° tempo e \\(" + b + "\\) pontos no 2°. Quantos pontos marcou no total?");
		}

		gerarAlternativasInteirasComNegativos(resultado);
		setResolucao(ResolucaoASInteiro.soma(a, b));
	}
}
