package matematica.intermediario.pitagoras.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase;
import modelo.matematica.Conta;


public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);

		Dados dados=new DadosBase(20);
		dados.hipotenusa.str="a";

		pergunta="Qual o valor de \\(a\\)?";
		
		resultadoCorreto = ""+dados.hipotenusa.show();
		
		resolucaoLatex = ResolucaoPitagoras.resolucaoXBC(dados);

		textLatex = dados.toString();

		Config config = Config.build3(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}

}
