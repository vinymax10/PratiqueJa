package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.conjuntos.ResolucaoConjuntos;
import modelo.matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		DadosAB dados=new DadosAB();
//		dados.aIbStr="";
		dados.bMaStr="";

		pergunta="Qual o valor de \\(|A|\\)?";
		
		resultadoCorreto = ""+dados.a;
		
		resolucaoLatex=ResolucaoConjuntos.menosA(dados);
		
		textLatex = dados.toString();

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
