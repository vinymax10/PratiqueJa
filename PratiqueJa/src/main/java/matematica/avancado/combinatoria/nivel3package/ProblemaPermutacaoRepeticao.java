package matematica.avancado.combinatoria.nivel3package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Algebra;
import matematica.Racional;

public class ProblemaPermutacaoRepeticao
{
	public String texto;

	public String palavra;
	public TipoPermutacaoRepeticao tipo;
	public int n;
	
	public ProblemaPermutacaoRepeticao(String texto, TipoPermutacaoRepeticao tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores()
	{
		palavra = TextoAnagramas.getPalavra().toUpperCase();
		n=palavra.length();
	}

	public String[] resolucao()
	{
		List<String> passos = new ArrayList<String>();
		passos.add("Na permutação com repetição, dividimos o fatorial do total de letras pelos fatoriais das repetições.");
		passos.add("A palavra \"" + palavra + "\" possui " + n + " letras, logo \\(n = " + n + "\\).");

        Map<Character, Integer> contador = new HashMap<>();

        for (char c : palavra.toCharArray())
            contador.put(c, contador.getOrDefault(c, 0) + 1);

        List<Integer> list=new ArrayList<Integer>();

        for (Map.Entry<Character, Integer> entry : contador.entrySet())
        {
            if (entry.getValue() > 1)
            {
            	passos.add("A letra '" + entry.getKey() + "' aparece " + entry.getValue() + " vezes: dividir por \\(" + entry.getValue() + "!\\).");
            	list.add(entry.getValue());
            }
        }
        String dInicial="";
        String dParcial="";
        int dFinal=1;
        int fatorial;
        for(int i = 0; i < list.size(); i++)
		{
        	dInicial+=""+list.get(i)+"!";
        	fatorial=Algebra.fatorial(list.get(i));
        	dParcial+=""+fatorial;
        	dFinal*=fatorial;
        	if(i<(list.size()-1))
        	{
        		dInicial+=" \\cdot ";
        		dParcial+=" \\cdot ";
        	}
		}

        passos.add("Aplicando a fórmula:");
        Racional resultado=new Racional(Algebra.fatorial(n),dFinal);
        resultado.fatoracao(2);
        String fracFinal = "\\dfrac{" + Algebra.fatorial(n) + "}{" + dFinal + "}";
        passos.add("\\(\\text{Total} = \\dfrac{" + n + "!}{" + dInicial + "} =\\)");
        if(resultado.isSimplificou())
        {
        	passos.add("\\(\\dfrac{" + Algebra.fatorial(n) + "}{" + dParcial + "} =\\)");
        	passos.add("\\(" + fracFinal + " = \\mathbf{" + resultado.showDfrac() + "}\\)");
        }
        else
        {
        	passos.add("\\(\\dfrac{" + Algebra.fatorial(n) + "}{" + dParcial + "} = \\mathbf{" + fracFinal + "}\\)");
        }

		return passos.toArray(new String[0]);
	}

	public String resultado()
	{
		Map<Character, Integer> contador = new HashMap<>();

        for (char c : palavra.toCharArray()) 
            contador.put(c, contador.getOrDefault(c, 0) + 1);

        int dFinal=1;
        for (Map.Entry<Character, Integer> entry : contador.entrySet())
        {
            if (entry.getValue() > 1)
            	dFinal*=Algebra.fatorial(entry.getValue());
        }
        Racional resultado=new Racional(Algebra.fatorial(n),dFinal);
        resultado.fatoracao(2);
        
		return resultado.toString();
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$a"))
			pergunta = pergunta.replace("$a", "\""+palavra+"\"");
		
		return pergunta;
	}

	public ProblemaPermutacaoRepeticao clone()
	{
		return new ProblemaPermutacaoRepeticao(texto,tipo);
	}
}
