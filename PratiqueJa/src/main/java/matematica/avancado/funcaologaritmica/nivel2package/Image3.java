package matematica.avancado.funcaologaritmica.nivel2package;

import java.awt.image.BufferedImage;
import matematica.GeradorExercicio;
import matematica.avancado.funcaologaritmica.config.ConfigFuncaoLog;
import matematica.avancado.funcaologaritmica.config.DadosFuncaoLog;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico mostra f(a) = 1, pedir que identifique a base a
		int[] basesOp = {2, 3, 4};
		int base = basesOp[rand.nextInt(3)];

		DadosFuncaoLog dados = DadosFuncaoLog.crescente(base);
		ConfigFuncaoLog config = new ConfigFuncaoLog(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "1";

		BufferedImage image = config.criarImagem();

		addParagrafo("Qual é a base \\(a\\) da função \\(f(x) = \\log_a(x)\\) representada no gráfico?");
		addParagrafoImagem(image);

		String res = "O gráfico mostra o ponto \\((" + base + ",\\,1)\\) destacado. \\(\\\\\\)";
		res += "Portanto \\(f(" + base + ") = \\log_a(" + base + ") = 1\\). \\(\\\\\\)";
		res += "Isso implica \\(a^1 = " + base + "\\), logo \\(a = \\mathbf{" + base + "}\\).";

		gerarAlternativas("" + base);
		setResolucao(res);
	}
}
