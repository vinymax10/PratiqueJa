package Matematica.Intermediario.AnguloInscritoCircunferencia.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.Config5;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.DadosConfig5;
import Modelo.Matematica.Conta;


public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int index)
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
		
		resultadoCorreto = "" + arcoEsq;

		textLatex = arcoEsq;
		
		resolucaoLatex ="";
		MyExpression expressao = new MyExpression(anguloCentral+"+c=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";

		resolucaoLatex += "a=\\dfrac{"+(2 * metadeArcoDir)+"}{2}="+metadeArcoDir+"\\\\";

		resolucaoLatex += "a+b+c=180\\\\";

		int c=180-metadeArcoEsq-metadeArcoDir;
		expressao = new MyExpression(c+"+"+metadeArcoDir+"+b=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		expressao = new MyExpression("x=2*"+metadeArcoEsq);
		resolucaoLatex+=expressao.resolverLatex();
		
		DadosConfig5 dados = new DadosConfig5();
		dados.lateralEsq = "x";
		dados.centroDir = anguloCentral+ "°";
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
		new Image7(1);
	}
}
