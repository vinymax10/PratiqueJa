package Matematica.Intermediario.Pitagoras.Dados;

import java.util.Random;

public class ValoresPitagoras
{
	static ValorPitagoras  problemas[]= {
			new ValorPitagoras(3,4,5),
			new ValorPitagoras(5,12,13),
			new ValorPitagoras(6,8,10),
			new ValorPitagoras(7,24,25),
			new ValorPitagoras(8,15,17),
			new ValorPitagoras(9,12,15),
			new ValorPitagoras(9,40,41),
			new ValorPitagoras(12,16,20),
			new ValorPitagoras(12,35,37),
			new ValorPitagoras(15,20,25),
			new ValorPitagoras(15,36,39),
			new ValorPitagoras(16,30,34),
			new ValorPitagoras(18,24,30),
			new ValorPitagoras(20,21,29),
			new ValorPitagoras(21,28,35),
			new ValorPitagoras(24,32,40),
			new ValorPitagoras(27,36,45),
			new ValorPitagoras(28,45,53),
			new ValorPitagoras(30,40,50),
			new ValorPitagoras(33,44,55),
			new ValorPitagoras(36,48,60),
			new ValorPitagoras(39,52,65),
			new ValorPitagoras(48,55,73),
			new ValorPitagoras(60,80,100)
	};
	
	public static ValorPitagoras getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
