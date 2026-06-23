package matematica.avancado.geometriaanalitica.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] as     = {3, 4,  0, 1};
		int[] bs     = {4, 3,  1, 0};
		int[] sqrts  = {5, 5,  1, 1};
		int idx = rand.nextInt(4);
		int a = as[idx], b = bs[idx], sqrtAB = sqrts[idx];

		int x0 = -2 + rand.nextInt(5);
		int y0 = -2 + rand.nextInt(5);
		int d  = 1 + rand.nextInt(5);
		int axby = a * x0 + b * y0;
		int c = rand.nextBoolean() ? d * sqrtAB - axby : -d * sqrtAB - axby;

		int numerador = Math.abs(a * x0 + b * y0 + c);

		addParagrafo("Calcule a distância do ponto \\(P(" + x0 + ";\\;" + y0 + ")\\) "
				+ "à reta \\(r: " + a + "x + " + b + "y + (" + c + ") = 0\\).");

		gerarAlternativasInteiras(d);

		addResolucao("Aplicando a fórmula da distância ponto–reta:");
		addResolucao("\\(d = \\dfrac{|a\\,x_0 + b\\,y_0 + c|}{\\sqrt{a^2+b^2}}\\)");
		addResolucao("\\(d = \\dfrac{|" + a + "\\cdot(" + x0 + ") + " + b + "\\cdot(" + y0 + ") + (" + c + ")|}"
				+ "{\\sqrt{" + (a*a) + "+" + (b*b) + "}}\\)");
		addResolucao("\\(d = \\dfrac{" + numerador + "}{" + sqrtAB + "} = \\mathbf{" + d + "}\\)");
	}
}
