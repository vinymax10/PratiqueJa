package Matematica.Basico.MmcMdc.Nivel1Package;



import Matematica.MMC;
import Matematica.Basico.MmcMdc.ResolucaoMmcMdc;
import Modelo.Matematica.Conta;

public class MmcMdc2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MmcMdc2(int index)
	{
		super(index);

		int a = 2 + rand.nextInt(19);
		int b = 2 + rand.nextInt(19);
		
		while(a==b)
			b = 2 + rand.nextInt(19);
		
		int fator= 2 + rand.nextInt(9);
		
		a*=fator;
		b*=fator;
		
		textLatex = "\\text{MDC}~ " + a + ", " + b + "=";

		resultadoCorreto = "" + MMC.mdc(a, b);

		resolucaoLatex=ResolucaoMmcMdc.mdc(a, b);

	}

	public MmcMdc2()
	{
	}

	public static void main(String[] args)
	{
		new MmcMdc2(1);
	}
}
