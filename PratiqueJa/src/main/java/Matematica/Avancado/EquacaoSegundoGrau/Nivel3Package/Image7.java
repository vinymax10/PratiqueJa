package Matematica.Avancado.EquacaoSegundoGrau.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Auxiliar;
import Matematica.Avancado.EquacaoSegundoGrau.ResolucaoEq2Grau;
import Matematica.Avancado.EquacaoSegundoGrau.Config.ConfigEq2Grau;
import Matematica.Avancado.EquacaoSegundoGrau.Config.DadosEq2Grau;
import Modelo.Matematica.Conta;


public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int indice)
	{
		super(indice);

		DadosEq2Grau dadosEq2Grau=new DadosEq2Grau();

		resultadoCorreto = "" + dadosEq2Grau.yVerticeRacional;

		textLatex = dadosEq2Grau.toString();
		pergunta = "Encontre \\( y_v\\), dado \\(f(x)=" + dadosEq2Grau.a + "x^2" + 
		Auxiliar.getNumber(dadosEq2Grau.b, "", false) + "x" 
		+  Auxiliar.getNumber(dadosEq2Grau.c, "", false)+" \\)";
		textLatex = dadosEq2Grau.toString();
		
		resolucaoLatex=ResolucaoEq2Grau.resolucaoYVertice(dadosEq2Grau.a,dadosEq2Grau.b,dadosEq2Grau.c);
		
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
		new Image7(1);
	}

}
