package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

import java.util.ArrayList;
import java.util.List;

public class TermosDivisao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 2 + rand.nextInt(9);
		int b = 2 + rand.nextInt(9);
		int a = q * b;
		int r = 0;

		int tipo = rand.nextInt(4);
		String numero;
		String termoCorreto;

		switch (tipo)
		{
			case 0:
				numero = "" + a;
				termoCorreto = "Dividendo";
				break;
			case 1:
				numero = "" + b;
				termoCorreto = "Divisor";
				break;
			case 2:
				numero = "" + q;
				termoCorreto = "Quociente";
				break;
			default:
				numero = "" + r;
				termoCorreto = "Resto";
				break;
		}

		addParagrafo("Na divisão \\(" + a + " \\div " + b + " = " + q + "\\), o número \\(" + numero + "\\) é chamado de:");

		List<String> distratores = new ArrayList<>();
		distratores.add("Dividendo");
		distratores.add("Divisor");
		distratores.add("Quociente");
		distratores.add("Resto");
		distratores.remove(termoCorreto);

		embaralharEAdicionarAlternativas(termoCorreto, distratores);

		String res = "Na divisão \\(a \\div b = q\\) com resto \\(r\\): \\(\\\\\\)";
		res += "Dividendo \\((" + a + ")\\): o número que está sendo dividido. \\(\\\\\\)";
		res += "Divisor \\((" + b + ")\\): o número pelo qual dividimos. \\(\\\\\\)";
		res += "Quociente \\((" + q + ")\\): o resultado da divisão. \\(\\\\\\)";
		res += "Resto \\((" + r + ")\\): o que sobra quando a divisão é exata o resto é zero. \\(\\\\\\)";
		res += "Portanto, \\(" + numero + "\\) é o \\(\\mathbf{" + termoCorreto + "}\\).";
		setResolucao(res);
	}
}
