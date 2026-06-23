package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class TruqueMental extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(4);
		int a, resultado;
		String enunciado, res;

		switch (tipo)
		{
			case 0: // ×5 = ×10÷2
				a = 4 + rand.nextInt(17);
				resultado = a * 5;
				enunciado = "Use o truque de multiplicar por \\(5\\)"
					+ " (multiplique por \\(10\\) e divida por \\(2\\)) para calcular"
					+ " \\(" + a + " \\times 5\\).";
				res = "\\(" + a + " \\times 5 = " + a + " \\times 10 \\div 2 = "
					+ (a * 10) + " \\div 2 = \\mathbf{" + resultado + "}\\)";
				break;
			case 1: // ×9 = ×10 - n
				a = 3 + rand.nextInt(10);
				resultado = a * 9;
				enunciado = "Use o truque de multiplicar por \\(9\\)"
					+ " (multiplique por \\(10\\) e subtraia o número) para calcular"
					+ " \\(" + a + " \\times 9\\).";
				res = "\\(" + a + " \\times 9 = " + a + " \\times 10 - " + a + " = "
					+ (a * 10) + " - " + a + " = \\mathbf{" + resultado + "}\\)";
				break;
			case 2: // ×4 = dobro do dobro
				a = 5 + rand.nextInt(21);
				resultado = a * 4;
				enunciado = "Use o truque de multiplicar por \\(4\\)"
					+ " (dobre duas vezes: \\(\\times 2\\) e depois \\(\\times 2\\)) para calcular"
					+ " \\(" + a + " \\times 4\\).";
				res = "\\(" + a + " \\times 4 = " + (a * 2) + " \\times 2 = \\mathbf{" + resultado + "}\\)";
				break;
			default: // ×25 = ×100÷4
				a = 2 + rand.nextInt(19);
				resultado = a * 25;
				enunciado = "Use o truque de multiplicar por \\(25\\)"
					+ " (multiplique por \\(100\\) e divida por \\(4\\)) para calcular"
					+ " \\(" + a + " \\times 25\\).";
				res = "\\(" + a + " \\times 25 = " + a + " \\times 100 \\div 4 = "
					+ (a * 100) + " \\div 4 = \\mathbf{" + resultado + "}\\)";
				break;
		}

		addParagrafo(enunciado);
		gerarAlternativasInteiras(resultado);
		addResolucao(res);
	}
}
