package matematica.intermediario.semelhancaangulos.nivel3package;

import java.awt.image.BufferedImage;

import jakarta.persistence.Transient;

import infra.Graphics;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;
import matematica.intermediario.semelhancaangulos.nivel1package.ConfigSemelhancaAngulos3;
import modelo.matematica.Conta;

public class Exercicio3 extends Conta
{
	private static final long serialVersionUID = 1L;
	
	@Transient
	String [][] problemas= {
	//1Conhecido
//	{"a=x,b", "", "i=a"},
//	{"a=x,c", "", "c=a->a+c"},
//	{"a=x,d", "", "c=a->d+a"},
//	{"a=x,e", "", "i=a"},
//	{"a=x,f", "b=y", "i=b,i=a"},
//	{"a=x,g", "c=y", "i=c,c=a->a+c"},
//	{"a=x,h", "d=y", "i=d,c=a->d+a"},
	{"b=x,a", "", "i=b"},
	{"b=x,c", "", "c=b->c+b"},
	{"b=x,d", "", "c=b->b+d"},
	{"b=x,e", "a=y", "i=a,i=b"},
	{"b=x,f", "", "i=b"},
	{"b=x,g", "c=y", "i=c,c=b->c+b"},
	{"b=x,h", "d=y", "i=d,c=b->b+d"},
	{"c=x,a", "", "c=c->a+c"},
	{"c=x,b", "", "c=c->c+b"},
	{"c=x,d", "", "i=c"},
	{"c=x,e", "a=y", "i=a,c=c->a+c"},
	{"c=x,f", "b=y", "i=b,c=c->c+b"},
	{"c=x,g", "", "i=c"},
	{"c=x,h", "d=y", "i=d,i=c"},
	{"d=x,a", "", "c=d->d+a"},
	{"d=x,b", "", "c=d->b+d"},
	{"d=x,c", "", "i=d"},
	{"d=x,e", "a=y", "i=a,c=d->d+a"},
	{"d=x,f", "b=y", "i=b,c=d->b+d"},
	{"d=x,g", "c=y", "i=c,i=d"},
	{"d=x,h", "", "i=d"},
	{"e=x,a", "", "i=e"},
	{"e=x,b", "a=y", "i=a,i=e"},
	{"e=x,c", "g=y", "i=g,c=e->e+g"},
	{"e=x,d", "h=y", "i=h,c=e->h+e"},
	{"e=x,f", "", "i=e"},
	{"e=x,g", "", "c=e->e+g"},
	{"e=x,h", "", "c=e->h+e"},
//	{"f=x,a", "b=y", "i=b,i=f"},
//	{"f=x,b", "", "i=f"},
//	{"f=x,c", "g=y", "i=g,c=f->g+f"},
//	{"f=x,d", "h=y", "i=h,c=f->f+h"},
//	{"f=x,e", "", "i=f"},
//	{"f=x,g", "", "c=f->g+f"},
//	{"f=x,h", "", "c=f->f+h"},
	{"g=x,a", "e=y", "i=e,c=g->e+g"},
	{"g=x,b", "f=y", "i=f,c=g->g+f"},
	{"g=x,c", "", "i=g"},
	{"g=x,d", "c=y", "i=c,i=g"},
	{"g=x,e", "", "c=g->e+g"},
	{"g=x,f", "", "c=g->g+f"},
	{"g=x,h", "", "i=g"},
	{"h=x,a", "e=y", "i=e,c=h->h+e"},
	{"h=x,b", "f=y", "i=f,c=h->f+h"},
	{"h=x,c", "d=y", "i=d,i=h"},
	{"h=x,d", "", "i=h"},
	{"h=x,e", "", "c=h->h+e"},
	{"h=x,f", "", "c=h->f+h"},
	{"h=x,g", "", "i=h"},
	};
	
	public Exercicio3(int index)
	{
		super(index);
		int pos=rand.nextInt(problemas.length);
//		int pos=index;

		String angleImage=problemas[pos][0];
		String angleResult=problemas[pos][1];
		String instrucao=problemas[pos][2];
		
		int a, b, c, d, e, f, g, h;
		b = e = f = a = 20 + rand.nextInt(50);
		c = d = g = h = 180 - a;
		
		ConfigSemelhancaAngulos3 config = new ConfigSemelhancaAngulos3(a, b, c, d, e, f, g, h);

		int resultado=1 + rand.nextInt(50);
		resultadoCorreto = "" + resultado+"°";
		
		AuxSemelhancaAngulos.mostrarAngulosExpressao(angleImage, config,resultado);

		textLatex = config.getTextLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		AuxSemelhancaAngulos.mostrarAngulos(angleResult, config);

		resolucaoLatex=AuxSemelhancaAngulos.resolucao(instrucao,config);
		
		if(!angleResult.equals(""))
		{
			BufferedImage imageResolucao = config.criarImagem(index);
			baosResolucao = Graphics.salvar(imageResolucao, false, "");
		}

		carregarBlob();
	}


	public static void main(String[] args)
	{
		new Exercicio3(1);
	}

}
