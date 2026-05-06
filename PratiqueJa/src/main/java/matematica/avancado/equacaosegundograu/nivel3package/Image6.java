package matematica.avancado.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Auxiliar;
import matematica.avancado.equacaosegundograu.ResolucaoEq2Grau;
import matematica.avancado.equacaosegundograu.config.ConfigEq2Grau;
import matematica.avancado.equacaosegundograu.config.DadosEq2Grau;
import modelo.matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.xVerticeRacional;
		textLatex = dadosEq2Grau.toString();
		pergunta = "Encontre \\(x_v\\), dado \\(f(x)=" + dadosEq2Grau.a + "x^2" 
		+ Auxiliar.getNumber(dadosEq2Grau.b, "", false) + "x" + Auxiliar.getNumber(dadosEq2Grau.c, "", false)+" \\)";
		
		resolucaoLatex=ResolucaoEq2Grau.resolucaoXVertice(dadosEq2Grau.a,dadosEq2Grau.b,dadosEq2Grau.c);
		
		ConfigEq2Grau config=new ConfigEq2Grau(dadosEq2Grau);
		config.indice=indice;
		config.pontoXv.mostrar=true;
		config.pontoXv.label="x_v";

		BufferedImage image=config.criarImagem();
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}

}
