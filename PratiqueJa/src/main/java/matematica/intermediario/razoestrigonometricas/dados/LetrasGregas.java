package matematica.intermediario.razoestrigonometricas.dados;

import java.util.Random;

public class LetrasGregas
{
	private static String letras[]= {
	"\\alpha",
	"\\beta",
	"\\gamma",
	"\\delta",
	"\\epsilon",
	"\\eta",
	"\\theta",
	"\\kappa",
	"\\lambda",
	"\\nu",
	"\\sigma",
	"\\tau",
	"\\upsilon",
	"\\phi",
	"\\chi",
	"\\psi",
	"\\omega",
	"\\iota",
	};
	
	public static String getLetra()
	{
		Random rand=new Random();
		return letras[rand.nextInt(letras.length)];
	}
	
	public static String getLetra(String angle)
	{
		Random rand=new Random();
		String novoAngle;
		do
			novoAngle = letras[rand.nextInt(letras.length)];
		while(novoAngle.equals(angle));
		
		return novoAngle;
	}
}
