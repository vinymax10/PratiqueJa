package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config6;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig6;
import modelo.matematica.Conta;


public class Image9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image9(int index)
	{
		super(index);

		int a = 55 + rand.nextInt(20);
		int intAnguloExterno = 10 + rand.nextInt(20);
		int c = a - intAnguloExterno;

		String lateralEsq = 2 * a + "°";
		String anguloExterno = intAnguloExterno + "°";
		String lateralDir = 2 * c + "°";
		resultadoCorreto = "" + anguloExterno;

		textLatex = lateralEsq;

		resolucaoLatex ="";
		resolucaoLatex += "a=\\dfrac{"+(2 * a)+"}{2}="+a+"\\\\";
		resolucaoLatex += "c=\\dfrac{"+(2 * c)+"}{2}="+c+"\\\\";

		MyExpression expressao = new MyExpression(a+"+b=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		int b=180-a;
		resolucaoLatex += "x+b+c=180\\\\";
		
		expressao = new MyExpression("x+"+b+"+"+c+"=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		DadosConfig6 dados = new DadosConfig6();
		dados.lateralEsq = lateralEsq;
		dados.lateralDir = lateralDir;
		dados.anguloExterno = "x";
		
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
		new Image9(1);
	}

}
