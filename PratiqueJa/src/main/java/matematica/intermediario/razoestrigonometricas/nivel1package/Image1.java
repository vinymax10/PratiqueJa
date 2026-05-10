package matematica.intermediario.razoestrigonometricas.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;
import modelo.matematica.Conta;

public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle=LetrasGregas.getLetra();
		dados.strAngleAltura=angle;
		dados.strBase="";

		pergunta="Qual o \\(sen~"+angle+"\\)?";

		resultadoCorreto = ""+dados.senAngleAltura;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.sen(angle,dados.altura,dados.hipotenusa);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
//		Graphics.salvar(image, true, "RazoesTrigonometricas.PNG");
	}

	public static void main(String[] args)
	{
		new Image1(1);
	}
}
