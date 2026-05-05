package Matematica.Intermediario.AnguloInscritoCircunferencia.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.Config6;
import Matematica.Intermediario.AnguloInscritoCircunferencia.Config.DadosConfig6;
import Modelo.Matematica.Conta;


public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);

		int a = 55 + rand.nextInt(20);
		int intAnguloExterno = 10 + rand.nextInt(20);
		int c = a - intAnguloExterno;

		String lateralEsq = 2 * a + "°";
		String anguloExterno = intAnguloExterno + "°";
		String lateralDir = 2 * c + "°";
		resultadoCorreto = "" + lateralDir;

		textLatex = lateralEsq;

		resolucaoLatex ="";
		resolucaoLatex += "a=\\dfrac{"+(2 * a)+"}{2}="+a+"\\\\";
		
		MyExpression expressao = new MyExpression(a+"+b=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		int b=180-a;
//		resolucaoLatex += "a+b+c=180\\\\";
		
		expressao = new MyExpression(intAnguloExterno+"+"+b+"+c=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		expressao = new MyExpression("x=2*"+c);
		resolucaoLatex+=expressao.resolverLatex();
		
		DadosConfig6 dados = new DadosConfig6();
		dados.lateralEsq = lateralEsq;
		dados.lateralDir = "x";
		dados.anguloExterno = anguloExterno;
		
		Config6 config = new Config6(dados);
		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");
		
		dados.superiorEsq = "a";
		dados.superiorDir = "b";
		dados.inferiorEsq = "c";
		config=new Config6(dados);
		image=config.criarImagem(index);
		baosResolucao = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}
}
