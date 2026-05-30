package matematica.intermediario.funcaoafim.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Racional;
import matematica.intermediario.funcaoafim.ResolucaoFuncaoAfim;
import matematica.intermediario.funcaoafim.config.ConfigRetaReal;
import modelo.matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int pontoAx = -(2 + rand.nextInt(7));
		int pontoAy = 2 + rand.nextInt(7);
		if (rand.nextBoolean())
			pontoAy *= -1;

		int pontoBx = 2 + rand.nextInt(7);
		int pontoBy = 2 + rand.nextInt(7);
		if (rand.nextBoolean())
			pontoBy *= -1;

		double a = (double) (pontoBy - pontoAy) / (pontoBx - pontoAx);
		double b = pontoAy - (a * pontoAx);

		Racional aRacional = new Racional((pontoBy - pontoAy)).div(new Racional(pontoBx - pontoAx));
		aRacional.fatoracao(2);
		resultadoCorreto = "" + aRacional;

		textLatex = a + "" + b;
		pergunta = "Encontre o coeficiente angular da reta:";

		ConfigRetaReal config=new ConfigRetaReal(a,b,pontoAx,pontoAy,pontoBx,pontoBy);
		BufferedImage image=config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

        resolucaoLatex="";
		resolucaoLatex+="\\text{Dados os pontos } A=("+pontoAx+","+pontoAy+") \\text{ e }";
		resolucaoLatex+="B=("+pontoBx+","+pontoBy+"), \\\\";
		resolucaoLatex+="\\text{temos que o coeficiente angular } a \\\\ \\text{ é calculado por: }\\\\";
		
		resolucaoLatex+=ResolucaoFuncaoAfim.resolucao(pontoAx,pontoAy,pontoBx,pontoBy);
		
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}

}
