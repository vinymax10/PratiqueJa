package util;

import java.awt.Color;

public class CorAux
{
	
	public static String convertHexPorc(String cor)
	{
	   if(!cor.startsWith("#") && !cor.startsWith("0x") && !cor.startsWith("0X"))
	       cor = "#" + cor;
	   Color color = Color.decode(cor);

        // pega os valores RGB (0-255)
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        // converte para porcentagem
        double rPercent = (r / 255.0);
        double gPercent = (g / 255.0);
        double bPercent = (b / 255.0);
        
		return rPercent+","+gPercent+","+bPercent;
	}
	
}
