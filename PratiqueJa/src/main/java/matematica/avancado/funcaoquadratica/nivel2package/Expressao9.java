package matematica.avancado.funcaoquadratica.nivel2package;

import matematica.GeradorExercicio;

public class Expressao9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);
		switch(tipo)
		{
			case 0: problemaProjetil(); break;
			case 1: problemaArea();     break;
			default: problemaLucro();   break;
		}
	}

	private void problemaProjetil()
	{
		// h(t) = -a*t² + v*t, v = 2*a*tv para garantir tv inteiro
		int a  = 1 + rand.nextInt(3);   // 1..3
		int tv = 1 + rand.nextInt(5);   // 1..5 segundos
		int v  = 2 * a * tv;
		int hv = a * tv * tv;           // altura máxima

		addParagrafo("Um projétil tem altura \\(h(t) = -" + a + "t^2 + " + v + "t\\) (metros). "
			+ "Qual é a altura máxima atingida?");

		String res = "A altura máxima ocorre no vértice, com \\(a = -" + a + "\\) e \\(b = " + v + "\\): \\(\\\\\\)";
		res += "\\(t_v = \\dfrac{-b}{2a} = \\dfrac{-" + v + "}{2 \\cdot (-" + a + ")} = \\dfrac{-" + v + "}{-" + (2 * a) + "} = " + tv + "\\,\\text{s}\\) \\(\\\\\\)";
		res += "\\(h_{\\max} = h(" + tv + ") = -" + a + " \\cdot " + (tv * tv) + " + " + v + " \\cdot " + tv + " = "
			+ (-a * tv * tv) + " + " + (v * tv) + " = \\mathbf{" + hv + "}\\,\\text{m}\\)";

		gerarAlternativas("" + hv);
		setResolucao(res);
	}

	private void problemaArea()
	{
		// A(x) = x·(2p - x) = -x² + 2p·x, xv = p, Amax = p²
		int p = 3 + rand.nextInt(8);    // 3..10
		int aMax = p * p;
		int doisP = 2 * p;

		addParagrafo("Um retângulo tem perímetro total de \\(" + (2 * doisP) + "\\,\\text{m}\\). "
			+ "Sendo \\(x\\) a largura, a área é \\(A(x) = -x^2 + " + doisP + "x\\). "
			+ "Qual é a área máxima?");

		String res = "A área máxima ocorre no vértice: \\(a = -1\\), \\(b = " + doisP + "\\). \\(\\\\\\)";
		res += "\\(x_v = \\dfrac{-" + doisP + "}{2 \\cdot (-1)} = " + p + "\\,\\text{m}\\) \\(\\\\\\)";
		res += "\\(A_{\\max} = -(" + p + ")^2 + " + doisP + " \\cdot " + p + " = " + (-p * p) + " + " + (doisP * p) + " = \\mathbf{" + aMax + "}\\,\\text{m}^2\\)";

		gerarAlternativas("" + aMax);
		setResolucao(res);
	}

	private void problemaLucro()
	{
		// L(x) = -x² + b*x + c, xv = b/2
		int xv = 5 + rand.nextInt(11);  // 5..15 unidades ótimas
		int b  = 2 * xv;
		int c  = 10 * (1 + rand.nextInt(10));
		int lMax = -(xv * xv) + b * xv + c;

		addParagrafo("O lucro de uma empresa é dado por \\(L(x) = -x^2 + " + b + "x + " + c + "\\) "
			+ "(em R\\$), onde \\(x\\) é a quantidade produzida. "
			+ "Qual é a quantidade que maximiza o lucro?");

		String res = "O lucro máximo ocorre no vértice: \\(a = -1\\), \\(b = " + b + "\\). \\(\\\\\\)";
		res += "\\(x_v = \\dfrac{-" + b + "}{2 \\cdot (-1)} = \\dfrac{" + b + "}{2} = \\mathbf{" + xv + "}\\) unidades";

		gerarAlternativas("" + xv);
		setResolucao(res);
	}
}
