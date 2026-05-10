package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Racional;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import modelo.matematica.Conta;

public class Image9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image9(int index)
	{
		super(index);
		
		Dados dados=new DadosBase();
		String angle="30°";
		dados.strAngleAltura=angle;

		int y=1+rand.nextInt(10);
		dados.base=y*y*3;

		dados.strAltura="x";
		dados.strBase="\\sqrt{"+dados.base+"}";
		dados.strHipotenusa="";
		
		pergunta="Qual o valor de x?";
		Racional resultado=new Racional(3*y,3);
		resultado.fatoracao(2);
		
		resultadoCorreto = resultado.toString();
		resolucaoLatex = ResolucaoRazoesTrigonometricas.tag30COX(dados.base);

		textLatex = dados.toString();

		Config config = Config.buildConfig(dados);
//		Config config = new Config1(dados);

		BufferedImage image = config.criarImagem(index);

		baos = Graphics.salvar(image, false, "");
		carregarBlob();

	}

	public static void main(String[] args)
	{
		new Image9(1);
	}
}
