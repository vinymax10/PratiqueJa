package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config6;
import matematica.intermediario.anguloinscritocircunferencia.config.DadosConfig6;
import modelo.matematica.Conta;


public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image10(int index)
	{
		super(index);

		int a = 55 + rand.nextInt(20);
		int intAnguloExterno = 10 + rand.nextInt(20);
		int c = a - intAnguloExterno;

		String lateralEsq = 2 * a + "°";
		String anguloExterno = intAnguloExterno + "°";
		String lateralDir = 2 * c + "°";
		resultadoCorreto = "" + lateralEsq;

		textLatex = lateralEsq;

		resolucaoLatex ="";
		resolucaoLatex += "c=\\dfrac{"+(2 * c)+"}{2}="+c+"\\\\";
		
		MyExpression expressao = new MyExpression("b+"+intAnguloExterno+"+"+c+"=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		int b=180-a;
		
		expressao = new MyExpression(b+"+a=180");
		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		expressao = new MyExpression("x=2*"+a);
		resolucaoLatex+=expressao.resolverLatex();
		
		DadosConfig6 dados = new DadosConfig6();
		dados.lateralEsq = "x";
		dados.lateralDir = lateralDir;
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
		new Image10(1);
	}

}
