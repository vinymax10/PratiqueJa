package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import modelo.matematica.Conta;

public class Image8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image8(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="60°";
		dados.strAngleBase=angle;

		dados.altura=rand.nextInt(100);
		dados.hipotenusa=dados.altura*2;

		dados.strBase="";
		dados.strAltura=dados.altura+"";
		dados.strHipotenusa="x";
		
		pergunta="Qual o valor de x?";

		resultadoCorreto = ""+dados.hipotenusa;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cos60HX(dados.altura);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
//		Config config = new Config1(dados);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image8(1);
	}
}
