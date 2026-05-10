package matematica.intermediario.pitagoras.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase;
import modelo.matematica.Conta;


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
