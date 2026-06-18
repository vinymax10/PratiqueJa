package matematica.avancado.funcaoquadratica.nivel3package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosEq2Grau dados = new DadosEq2Grau();

		String funcao = "f(x)=" + dados.a + "x^2"
				+ Auxiliar.getNumber(dados.b, "x", false)
				+ Auxiliar.getNumber(dados.c, "", false);

		ConfigEq2Grau config = new ConfigEq2Grau(dados);
		config.indice = 1 + rand.nextInt(10);
		config.pontoXv.mostrar = true;
		config.pontoXv.label = "x_v";

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre a coordenada \\(x_v\\) do vértice");
		addParagrafo("\\(" + funcao + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(dados.xVerticeRacional);
		setResolucao(ResolucaoFuncaoQuadratica.resolucaoXv(dados.a, dados.b, dados.c));
	}
}
