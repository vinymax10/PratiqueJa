package matematica.avancado.funcaoexponencial.nivel2package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.config.ConfigFuncaoExp;
import matematica.avancado.funcaoexponencial.config.DadosFuncaoExp;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean crescente = rand.nextBoolean();
		DadosFuncaoExp dados;
		String baseDesc;

		if (crescente)
		{
			int base = rand.nextBoolean() ? 2 : 3;
			dados    = DadosFuncaoExp.crescente(base);
			baseDesc = "\\(a = " + base + " > 1\\)";
		}
		else
		{
			int den  = rand.nextBoolean() ? 2 : 3;
			dados    = DadosFuncaoExp.decrescente(den);
			baseDesc = "\\(a = \\dfrac{1}{" + den + "}\\), onde \\(0 < a < 1\\)";
		}

		ConfigFuncaoExp config = new ConfigFuncaoExp(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = false;

		BufferedImage image = config.criarImagem();

		addParagrafo("Com base no gráfico, a função exponencial \\(f(x) = a^x\\) representada é:");
		addParagrafoImagem(image);

		String correta = crescente ? "Crescente, pois \\(a > 1\\)" : "Decrescente, pois \\(0 < a < 1\\)";
		List<String> dist = new ArrayList<>();
		if (crescente)
		{
			dist.add("Decrescente, pois \\(0 < a < 1\\)");
			dist.add("Constante, pois \\(a = 1\\)");
			dist.add("Crescente apenas para \\(x > 0\\)");
		}
		else
		{
			dist.add("Crescente, pois \\(a > 1\\)");
			dist.add("Constante, pois \\(a = 1\\)");
			dist.add("Decrescente apenas para \\(x > 0\\)");
		}
		embaralharEAdicionarAlternativas(correta, dist);

		addResolucao("A base da função é " + baseDesc + ".");
		addResolucao("Regra: \\(a > 1 \\Rightarrow\\) crescente; \\(0 < a < 1 \\Rightarrow\\) decrescente.");
		addResolucao("\\(\\mathbf{" + (crescente ? "Crescente" : "Decrescente") + "}\\)");
	}
}
