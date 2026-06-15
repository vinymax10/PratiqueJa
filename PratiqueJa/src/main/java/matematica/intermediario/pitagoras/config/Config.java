package matematica.intermediario.pitagoras.config;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import matematica.intermediario.pitagoras.dados.Dados;

public abstract class Config
{
	protected static final int IMG_W = matematica.ConfigImagem.IMG_W;
	protected static final int IMG_H = matematica.ConfigImagem.IMG_H;

	Dados dados;
	Point a, b, c, d;
	
	public Config(Dados dados)
	{
		this.dados=dados;		
	}
	
	public BufferedImage criarImagem(){
		return null;
	}
	
	public static Config build1(Dados dados)
	{
		Random rand=new Random();
		switch(rand.nextInt(4))
		{
			case 0: return new Config1(dados);
			case 1: return new Config2(dados);
			case 2: return new Config3(dados);
			case 3: return new Config4(dados);
			
		}
		return new Config3(dados);
	}
	
	public static Config build2(Dados dados)
	{
		Random rand=new Random();
		switch(rand.nextInt(4))
		{
			case 0: return new Config5(dados);
			case 1: return new Config6(dados);
			case 2: return new Config7(dados);
			case 3: return new Config8(dados);
			
		}
		return new Config5(dados);
	}
	
	public static Config build3(Dados dados)
	{
		Random rand=new Random();
		switch(rand.nextInt(4))
		{
			case 0: return new Config9(dados);
			case 1: return new Config10(dados);
			case 2: return new Config11(dados);
			case 3: return new Config12(dados);
		}
		return new Config9(dados);
	}
}
