package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

public class Image9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image9(int index)
	{
		super(index);

		Dados dados=new DadosBase();
		String angle=LetrasGregas.getLetra();
		dados.strAngleBase=angle;
		dados.strAltura="x";
		dados.strBase="";

		pergunta="Se o \\(cos~"+angle+"="+dados.cosAngleBase.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.altura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cosCAX(angle,dados.cosAngleBase,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image9(1);
	}
}
