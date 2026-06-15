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

	public String resolucao()
	{
		String res = "Na permutação com repetição, dividimos o fatorial do total de letras pelos fatoriais das repetições.";
		res += "\\(\\\\\\)";
		res += "A palavra \"" + palavra + "\" possui " + n + " letras, logo \\(n = " + n + "\\).";
		res += "\\(\\\\\\)";

        Map<Character, Integer> contador = new HashMap<>();

        for (char c : palavra.toCharArray())
            contador.put(c, contador.getOrDefault(c, 0) + 1);

        List<Integer> list=new ArrayList<Integer>();

        for (Map.Entry<Character, Integer> entry : contador.entrySet())
        {
            if (entry.getValue() > 1)
            {
            	res += "A letra '" + entry.getKey() + "' aparece " + entry.getValue() + " vezes: dividir por \\(" + entry.getValue() + "!\\).";
            	res += "\\(\\\\\\)";
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

        res += "Aplicando a fórmula:";
        res += "\\(\\\\\\)";
        Racional resultado=new Racional(Algebra.fatorial(n),dFinal);
        resultado.fatoracao(2);
        String fracFinal = "\\dfrac{" + Algebra.fatorial(n) + "}{" + dFinal + "}";
        res += "\\(\\text{Total} = \\dfrac{" + n + "!}{" + dInicial + "} = \\\\ ";
        if(resultado.isSimplificou())
        {
        	res += "\\dfrac{" + Algebra.fatorial(n) + "}{" + dParcial + "} = \\\\ ";
        	res += fracFinal + " = \\mathbf{" + resultado.showDfrac() + "}\\)";
        }
        else
        {
        	res += "\\dfrac{" + Algebra.fatorial(n) + "}{" + dParcial + "} = \\mathbf{" + fracFinal + "}\\)";
        }

		return res;
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
            	dFinal*=entry.getValue();
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
