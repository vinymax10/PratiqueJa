package matematica.basico.subtracaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.Nomes;

public class TresEtapas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 10 + rand.nextInt(25);
		int c = 5 + rand.nextInt(20);
		int a = b + c + 10 + rand.nextInt(30);
		int ab = a - b;
		int resultado = ab - c;

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma caixa tinha \\(" + a + "\\) chocolates. Foram dados \\(" + b + "\\) para a turma A e \\(" + c + "\\) para a turma B. Quantos chocolates restaram?");
				break;
			case 1:
				addParagrafo(Nomes.masculino(rand) + " tinha \\(" + a + "\\) reais. Gastou \\(" + b + "\\) reais no mercado e \\(" + c + "\\) reais numa livraria. Quanto lhe sobrou?");
				break;
			default:
				addParagrafo("Uma fazenda tinha \\(" + a + "\\) animais. Foram vendidos \\(" + b + "\\) na feira de segunda e \\(" + c + "\\) na feira de quarta. Quantos animais restaram?");
				break;
		}

		gerarAlternativasInteiras(resultado);

		String res = "Subtraímos em dois passos: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = " + ab + "\\) \\(\\\\\\)";
		res += "\\(" + ab + " - " + c + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
