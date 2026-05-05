package Matematica.Intermediario.Pitagoras.Nivel2Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Intermediario.Pitagoras.ResolucaoPitagoras;
import Matematica.Intermediario.Pitagoras.Config.Config;
import Matematica.Intermediario.Pitagoras.Dados.Dados;
import Matematica.Intermediario.Pitagoras.Dados.DadosAltura2;
import Modelo.Matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		Dados dados=new DadosAltura2(20);
		dados.base.str="b";

		pergunta="Qual o valor de \\(b\\)?";
		
		resultadoCorreto = ""+dados.base.show();
		
		resolucaoLatex = ResolucaoPitagoras.resolucaoAXC(dados);

		textLatex = dados.toString();

		Config config = Config.build2(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}

}
