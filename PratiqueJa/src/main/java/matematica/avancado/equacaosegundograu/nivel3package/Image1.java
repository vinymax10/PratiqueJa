package matematica.avancado.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.avancado.equacaosegundograu.ResolucaoEq2Grau;
import matematica.avancado.equacaosegundograu.config.ConfigEq2Grau;
import matematica.avancado.equacaosegundograu.config.DadosEq2Grau;
import modelo.matematica.Conta;


public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.x1;

		textLatex = dadosEq2Grau.toString();
		pergunta = "Encontre \\(x_1 \\), dado \\( f(x)=" + dadosEq2Grau.a + "x^2" + get(dadosEq2Grau.b) + "x+c \\)";
		
		resolucaoLatex=ResolucaoEq2Grau.resolucaoX1(dadosEq2Grau.a,dadosEq2Grau.b,dadosEq2Grau.c);
		
		ConfigEq2Grau config=new ConfigEq2Grau(dadosEq2Grau);
		config.indice=indice;
		config.pontoX1.label="x_1";
		config.pontoX1.mostrar=true;
		config.pontoC.label=""+dadosEq2Grau.c;
		config.pontoC.mostrar=true;
		
		BufferedImage image=config.criarImagem();
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
        Graphics.salvar(image,true,"image.png");

	}

	public String get(int x)
	{
		if (x >= 0)
			return "+" + x;
		else
			return "" + x;
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}

}
