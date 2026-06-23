package matematica.avancado.funcaoexponencial.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.config.ConfigFuncaoExp;
import matematica.avancado.funcaoexponencial.config.DadosFuncaoExp;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] basesOp = {2, 3, 4};
		int base = basesOp[rand.nextInt(3)];
		DadosFuncaoExp dados = DadosFuncaoExp.crescente(base);

		ConfigFuncaoExp config = new ConfigFuncaoExp(dados);
		config.indice        = 1 + rand.nextInt(10);
		config.mostrarPonto  = true;
		config.labelPonto    = "" + base;

		BufferedImage image = config.criarImagem();

		addParagrafo("Qual é a base \\(a\\) da função \\(f(x) = a^x\\) representada no gráfico?");
		addParagrafoImagem(image);

		gerarAlternativas("" + base);
		addResolucao("O gráfico mostra \\(f(1) = " + base + "\\).");
		addResolucao("Como \\(f(1) = a^1 = a\\), temos:");
		addResolucao("\\(a = \\mathbf{" + base + "}\\)");
	}
}
