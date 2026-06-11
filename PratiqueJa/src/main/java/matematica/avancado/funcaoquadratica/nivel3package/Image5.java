package matematica.avancado.funcaoquadratica.nivel3package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosEq2Grau dados = new DadosEq2Grau();

		String funcao = "f(x)=ax^2"
				+ Auxiliar.getNumber(dados.b, "x", false)
				+ Auxiliar.getNumber(dados.c, "", false);

		ConfigEq2Grau config = new ConfigEq2Grau(dados);
		config.indice = 1 + rand.nextInt(10);
		config.pontoYv.mostrar = true;
		config.pontoYv.label = dados.yVerticeRacional.showFrac();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre \\(a\\), dado \\(" + funcao + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas("" + dados.a);
		setResolucao(ResolucaoFuncaoQuadratica.resolucaoAcharA_Yv(dados.a, dados.b, dados.c, dados.yVerticeRacional));
	}
}
