package matematica.intermediario.funcaoafim.nivel2package;

import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);
		switch(tipo)
		{
			case 0: problemaServico(); break;
			case 1: problemaResfriamento(); break;
			default: problemaPoupanca(); break;
		}
	}

	private void problemaServico()
	{
		int a = 2 + rand.nextInt(5);
		int b = 5 * (1 + rand.nextInt(6));
		int x = 2 + rand.nextInt(9);
		int k = a * x + b;

		addParagrafo("Uma empresa cobra \\(R\\$\\ " + b + ",00\\) de taxa fixa e "
			+ "\\(R\\$\\ " + a + ",00\\) por hora de serviço. "
			+ "Quantas horas de serviço correspondem ao total de \\(R\\$\\ " + k + ",00\\)?");

		String res = "Taxa de variação: \\(a = " + a + "\\) (R$/h), taxa fixa: \\(b = " + b + "\\). \\(\\\\\\)";
		res += "Modelo: \\(f(x) = " + a + "x + " + b + "\\). Igualamos a \\(" + k + "\\): \\(\\\\\\)";
		res += "\\(" + a + "x + " + b + " = " + k + "\\\\";
		res += "" + a + "x = " + (k - b) + "\\\\";
		res += "x = \\dfrac{" + (k - b) + "}{" + a + "} = \\mathbf{" + x + "}\\) horas";

		gerarAlternativas("" + x);
		setResolucao(res);
	}

	private void problemaResfriamento()
	{
		int a = 3 + rand.nextInt(5);
		int b = 40 + rand.nextInt(51);
		int x = 2 + rand.nextInt(9);
		int k = b - a * x;
		while(k < 5)
		{
			x = 2 + rand.nextInt(9);
			k = b - a * x;
		}

		addParagrafo("A temperatura de um objeto parte de \\(" + b + "\\,\\text{°C}\\) "
			+ "e diminui \\(" + a + "\\,\\text{°C}\\) por minuto de forma linear. "
			+ "Após quantos minutos a temperatura será \\(" + k + "\\,\\text{°C}\\)?");

		int bMinusK = b - k;
		String res = "Taxa de variação: \\(a = -" + a + "\\) (°C/min), temp. inicial: \\(b = " + b + "\\). \\(\\\\\\)";
		res += "Modelo: \\(T(x) = -" + a + "x + " + b + "\\). Igualamos a \\(" + k + "\\): \\(\\\\\\)";
		res += "\\(-" + a + "x + " + b + " = " + k + "\\\\";
		res += "-" + a + "x = " + (k - b) + "\\\\";
		res += "x = \\dfrac{" + bMinusK + "}{" + a + "} = \\mathbf{" + x + "}\\) minutos";

		gerarAlternativas("" + x);
		setResolucao(res);
	}

	private void problemaPoupanca()
	{
		int a = 50 * (1 + rand.nextInt(8));
		int b = 100 + 100 * rand.nextInt(9);
		int x = 2 + rand.nextInt(9);
		int k = a * x + b;

		addParagrafo("Carlos tem \\(R\\$\\ " + b + ",00\\) poupados e economiza "
			+ "\\(R\\$\\ " + a + ",00\\) por mês. "
			+ "Em quantos meses ele terá \\(R\\$\\ " + k + ",00\\)?");

		String res = "Taxa de poupança: \\(a = " + a + "\\) (R$/mês), saldo inicial: \\(b = " + b + "\\). \\(\\\\\\)";
		res += "Modelo: \\(f(x) = " + a + "x + " + b + "\\). Igualamos a \\(" + k + "\\): \\(\\\\\\)";
		res += "\\(" + a + "x + " + b + " = " + k + "\\\\";
		res += "" + a + "x = " + (k - b) + "\\\\";
		res += "x = \\dfrac{" + (k - b) + "}{" + a + "} = \\mathbf{" + x + "}\\) meses";

		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
