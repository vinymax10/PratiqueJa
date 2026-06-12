package matematica.intermediario.funcoes.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;
import matematica.intermediario.funcoes.DiagramaFuncao;
import matematica.intermediario.funcoes.ImagemDiagrama;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gera função onde Im(f) ⊊ B (pelo menos um elemento de B não é atingido)
		DiagramaFuncao d;
		Set<Integer> imagemIdx;
		do
		{
			d = DiagramaFuncao.gerarFuncao(rand);
			imagemIdx = new HashSet<>();
			for(int[] m : d.mapeamento) imagemIdx.add(m[0]);
		}
		while(imagemIdx.size() >= d.contradominio.length);

		BufferedImage img = ImagemDiagrama.criar(d, 1 + rand.nextInt(6));

		// Conjunto imagem: valores de B efetivamente atingidos, em ordem crescente
		List<Integer> imagemVals = new ArrayList<>();
		for(int idx : imagemIdx) imagemVals.add(d.contradominio[idx]);
		Collections.sort(imagemVals);

		String imagemCorreta = toSetLatex(imagemVals);

		addParagrafo("Qual é a imagem de \\(f\\)?");
		addParagrafoImagem(img);

		// Alternativas
		// D1: contradomínio inteiro (confusão imagem × contradomínio)
		List<Integer> cdList = new ArrayList<>();
		for(int v : d.contradominio) cdList.add(v);
		Collections.sort(cdList);
		String d1 = toSetLatex(cdList);

		// D2: imagem + um elemento de B que não é atingido
		List<Integer> d2vals = new ArrayList<>(imagemVals);
		for(int j = 0; j < d.contradominio.length; j++)
		{
			if(!imagemIdx.contains(j))
			{
				d2vals.add(d.contradominio[j]);
				break;
			}
		}
		Collections.sort(d2vals);
		String d2 = toSetLatex(d2vals);

		// D3: imagem sem um elemento (se tiver mais de 1)
		String d3;
		if(imagemVals.size() > 1)
		{
			List<Integer> d3vals = new ArrayList<>(imagemVals);
			d3vals.remove(0);
			d3 = toSetLatex(d3vals);
		}
		else
			d3 = toSetLatex(cdList.subList(0, Math.min(2, cdList.size())));

		List<String> distratores = new ArrayList<>();
		if(!d1.equals(imagemCorreta)) distratores.add(d1);
		if(!d2.equals(imagemCorreta) && !distratores.contains(d2)) distratores.add(d2);
		if(!d3.equals(imagemCorreta) && !distratores.contains(d3)) distratores.add(d3);
		while(distratores.size() < 3) distratores.add(toSetLatex(imagemVals.subList(0, 1)));

		List<String> distWrap = new ArrayList<>();
		for(String str : distratores.subList(0, 3)) 
			distWrap.add("\\(" + str + "\\)");
		
		embaralharEAdicionarAlternativas("\\(" + imagemCorreta + "\\)", distWrap);

		// Resolução
		StringBuilder mapeamentos = new StringBuilder();
		for(int i = 0; i < d.dominio.length; i++)
		{
			if(i > 0) mapeamentos.append(", \\;");
			mapeamentos.append("f(").append(d.dominio[i]).append(") = ").append(d.imagemDe(i));
		}
		String res = "A imagem de \\(f\\) é o conjunto dos valores de \\(B\\) "
				+ "efetivamente atingidos por alguma seta. \\(\\\\\\)";
		res += "Lendo o diagrama: \\(" + mapeamentos + "\\). \\(\\\\\\)";
		res += "Os valores distintos atingidos são \\(" + imagemCorreta.replace("\\{", "").replace("\\}", "") + "\\). \\(\\\\\\)";
		res += "Portanto, \\(\\text{Im}(f) = " + imagemCorreta + "\\).";
		setResolucao(res);
	}

	private static String toSetLatex(List<Integer> vals)
	{
		StringBuilder sb = new StringBuilder("\\{");
		for(int i = 0; i < vals.size(); i++)
		{
			if(i > 0) sb.append(", ");
			sb.append(vals.get(i));
		}
		sb.append("\\}");
		return sb.toString();
	}
}
