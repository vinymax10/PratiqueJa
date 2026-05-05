package Matematica.Intermediario.Pitagoras.Nivel1Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.Pitagoras.ResolucaoPitagoras;
import Matematica.Intermediario.Pitagoras.Config.Config;
import Matematica.Intermediario.Pitagoras.Dados.Dados;
import Matematica.Intermediario.Pitagoras.Dados.DadosBase;
import Modelo.Matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		Dados dados=new DadosBase(20);
		dados.hipotenusa.str="a";

		pergunta="Qual o valor de \\(a\\)?";
		
		resultadoCorreto = ""+dados.hipotenusa.show();
		
		resolucaoLatex = ResolucaoPitagoras.resolucaoXBC(dados);
		
		textLatex = dados.toString();

		Config config = Config.build1(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

//		Graphics.salvar(image, true, "pitagoras.PNG");
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
