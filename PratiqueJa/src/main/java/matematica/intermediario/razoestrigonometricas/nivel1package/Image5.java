package matematica.intermediario.razoestrigonometricas.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

public class Image5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image5(int index)
	{
		super(index);

		Dados dados=new DadosBase();
		String angle=LetrasGregas.getLetra();
		dados.strAngleBase=angle;
		dados.strBase="";

		pergunta="Qual o \\(cos~"+angle+"\\)?";

		resultadoCorreto = ""+dados.cosAngleBase;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cos(angle,dados.altura,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image5(1);
	}
}
