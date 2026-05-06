package matematica.intermediario.pitagoras.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase;
import modelo.matematica.Conta;


public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		Dados dados=new DadosBase(20);
		dados.altura.str="c";

		pergunta="Qual o valor de \\(c\\)?";
		
		resultadoCorreto = ""+dados.altura.show();
		
		resolucaoLatex = ResolucaoPitagoras.resolucaoABX(dados);

		textLatex = dados.toString();

		Config config = Config.build1(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}
	
	public static void main(String[] args)
	{
		new Image3(1);
	}

}
