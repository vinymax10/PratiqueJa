package matematica.basico.multiplicacaonatural.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Verificacao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 11 + rand.nextInt(89);
		int b = 2 + rand.nextInt(8);
		int c = a * b;

		addParagrafo("Para verificar que \\(" + a + " \\times " + b + " = " + c
			+ "\\), qual das divisões abaixo deve ser verdadeira?");

		String correta = "\\(" + c + " \\div " + b + " = " + a + "\\)";
		List<String> distrais = new ArrayList<>();
		distrais.add("\\(" + a + " \\div " + b + "\\)");
		distrais.add("\\(" + b + " \\div " + c + "\\)");
		distrais.add("\\(" + c + " \\times " + b + "\\)");
		embaralharEAdicionarAlternativas(correta, distrais);

		String res = "A divisão é a operação inversa da multiplicação. Se \\("
			+ a + " \\times " + b + " = " + c + "\\), então: \\(\\\\\\)";
		res += "\\(" + c + " \\div " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
