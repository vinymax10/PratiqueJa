package matematica.basico.numerosdecimais.nivel3package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,b ÷ c,d — divisão de decimais (multiplicar ambos por 10)
public class Decimal1 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		// quociente inteiro n; divisor em décimos bT ∈ [11,29]
		int n = 2 + rand.nextInt(7);
		int bT = 11 + rand.nextInt(19);
		int aT = n * bT;

		String a = fmtT(aT);
		String b = fmtT(bT);

		addParagrafo("Calcule a divisão dos números decimais:");
		addParagrafo("\\(" + a + " \\div " + b + " = \\,?\\)");
		gerarAlternativasInteiras(n);

		addResolucao("Multiplicamos dividendo e divisor por 10 para eliminar a vírgula do divisor:");
		addResolucao("\\(" + aT + " \\div " + bT + " = \\mathbf{" + n + "}\\)");
	}
}
