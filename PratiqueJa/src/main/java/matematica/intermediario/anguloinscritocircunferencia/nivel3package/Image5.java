package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config5;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig5;
import modelo.matematica.Conta;


public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);

		int metadeArcoEsq = 30 + rand.nextInt(30);
		int metadeArcoDir = 30 + rand.nextInt(30);
		int anguloCentral = metadeArcoEsq + metadeArcoDir;

		if (metadeArcoEsq < metadeArcoDir)
		{
			int aux = metadeArcoDir;
			metadeArcoDir = metadeArcoEsq;
			metadeArcoEsq = aux;
		}

		String arcoEsq = 2 * metadeArcoEsq + "°";
		String arcoDir = 2 * metadeArcoDir + "°";
		resultadoCorreto = "" + anguloCentral + "°";

		textLatex = arcoEsq;
		
		resolucaoLatex ="";
		resolucaoLatex += "a=\\dfrac{"+(2 * metadeArcoDir)+"}{2}="+metadeArcoDir+"\\\\";
		resolucaoLatex += "b=\\dfrac{"+(2 * metadeArcoEsq)+"}{2}="+metadeArcoEsq+"\\\\";
		resolucaoLatex += "a+b+c=180\\\\";
		MyExpression expressao = new MyExpression(metadeArcoEsq+"+"+metadeArcoDir+"+c=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		int c=180-metadeArcoEsq-metadeArcoDir;
		expressao = new MyExpression(c+"+x=180");
		resolucaoLatex+=expressao.resolverLatex();
		
		DadosConfig5 dados = new DadosConfig5();
		dados.lateralEsq = arcoEsq;
		dados.centroEsq = "x";
		dados.lateralDir = arcoDir;
		
		Config5 config=new Config5(dados);
		BufferedImage image=config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		dados.superiorEsq = "a";
		dados.superiorDir = "b";
		dados.superiorCentro = "c";
		config=new Config5(dados);
		image=config.criarImagem(index);
		baosResolucao = Graphics.salvar(image, false, "");
		
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}

}
