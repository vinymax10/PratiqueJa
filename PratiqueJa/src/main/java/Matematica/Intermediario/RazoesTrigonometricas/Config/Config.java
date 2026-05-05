package Matematica.Intermediario.RazoesTrigonometricas.Config;

import java.awt.image.BufferedImage;
import java.util.Random;

import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;

public abstract class Config
{
	String base,altura,hipotenusa;
	String angleBase,angleAltura;
	
	public Config(Dados dados)
	{
		this.base = dados.strBase;
		this.altura = dados.strAltura;
		this.hipotenusa = dados.strHipotenusa;
		this.angleBase = dados.strAngleBase;
		this.angleAltura = dados.strAngleAltura;
	}
	
	public BufferedImage criarImagem(int index) {
		return null;
	}
	
	public static Config buildConfig(Dados dados)
	{
		Random rand=new Random();
		switch(rand.nextInt(4))
		{
			case 0: return new Config1(dados);
			case 1: return new Config2(dados);
			case 2: return new Config3(dados);
			case 3: return new Config4(dados);
		}
		return null;
	}
}
