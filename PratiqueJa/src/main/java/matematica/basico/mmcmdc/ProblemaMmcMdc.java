package matematica.basico.mmcmdc;

import java.util.Random;

import matematica.MMC;

public class ProblemaMmcMdc
{
	public String texto;
	public int a,b,c;
	public TipoProblema sizeProblema;
	
	public ProblemaMmcMdc(String texto, TipoProblema sizeProblema)
	{
		super();
		this.texto = texto;
		this.sizeProblema = sizeProblema;
	}

	public void gerarValores()
	{
		Random rand=new Random();
		int x=2 + rand.nextInt(9);
		a = x*(2 + rand.nextInt(9));
		b = x*(2 + rand.nextInt(9));
		
		while(a==b)
			b = x*(2 + rand.nextInt(9));
		
		switch(sizeProblema)
		{
			case MmcTres,MdcTres: 
				a = x*(2 + rand.nextInt(19));
				b = x*(2 + rand.nextInt(19));
				c = x*(2 + rand.nextInt(19));
				
				while(a==b)
					b = x*(2 + rand.nextInt(9));
				
				while(b==c||a==c)
					c = x*(2 + rand.nextInt(19));
				break;
			default:
				break;
		}
	}
	
	public ProblemaMmcMdc clone()
	{
		return new ProblemaMmcMdc(texto,sizeProblema);
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		pergunta=pergunta.replace("$a", a+"");
		pergunta=pergunta.replace("$b", b+"");
		pergunta=pergunta.replace("$c", c+"");

		return pergunta;
	}
	
	public String[] resolucao()
	{
		switch(sizeProblema)
		{
			case MmcDuas:
				return new String[]
				{
					"\\text{MMC}~ " + a + ", " + b,
					ResolucaoMmcMdc.mmc(a, b)
				};

			case MmcTres:
				return new String[]
				{
					"\\text{MMC}~ " + a + ", " + b + ", " + c,
					ResolucaoMmcMdc.mmc(a, b, c)
				};

			case MdcDuas:
				return new String[]
				{
					"\\text{MDC}~ " + a + ", " + b,
					ResolucaoMmcMdc.mdc(a, b)
				};

			case MdcTres:
				return new String[]
				{
					"\\text{MDC}~ " + a + ", " + b + ", " + c,
					ResolucaoMmcMdc.mdc(a, b, c)
				};
		}

		return new String[0];
	}
	
	public long resultado()
	{
		switch(sizeProblema)
		{
			case MmcDuas:
				return MMC.mmc(a, b);
				
			case MmcTres:
				return MMC.mmc(a, b, c);
				
			case MdcDuas:
				return MMC.mdc(a, b);
				
			case MdcTres:
				return MMC.mdc(a, b, c);
		}
		return 0;
	}
}
