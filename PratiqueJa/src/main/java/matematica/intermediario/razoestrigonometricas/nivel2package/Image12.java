package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosHipotenusa;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

public class Image12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image12(int index)
	{
		super(index);

		Dados dados=new DadosHipotenusa();
		String angle=LetrasGregas.getLetra();
		dados.strAngleBase=angle;
		dados.strAltura="x";
		dados.strHipotenusa="";

		pergunta="Se a \\(tan~"+angle+"="+dados.tagAngleBase.showDfrac()+"\\), qual o valor de x?";

		resultadoCorreto = ""+dados.altura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.tagCAX(angle,dados.tagAngleBase,dados.base);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image12(1);
	}
}
