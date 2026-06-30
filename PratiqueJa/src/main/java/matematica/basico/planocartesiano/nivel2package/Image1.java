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
		BufferedImage image = config.criarImagem();

		boolean pedirOrdenada = rand.nextBoolean();
		int resposta = pedirOrdenada ? py : px;
		String tipoCoord = pedirOrdenada ? "ordenada" : "abscissa";
		String eixo = pedirOrdenada ? "y" : "x";
		String eixoNome = pedirOrdenada ? "ordenadas (vertical)" : "abscissas (horizontal)";
		String direcaoProj = pedirOrdenada ? "horizontal" : "vertical";

		addParagrafo("Qual é a " + tipoCoord + " do ponto P indicado no plano cartesiano?");
		addParagrafoImagem(image);
		gerarAlternativasInteirasComNegativos(resposta);

		addResolucao("A " + tipoCoord + " é o valor lido no eixo das " + eixoNome + ". Seguindo a linha tracejada " + direcaoProj + " de P até o eixo \\(" + eixo + "\\), lemos:");
		if (pedirOrdenada)
			addResolucao("\\(y = " + py + "\\).");
		else
			addResolucao("\\(x = " + px + "\\).");
		addResolucao("Portanto, a " + tipoCoord + " de P é \\(\\mathbf{" + resposta + "}\\).");
	}
}
