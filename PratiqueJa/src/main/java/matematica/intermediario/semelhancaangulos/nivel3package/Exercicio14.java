package matematica.intermediario.semelhancaangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;
import matematica.intermediario.semelhancaangulos.nivel1package.ConfigSemelhancaAngulos2;

public class Exercicio14 extends GeradorExercicio
{
	// Subconjunto dos problemas de 1 ângulo conhecido do nivel1/Exercicio2,
	// com expressão algébrica no ângulo desconhecido
	String[][] problemas = {
	{ "a=x,b", "", "i=a" },
	{ "a=x,c", "", "c=a->a+c" },
	{ "a=x,d", "", "c=a->d+a" },
	{ "a=x,e", "", "i=a" },
	{ "b=x,a", "", "i=b" },
	{ "b=x,c", "", "c=b->c+b" },
	{ "b=x,d", "", "c=b->b+d" },
	{ "b=x,f", "", "i=b" },
	{ "c=x,a", "", "c=c->a+c" },
	{ "c=x,b", "", "c=c->c+b" },
	{ "c=x,d", "", "i=c" },
	{ "c=x,g", "", "i=c" },
	{ "d=x,a", "", "c=d->d+a" },
	{ "d=x,b", "", "c=d->b+d" },
	{ "d=x,c", "", "i=d" },
	{ "d=x,h", "", "i=d" },
	{ "e=x,a", "", "i=e" },
	{ "e=x,f", "", "i=e" },
	{ "e=x,g", "", "c=e->e+g" },
	{ "e=x,h", "", "c=e->h+e" },
	{ "f=x,b", "", "i=f" },
	{ "f=x,e", "", "i=f" },
	{ "f=x,g", "", "c=f->g+f" },
	{ "f=x,h", "", "c=f->f+h" },
	{ "g=x,c", "", "i=g" },
	{ "g=x,e", "", "c=g->e+g" },
	{ "g=x,f", "", "c=g->g+f" },
	{ "g=x,h", "", "i=g" },
	{ "h=x,d", "", "i=h" },
	{ "h=x,e", "", "c=h->h+e" },
	{ "h=x,f", "", "c=h->f+h" },
	{ "h=x,g", "", "i=h" },
	};

	@Override
	protected void construir()
	{
		int pos = rand.nextInt(problemas.length);

		String angleImage = problemas[pos][0];
		String angleResult = problemas[pos][1];
		String instrucao = problemas[pos][2];

		int a, b, c, d, e, f, g, h;
		b = e = f = a = 20 + rand.nextInt(50);
		c = d = g = h = 180 - a;

		ConfigSemelhancaAngulos2 config = new ConfigSemelhancaAngulos2(a, b, c, d, e, f, g, h);

		int resultado = 1 + rand.nextInt(50);
		String resultadoCorreto = "" + resultado;

		AuxSemelhancaAngulos.mostrarAngulosExpressao(angleImage, config, resultado);

		BufferedImage image = config.criarImagem();

		AuxSemelhancaAngulos.mostrarAngulos(angleResult, config);

		String resolucao = AuxSemelhancaAngulos.resolucao(instrucao, config);

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
