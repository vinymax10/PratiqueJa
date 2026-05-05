package Matematica.Intermediario.Pitagoras.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.Pitagoras.ResolucaoPitagoras;
import Matematica.Intermediario.Pitagoras.Config.Config;
import Matematica.Intermediario.Pitagoras.Dados.Dados;
import Matematica.Intermediario.Pitagoras.Dados.DadosBase;
import Modelo.Matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		Dados dados=new DadosBase(20);
		dados.altura.str="c";

		pergunta="Qual o valor de \\(c\\)?";
		
		resultadoCorreto = ""+dados.altura.show();
		
		resolucaoLatex = ResolucaoPitagoras.resolucaoABX(dados);

		textLatex = dados.toString();

		Config config = Config.build3(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}
	
	public static void main(String[] args)
	{
		new Image6(1);
	}

}
