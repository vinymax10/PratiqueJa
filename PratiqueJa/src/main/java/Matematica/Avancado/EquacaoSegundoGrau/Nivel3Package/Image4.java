package Matematica.Avancado.EquacaoSegundoGrau.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Auxiliar;
import Matematica.Avancado.EquacaoSegundoGrau.ResolucaoEq2Grau;
import Matematica.Avancado.EquacaoSegundoGrau.Config.ConfigEq2Grau;
import Matematica.Avancado.EquacaoSegundoGrau.Config.DadosEq2Grau;
import Modelo.Matematica.Conta;


public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.b;

		textLatex = dadosEq2Grau.toString();
		pergunta = "Encontre \\( b\\), dado \\(f(x)=" + dadosEq2Grau.a + "x^2+bx " 
		+ Auxiliar.getNumber(dadosEq2Grau.c, "", false)+" \\)";
		
		resolucaoLatex=ResolucaoEq2Grau.resolucaoBX1(dadosEq2Grau.a,dadosEq2Grau.b,dadosEq2Grau.c,dadosEq2Grau.x1);
		
		ConfigEq2Grau config=new ConfigEq2Grau(dadosEq2Grau);
		config.indice=indice;
		config.pontoX1.label=""+dadosEq2Grau.x1;
		config.pontoX1.mostrar=true;
		
		BufferedImage image=config.criarImagem();
		
		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}

}
