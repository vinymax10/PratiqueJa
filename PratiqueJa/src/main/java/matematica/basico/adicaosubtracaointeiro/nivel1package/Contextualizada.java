package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.Auxiliar;
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
				int tempInicial = -(1 + rand.nextInt(10));
				int variacao = rand.nextBoolean() ? (2 + rand.nextInt(14)) : -(1 + rand.nextInt(7));
				int tempFinal = tempInicial + variacao;
				if (variacao > 0)
					addParagrafo("A temperatura estava em \\(" + tempInicial + "\\,°C\\). Ela subiu \\(" + variacao + "\\,°C\\). Qual é a temperatura final?");
				else
					addParagrafo("A temperatura estava em \\(" + tempInicial + "\\,°C\\). Ela caiu mais \\(" + Math.abs(variacao) + "\\,°C\\). Qual é a temperatura final?");
				gerarAlternativasInteirasComNegativos(tempFinal);
				String res0 = "Somamos a variação à temperatura inicial: \\(\\\\\\)";
				res0 += "\\(" + tempInicial + Auxiliar.getNumber(variacao, "", false) + " = \\mathbf{" + tempFinal + "\\,°C}\\)";
				setResolucao(res0);
				break;
			}
			case 1:
			{
				int subsolos = 1 + rand.nextInt(5);
				int andarInicial = -subsolos;
				int subida = 2 + rand.nextInt(10);
				int andarFinal = andarInicial + subida;
				addParagrafo("Um elevador está no " + subsolos + "° subsolo. Sobe \\(" + subida + "\\) andares. Em que andar para?");
				gerarAlternativasInteirasComNegativos(andarFinal);
				String res1 = "O " + subsolos + "° subsolo corresponde ao andar \\(" + andarInicial + "\\): \\(\\\\\\)";
				res1 += "\\(" + andarInicial + Auxiliar.getNumber(subida, "", false) + " = \\mathbf{" + andarFinal + "}\\)";
				if (andarFinal > 0) res1 += " (" + andarFinal + "° andar)";
				else if (andarFinal < 0) res1 += " (" + Math.abs(andarFinal) + "° subsolo)";
				else res1 += " (térreo)";
				setResolucao(res1);
				break;
			}
			default:
			{
				int penalidade = -(1 + rand.nextInt(8));
				int ganho = 1 + rand.nextInt(15);
				int saldo = penalidade + ganho;
				addParagrafo("Um jogador tinha \\(" + penalidade + "\\) pontos de penalidade. Ganhou \\(" + ganho + "\\) pontos na próxima rodada. Qual é o saldo de pontos?");
				gerarAlternativasInteirasComNegativos(saldo);
				String res2 = "Somamos a penalidade negativa com o ganho positivo: \\(\\\\\\)";
				res2 += "\\(" + penalidade + Auxiliar.getNumber(ganho, "", false) + " = \\mathbf{" + saldo + "}\\)";
				setResolucao(res2);
				break;
			}
		}
	}
}
