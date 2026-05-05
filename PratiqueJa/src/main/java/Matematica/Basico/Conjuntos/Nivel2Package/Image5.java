package Matematica.Basico.Conjuntos.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.Conjuntos.ResolucaoConjuntos;
import Modelo.Matematica.Conta;

public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);

		DadosAB dados=new DadosAB();
		dados.aMbStr="";
//		dados.bMaStr="";

		pergunta="Se \\(|A \\cup B| = "+dados.aUb+"\\), qual o valor de \\(|A|\\)?";
		
		resultadoCorreto = ""+dados.a;
		
		resolucaoLatex=ResolucaoConjuntos.uniaoA(dados);
		
		textLatex = dados.toString();

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
