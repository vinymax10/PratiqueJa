package matematica.intermediario.razoestrigonometricas.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosAltura;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		Dados dados=new DadosAltura();
		String angle=LetrasGregas.getLetra();
		dados.strAngleAltura=angle;
		dados.strAltura="";

		pergunta="Qual o \\(cos~"+angle+"\\)?";

		resultadoCorreto = ""+dados.cosAngleAltura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cos(angle,dados.base,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
