package matematica.avancado.funcaoexponencial.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.config.ConfigFuncaoExp;
import matematica.avancado.funcaoexponencial.config.DadosFuncaoExp;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Mostra f(2) = a² no gráfico, pede a base
		int[] basesOp = {2, 3, 4};
		int base  = basesOp[rand.nextInt(3)];
		int fDois = base * base;

		DadosFuncaoExp dados = DadosFuncaoExp.crescenteComPonto2(base);

		ConfigFuncaoExp config = new ConfigFuncaoExp(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "" + fDois;

		BufferedImage image = config.criarImagem();

		addParagrafo("O gráfico mostra que \\(f(2) = " + fDois + "\\) para \\(f(x) = a^x\\). "
			+ "Qual é a base \\(a\\)?");
		addParagrafoImagem(image);

		String res = "Sabemos que \\(f(2) = a^2 = " + fDois + "\\). \\(\\\\\\)";
		res += "Extraindo a raiz (com \\(a > 0\\)): \\(\\\\\\)";
		res += "\\(a = \\sqrt{" + fDois + "} = \\mathbf{" + base + "}\\)";

		gerarAlternativas("" + base);
		setResolucao(res);
	}
}
