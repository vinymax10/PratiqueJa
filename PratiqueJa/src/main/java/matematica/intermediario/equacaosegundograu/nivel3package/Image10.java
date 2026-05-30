package matematica.intermediario.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Auxiliar;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;
import modelo.matematica.Conta;

public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image10(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.a;

		textLatex = dadosEq2Grau.toString();

		pergunta = "Encontre \\( a\\), dado \\( f(x)=" + "ax^2" 
		+ Auxiliar.getNumber(dadosEq2Grau.b, "x", false) + Auxiliar.getNumber(dadosEq2Grau.c, "", false)+"\\)";
		
		resolucaoLatex=ResolucaoEq2Grau.resolucaoAYv(dadosEq2Grau.a,dadosEq2Grau.b,
		dadosEq2Grau.c,dadosEq2Grau.yVerticeRacional);
		
		ConfigEq2Grau config=new ConfigEq2Grau(dadosEq2Grau);
		config.indice=indice;
		config.pontoYv.mostrar=true;
		config.pontoYv.label=dadosEq2Grau.yVerticeRacional.showFrac();
		
		BufferedImage image=config.criarImagem();
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image10(1);
	}

}
