package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;

public class TresEtapas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 2 + rand.nextInt(5);
		int b = 3 + rand.nextInt(6);
		int q = 20 + rand.nextInt(30);
		int total = b * c * q;
		int porDep = c * q;

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma empresa tem \\(" + total + "\\) funcionários divididos igualmente em \\(" + b + "\\) departamentos. Cada departamento é subdividido em \\(" + c + "\\) equipes iguais. Quantos funcionários há em cada equipe?");
				break;
			case 1:
				addParagrafo("Um armazém recebeu \\(" + total + "\\) caixas que foram distribuídas igualmente em \\(" + b + "\\) galpões. Cada galpão divide as caixas em \\(" + c + "\\) setores iguais. Quantas caixas há em cada setor?");
				break;
			default:
				addParagrafo("Uma escola tem \\(" + total + "\\) livros divididos igualmente entre \\(" + b + "\\) andares. Cada andar distribui seus livros em \\(" + c + "\\) salas iguais. Quantos livros há em cada sala?");
				break;
		}

		gerarAlternativasInteiras(q);

		String res = "Resolvemos em dois passos: \\(\\\\\\)";
		res += "Passo 1 — dividimos o total pelo número de grupos: \\(\\\\\\)";
		res += "\\(" + total + " \\div " + b + " = " + porDep + "\\) \\(\\\\\\)";
		res += "Passo 2 — dividimos cada grupo pelo número de subgrupos: \\(\\\\\\)";
		res += "\\(" + porDep + " \\div " + c + " = \\mathbf{" + q + "}\\)";
		setResolucao(res);
	}
}
