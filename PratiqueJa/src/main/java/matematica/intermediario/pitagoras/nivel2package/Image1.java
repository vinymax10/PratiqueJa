package matematica.intermediario.pitagoras.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase2;
import modelo.matematica.Conta;


public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		Dados dados=new DadosBase2(20);
		dados.hipotenusa.str="a";

		pergunta="Qual o valor de \\(a\\)?";
		
		resultadoCorreto = ""+dados.hipotenusa.show();
		
		resolucaoLatex = ResolucaoPitagoras.resolucaoXBC(dados);

		textLatex = dados.toString();

		Config config = Config.build2(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}
	
	public static void main(String[] args)
	{
		new Image1(1);
	}

}
