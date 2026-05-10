package matematica.avancado.funcaoafim.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.avancado.funcaoafim.config.ConfigRetaInteiro;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int a = -3 + rand.nextInt(7);
		int b = -3 + rand.nextInt(7);

		while (a == 0 && b == 0)
		{
			a = -3 + rand.nextInt(7);
			b = -3 + rand.nextInt(7);
		}

		resultadoCorreto = "" + b;

		textLatex = a + "" + b;
		pergunta = "Encontre o valor de b: \\( f(x)="+a+"x+b \\)";
		
		ConfigRetaInteiro config=new ConfigRetaInteiro(a,b);
		BufferedImage image=config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");
		
		int pontoAx=(int)config.pontoAx;
		int pontoAy=(int)config.pontoAy;
		
		resolucaoLatex="";
		
		resolucaoLatex+="\\text{O coeficiente linear } b \\text{ pode ser calculado por: }\\\\";

		resolucaoLatex+="y="+a+"x + b\\\\";
		resolucaoLatex+="\\text{Considerando o ponto } ("+pontoAx+","+pontoAy+"), \\text{ temos: }\\\\";
		
		MyExpression expressao = new MyExpression(pontoAy+" = "+a+"*"+pontoAx+"+b");
		resolucaoLatex+=expressao.resolverLatex();
		
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}

}
