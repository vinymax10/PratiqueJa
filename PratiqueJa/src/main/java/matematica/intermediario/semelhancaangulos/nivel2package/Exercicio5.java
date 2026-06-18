package matematica.intermediario.semelhancaangulos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;

public class Exercicio5 extends GeradorExercicio
{
	private static final long serialVersionUID = 1L;
	
	String [][] problemas= {
	//1Conhecido
	{"b=x,g", "h=y", "c=h->g+h,i=b,"},
	{"b=x,i", "h=y", "c=h->h+i,i=b,"},
	{"b=x,j", "e=y", "i=e,i=b"},
	{"e=x,g", "j=y", "c=j->j+g,i=e,"},
	{"e=x,h", "b=y", "i=b,i=e"},
	{"e=x,i", "j=y", "c=j->i+j,i=e,"},
	{"g=x,b", "h=y", "i=h,c=g->g+h"},
	{"g=x,e", "j=y", "i=j,c=g->j+g"},
	{"h=x,e", "b=y", "i=b,i=h"},
	{"i=x,b", "h=y", "i=h,c=i->h+i"},
	{"i=x,e", "j=y", "i=j,c=i->i+j"},
	{"j=x,b", "e=y", "i=e,i=j"},

	//2Conhecido
	{"a=x,b,g", "f=y", "c=f->f+a+b,s=a->a+f->g"},
	{"a=x,c,e", "b=y", "i=b,c=a->a+b+c"},
	{"a=x,c,g", "f=y", "i=f,s=a->a+f->g"},
	{"a=x,c,h", "b=y", "i=b,c=a->a+b+c"},
	{"a=x,c,j", "f=y,e=z", "i=f,i=e,c=a->e+f+a"},
	{"a=x,e,g", "f=y", "c=f->e+f+a,s=a->a+f->g"},
	{"a=x,f,g", "", "s=a->a+f->g"},
	{"a=x,f,h", "b=y", "i=b,c=a->f+a+b"},
	{"a=x,f,j", "e=y", "i=e,c=a->e+f+a"},
	{"b=x,a,g", "f=y", "s=f->a+f->g,c=b->f+a+b"},
	{"b=x,a,i", "h=y", "c=h->h+i,i=b"},
	{"b=x,c,g", "h=y", "c=h->g+h,i=b"},
	{"b=x,c,i", "d=y", "s=d->c+d->i,c=b->b+c+d"},
	{"b=x,d,f", "c=y", "i=c,c=b->b+c+d"},
	{"b=x,d,g", "h=y", "c=h->g+h,i=b"},
	{"b=x,d,i", "c=y", "s=c->c+d->i,c=b->b+c+d"},
	{"b=x,f,g", "a=y", "s=a->a+f->g,c=b->f+a+b"},
	{"b=x,f,i", "h=y", "c=h->h+i,i=b"},
	{"c=x,a,e", "b=y", "i=b,c=c->a+b+c"},
	{"c=x,a,h", "b=y", "i=b,c=c->a+b+c"},
	{"c=x,a,i", "d=y", "i=d,s=c->c+d->i"},
	{"c=x,a,j", "d=y,e=z", "i=d,i=e,c=c->c+d+e"},
	{"c=x,b,i", "d=y", "c=d->b+c+d,s=c->c+d->i"},
	{"c=x,d,h", "b=y", "i=b,c=c->b+c+d"},
	{"c=x,d,i", "", "s=c->c+d->i"},
	{"c=x,d,j", "e=y", "i=e,c=c->c+d+e"},
	{"c=x,e,i", "d=y", "c=d->c+d+e,s=c->c+d->i"},
	{"d=x,b,f", "c=y", "i=c,c=d->b+c+d"},
	{"d=x,b,i", "c=y", "c=c->b+c+d,s=d->c+d->i"},
	{"d=x,c,h", "b=y", "i=b,c=d->b+c+d"},
	{"d=x,c,i", "", "s=d->d+c->i"},
	{"d=x,c,j", "e=y", "i=e,c=d->c+d+e"},
	{"d=x,e,i", "c=y", "c=c->c+d+e,s=d->c+d->i"},
	{"d=x,f,h", "c=y,b=z", "i=c,i=b,c=d->b+c+d"},
	{"d=x,f,i", "c=y", "i=c,s=d->d+c->i"},
	{"d=x,f,j", "e=y", "i=e,c=d->d+e+f"},
	{"e=x,a,c", "d=y", "i=d,c=e->c+d+e"},
	{"e=x,a,g", "f=y", "s=f->a+f->g,c=e->e+f+a"},
	{"e=x,a,i", "j=y", "c=j->i+j,i=e"},
	{"e=x,c,g", "j=y", "c=j->j+g,i=e"},
	{"e=x,c,i", "d=y", "s=d->c+d->i,c=e->c+d+e"},
	{"e=x,d,g", "j=y", "c=j->j+g,i=e"},
	{"e=x,d,i", "c=y", "s=c->c+d->i,c=e->c+d+e"},
	{"e=x,f,g", "a=y", "s=a->a+f->g,c=e->e+f+a"},
	{"e=x,f,i", "j=y", "c=j->i+j,i=e"},
	{"f=x,a,g", "", "s=f->f+a->g"},
	{"f=x,a,h", "b=y", "i=b,c=f->f+a+b"},
	{"f=x,a,j", "e=y", "i=e,c=f->e+f+a"},
	{"f=x,b,d", "e=y", "i=e,c=f->d+e+f"},
	{"f=x,b,g", "a=y", "c=a->f+a+b,s=f->a+f->g"},
	{"f=x,d,g", "a=y", "i=a,s=f->f+a->g"},
	{"f=x,d,h", "a=y,b=z", "i=a,i=b,c=f->f+a+b"},
	{"f=x,d,j", "e=y", "i=e,c=f->d+e+f"},
	{"f=x,e,g", "a=y", "c=a->e+f+a,s=f->a+f->g"},
	{"g=x,b,c", "h=y", "i=h,c=g->g+h"},
	{"g=x,b,d", "h=y", "i=h,c=g->g+h"},
	{"g=x,c,d", "f=y,a=z", "i=f,i=a,s=g->g->f+a"},
	{"g=x,c,e", "j=y", "i=j,c=g->j+g"},
	{"g=x,d,e", "j=y", "i=j,c=g->j+g"},
	{"h=x,a,c", "b=y", "c=b->a+b+c,i=h"},
	{"h=x,a,f", "b=y", "c=b->f+a+b,i=h"},
	{"h=x,c,d", "b=y", "c=b->b+c+d,i=h"},
	{"i=x,a,b", "h=y", "i=h,c=i->h+i"},
	{"i=x,a,e", "j=y", "i=j,c=i->i+j"},
	{"i=x,a,f", "d=y,c=z", "i=d,i=c,s=i->i->d+c"},
	{"i=x,b,f", "h=y", "i=h,c=i->h+i"},
	{"i=x,e,f", "j=y", "i=j,c=i->i+j"},
	{"j=x,a,f", "e=y", "c=e->e+f+a,i=j"},
	{"j=x,c,d", "e=y", "c=e->c+d+e,i=j"},
	{"j=x,d,f", "e=y", "c=e->d+e+f,i=j"},
	};
	
	@Override
	protected void construir()
	{
		int pos=rand.nextInt(problemas.length);
//		int pos=index;

		String angleImage=problemas[pos][0];
		String angleResult=problemas[pos][1];
		String instrucao=problemas[pos][2];
		
		int a = 35 + rand.nextInt(20);
		int b = 35 + rand.nextInt(20);
		int c, d, e, f, g, h, i, j;
		h = j = e = b;
		d = a;
		f = 180 - a - b;
		c = f;
		g = i = a + f;
		
		ConfigSemelhancaAngulos5 config = new ConfigSemelhancaAngulos5(a, b, c, d, e, f, g, h, i, j);

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
