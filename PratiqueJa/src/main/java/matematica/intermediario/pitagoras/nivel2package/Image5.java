package matematica.intermediario.pitagoras.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosHipotenusa;
import modelo.matematica.Conta;

public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);

		Dados dados=new DadosHipotenusa(20);
		dados.base.str="b";

		pergunta="Qual o valor de \\(b\\)?";
		
		resultadoCorreto = ""+dados.base.show();
		
		resolucaoLatex = ResolucaoPitagoras.resolucaoAXC(dados);

		textLatex = dados.toString();

		Config config = Config.build3(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}
	
	public static void main(String[] args)
	{
		new Image5(1);
	}
}
