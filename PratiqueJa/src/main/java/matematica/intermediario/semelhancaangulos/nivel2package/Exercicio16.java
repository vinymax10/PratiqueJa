package matematica.intermediario.semelhancaangulos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;

public class Exercicio16 extends GeradorExercicio
{
	String[][] problemas = {
	{ "a=x,b,c", "", "s=a->a->c+b" },
	{ "a=x,b,e", "c=y", "c=c->c+e,s=a->a->b+c" },
	{ "a=x,c,d", "b=y", "c=b->b+d,s=a->a->b+c" },
	{ "b=x,a,c", "", "s=b->b+c->a" },
	{ "b=x,a,e", "c=y", "c=c->c+e,s=b->c+b->a" },
	{ "c=x,a,b", "", "s=c->c+b->a" },
	{ "c=x,a,d", "b=y", "c=b->b+d,s=c->c+b->a" },
	{ "d=x,a,c", "b=y", "s=b->c+b->a,c=d->b+d" },
	{ "e=x,a,b", "c=y", "s=c->c+b->a,c=e->c+e" },
	};

	@Override
	protected void construir()
	{
		int pos = rand.nextInt(problemas.length);

		String angleImage = problemas[pos][0];
		String angleResult = problemas[pos][1];
		String instrucao = problemas[pos][2];

		int b = 10 + rand.nextInt(15);
		int c = 10 + rand.nextInt(15);
		int a = c + b;
		int d = 180 - b;
		int e = 180 - c;

		ConfigSemelhancaAngulos4 config = new ConfigSemelhancaAngulos4(a, b, c, d, e);

		String resultadoCorreto = "" + AuxSemelhancaAngulos.getAnguloLabel(angleImage, config, "x").angulo + "°";

		AuxSemelhancaAngulos.mostrarAngulos(angleImage, config);

		BufferedImage image = config.criarImagem();

		AuxSemelhancaAngulos.mostrarAngulos(angleResult, config);

		String resolucao = AuxSemelhancaAngulos.resolucao(instrucao, config);

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
