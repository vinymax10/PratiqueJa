package matematica.basico.multiplicacaodivisaointeiro.nivel3package;

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
				int prejuizo = 1000 + rand.nextInt(9001);
				int meses = 12 + rand.nextInt(25);
				long total = (long) -prejuizo * meses;
				addParagrafo("Uma empresa registra um prejuízo mensal de \\(R\\$\\," + prejuizo + "\\). Qual é o saldo acumulado após \\(" + meses + "\\) meses?");
				gerarAlternativasInteirasComNegativos((int) total);
				String res = "Cada mês representa \\(-" + prejuizo + "\\) reais: \\(\\\\\\)";
				res += "\\(" + meses + " \\times (-" + prejuizo + ") = \\mathbf{" + total + "\\text{ reais}}\\)";
				setResolucao(res);
				break;
			}
			case 1:
			{
				int profundidade = 100 + rand.nextInt(401);
				int n = 2 + rand.nextInt(9);
				int porMergulhador = -(profundidade / n);
				int divisaoExata = porMergulhador * n;
				addParagrafo("Uma dívida de exploração marítima de \\(R\\$\\," + Math.abs(divisaoExata) + "\\) é dividida igualmente entre \\(" + n + "\\) expedições. Qual é o valor por expedição?");
				gerarAlternativasInteirasComNegativos(porMergulhador);
				String res = "A dívida total é \\(-" + Math.abs(divisaoExata) + "\\) reais: \\(\\\\\\)";
				res += "\\((-" + Math.abs(divisaoExata) + ") \\div " + n + " = \\mathbf{" + porMergulhador + "\\text{ reais}}\\)";
				setResolucao(res);
				break;
			}
			default:
			{
				int taxaAnual = 200 + rand.nextInt(801);
				int anos = 10 + rand.nextInt(16);
				long variacao = (long) -taxaAnual * anos;
				addParagrafo("Um glaciar recua \\(" + taxaAnual + "\\,m\\) por ano devido ao aquecimento global. Qual é a variação total em \\(" + anos + "\\) anos?");
				gerarAlternativasInteirasComNegativos((int) variacao);
				String res = "O recuo anual é \\(-" + taxaAnual + "\\,m\\): \\(\\\\\\)";
				res += "\\((-" + taxaAnual + ") \\times " + anos + " = \\mathbf{" + variacao + "\\,m}\\)";
				setResolucao(res);
				break;
			}
		}
	}
}
