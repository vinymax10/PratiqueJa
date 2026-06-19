package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ProblemaIntDiv;
import matematica.basico.multiplicacaodivisaointeiro.ProblemaIntMult;
import matematica.basico.multiplicacaodivisaointeiro.TextoIntDiv;
import matematica.basico.multiplicacaodivisaointeiro.TextoIntMult;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		if(rand.nextInt(3) < 2) // multiplicação (2/3)
		{
			ProblemaIntMult p = TextoIntMult.getProblema();
			p.gerarValores(1);
			addParagrafo(p.getPergunta());
			gerarAlternativasInteirasComNegativos(p.resultado());
			setResolucao(p.resolucao());
		}
		else // divisão (1/3)
		{
			ProblemaIntDiv p = TextoIntDiv.getProblema();
			p.gerarValores(1);
			addParagrafo(p.getPergunta());
			gerarAlternativasInteirasComNegativos(p.resultado());
			setResolucao(p.resolucao());
		}
	}
}
