package matematica.basico.mmcmdc.nivel1package;



import matematica.MMC;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;
import modelo.matematica.Conta;

public class MmcMdc4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MmcMdc4(int index)
	{
		super(index);

		int a = 2 + rand.nextInt(19);
		int b = 2 + rand.nextInt(19);
		int c = 2 + rand.nextInt(19);
		
		int fator= 2 + rand.nextInt(9);
		
		a*=fator;
		b*=fator;
		c*=fator;

		textLatex = "\\text{MDC}~ " + a + ", " + b + ", " + c + "=";

		resultadoCorreto = "" + MMC.mdc(a, b, c);
		
		resolucaoLatex=ResolucaoMmcMdc.mdc(a, b, c);

	}

	public MmcMdc4()
	{
	}

	public static void main(String[] args)
	{
		new MmcMdc4(1);
	}
}
