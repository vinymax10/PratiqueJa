package matematica.basico.multiplicacaonatural.nivel3package;

import matematica.GeradorExercicio;

public class Expressao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(900);
		int b = 2 + rand.nextInt(8);
		int c = 10 + rand.nextInt(90);
		boolean soma = rand.nextBoolean();
		int p1 = a * b;
		int resultado = soma ? p1 + c : p1 - c;

		String op = soma ? " + " : " - ";
		addParagrafo("Calcule, respeitando a ordem das operações:");
		addParagrafo("\\(" + a + " \\times " + b + op + c + "\\)");

		gerarAlternativasInteiras(resultado);

		String acao = soma ? "somamos" : "subtraímos";
		String res = "Realizamos a multiplicação antes da adição/subtração: \\(\\\\\\)";
		res += "\\(" + a + " \\times " + b + " = " + p1 + "\\) \\(\\\\\\)";
		res += "Em seguida, " + acao + " \\(" + c + "\\): \\(\\\\\\)";
		res += "\\(" + p1 + op + c + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
