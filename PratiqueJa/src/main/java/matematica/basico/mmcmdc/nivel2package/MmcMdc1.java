package matematica.basico.mmcmdc.nivel2package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import matematica.basico.mmcmdc.ProblemaMmcMdc;
import modelo.matematica.Conta;

public class MmcMdc1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MmcMdc1(int index)
	{
		super(index);

		ProblemaMmcMdc problema = TextoMDC.getProblemaMMC();
		problema.gerarValores();
		pergunta = problema.getPergunta();

		resultadoCorreto = "" + problema.resultado();

		resolucaoLatex = problema.resolucao();
	}

	public static void main(String[] args)
	{
		try
		{
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
		}
		catch(UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < 50; i++)
		{
			new MmcMdc1(1);
		}
	}
}
