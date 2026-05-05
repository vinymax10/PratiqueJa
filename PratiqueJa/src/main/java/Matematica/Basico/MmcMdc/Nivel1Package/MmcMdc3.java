package Matematica.Basico.MmcMdc.Nivel1Package;



import Matematica.MMC;
import Matematica.Basico.MmcMdc.ResolucaoMmcMdc;
import Modelo.Matematica.Conta;

public class MmcMdc3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MmcMdc3(int index)
	{
		super(index);

		int a = 2 + rand.nextInt(29);
		int b = 2 + rand.nextInt(29);
		int c = 2 + rand.nextInt(29);
		
		while(a==b)
			b = 2 + rand.nextInt(29);

		while(b==c)
			c = 2 + rand.nextInt(29);
		
		textLatex = "\\text{MMC}~ " + a + ", " + b + ", " + c + "=";

		resultadoCorreto = "" + MMC.mmc(a, b, c);
		
		resolucaoLatex=ResolucaoMmcMdc.mmc(a, b, c);
	}

	public MmcMdc3()
	{
	}

	public static void main(String[] args)
	{
		new MmcMdc3(1);
	}
}
