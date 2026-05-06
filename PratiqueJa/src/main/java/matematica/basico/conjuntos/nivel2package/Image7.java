package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.basico.conjuntos.ResolucaoConjuntos;
import modelo.matematica.Conta;

public class Image7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image7(int index)
	{
		super(index);

		DadosAB dados=new DadosAB();
		dados.aIbStr="";
//		dados.bMaStr="";

		pergunta="Se \\(|A \\cup B| = "+dados.aUb+"\\), qual o valor de \\(|A \\cap B|\\)?";
		
		resultadoCorreto = ""+dados.aIb;
		
		resolucaoLatex=ResolucaoConjuntos.uniaoAIntersecB2(dados);
		
		textLatex = dados.toString();

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image7(1);
	}
}
