package Matematica.Basico.Conjuntos.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.Conjuntos.ResolucaoConjuntos;
import Modelo.Matematica.Conta;

public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);

		DadosAB dados=new DadosAB();
//		dados.aIbStr="";
//		dados.bMaStr="";

		pergunta="Qual o valor de \\(|A \\cup B|\\)?";
		
		resultadoCorreto = ""+dados.aUb;
		
		resolucaoLatex=ResolucaoConjuntos.uniaoAUniaoB2(dados);
		
		textLatex = dados.toString();

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
