package matematica.avancado.funcaologaritmica.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.avancado.funcaologaritmica.config.ConfigFuncaoLog;
import matematica.avancado.funcaologaritmica.config.DadosFuncaoLog;

public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico mostra (a, 1); pergunta: quanto vale f(1/a)?
		// log_a(1/a) = log_a(a^(-1)) = -1
		int[] basesOp = {2, 3, 4};
		int base = basesOp[rand.nextInt(3)];

		DadosFuncaoLog dados = DadosFuncaoLog.crescente(base);
		ConfigFuncaoLog config = new ConfigFuncaoLog(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "1";

		BufferedImage image = config.criarImagem();

		addParagrafo("O gráfico mostra que \\(f(" + base + ") = 1\\) para \\(f(x) = \\log_a(x)\\).");
		addParagrafoImagem(image);
		addParagrafo("Com base no gráfico, quanto vale "
			+ "\\(f\\!\\left(\\dfrac{1}{" + base + "}\\right)\\)?");

		String correto = "\\(-1\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(1\\)");
		dist.add("\\(0\\)");
		dist.add("\\(-" + base + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "De \\(f(" + base + ") = 1\\) temos \\(a = " + base + "\\). \\(\\\\\\)";
		res += "\\(f\\!\\left(\\dfrac{1}{" + base + "}\\right) = \\log_{" + base + "} " + base + "^{-1}\\\\";
		res += "= -1 \\cdot \\log_{" + base + "} " + base + " = -1 \\cdot 1 = \\mathbf{-1}\\)";
		setResolucao(res);
	}
}
