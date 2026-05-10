package matematica.avancado.funcaoafim.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Racional;
import matematica.avancado.funcaoafim.config.ConfigRetaReal;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int pontoAx = -(2 + rand.nextInt(7));
		int pontoAy = 2 + rand.nextInt(7);
		if (rand.nextBoolean())
			pontoAy *= -1;

		int pontoBx = 2 + rand.nextInt(7);
		int pontoBy = 2 + rand.nextInt(7);
		if (rand.nextBoolean())
			pontoBy *= -1;

		double a = (double) (pontoBy - pontoAy) / (pontoBx - pontoAx);
		double b = pontoAy - (a * pontoAx);

		Racional aRacional = new Racional((pontoBy - pontoAy)).div(new Racional(pontoBx - pontoAx));
		Racional bRacional = new Racional(pontoAy).minus(aRacional.mult(new Racional(pontoAx)));

		bRacional.fatoracao(2);
		resultadoCorreto = "" + bRacional;

		textLatex = a + "" + b;
		pergunta = "Encontre o valor de b:  \\( f(x)="+aRacional.toStringLatex()+"x+b \\)";

		ConfigRetaReal config=new ConfigRetaReal(a,b,pontoAx,pontoAy,pontoBx,pontoBy);
		BufferedImage image=config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");
		
		resolucaoLatex="";
		resolucaoLatex+="\\text{O coeficiente linear } b \\text{ pode ser calculado por: }\\\\";

		resolucaoLatex+="y="+aRacional.toStringLatex()+"x + b\\\\";
		resolucaoLatex+="\\text{Considerando o ponto } ("+pontoAx+","+pontoAy+"), \\text{ temos: }\\\\";
		
		MyExpression expressao = new MyExpression(pontoAy+" = "+aRacional.toString()+"*"+pontoAx+"+b");
		resolucaoLatex+=expressao.resolverLatex();
		
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}

}
