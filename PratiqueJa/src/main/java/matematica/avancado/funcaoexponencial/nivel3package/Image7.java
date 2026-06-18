package matematica.avancado.funcaoexponencial.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.config.ConfigFuncaoExp;
import matematica.avancado.funcaoexponencial.config.DadosFuncaoExp;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Mostra f(1) = a no gráfico, pede f(2) = a²
		int[] basesOp = {2, 3, 4};
		int base  = basesOp[rand.nextInt(3)];
		int fDois = base * base;

		DadosFuncaoExp dados = DadosFuncaoExp.crescente(base);

		ConfigFuncaoExp config = new ConfigFuncaoExp(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "" + base;

		BufferedImage image = config.criarImagem();

		addParagrafo("O gráfico mostra \\(f(1) = " + base + "\\) para a função \\(f(x) = a^x\\). "
			+ "Determine \\(f(2)\\).");
		addParagrafoImagem(image);

		String res = "Do gráfico: \\(f(1) = " + base + " \\Rightarrow a = " + base + "\\). \\(\\\\\\)";
		res += "Calculando \\(f(2)\\): \\(\\\\\\)";
		res += "\\(f(2) = " + base + "^2 = \\mathbf{" + fDois + "}\\)";

		gerarAlternativas("" + fDois);
		setResolucao(res);
	}
}
