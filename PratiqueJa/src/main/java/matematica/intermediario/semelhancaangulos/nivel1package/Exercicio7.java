package matematica.intermediario.semelhancaangulos.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;

public class Exercicio7 extends GeradorExercicio
{
	String[][] problemas = {
	{ "a=x,b", "", "i=a" },
	{ "a=x,c", "", "c=a->a+c" },
	{ "a=x,d", "", "c=a->d+a" },
	{ "a=x,e", "", "i=a" },
	{ "a=x,f", "b=y", "i=b,i=a" },
	{ "a=x,g", "c=y", "i=c,c=a->a+c" },
	{ "a=x,h", "d=y", "i=d,c=a->d+a" },
	{ "b=x,a", "", "i=b" },
	{ "b=x,c", "", "c=b->c+b" },
	{ "b=x,d", "", "c=b->b+d" },
	{ "b=x,e", "a=y", "i=a,i=b" },
	{ "b=x,f", "", "i=b" },
	{ "b=x,h", "d=y", "i=d,c=b->b+d" },
	{ "c=x,a", "", "c=c->a+c" },
	{ "c=x,b", "", "c=c->c+b" },
	{ "c=x,d", "", "i=c" },
	{ "c=x,e", "a=y", "i=a,c=c->a+c" },
	{ "c=x,f", "b=y", "i=b,c=c->c+b" },
	{ "c=x,g", "", "i=c" },
	{ "c=x,h", "d=y", "i=d,i=c" },
	{ "d=x,a", "", "c=d->d+a" },
	{ "d=x,b", "", "c=d->b+d" },
	{ "d=x,c", "", "i=d" },
	{ "d=x,f", "b=y", "i=b,c=d->b+d" },
	{ "d=x,g", "c=y", "i=c,i=d" },
	{ "d=x,h", "", "i=d" },
	{ "e=x,a", "", "i=e" },
	{ "e=x,b", "a=y", "i=a,i=e" },
	{ "e=x,c", "g=y", "i=g,c=e->e+g" },
	{ "e=x,f", "", "i=e" },
	{ "e=x,g", "", "c=e->e+g" },
	{ "e=x,h", "", "c=e->h+e" },
	{ "f=x,a", "b=y", "i=b,i=f" },
	{ "f=x,b", "", "i=f" },
	{ "f=x,c", "g=y", "i=g,c=f->g+f" },
	{ "f=x,d", "h=y", "i=h,c=f->f+h" },
	{ "f=x,e", "", "i=f" },
	{ "f=x,g", "", "c=f->g+f" },
	{ "f=x,h", "", "c=f->f+h" },
	{ "g=x,a", "e=y", "i=e,c=g->e+g" },
	{ "g=x,c", "", "i=g" },
	{ "g=x,d", "c=y", "i=c,i=g" },
	{ "g=x,e", "", "c=g->e+g" },
	{ "g=x,f", "", "c=g->g+f" },
	{ "g=x,h", "", "i=g" },
	{ "h=x,a", "e=y", "i=e,c=h->h+e" },
	{ "h=x,b", "f=y", "i=f,c=h->f+h" },
	{ "h=x,c", "d=y", "i=d,i=h" },
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
