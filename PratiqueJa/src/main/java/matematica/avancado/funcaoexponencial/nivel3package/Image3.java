package matematica.avancado.funcaoexponencial.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.config.ConfigFuncaoExp;
import matematica.avancado.funcaoexponencial.config.DadosFuncaoExp;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Pede qual ponto está garantidamente no gráfico de f(x) = a^x
		int[] basesOp = {2, 3, 4, 5};
		int base = basesOp[rand.nextInt(basesOp.length)];

		DadosFuncaoExp dados = DadosFuncaoExp.crescente(base);

		ConfigFuncaoExp config = new ConfigFuncaoExp(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = false;

		BufferedImage image = config.criarImagem();

		addParagrafo("Qual dos pontos abaixo pertence obrigatoriamente ao gráfico de "
			+ "\\(f(x) = " + base + "^x\\)?");
		addParagrafoImagem(image);

		String correta = "\\((0,\\, 1)\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\((1,\\, 0)\\)");
		dist.add("\\((0,\\, 0)\\)");
		dist.add("\\((1,\\, " + base + " \\cdot 2)\\)");
		embaralharEAdicionarAlternativas(correta, dist);

		String res = "Toda função exponencial \\(f(x) = a^x\\) satisfaz \\(a^0 = 1\\). \\(\\\\\\)";
		res += "Portanto, \\(f(0) = " + base + "^0 = 1\\), e o ponto \\((0, 1)\\) está sempre no gráfico. \\(\\\\\\)";
		res += "\\(\\mathbf{(0,\\, 1)}\\)";

		setResolucao(res);
	}
}
