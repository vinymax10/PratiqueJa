package Matematica.Avancado.EquacaoSegundoGrau.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Auxiliar;
import Matematica.Avancado.EquacaoSegundoGrau.ResolucaoEq2Grau;
import Matematica.Avancado.EquacaoSegundoGrau.Config.ConfigEq2Grau;
import Matematica.Avancado.EquacaoSegundoGrau.Config.DadosEq2Grau;
import Modelo.Matematica.Conta;


public class Image11 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image11(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.c;

		textLatex = dadosEq2Grau.toString();
		pergunta = "Encontre \\( c\\), dado \\( f(x)=" + dadosEq2Grau.a + "x^2" 
		+Auxiliar.getNumber(dadosEq2Grau.b, "x", false) + "+c \\)";

		resolucaoLatex=ResolucaoEq2Grau.resolucaoCYv(dadosEq2Grau.a,dadosEq2Grau.b,
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
		new Image11(1);
	}

}
