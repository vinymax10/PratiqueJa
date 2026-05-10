package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.conjuntos.ResolucaoConjuntos;
import modelo.matematica.Conta;

public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		DadosAB dados=new DadosAB();
		dados.aMbStr="";
		dados.bMaStr="";

		pergunta="Se \\(|A|="+dados.a+"\\) qual o valor de \\(|A - B|\\)?";
		
		resultadoCorreto = ""+dados.aMb;
		
		resolucaoLatex=ResolucaoConjuntos.menosAMenosB(dados);
		
		textLatex = dados.toString();

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
