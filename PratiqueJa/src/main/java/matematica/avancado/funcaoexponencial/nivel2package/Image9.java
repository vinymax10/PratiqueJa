package matematica.avancado.funcaoexponencial.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.funcaoexponencial.config.ConfigFuncaoExp;
import matematica.avancado.funcaoexponencial.config.DadosFuncaoExp;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Decrescente (1/a)^x: gráfico mostra f(-1) = a, pede f(2) = 1/a²
		int[] dens = {2, 3, 4};
		int den = dens[rand.nextInt(3)];
		int fNegUm = den;            // (1/a)^(-1) = a
		Racional fDois = new Racional(1, den * den); // (1/a)^2 = 1/a²

		DadosFuncaoExp dados = DadosFuncaoExp.decrescente(den);

		ConfigFuncaoExp config = new ConfigFuncaoExp(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "" + fNegUm;

		BufferedImage image = config.criarImagem();

		String baseStr = "\\left(\\dfrac{1}{" + den + "}\\right)";

		addParagrafo("O gráfico mostra \\(f(-1) = " + fNegUm + "\\) para \\(f(x) = " + baseStr + "^x\\). "
			+ "Calcule \\(f(2)\\).");
		addParagrafoImagem(image);

		String res = "Do gráfico: \\(f(-1) = " + fNegUm + " \\Rightarrow a = " + den + "\\). \\(\\\\\\)";
		res += "Calculando \\(f(2)\\): \\(\\\\\\)";
		res += "\\(f(2) = " + baseStr + "^2 = \\dfrac{1}{" + den + "^2} = \\dfrac{1}{" + (den * den) + "} = \\mathbf{" + fDois.toStringLatex() + "}\\)";

		gerarAlternativas(fDois);
		setResolucao(res);
	}
}
