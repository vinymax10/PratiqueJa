package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.GeradorExercicio;

public class Oposto extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(19);
		boolean neg = rand.nextBoolean();
		int x = neg ? -a : a;
		int oposto = -x;

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Qual é o oposto do número \\(" + x + "\\)?");
				break;
			case 1:
				addParagrafo("Qual número, somado a \\(" + x + "\\), resulta zero?");
				break;
			default:
				addParagrafo("Na reta numérica, qual inteiro está à mesma distância de zero que \\(" + x + "\\), mas do lado oposto?");
				break;
		}

		gerarAlternativasInteirasComNegativos(oposto);

		String opostoStr = oposto >= 0 ? "+" + oposto : "" + oposto;
		String res = "O oposto de um número é o que, somado a ele, resulta zero: \\(\\\\\\)";
		res += "\\(" + x + " + (" + opostoStr + ") = 0\\) \\(\\\\\\)";
		res += "Portanto, o oposto de \\(" + x + "\\) é \\(\\mathbf{" + oposto + "}\\).";
		setResolucao(res);
	}
}
