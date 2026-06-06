package matematica.intermediario.equacoes;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class EquacoesNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int numerosTermos = 3 + rand.nextInt(3);
		int numeros[] = new int[numerosTermos];
		String termos[] = new String[numerosTermos + 1];
		String operadores[] = new String[numerosTermos];

		for(int i = 0; i < numeros.length; i++)
		{
			numeros[i] = 1 + rand.nextInt(10);
			termos[i] = numeros[i] + "";
			if(i < numeros.length - 1)
				operadores[i] = AuxEquacoes.getOperador();
		}
		String icognita = "" + (char) (97 + rand.nextInt(122 - 97));
		int numero = 1 + rand.nextInt(10);
		if(numero > 1)
			termos[numerosTermos] = "" + numero + icognita;
		else
			termos[numerosTermos] = "" + icognita;

		operadores[numerosTermos - 1] = "=";

		AuxEquacoes.mudaOrdem(termos);
		AuxEquacoes.mudaOrdem(operadores);

		String expressao = "";
		for(int i = 0; i < termos.length; i++)
		{
			expressao += termos[i];
			if(i < termos.length - 1)
				expressao += operadores[i];
		}

		MyExpression myExpression = new MyExpression(expressao);
		String enunciado = myExpression.imprimir();
		String resolucao = myExpression.resolverLatex();

		addParagrafo("Encontre \\(" + icognita + "\\)");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + myExpression.getResultado().toString());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
