package matematica.avancado.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.avancado.equacaosegundograu.ResolucaoEq2Grau;
import matematica.avancado.equacaosegundograu.config.ConfigEq2Grau;
import matematica.avancado.equacaosegundograu.config.DadosEq2Grau;
import modelo.matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.b;
		
		textLatex = dadosEq2Grau.toString();
		pergunta = "Encontre \\( b\\), dado \\( f(x)=" + dadosEq2Grau.a + "x^2+bx+c \\)";
		
		resolucaoLatex="c="+dadosEq2Grau.c+"\\\\";
		resolucaoLatex+=ResolucaoEq2Grau.resolucaoBX1(dadosEq2Grau.a,dadosEq2Grau.b,dadosEq2Grau.c,dadosEq2Grau.x1);
		
		ConfigEq2Grau config=new ConfigEq2Grau(dadosEq2Grau);
		config.indice=indice;
		config.pontoX1.label=""+dadosEq2Grau.x1;
		config.pontoX1.mostrar=true;
		config.pontoC.label=""+dadosEq2Grau.c;
		config.pontoC.mostrar=true;
		
		BufferedImage image=config.criarImagem();
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}

}
