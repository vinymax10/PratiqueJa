package matematica.intermediario.pitagoras.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Image15 extends GeradorExercicio
{
	// {a, b, c, tipo}  tipo: 0=Retângulo, 1=Obtusângulo, 2=Acutângulo
	// a é sempre o maior lado
	private static final int[][] CASOS = {
		{5,  3, 4, 0},  // 25 = 9+16
		{10, 6, 8, 0},  // 100 = 36+64
		{13, 5, 12, 0}, // 169 = 25+144
		{7,  3, 4, 1},  // 49 > 9+16=25
		{8,  4, 5, 1},  // 64 > 16+25=41
		{10, 5, 7, 1},  // 100 > 25+49=74
		{5,  4, 4, 2},  // 25 < 16+16=32
		{6,  5, 5, 2},  // 36 < 25+25=50
		{7,  5, 6, 2},  // 49 < 25+36=61
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int a = caso[0], b = caso[1], c = caso[2], tipo = caso[3];
		int a2 = a * a, b2 = b * b, c2 = c * c;

		addParagrafo("Um triângulo tem lados \\(" + a + "\\), \\(" + b + "\\) e \\(" + c + "\\). "
			+ "Como ele pode ser classificado em relação a seus ângulos?");

		String correto;
		List<String> errados = new ArrayList<>();
		if(tipo == 0)
		{
			correto = "Retângulo";
			errados.add("Obtusângulo");
			errados.add("Acutângulo");
		}
		else if(tipo == 1)
		{
			correto = "Obtusângulo";
			errados.add("Retângulo");
			errados.add("Acutângulo");
		}
		else
		{
			correto = "Acutângulo";
			errados.add("Retângulo");
			errados.add("Obtusângulo");
		}
		embaralharEAdicionarAlternativas(correto, errados);

		String rel = (tipo == 0) ? "=" : (tipo == 1) ? ">" : "<";
		String concl = (tipo == 0)
			? "\\(" + a + "^2 = " + b + "^2 + " + c + "^2\\), logo o triângulo é \\(\\mathbf{retângulo}\\)."
			: (tipo == 1)
			? "\\(" + a + "^2 > " + b + "^2 + " + c + "^2\\), logo o triângulo é \\(\\mathbf{obtusângulo}\\)."
			: "\\(" + a + "^2 < " + b + "^2 + " + c + "^2\\), logo o triângulo é \\(\\mathbf{acutângulo}\\).";

		String res = "Comparamos o quadrado do maior lado com a soma dos quadrados dos demais:";
		res += "\\(\\\\\\)";
		res += "\\(" + a + "^2 = " + a2 + ", \\quad " + b + "^2 + " + c + "^2 = "
			+ b2 + " + " + c2 + " = " + (b2 + c2) + "\\)";
		res += "\\(\\\\\\)";
		res += "Como " + concl;
		setResolucao(res);
	}
}
