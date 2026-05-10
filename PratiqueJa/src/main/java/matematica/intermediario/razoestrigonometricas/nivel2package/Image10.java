package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

public class Image10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image10(int index)
	{
		super(index);

		Dados dados=new DadosBase();
		String angle=LetrasGregas.getLetra();
		dados.strAngleBase=angle;
		dados.strBase="";
		dados.strHipotenusa="x";

		pergunta="Se o \\(cos~"+angle+"="+dados.cosAngleBase.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.hipotenusa;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cosHX(angle,dados.cosAngleBase,dados.altura);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image10(1);
	}
}
