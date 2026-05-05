package Matematica.Intermediario.RazoesTrigonometricas.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Racional;
import Matematica.Intermediario.RazoesTrigonometricas.ResolucaoRazoesTrigonometricas;
import Matematica.Intermediario.RazoesTrigonometricas.Config.Config;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.DadosBase;
import Modelo.Matematica.Conta;

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
