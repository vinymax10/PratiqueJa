package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
			{
				int taxa = 2 + rand.nextInt(4);
				int horas = 2 + rand.nextInt(5);
				int variacao = -taxa * horas;
				addParagrafo("A temperatura cai \\(" + taxa + "\\,°C\\) por hora. Qual é a variação total em \\(" + horas + "\\) horas?");
				gerarAlternativasInteirasComNegativos(variacao);
				String res0 = "A queda de temperatura é representada como \\(-" + taxa + "\\,°C/h\\): \\(\\\\\\)";
				res0 += "\\((-" + taxa + ") \\times " + horas + " = \\mathbf{" + variacao + "\\,°C}\\)";
				setResolucao(res0);
				break;
			}
			case 1:
			{
				int divida = 2 + rand.nextInt(8);
				int n = 2 + rand.nextInt(5);
				int total = -divida * n;
				addParagrafo("Uma pessoa tem \\(" + n + "\\) dívidas de \\(" + divida + "\\) reais cada. Qual é o saldo total?");
				gerarAlternativasInteirasComNegativos(total);
				String res1 = "Cada dívida representa \\(-" + divida + "\\) reais: \\(\\\\\\)";
				res1 += "\\(" + n + " \\times (-" + divida + ") = \\mathbf{" + total + "\\text{ reais}}\\)";
				setResolucao(res1);
				break;
			}
			default:
			{
				int porPessoa = -(2 + rand.nextInt(4));
				int n = 2 + rand.nextInt(5);
				int divida = porPessoa * n;
				addParagrafo("Uma dívida de \\(" + Math.abs(divida) + "\\) reais é dividida igualmente entre \\(" + n + "\\) pessoas. Quanto cada pessoa deve?");
				gerarAlternativasInteirasComNegativos(porPessoa);
				String res2 = "A dívida total é representada como \\(-" + Math.abs(divida) + "\\) reais: \\(\\\\\\)";
				res2 += "\\((-" + Math.abs(divida) + ") \\div " + n + " = \\mathbf{" + porPessoa + "\\text{ reais}}\\)";
				setResolucao(res2);
				break;
			}
		}
	}
}
