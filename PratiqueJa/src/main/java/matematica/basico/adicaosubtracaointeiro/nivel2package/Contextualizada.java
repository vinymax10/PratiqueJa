package matematica.basico.adicaosubtracaointeiro.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
			{
				int divida = -(20 + rand.nextInt(80));
				int deposito = 30 + rand.nextInt(120);
				int saldo = divida + deposito;
				addParagrafo("Uma conta bancária tinha um saldo de \\(" + divida + "\\) reais (dívida). Foi feito um depósito de \\(" + deposito + "\\) reais. Qual é o saldo final?");
				gerarAlternativasInteirasComNegativos(saldo);
				String res0 = "Somamos o depósito ao saldo inicial negativo: \\(\\\\\\)";
				res0 += "\\(" + divida + Auxiliar.getNumber(deposito, "", false) + " = \\mathbf{" + saldo + "\\text{ reais}}\\)";
				setResolucao(res0);
				break;
			}
			case 1:
			{
				int tempA = -(5 + rand.nextInt(20));
				int tempB = 10 + rand.nextInt(30);
				int diferenca = tempB - tempA;
				addParagrafo("A temperatura mínima registrada foi \\(" + tempA + "\\,°C\\) e a máxima foi \\(+" + tempB + "\\,°C\\). Qual foi a variação de temperatura?");
				gerarAlternativasInteirasComNegativos(diferenca);
				String res1 = "A variação é a diferença entre a temperatura máxima e a mínima: \\(\\\\\\)";
				res1 += "\\(" + tempB + " - (" + tempA + ") = " + tempB + Auxiliar.getNumber(-tempA, "", false) + " = \\mathbf{" + diferenca + "\\,°C}\\)";
				setResolucao(res1);
				break;
			}
			case 2:
			{
				int altitude = -(20 + rand.nextInt(80));
				int subida = 50 + rand.nextInt(100);
				int altitudeFinal = altitude + subida;
				addParagrafo("Um submarino estava a \\(" + altitude + "\\) m (abaixo do nível do mar). Subiu \\(" + subida + "\\) m. Em que altitude ficou?");
				gerarAlternativasInteirasComNegativos(altitudeFinal);
				String res2 = "Somamos a subida à altitude inicial: \\(\\\\\\)";
				res2 += "\\(" + altitude + Auxiliar.getNumber(subida, "", false) + " = \\mathbf{" + altitudeFinal + "\\text{ m}}\\)";
				setResolucao(res2);
				break;
			}
			default:
			{
				int pontos = -(20 + rand.nextInt(50));
				int ganho = 30 + rand.nextInt(80);
				int saldo = pontos + ganho;
				addParagrafo("Uma equipe acumulou \\(" + pontos + "\\) pontos (penalidades). Na última partida, ganhou \\(" + ganho + "\\) pontos. Qual é o saldo final?");
				gerarAlternativasInteirasComNegativos(saldo);
				String res3 = "Somamos os pontos ganhos às penalidades acumuladas: \\(\\\\\\)";
				res3 += "\\(" + pontos + Auxiliar.getNumber(ganho, "", false) + " = \\mathbf{" + saldo + "\\text{ pontos}}\\)";
				setResolucao(res3);
				break;
			}
		}
	}
}
