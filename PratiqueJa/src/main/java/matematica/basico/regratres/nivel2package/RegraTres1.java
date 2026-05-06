package matematica.basico.regratres.nivel2package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import matematica.basico.regratres.ProblemaRegraTres;
import modelo.matematica.Conta;


public class RegraTres1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RegraTres1(int indice)
	{
		super(indice);

		ProblemaRegraTres problema=TextoRegraTresInversa.getProblemaProporcao();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado().toString();
		
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
	        	new RegraTres1(1);
			}
	}

}
