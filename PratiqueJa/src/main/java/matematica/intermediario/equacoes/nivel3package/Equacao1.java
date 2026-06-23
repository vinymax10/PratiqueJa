package matematica.intermediario.equacoes.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.expressao.MyExpression;
import matematica.intermediario.equacoes.AuxEquacoes;

public class Equacao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int numerosTermos = 5 + rand.nextInt(3);
		Racional numeros[] = new Racional[numerosTermos];
		String termos[] = new String[numerosTermos + 1];
		String operadores[] = new String[numerosTermos];

		for(int i = 0; i < numeros.length; i++)
		{
			numeros[i] = new Racional(1 + rand.nextInt(10));
			if(rand.nextBoolean())
				numeros[i].denominador = 1 + rand.nextInt(10);

			termos[i] = numeros[i].toString() + "";
			if(i < numeros.length - 1)
				operadores[i] = AuxEquacoes.getOperador();
		}
		String icognita = "" + (char) (97 + rand.nextInt(25));
		int numero = 1 + rand.nextInt(10);
		String termoIcognita = numero > 1 ? "" + numero + icognita : "" + icognita;

		termos[numerosTermos] = termoIcognita;
		operadores[numerosTermos - 1] = "=";

		AuxEquacoes.mudaOrdem(termos);
		AuxEquacoes.mudaOrdem(operadores);
		int indexIgual = AuxEquacoes.indexIgual(operadores, "=");
		int indexIncognita = AuxEquacoes.indexIgual(termos, termoIcognita);

		int posParenteses = rand.nextInt(numerosTermos - 1);
		while(posParenteses == indexIgual
			|| posParenteses == indexIncognita
			|| posParenteses == (indexIncognita - 1))
			posParenteses = rand.nextInt(numerosTermos - 1);

		String expressao = "";
		for(int i = 0; i < termos.length; i++)
		{
			if(i == posParenteses)
				expressao += "(";

			expressao += termos[i];
			if(i == (posParenteses + 1))
				expressao += ")";

			if(i < termos.length - 1)
				expressao += operadores[i];
		}

		MyExpression myExpression = new MyExpression(expressao);
		String enunciado = myExpression.imprimir().replace("(", "\\left(").replace(")", "\\right)");
		String resolucao = myExpression.resolverLatex().replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Encontre \\(" + icognita + "\\)");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + myExpression.getResultado().toString());
		addResolucao("\\(" + resolucao + "\\)");
	}
}
