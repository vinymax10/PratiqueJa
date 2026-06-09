package matematica.basico.planocartesiano.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.planocartesiano.config.ConfigPlanoCartesiano;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int px = 1 + rand.nextInt(6);
		int py = 1 + rand.nextInt(6);
		if (rand.nextBoolean()) px = -px;
		if (rand.nextBoolean()) py = -py;

		ConfigPlanoCartesiano config = new ConfigPlanoCartesiano(px, py);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		boolean pedirOrdenada = rand.nextBoolean();
		int resposta = pedirOrdenada ? py : px;
		String tipoCoord = pedirOrdenada ? "ordenada" : "abscissa";
		String eixo = pedirOrdenada ? "y" : "x";
		String eixoNome = pedirOrdenada ? "ordenadas (vertical)" : "abscissas (horizontal)";
		String direcaoProj = pedirOrdenada ? "horizontal" : "vertical";

		addParagrafo("Qual é a " + tipoCoord + " do ponto P indicado no plano cartesiano?");
		addParagrafoImagem(image);
		gerarAlternativasInteirasComNegativos(resposta);

		String res = "A " + tipoCoord + " é o valor lido no eixo das " + eixoNome + ". ";
		res += "Seguindo a linha tracejada " + direcaoProj + " de P até o eixo \\(" + eixo + "\\), lemos: \\(\\\\\\)";
		if (pedirOrdenada)
			res += "\\(y = " + py + "\\). \\(\\\\\\)";
		else
			res += "\\(x = " + px + "\\). \\(\\\\\\)";
		res += "Portanto, a " + tipoCoord + " de P é \\(" + resposta + "\\).";

		setResolucao(res);
	}
}
