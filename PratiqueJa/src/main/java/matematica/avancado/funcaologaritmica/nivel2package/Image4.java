package matematica.avancado.funcaologaritmica.nivel2package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.avancado.funcaologaritmica.config.ConfigFuncaoLog;
import matematica.avancado.funcaologaritmica.config.DadosFuncaoLog;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico mostra curva logarítmica; identificar crescente ou decrescente
		boolean crescente = rand.nextBoolean();

		DadosFuncaoLog dados;
		String correto;
		String errado;
		String resolucao;

		if (crescente)
		{
			int[] basesOp = {2, 3, 4};
			int base = basesOp[rand.nextInt(3)];
			dados     = DadosFuncaoLog.crescente(base);
			correto   = "Crescente";
			errado    = "Decrescente";
			resolucao = "A curva sobe da esquerda para a direita: \\(\\mathbf{crescente}\\). "
				+ "Isso indica base \\(a > 1\\).";
		}
		else
		{
			int[] densOp = {2, 3};
			int den   = densOp[rand.nextInt(2)];
			dados     = DadosFuncaoLog.decrescente(den);
			correto   = "Decrescente";
			errado    = "Crescente";
			resolucao = "A curva desce da esquerda para a direita: \\(\\mathbf{decrescente}\\). "
				+ "Isso indica base \\(0 < a < 1\\).";
		}

		ConfigFuncaoLog config = new ConfigFuncaoLog(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = false;

		BufferedImage image = config.criarImagem();

		addParagrafo("A função \\(f(x) = \\log_a(x)\\) representada no gráfico é:");
		addParagrafoImagem(image);

		List<String> dist = new ArrayList<>();
		dist.add(errado);
		dist.add("Constante");
		dist.add("Nem crescente nem decrescente");
		embaralharEAdicionarAlternativas(correto, dist);
		setResolucao(resolucao);
	}
}
