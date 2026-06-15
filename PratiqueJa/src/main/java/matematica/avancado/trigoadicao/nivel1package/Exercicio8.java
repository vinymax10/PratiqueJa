package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// Bissecção: sen²(a) = (1 - cos(2a)) / 2 com ângulo notável
public class Exercicio8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// choice 0: sen²(30°) = (1-cos60°)/2 = 1/4
		// choice 1: sen²(45°) = (1-cos90°)/2 = 1/2
		// choice 2: sen²(60°) = (1-cos120°)/2 = 3/4
		int choice = rand.nextInt(3);

		String angulo, anguloDouble, cosDouble, numExibido, passoNum, resultado;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			angulo       = "30°";
			anguloDouble = "60°";
			cosDouble    = "\\dfrac{1}{2}";
			numExibido   = "1 - \\dfrac{1}{2}";
			passoNum     = "\\dfrac{1}{2}";
			resultado    = "\\dfrac{1}{4}";
			distratores.add("\\(\\dfrac{3}{4}\\)");
			distratores.add("\\(\\dfrac{1}{2}\\)");
			distratores.add("\\(\\dfrac{3}{8}\\)");
		} else if (choice == 1) {
			angulo       = "45°";
			anguloDouble = "90°";
			cosDouble    = "0";
			numExibido   = "1 - 0";
			passoNum     = "1";
			resultado    = "\\dfrac{1}{2}";
			distratores.add("\\(\\dfrac{1}{4}\\)");
			distratores.add("\\(\\dfrac{3}{4}\\)");
			distratores.add("\\(1\\)");
		} else {
			angulo       = "60°";
			anguloDouble = "120°";
			cosDouble    = "-\\dfrac{1}{2}";
			numExibido   = "1 - \\left(-\\dfrac{1}{2}\\right) = 1 + \\dfrac{1}{2}";
			passoNum     = "\\dfrac{3}{2}";
			resultado    = "\\dfrac{3}{4}";
			distratores.add("\\(\\dfrac{1}{4}\\)");
			distratores.add("\\(\\dfrac{1}{2}\\)");
			distratores.add("\\(\\dfrac{3}{8}\\)");
		}

		addParagrafo("Usando a fórmula de bissecção \\(\\operatorname{sen}^2 a = \\dfrac{1 - \\cos(2a)}{2}\\),"
				+ " calcule \\(\\operatorname{sen}^2(" + angulo + ")\\).");
		embaralharEAdicionarAlternativas("\\(" + resultado + "\\)", distratores);

		setResolucao("Fazendo \\(a = " + angulo + "\\), temos \\(2a = " + anguloDouble
				+ "\\) e \\(\\cos(" + anguloDouble + ") = " + cosDouble + "\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{sen}^2(" + angulo + ") = \\dfrac{" + numExibido + "}{2} = \\dfrac{" + passoNum
				+ "}{2} = \\mathbf{" + resultado + "}\\)");
	}
}
