package Matematica.Basico.Conjuntos.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Basico.Conjuntos.ResolucaoConjuntos;
import Modelo.Matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		DadosAB dados=new DadosAB();
		dados.aIbStr="";
		dados.bMaStr="";

		pergunta="Se \\(|A|="+dados.a+"\\) qual o valor de \\(|A \\cap B|\\)?";
		
		resultadoCorreto = ""+dados.aIb;
		
		resolucaoLatex=ResolucaoConjuntos.menosAIntersecB(dados);
		
		textLatex = dados.toString();

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

//		Graphics.salvar(image, true, "image.PNG");
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
