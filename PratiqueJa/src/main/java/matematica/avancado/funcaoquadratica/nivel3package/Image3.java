package matematica.avancado.funcaoquadratica.nivel3package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosEq2Grau dados = new DadosEq2Grau();

		String aDisp = dados.a == 1 ? "" : (dados.a == -1 ? "-" : dados.a + "");
		String funcao = "f(x)=" + aDisp + "x^2+bx"
				+ Auxiliar.getNumber(dados.c, "", false);

		ConfigEq2Grau config = new ConfigEq2Grau(dados);
		config.indice = 1 + rand.nextInt(10);
		config.pontoXv.mostrar = true;
		config.pontoXv.label = dados.xVerticeRacional.showFrac();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre \\(b\\), dado \\(f(x)=" + aDisp + "x^2+bx"
				+ Auxiliar.getNumber(dados.c, "", false) + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas("" + dados.b);
		setResolucao(ResolucaoFuncaoQuadratica.resolucaoAcharB_Xv(dados.a, dados.b, dados.c, dados.xVerticeRacional));
	}
}
