package matematica.intermediario.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Auxiliar;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;
import modelo.matematica.Conta;


public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.b;

		textLatex = dadosEq2Grau.toString();
		pergunta = "Encontre \\(b\\), dado \\(f(x)=" + dadosEq2Grau.a + "x^2+bx" 
		+ Auxiliar.getNumber(dadosEq2Grau.c, "", false)+" \\)";
		
		resolucaoLatex=ResolucaoEq2Grau.resolucaoBXv(dadosEq2Grau.a,dadosEq2Grau.b,dadosEq2Grau.c,dadosEq2Grau.xVerticeRacional);
		
		ConfigEq2Grau config=new ConfigEq2Grau(dadosEq2Grau);
		config.indice=indice;
		config.pontoXv.mostrar=true;
		config.pontoXv.label=dadosEq2Grau.xVerticeRacional.showFrac();
		
		BufferedImage image=config.criarImagem();
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image8(1);
	}

}
