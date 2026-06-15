package matematica.intermediario.equacaosegundograu.config;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static matematica.ConfigImagem.IMG_H;
import static matematica.ConfigImagem.IMG_W;

public class ConfigEq2Grau
{
	public int indice;
	public Ponto pontoX1=new Ponto();
	public Ponto pontoX2=new Ponto();
	public Ponto pontoC=new Ponto();
	public Ponto pontoXv=new Ponto();
	public Ponto pontoYv=new Ponto();

	public DadosEq2Grau dadosEq2Grau;
	
	public ConfigEq2Grau(DadosEq2Grau dadosEq2Grau)
	{
		this.dadosEq2Grau=dadosEq2Grau;
	}
	
	public BufferedImage criarImagem()
	{
		BufferedImage image = new BufferedImage(IMG_W, IMG_H, BufferedImage.TYPE_INT_ARGB);
		
		Grafico grafico=new Grafico(dadosEq2Grau);
		
		Graphics2D g2=grafico.carregamentoInicial(image);

		grafico.inserirEixoCartesiano(g2);

		grafico.inserirCurva(g2);

		if(pontoYv.mostrar)
			grafico.inserirPontoYVertice(g2,pontoYv.label);
		
		if(pontoXv.mostrar)
			grafico.inserirPontoXVertice(g2,pontoXv.label);
		
		if(pontoC.mostrar)
			grafico.inserirPontoC(g2,pontoC.label);

		if(pontoX1.mostrar)
			grafico.inserirPontoX1(g2,pontoX1.label);

		return image;
	}
}
