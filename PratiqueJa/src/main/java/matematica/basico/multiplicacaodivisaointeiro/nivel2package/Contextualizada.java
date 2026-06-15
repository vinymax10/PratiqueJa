package matematica.basico.multiplicacaodivisaointeiro.nivel2package;

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
				int taxa = 10 + rand.nextInt(16);
				int horas = 10 + rand.nextInt(16);
				int variacao = -taxa * horas;
				addParagrafo("Uma câmara fria resfria o ambiente a \\(" + taxa + "\\,°C\\) por hora. Qual é a variação de temperatura em \\(" + horas + "\\) horas?");
				gerarAlternativasInteirasComNegativos(variacao);
				String res = "Cada hora representa \\(-" + taxa + "\\,°C\\): \\(\\\\\\)";
				res += "\\((-" + taxa + ") \\times " + horas + " = \\mathbf{" + variacao + "\\,°C}\\)";
				setResolucao(res);
				break;
			}
			case 1:
			{
				int prejuizo = 50 + rand.nextInt(451);
				int meses = 10 + rand.nextInt(11);
				int total = -prejuizo * meses;
				addParagrafo("Uma empresa tem um prejuízo de \\(R\\$\\," + prejuizo + "\\) por mês. Qual é o saldo total após \\(" + meses + "\\) meses?");
				gerarAlternativasInteirasComNegativos(total);
				String res = "Cada mês representa \\(-" + prejuizo + "\\) reais: \\(\\\\\\)";
				res += "\\(" + meses + " \\times (-" + prejuizo + ") = \\mathbf{" + total + "\\text{ reais}}\\)";
				setResolucao(res);
				break;
			}
			default:
			{
				int velocidade = 10 + rand.nextInt(21);
				int tempo = 10 + rand.nextInt(11);
				int deslocamento = -velocidade * tempo;
				addParagrafo("Um submarino desce \\(" + velocidade + "\\,m\\) por minuto. Qual é a variação de profundidade em \\(" + tempo + "\\) minutos?");
				gerarAlternativasInteirasComNegativos(deslocamento);
				String res = "A descida é representada como \\(-" + velocidade + "\\,m/min\\): \\(\\\\\\)";
				res += "\\((-" + velocidade + ") \\times " + tempo + " = \\mathbf{" + deslocamento + "\\,m}\\)";
				setResolucao(res);
				break;
			}
		}
	}
}
