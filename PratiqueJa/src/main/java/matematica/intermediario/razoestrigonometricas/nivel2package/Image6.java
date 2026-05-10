package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosHipotenusa;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		Dados dados=new DadosHipotenusa();
		String angle=LetrasGregas.getLetra();
		dados.strAngleAltura=angle;
		dados.strBase="x";
		dados.strHipotenusa="";

		pergunta="Se a \\(tan~"+angle+"="+dados.tagAngleAltura.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.base;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.tagCAX(angle,dados.tagAngleAltura,dados.altura);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
