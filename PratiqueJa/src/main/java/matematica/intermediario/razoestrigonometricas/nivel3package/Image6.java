package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import modelo.matematica.Conta;

public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="30°";
		dados.strAngleAltura=angle;

		int y=1+rand.nextInt(10);
		dados.base=y*y*3;

		dados.strAltura="";
		dados.strBase="\\sqrt{"+dados.base+"}";
		dados.strHipotenusa="x";
		
		pergunta="Qual o valor de x?";
		
		resultadoCorreto = ""+y*2;
		resolucaoLatex = ResolucaoRazoesTrigonometricas.cos30HX(dados.base);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
//		Config config = new Config1(dados);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
