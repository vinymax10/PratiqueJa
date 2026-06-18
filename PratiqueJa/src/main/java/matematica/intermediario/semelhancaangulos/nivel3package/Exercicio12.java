package matematica.intermediario.semelhancaangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;
import matematica.intermediario.semelhancaangulos.nivel2package.ConfigSemelhancaAngulos6;
public class Exercicio12 extends GeradorExercicio
{
	private static final long serialVersionUID = 1L;
	
	String [][] problemas= {
	
	//1Conhecido
	{"b=x,g", "h=y", "c=h->g+h,i=b,"},
	{"b=x,i", "h=y", "c=h->h+i,i=b,"},
	{"b=x,j", "e=y", "i=e,i=b"},
	{"c=x,l", "f=y", "i=f,i=c"},
	{"c=x,m", "n=y", "c=n->m+n,i=c,"},
	{"c=x,o", "n=y", "c=n->n+o,i=c,"},
	{"e=x,g", "j=y", "c=j->j+g,i=e,"},
	{"e=x,h", "b=y", "i=b,i=e"},
	{"e=x,i", "j=y", "c=j->i+j,i=e,"},
	{"f=x,m", "l=y", "c=l->l+m,i=f,"},
	{"f=x,n", "c=y", "i=c,i=f"},
	{"f=x,o", "l=y", "c=l->o+l,i=f,"},
	{"g=x,b", "h=y", "i=h,c=g->g+h"},
	{"g=x,e", "j=y", "i=j,c=g->j+g"},
	{"h=x,e", "b=y", "i=b,i=h"},
	{"l=x,c", "f=y", "i=f,i=l"},
	{"m=x,c", "n=y", "i=n,c=m->m+n"},
	{"m=x,f", "l=y", "i=l,c=m->l+m"},
	{"o=x,c", "n=y", "i=n,c=o->n+o"},
	{"o=x,f", "l=y", "i=l,c=o->o+l"},

	//2Conhecido
	{"b=x,a,g", "f=y", "s=f->a+f->g,c=b->f+a+b"},
	{"b=x,a,i", "h=y", "c=h->h+i,i=b"},
	{"b=x,a,l", "f=y", "i=f,c=b->f+a+b"},
	{"b=x,a,m", "", "s=b->b+a->m"},
	{"b=x,a,n", "c=y", "i=c,c=b->a+b+c"},
	{"b=x,c,g", "h=y", "c=h->g+h,i=b"},
	{"b=x,c,i", "d=y", "s=d->c+d->i,c=b->b+c+d"},
	{"b=x,d,f", "c=y", "i=c,c=b->b+c+d"},
	{"b=x,d,g", "h=y", "c=h->g+h,i=b"},
	{"b=x,d,i", "c=y", "s=c->c+d->i,c=b->b+c+d"},
	{"b=x,d,l", "a=y,f=z", "i=a,i=f,c=b->f+a+b"},
	{"b=x,d,m", "a=y", "i=a,s=b->b+a->m"},
	{"b=x,d,n", "c=y", "i=c,c=b->b+c+d"},
	{"b=x,f,g", "a=y", "s=a->a+f->g,c=b->f+a+b"},
	{"b=x,f,i", "h=y", "c=h->h+i,i=b"},
	{"b=x,g,l", "h=y", "c=h->g+h,i=b"},
	{"b=x,g,m", "h=y", "c=h->g+h,i=b"},
	{"b=x,g,n", "h=y", "c=h->g+h,i=b"},
	{"b=x,g,o", "h=y", "c=h->g+h,i=b"},
	{"b=x,i,l", "h=y", "c=h->h+i,i=b"},
	{"b=x,i,m", "h=y", "c=h->h+i,i=b"},
	{"b=x,i,n", "h=y", "c=h->h+i,i=b"},
	{"b=x,i,o", "h=y", "c=h->h+i,i=b"},
	{"c=x,a,e", "b=y", "i=b,c=c->a+b+c"},
	{"c=x,a,h", "b=y", "i=b,c=c->a+b+c"},
	{"c=x,a,i", "d=y", "i=d,s=c->c+d->i"},
	{"c=x,a,j", "d=y,e=z", "i=d,i=e,c=c->c+d+e"},
	{"c=x,a,m", "b=y", "s=b->a+b->m,c=c->a+b+c"},
	{"c=x,a,o", "n=y", "c=n->n+o,i=c"},
	{"c=x,b,m", "a=y", "s=a->a+b->m,c=c->a+b+c"},
	{"c=x,b,o", "n=y", "c=n->n+o,i=c"},
	{"c=x,d,h", "b=y", "i=b,c=c->b+c+d"},
	{"c=x,d,i", "", "s=c->c+d->i"},
	{"c=x,d,j", "e=y", "i=e,c=c->c+d+e"},
	{"c=x,d,m", "n=y", "c=n->m+n,i=c"},
	{"c=x,d,o", "e=y", "s=e->e+d->o,c=c->c+d+e"},
	{"c=x,e,m", "n=y", "c=n->m+n,i=c"},
	{"c=x,e,o", "d=y", "s=d->e+d->o,c=c->c+d+e"},
	{"c=x,g,m", "n=y", "c=n->m+n,i=c"},
	{"c=x,g,o", "n=y", "c=n->n+o,i=c"},
	{"c=x,h,m", "n=y", "c=n->m+n,i=c"},
	{"c=x,h,o", "n=y", "c=n->n+o,i=c"},
	{"c=x,i,m", "n=y", "c=n->m+n,i=c"},
	{"c=x,i,o", "n=y", "c=n->n+o,i=c"},
	{"c=x,j,m", "n=y", "c=n->m+n,i=c"},
	{"c=x,j,o", "n=y", "c=n->n+o,i=c"},
	{"d=x,b,f", "c=y", "i=c,c=d->b+c+d"},
	{"d=x,b,n", "c=y", "i=c,c=d->b+c+d"},
	{"d=x,b,o", "e=y", "i=e,s=d->d+e->o"},
	{"d=x,c,i", "", "s=d->d+c->i"},
	{"d=x,c,j", "e=y", "i=e,c=d->c+d+e"},
	{"d=x,e,n", "c=y", "i=c,c=d->c+d+e"},
	{"d=x,e,o", "", "s=d->d+e->o"},
	{"d=x,f,h", "c=y,b=z", "i=c,i=b,c=d->b+c+d"},
	{"d=x,f,i", "c=y", "i=c,s=d->d+c->i"},
	{"d=x,f,j", "e=y", "i=e,c=d->d+e+f"},
	{"d=x,h,n", "b=y,c=z", "i=b,i=c,c=d->b+c+d"},
	{"d=x,i,n", "c=y", "i=c,s=d->d+c->i"},
	{"d=x,j,n", "e=y,c=z", "i=e,i=c,c=d->c+d+e"},
	{"d=x,j,o", "e=y", "i=e,s=d->d+e->o"},
	{"e=x,a,c", "d=y", "i=d,c=e->c+d+e"},
	{"e=x,a,g", "f=y", "s=f->a+f->g,c=e->e+f+a"},
	{"e=x,a,i", "j=y", "c=j->i+j,i=e"},
	{"e=x,a,l", "f=y", "i=f,c=e->e+f+a"},
	{"e=x,a,n", "d=y,c=z", "i=d,i=c,c=e->c+d+e"},
	{"e=x,a,o", "d=y", "i=d,s=e->e+d->o"},
	{"e=x,c,g", "j=y", "c=j->j+g,i=e"},
	{"e=x,c,i", "d=y", "s=d->c+d->i,c=e->c+d+e"},
	{"e=x,d,g", "j=y", "c=j->j+g,i=e"},
	{"e=x,d,i", "c=y", "s=c->c+d->i,c=e->c+d+e"},
	{"e=x,d,l", "f=y", "i=f,c=e->d+e+f"},
	{"e=x,d,n", "c=y", "i=c,c=e->c+d+e"},
	{"e=x,d,o", "", "s=e->e+d->o"},
	{"e=x,f,g", "a=y", "s=a->a+f->g,c=e->e+f+a"},
	{"e=x,f,i", "j=y", "c=j->i+j,i=e"},
	{"e=x,g,l", "j=y", "c=j->j+g,i=e"},
	{"e=x,g,m", "j=y", "c=j->j+g,i=e"},
	{"e=x,g,n", "j=y", "c=j->j+g,i=e"},
	{"e=x,g,o", "j=y", "c=j->j+g,i=e"},
	{"e=x,i,l", "j=y", "c=j->i+j,i=e"},
	{"e=x,i,m", "j=y", "c=j->i+j,i=e"},
	{"e=x,i,n", "j=y", "c=j->i+j,i=e"},
	{"e=x,i,o", "j=y", "c=j->i+j,i=e"},
	{"f=x,a,g", "", "s=f->f+a->g"},
	{"f=x,a,h", "b=y", "i=b,c=f->f+a+b"},
	{"f=x,a,j", "e=y", "i=e,c=f->e+f+a"},
	{"f=x,a,m", "b=y", "s=b->a+b->m,c=f->f+a+b"},
	{"f=x,a,o", "l=y", "c=l->o+l,i=f"},
	{"f=x,b,d", "e=y", "i=e,c=f->d+e+f"},
	{"f=x,b,m", "a=y", "s=a->a+b->m,c=f->f+a+b"},
	{"f=x,b,o", "l=y", "c=l->o+l,i=f"},
	{"f=x,d,g", "a=y", "i=a,s=f->f+a->g"},
	{"f=x,d,h", "a=y,b=z", "i=a,i=b,c=f->f+a+b"},
	{"f=x,d,j", "e=y", "i=e,c=f->d+e+f"},
	{"f=x,d,m", "l=y", "c=l->l+m,i=f"},
	{"f=x,d,o", "e=y", "s=e->e+d->o,c=f->d+e+f"},
	{"f=x,e,m", "l=y", "c=l->l+m,i=f"},
	{"f=x,e,o", "d=y", "s=d->e+d->o,c=f->d+e+f"},
	{"f=x,g,m", "l=y", "c=l->l+m,i=f"},
	{"f=x,g,o", "l=y", "c=l->o+l,i=f"},
	{"f=x,h,m", "l=y", "c=l->l+m,i=f"},
	{"f=x,h,o", "l=y", "c=l->o+l,i=f"},
	{"f=x,i,m", "l=y", "c=l->l+m,i=f"},
	{"f=x,i,o", "l=y", "c=l->o+l,i=f"},
	{"f=x,j,m", "l=y", "c=l->l+m,i=f"},
	{"f=x,j,o", "l=y", "c=l->o+l,i=f"},
	{"g=x,b,c", "h=y", "i=h,c=g->g+h"},
	{"g=x,b,d", "h=y", "i=h,c=g->g+h"},
	{"g=x,b,l", "h=y", "i=h,c=g->g+h"},
	{"g=x,b,m", "h=y", "i=h,c=g->g+h"},
	{"g=x,b,n", "h=y", "i=h,c=g->g+h"},
	{"g=x,b,o", "h=y", "i=h,c=g->g+h"},
	{"g=x,c,d", "f=y,a=z", "i=f,i=a,s=g->g->f+a"},
	{"g=x,c,e", "j=y", "i=j,c=g->j+g"},
	{"g=x,d,e", "j=y", "i=j,c=g->j+g"},
	{"g=x,d,l", "a=y,f=z", "i=a,i=f,s=g->g->a+f"},
	{"g=x,e,l", "j=y", "i=j,c=g->j+g"},
	{"g=x,e,m", "j=y", "i=j,c=g->j+g"},
	{"g=x,e,n", "j=y", "i=j,c=g->j+g"},
	{"g=x,e,o", "j=y", "i=j,c=g->j+g"},
	{"h=x,a,c", "b=y", "c=b->a+b+c,i=h"},
	{"h=x,a,f", "b=y", "c=b->f+a+b,i=h"},
	{"l=x,a,b", "f=y", "c=f->f+a+b,i=l"},
	{"l=x,a,e", "f=y", "c=f->e+f+a,i=l"},
	{"l=x,d,e", "f=y", "c=f->d+e+f,i=l"},
	{"m=x,c,d", "n=y", "i=n,c=m->m+n"},
	{"m=x,c,e", "n=y", "i=n,c=m->m+n"},
	{"m=x,c,g", "n=y", "i=n,c=m->m+n"},
	{"m=x,c,h", "n=y", "i=n,c=m->m+n"},
	{"m=x,c,i", "n=y", "i=n,c=m->m+n"},
	{"m=x,c,j", "n=y", "i=n,c=m->m+n"},
	{"m=x,d,e", "a=y,b=z", "i=a,i=b,s=m->m->a+b"},
	{"m=x,d,f", "l=y", "i=l,c=m->l+m"},
	{"m=x,d,h", "a=y,b=z", "i=a,i=b,s=m->m->a+b"},
	{"m=x,e,f", "l=y", "i=l,c=m->l+m"},
	{"m=x,f,g", "l=y", "i=l,c=m->l+m"},
	{"m=x,f,h", "l=y", "i=l,c=m->l+m"},
	{"m=x,f,i", "l=y", "i=l,c=m->l+m"},
	{"m=x,f,j", "l=y", "i=l,c=m->l+m"},
	};
	
	@Override
	protected void construir()
	{
		int pos=rand.nextInt(problemas.length);
		String angleImage=problemas[pos][0];
		String angleResult=problemas[pos][1];
		String instrucao=problemas[pos][2];
		
		int a = 30 + rand.nextInt(20);
		int b = 50 + rand.nextInt(20);
		int c, d, e, f, g, h, i, j, l, m, n, o;
		d = a;
		e = h = j = b;
		m = o = a + b;
		f = c = l = n = 180 - a - b;
		g = i = f + a;

		ConfigSemelhancaAngulos6 config = new ConfigSemelhancaAngulos6(a, b, c, d, e, f, g, h, i, j, l, m, n, o);

		int resultado=1 + rand.nextInt(50);
		String resultadoCorreto = "" + resultado+"°";
		
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
