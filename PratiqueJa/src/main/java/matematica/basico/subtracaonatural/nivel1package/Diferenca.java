package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.Nomes;

public class Diferenca extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 10 + rand.nextInt(20);
		int a = b + 5 + rand.nextInt(20);
		int dif = a - b;

		String nomeM = Nomes.masculino(rand), nomeF = Nomes.feminino(rand);
		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo(nomeM + " tem \\(" + a + "\\) figurinhas e " + nomeF + " tem \\(" + b + "\\). Quantas figurinhas a mais " + nomeM + " tem do que " + nomeF + "?");
				break;
			case 1:
				addParagrafo(nomeM + " tem \\(" + a + "\\) anos e seu irmão tem \\(" + b + "\\) anos. Quantos anos mais velho " + nomeM + " é?");
				break;
			case 2:
				addParagrafo("Numa prova, " + nomeF + " tirou \\(" + a + "\\) pontos e " + nomeM + " tirou \\(" + b + "\\) pontos. Qual foi a diferença de pontuação?");
				break;
			default:
				addParagrafo("Um carro percorreu \\(" + a + "\\) km e outro percorreu \\(" + b + "\\) km. Qual é a diferença de distância percorrida?");
				break;
		}

		gerarAlternativasInteiras(dif);

		String res = "Para encontrar a diferença entre duas quantidades, subtraímos a menor da maior: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = \\mathbf{" + dif + "}\\)";
		setResolucao(res);
	}
}
