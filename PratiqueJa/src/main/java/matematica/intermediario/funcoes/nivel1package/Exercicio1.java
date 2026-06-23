package matematica.intermediario.funcoes.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.funcoes.DiagramaFuncao;
import matematica.intermediario.funcoes.ImagemDiagrama;

public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean gerarFuncao = rand.nextBoolean();
		DiagramaFuncao d = gerarFuncao
				? DiagramaFuncao.gerarFuncao(rand)
				: DiagramaFuncao.gerarNaoFuncao(rand);

		BufferedImage img = ImagemDiagrama.criar(d, 1 + rand.nextInt(6));

		addParagrafo("A relação representada no diagrama de setas é uma função?");
		addParagrafoImagem(img);
		gerarAlternativasBoolean(d.eFuncao);

		if(d.eFuncao)
		{
			addResolucao("Uma função associa cada elemento do domínio a "
				+ "\\(\\textbf{exatamente um}\\) elemento do contradomínio.");
			addResolucao("Analisando o diagrama: cada elemento de \\(A\\) possui "
				+ "exatamente uma seta saindo.");
			addResolucao("Portanto, a relação \\(\\textbf{é}\\) uma função.");
		}
		else
		{
			int elem  = d.dominio[d.idxBiSeta];
			int img1  = d.contradominio[d.mapeamento[d.idxBiSeta][0]];
			int img2  = d.contradominio[d.mapeamento[d.idxBiSeta][1]];
			addResolucao("Uma função associa cada elemento do domínio a "
				+ "\\(\\textbf{exatamente um}\\) elemento do contradomínio.");
			addResolucao("No diagrama, o elemento \\(" + elem + "\\) possui setas para "
				+ "\\(" + img1 + "\\) e \\(" + img2 + "\\): duas imagens distintas.");
			addResolucao("Portanto, a relação \\(\\textbf{não é}\\) uma função.");
		}
	}
}
