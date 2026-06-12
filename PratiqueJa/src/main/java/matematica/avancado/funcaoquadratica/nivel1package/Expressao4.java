package matematica.avancado.funcaoquadratica.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		if(rand.nextBoolean()) a *= -1;
		int b = rand.nextInt(9) - 4;
		int c = rand.nextInt(9) - 4;
		while(b == 0) b = rand.nextInt(9) - 4;
		while(c == 0) c = rand.nextInt(9) - 4;

		String funcao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false)
			+ Auxiliar.getNumber(c, "", false);

		addParagrafo("A concavidade da parábola \\(f(x) = " + funcao + "\\) é:");

		String cima    = "Para cima \\((a > 0)\\)";
		String baixo   = "Para baixo \\((a < 0)\\)";
		String cimaB   = "Para cima \\((b > 0)\\)";
		String baixoB  = "Para baixo \\((b < 0)\\)";
		String cimaC   = "Para cima \\((c > 0)\\)";
		String baixoC  = "Para baixo \\((c < 0)\\)";

		String correta;
		List<String> distratores = new ArrayList<>();

		if(a > 0)
		{
			correta = cima;
			distratores.add(baixo);
			distratores.add(b > 0 ? cimaB : baixoB);
			distratores.add(c > 0 ? cimaC : baixoC);
		}
		else
		{
			correta = baixo;
			distratores.add(cima);
			distratores.add(b > 0 ? cimaB : baixoB);
			distratores.add(c > 0 ? cimaC : baixoC);
		}

		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "A concavidade é determinada pelo coeficiente \\(a\\). \\(\\\\\\)";
		res += "Identificamos: \\(a = " + a + ", \\quad b = " + b + ", \\quad c = " + c + "\\) \\(\\\\\\)";
		if(a > 0)
			res += "Como \\(a = " + a + " > 0\\), a parábola abre para \\(\\mathbf{cima}\\).";
		else
			res += "Como \\(a = " + a + " < 0\\), a parábola abre para \\(\\mathbf{baixo}\\).";

		setResolucao(res);
	}
}
