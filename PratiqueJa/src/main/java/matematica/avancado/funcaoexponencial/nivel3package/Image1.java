package matematica.avancado.funcaoexponencial.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.config.ConfigFuncaoExp;
import matematica.avancado.funcaoexponencial.config.DadosFuncaoExp;

public class Image1 extends GeradorExercicio
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

		gerarAlternativas("" + fDois);
		addResolucao("Do gráfico: \\(f(1) = " + base + " \\Rightarrow a = " + base + "\\).");
		addResolucao("Calculando \\(f(2)\\):");
		addResolucao("\\(f(2) = " + base + "^2 = \\mathbf{" + fDois + "}\\)");
	}
}
