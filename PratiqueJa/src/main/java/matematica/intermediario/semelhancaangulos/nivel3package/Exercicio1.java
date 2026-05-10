package matematica.intermediario.semelhancaangulos.nivel3package;

import java.awt.image.BufferedImage;

import jakarta.persistence.Transient;

import infra.Graphics;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;
import matematica.intermediario.semelhancaangulos.nivel1package.ConfigSemelhancaAngulos1;
import modelo.matematica.Conta;

public class Exercicio1 extends Conta
{
	private static final long serialVersionUID = 1L;
	
	@Transient
	String [][] problemas= {
	//1Conhecido
	{"a=x,b", "", "i=a"},
	{"a=x,c", "", "c=a->a+c"},
	{"a=x,d", "", "c=a->d+a"},
	{"b=x,a", "", "i=b"},
	{"b=x,c", "", "c=b->c+b"},
	{"b=x,d", "", "c=b->b+d"},
	{"c=x,a", "", "c=c->a+c"},
	{"c=x,b", "", "c=c->c+b"},
	{"c=x,d", "", "i=c"},
	{"d=x,a", "", "c=d->d+a"},
	{"d=x,b", "", "c=d->b+d"},
	{"d=x,c", "", "i=d"},
	};
	
	public Exercicio1(int index)
	{
		super(index);
		int pos=rand.nextInt(problemas.length);
//		int pos=index;

		String angleImage=problemas[pos][0];
		String angleResult=problemas[pos][1];
		String instrucao=problemas[pos][2];
		
		int a = 20 + rand.nextInt(50);
		int b = 180 - a;
		
		ConfigSemelhancaAngulos1 config = new ConfigSemelhancaAngulos1(a, a, b, b);

		int resultado=1 + rand.nextInt(50);
		resultadoCorreto = "" + resultado+"°";
		
		AuxSemelhancaAngulos.mostrarAngulosExpressao(angleImage, config, resultado);

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
		new Exercicio1(1);
	}

}
