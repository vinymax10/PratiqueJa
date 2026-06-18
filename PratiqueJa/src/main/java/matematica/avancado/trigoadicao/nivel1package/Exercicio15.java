package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// Bissecção: cos²(a) = (1 + cos(2a)) / 2 com ângulo notável → resultado racional
public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// choice 0: cos²(30°) = (1+cos60°)/2 = 3/4
		// choice 1: cos²(45°) = (1+cos90°)/2 = 1/2
		// choice 2: cos²(60°) = (1+cos120°)/2 = 1/4
		int choice = rand.nextInt(3);

		String angulo, anguloDouble, cosDouble, passoNum, resultado;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			angulo      = "30°";
			anguloDouble = "60°";
			cosDouble   = "\\dfrac{1}{2}";
			passoNum    = "\\dfrac{3}{2}";
			resultado   = "\\dfrac{3}{4}";
			distratores.add("\\(\\dfrac{1}{4}\\)");
			distratores.add("\\(\\dfrac{1}{2}\\)");
			distratores.add("\\(\\dfrac{3}{8}\\)");
		} else if (choice == 1) {
			angulo      = "45°";
			anguloDouble = "90°";
			cosDouble   = "0";
			passoNum    = "1";
			resultado   = "\\dfrac{1}{2}";
			distratores.add("\\(\\dfrac{3}{4}\\)");
			distratores.add("\\(\\dfrac{1}{4}\\)");
			distratores.add("\\(1\\)");
		} else {
			angulo      = "60°";
			anguloDouble = "120°";
			cosDouble   = "-\\dfrac{1}{2}";
			passoNum    = "\\dfrac{1}{2}";
			resultado   = "\\dfrac{1}{4}";
			distratores.add("\\(\\dfrac{3}{4}\\)");
			distratores.add("\\(\\dfrac{1}{2}\\)");
			distratores.add("\\(\\dfrac{1}{8}\\)");
		}

		addParagrafo("Usando a fórmula de bissecção \\(\\cos^2 a = \\dfrac{1 + \\cos(2a)}{2}\\),"
				+ " calcule \\(\\cos^2(" + angulo + ")\\).");
		embaralharEAdicionarAlternativas("\\(" + resultado + "\\)", distratores);

		// para valor negativo de cos, exibir "1 - valor" em vez de "1 + -valor"
		String numExibido = cosDouble.startsWith("-")
				? "1 - " + cosDouble.substring(1)
				: "1 + " + cosDouble;
		setResolucao("Fazendo \\(a = " + angulo + "\\), temos \\(2a = " + anguloDouble + "\\) e \\(\\cos(" + anguloDouble + ") = " + cosDouble + "\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\cos^2(" + angulo + ") = \\dfrac{" + numExibido + "}{2} = \\dfrac{" + passoNum + "}{2} = \\mathbf{" + resultado + "}\\)");
	}
}
