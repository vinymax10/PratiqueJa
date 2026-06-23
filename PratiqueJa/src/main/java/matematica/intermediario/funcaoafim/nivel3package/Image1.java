package matematica.intermediario.funcaoafim.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.intermediario.funcaoafim.ResolucaoFuncaoAfim;
import matematica.intermediario.funcaoafim.config.ConfigRetaReal;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pontoAx = -(2 + rand.nextInt(7));
		int pontoAy = 2 + rand.nextInt(7);
		if(rand.nextBoolean())
			pontoAy *= -1;

		int pontoBx = 2 + rand.nextInt(7);
		int pontoBy = 2 + rand.nextInt(7);
		if(rand.nextBoolean())
			pontoBy *= -1;

		double a = (double) (pontoBy - pontoAy) / (pontoBx - pontoAx);
		double b = pontoAy - (a * pontoAx);

		Racional aRacional = new Racional((pontoBy - pontoAy)).div(new Racional(pontoBx - pontoAx));
		aRacional.fatoracao(2);

		ConfigRetaReal config = new ConfigRetaReal(a, b, pontoAx, pontoAy, pontoBx, pontoBy);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o coeficiente angular da reta:");
		addParagrafoImagem(image);
		gerarAlternativas("" + aRacional);

		addResolucao("Dados os pontos  \\(A=(" + pontoAx + "," + pontoAy + ")\\)  e "
			+ "\\(B=(" + pontoBx + "," + pontoBy + ")\\), "
			+ "temos que o coeficiente angular  \\(a\\)  é calculado por:");
		for(String passo : ResolucaoFuncaoAfim.resolucao(pontoAx, pontoAy, pontoBx, pontoBy))
			addResolucao("\\(" + passo + "\\)");
	}
}
