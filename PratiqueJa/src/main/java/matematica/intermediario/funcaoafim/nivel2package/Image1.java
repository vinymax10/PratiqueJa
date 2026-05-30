package matematica.intermediario.funcaoafim.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.funcaoafim.ResolucaoFuncaoAfim;
import matematica.intermediario.funcaoafim.config.ConfigRetaInteiro;
import modelo.matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);
		
		int a = -3 + rand.nextInt(7);
		int b = -3 + rand.nextInt(7);

		while (a == 0 && b == 0)
		{
			a = -3 + rand.nextInt(7);
			b = -3 + rand.nextInt(7);
		}

		resultadoCorreto = "" + a;

		textLatex = a + "" + b;
		pergunta = "Encontre o coeficiente angular da reta:";

		ConfigRetaInteiro config=new ConfigRetaInteiro(a,b);
		BufferedImage image=config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");
		
		int pontoAx=(int)config.pontoAx;
		int pontoAy=(int)config.pontoAy;
		int pontoBx=(int)config.pontoBx;
		int pontoBy=(int)config.pontoBy;
		
		resolucaoLatex="";
		resolucaoLatex+="\\text{Dado os pontos } A=("+pontoAx+","+pontoAy+") \\text{ e }";
		resolucaoLatex+="B=("+pontoBx+","+pontoBy+"), \\\\";
		resolucaoLatex+="\\text{temos que o coeficiente angular } a \\\\ \\text{ é calculado por: }\\\\";
		
		resolucaoLatex+=ResolucaoFuncaoAfim.resolucao(pontoAx,pontoAy,pontoBx,pontoBy);
		
		carregarBlob();
//		Graphics.salvar(image, true, "image.PNG");
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}

}
