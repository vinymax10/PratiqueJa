package Matematica.Intermediario.SemelhancaAngulos.Nivel2Package;

import java.awt.image.BufferedImage;

import javax.persistence.Transient;

import Auxiliar.Graphics;
import Matematica.Intermediario.SemelhancaAngulos.AuxSemelhancaAngulos;
import Modelo.Matematica.Conta;

public class Exercicio1 extends Conta
{
	private static final long serialVersionUID = 1L;
	
	@Transient
	String [][] problemas= {
	//1Conhecido

	//2Conhecido
	{"a=x,b,c", "", "s=a->a->c+b"},
	{"a=x,b,e", "c=y", "c=c->c+e,s=a->a->b+c"},
	{"a=x,c,d", "b=y", "c=b->b+d,s=a->a->b+c"},
	{"b=x,a,c", "", "s=b->b+c->a"},
	{"b=x,a,e", "c=y", "c=c->c+e,s=b->c+b->a"},
	{"c=x,a,b", "", "s=c->c+b->a"},
	{"c=x,a,d", "b=y", "c=b->b+d,s=c->c+b->a"},
	{"d=x,a,c", "b=y", "s=b->c+b->a,c=d->b+d"},
	{"e=x,a,b", "c=y", "s=c->c+b->a,c=e->c+e"},
	};
	
	public Exercicio1(int index)
	{
		super(index);
		int pos=rand.nextInt(problemas.length);
//		int pos=index;

		String angleImage=problemas[pos][0];
		String angleResult=problemas[pos][1];
		String instrucao=problemas[pos][2];
		
		int b = 10 + rand.nextInt(15);
		int c = 10 + rand.nextInt(15);
		int a = c + b;
		int d = 180 - b;
		int e = 180 - c;
		
		ConfigSemelhancaAngulos4 config = new ConfigSemelhancaAngulos4(a, b, c, d, e);

		resultadoCorreto = "" + AuxSemelhancaAngulos.getAnguloLabel(angleImage, config, "x").angulo + "°";

		AuxSemelhancaAngulos.mostrarAngulos(angleImage, config);

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
		for(int i = 1; i < 11; i++)
		{
			new Exercicio1(i);
		}
	}

}
