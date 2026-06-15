package matematica.basico.adicaosubtracaointeiro.nivel3package;

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
				int divida = -(100 + rand.nextInt(400));
				int receita = 200 + rand.nextInt(600);
				int saldo = divida + receita;
				addParagrafo("Uma empresa fechou o trimestre com dívidas de \\(" + Math.abs(divida) + "\\) reais (saldo \\(" + divida + "\\) reais). No trimestre seguinte, obteve uma receita de \\(" + receita + "\\) reais. Qual é o saldo acumulado?");
				gerarAlternativasInteirasComNegativos(saldo);
				String res0 = "Somamos a receita ao saldo negativo: \\(\\\\\\)";
				res0 += "\\(" + divida + Auxiliar.getNumber(receita, "", false) + " = \\mathbf{" + saldo + "\\text{ reais}}\\)";
				setResolucao(res0);
				break;
			}
			case 1:
			{
				int ac = -(100 + rand.nextInt(900));
				int dc = 100 + rand.nextInt(900);
				int anos = dc - ac;
				addParagrafo("Uma batalha histórica ocorreu em \\(" + Math.abs(ac) + "\\) a.C. (ano \\(" + ac + "\\)). Outra ocorreu em \\(" + dc + "\\) d.C. (ano \\(+" + dc + "\\)). Quantos anos separam os dois eventos?");
				gerarAlternativasInteirasComNegativos(anos);
				String res1 = "A diferença entre dois anos é calculada por subtração: \\(\\\\\\)";
				res1 += "\\(" + dc + " - (" + ac + ") = " + dc + Auxiliar.getNumber(-ac, "", false) + " = \\mathbf{" + anos + "\\text{ anos}}\\)";
				setResolucao(res1);
				break;
			}
			case 2:
			{
				int profundidade = -(200 + rand.nextInt(800));
				int subida = 300 + rand.nextInt(700);
				int altitude = profundidade + subida;
				addParagrafo("Um mergulhador estava a \\(" + Math.abs(profundidade) + "\\) m abaixo do nível do mar (altitude \\(" + profundidade + "\\) m). Subiu \\(" + subida + "\\) m. Qual é a altitude final?");
				gerarAlternativasInteirasComNegativos(altitude);
				String res2 = "Somamos a subida à altitude inicial: \\(\\\\\\)";
				res2 += "\\(" + profundidade + Auxiliar.getNumber(subida, "", false) + " = \\mathbf{" + altitude + "\\text{ m}}\\)";
				setResolucao(res2);
				break;
			}
			default:
			{
				int perda = -(100 + rand.nextInt(400));
				int ganho1 = 100 + rand.nextInt(300);
				int ganho2 = 50 + rand.nextInt(200);
				int saldo = perda + ganho1 + ganho2;
				addParagrafo("Um investidor tinha \\(" + perda + "\\) reais (perda acumulada). Recuperou \\(" + ganho1 + "\\) reais no primeiro mês e mais \\(" + ganho2 + "\\) reais no segundo. Qual é o saldo?");
				gerarAlternativasInteirasComNegativos(saldo);
				String res3 = "Somamos as recuperações à perda inicial: \\(\\\\\\)";
				res3 += "\\(" + perda + Auxiliar.getNumber(ganho1, "", false) + Auxiliar.getNumber(ganho2, "", false) + " = \\mathbf{" + saldo + "\\text{ reais}}\\)";
				setResolucao(res3);
				break;
			}
		}
	}
}
