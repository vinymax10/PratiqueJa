package matematica.basico.multiplicacaodivisaointeiro.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class TresFatores extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean quatroFatores = rand.nextBoolean();

		int a = 2 + rand.nextInt(19); if (rand.nextBoolean()) a = -a;
		int b = 2 + rand.nextInt(19); if (rand.nextBoolean()) b = -b;
		int c = 2 + rand.nextInt(19); if (rand.nextBoolean()) c = -c;

		String aFmt = a < 0 ? "(" + a + ")" : "" + a;
		String bFmt = b < 0 ? "(" + b + ")" : "" + b;
		String cFmt = c < 0 ? "(" + c + ")" : "" + c;

		int negativos;
		int resultado;
		String enunciado;
		String passoMeio;

		if (quatroFatores)
		{
			int d = 2 + rand.nextInt(19); if (rand.nextBoolean()) d = -d;
			String dFmt = d < 0 ? "(" + d + ")" : "" + d;
			resultado = a * b * c * d;
			negativos = (a < 0 ? 1 : 0) + (b < 0 ? 1 : 0) + (c < 0 ? 1 : 0) + (d < 0 ? 1 : 0);
			enunciado = "Calcule \\(" + aFmt + " \\times " + bFmt + " \\times " + cFmt + " \\times " + dFmt + "\\).";
			int ab = a * b;
			int abc = ab * c;
			String abFmt  = ab  < 0 ? "(" + ab  + ")" : "" + ab;
			String abcFmt = abc < 0 ? "(" + abc + ")" : "" + abc;
			passoMeio  = "\\(" + Math.abs(a) + " \\times " + Math.abs(b) + " = " + Math.abs(ab) + "\\); ";
			passoMeio += "\\(" + abFmt + " \\times " + cFmt + " = " + abcFmt + "\\). \\(\\\\\\)";
			passoMeio += negativos + " fatores negativos ";
			passoMeio += (negativos % 2 == 0) ? "(par) → produto positivo. \\(\\\\\\)" : "(ímpar) → produto negativo. \\(\\\\\\)";
			passoMeio += ResolucaoMDInteiro.multiplicacao(abc, d);
		}
		else
		{
			resultado = a * b * c;
			negativos = (a < 0 ? 1 : 0) + (b < 0 ? 1 : 0) + (c < 0 ? 1 : 0);
			enunciado = "Calcule \\(" + aFmt + " \\times " + bFmt + " \\times " + cFmt + "\\).";
			int ab = a * b;
			String abFmt = ab < 0 ? "(" + ab + ")" : "" + ab;
			passoMeio  = "\\(" + Math.abs(a) + " \\times " + Math.abs(b) + " = " + Math.abs(ab) + "\\). \\(\\\\\\)";
			passoMeio += negativos + " fatores negativos ";
			passoMeio += (negativos % 2 == 0) ? "(par) → produto positivo. \\(\\\\\\)" : "(ímpar) → produto negativo. \\(\\\\\\)";
			passoMeio += ResolucaoMDInteiro.multiplicacao(ab, c);
		}

		addParagrafo(enunciado);
		gerarAlternativasInteirasComNegativos(resultado);
		setResolucao(passoMeio);
	}
}
