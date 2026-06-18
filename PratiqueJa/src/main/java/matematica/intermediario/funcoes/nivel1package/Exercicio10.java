package matematica.intermediario.funcoes.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.funcoes.DiagramaFuncao;
import matematica.intermediario.funcoes.ImagemDiagrama;

public class Exercicio10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DiagramaFuncao d = DiagramaFuncao.gerarFuncao(rand);
		BufferedImage img = ImagemDiagrama.criar(d, 1 + rand.nextInt(6));

		int queryIdx  = rand.nextInt(d.dominio.length);
		int queryElem = d.dominio[queryIdx];
		int resposta  = d.imagemDe(queryIdx);

		addParagrafo("No diagrama de setas, qual o valor de \\(f(" + queryElem + ")\\)?");
		addParagrafoImagem(img);

		// Alternativas: resposta correta + outros elementos do contradomínio
		List<String> distratores = new ArrayList<>();
		for(int j = 0; j < d.contradominio.length && distratores.size() < 3; j++)
			if(d.contradominio[j] != resposta)
				distratores.add("" + d.contradominio[j]);

		// Completa com vizinhos se necessário
		for(int delta = 1; distratores.size() < 3; delta++)
		{
			String cand = "" + (resposta + delta);
			if(!distratores.contains(cand)) distratores.add(cand);
			if(distratores.size() < 3)
			{
				cand = "" + (resposta - delta);
				if(!distratores.contains(cand)) distratores.add(cand);
			}
		}

		embaralharEAdicionarAlternativas("" + resposta, distratores);

		String res = "Observando o diagrama, o elemento \\(" + queryElem
				+ "\\) possui seta apontando para \\(" + resposta + "\\). \\(\\\\\\)";
		res += "Portanto, \\(f(" + queryElem + ") = " + resposta + "\\).";
		setResolucao(res);
	}
}
