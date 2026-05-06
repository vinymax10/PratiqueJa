package matematica.intermediario.pitagoras.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase;
import modelo.matematica.Conta;

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
