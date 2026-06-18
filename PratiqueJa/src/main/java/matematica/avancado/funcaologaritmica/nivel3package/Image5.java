package matematica.avancado.funcaologaritmica.nivel3package;

import java.awt.image.BufferedImage;
import matematica.GeradorExercicio;
import matematica.avancado.funcaologaritmica.config.ConfigFuncaoLog;
import matematica.avancado.funcaologaritmica.config.DadosFuncaoLog;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico mostra (a^2, 2); pergunta: quanto vale f(a)?
		// log_a(a) = 1 sempre; o aluno identifica a = sqrt(pontoX)
		int[] basesOp = {2, 3};
		int base = basesOp[rand.nextInt(2)];

		DadosFuncaoLog dados = DadosFuncaoLog.crescenteComPonto2(base);
		ConfigFuncaoLog config = new ConfigFuncaoLog(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "2";

		BufferedImage image = config.criarImagem();
		int x2 = base * base;

		addParagrafo("O gráfico mostra que \\(f(" + x2 + ") = 2\\) para \\(f(x) = \\log_a(x)\\).");
		addParagrafoImagem(image);
		addParagrafo("Com base no gráfico, quanto vale \\(f(" + base + ")\\)?");

		String res = "De \\(f(" + x2 + ") = \\log_a(" + x2 + ") = 2\\) temos \\(a^2 = " + x2 + "\\), logo \\(a = " + base + "\\). \\(\\\\\\)";
		res += "\\(f(" + base + ") = \\log_{" + base + "}(" + base + ") = \\mathbf{1}\\)";

		gerarAlternativas("1");
		setResolucao(res);
	}
}
