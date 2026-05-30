package matematica.intermediario.jurosdesconto.nivel1package;

import java.util.Random;

import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.Racional;

public class ProblemaJurosSimples
{
	public String texto;

	public TipoJurosSimples tipoJuros;

	public Racional j, c, i, t, m;

	public ProblemaJurosSimples(String texto, TipoJurosSimples tipoJuros)
	{
		super();
		this.texto = texto;
		this.tipoJuros = tipoJuros;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		c = new Racional((1 + rand.nextInt(100)) * 100);
		i = new Racional(1 + rand.nextInt(20), 100);
		t = new Racional(2 + rand.nextInt(35));
		j = c.mult(i).mult(t);
		j.fatoracao(2);
		m = c.add(j);
	}

	public String resolucao()
	{
		String resolucaoLatex = "";
		Racional resultado;
		switch(tipoJuros) {
			case XCIT:
				resolucaoLatex += formulaJurosSimples() + "\\\\";
				resolucaoLatex += "C=" + c + ", \\quad i=" + i.porcentagem() + "\\%" + ", \\quad t=" + t + "\\\\";
				resultado = c.mult(i).mult(t);
				resolucaoLatex += "J=" + c + " \\cdot " + i.showDfrac() + " \\cdot " + t + "=" + resultado.showDfrac();
				resultado.fatoracao(2);
				if(resultado.isSimplificou())
					resolucaoLatex += "=" + resultado;
				break;

			case XJCIT:
				resolucaoLatex += formulaJurosSimples() + "\\\\";
				resolucaoLatex += "C=" + c + ", \\quad i=" + i.porcentagem() + "\\%" + ", \\quad t=" + t + "\\\\";
				resultado = c.mult(i).mult(t);
				resolucaoLatex += "J=" + c + " \\cdot " + i.showDfrac() + " \\cdot " + t + "=" + resultado.showDfrac();
				resultado.fatoracao(2);
				if(resultado.isSimplificou())
					resolucaoLatex += "=" + resultado.showDfrac() + "\\\\";
				resolucaoLatex += formulaMontante() + "\\\\";
				resolucaoLatex += "M=" + resultado.showDfrac() + "+" + c.showDfrac() + "=" + resultado.add(c).showDfrac();
				break;

			case JXIT:
				resolucaoLatex += formulaJurosSimples() + "\\\\";
				resolucaoLatex += "J=" + j.showDfrac() + ", \\quad  i=" + i.porcentagem() + "\\%" + ", \\quad t=" + t.showDfrac() + "\\\\";
				resolucaoLatex += j.showDfrac() + "=C \\cdot " + i.showDfrac() + " \\cdot " + t.showDfrac() + "\\\\";
				resolucaoLatex += "C=\\dfrac{" + j + "}{" + i.showDfrac() + " \\cdot " + t.showDfrac() + "}";
				resultado = i.mult(t);
				resultado.fatoracao(2);
				resolucaoLatex += "=\\dfrac{" + j + "}{" + resultado.showDfrac() + "}=" + c.showDfrac();
				break;

			case JCXT:
				resolucaoLatex += formulaJurosSimples() + "\\\\";
				resolucaoLatex += "C=" + c.showDfrac() + ", \\quad J=" + j.showDfrac() + ", \\quad t=" + t.showDfrac() + "\\\\";
				resultado = j.div(c).div(t);
				resolucaoLatex += j.showDfrac() + "=" + c + " \\cdot i \\cdot " + t + "\\\\";
				resolucaoLatex += "i=\\dfrac{" + j + "}{" + c + " \\cdot " + t + "}";
				resolucaoLatex += "=\\dfrac{" + j + "}{" + c.mult(t) + "}=" + i.showDfrac();
				break;

			case JCIX:
				resolucaoLatex += formulaJurosSimples() + "\\\\";
				resolucaoLatex += "J=" + j.showDfrac() + ", \\quad  C=" + c.showDfrac() + ", \\quad i=" + i.porcentagem() + "\\%" + "\\\\";
				resolucaoLatex += j.showDfrac() + "=" + c.showDfrac() + " \\cdot " + i.showDfrac() + " \\cdot t" + "\\\\";
				resolucaoLatex += "t=\\dfrac{" + j + "}{" + c.showDfrac() + " \\cdot " + i.showDfrac() + "}";
				resultado = c.mult(i);
				resultado.fatoracao(2);
				resolucaoLatex += "=\\dfrac{" + j + "}{" + resultado.showDfrac() + "}=" + t.showDfrac();
				break;
		}
		return resolucaoLatex;

	}

	private String formulaJurosSimples()
	{
		return ParCor.formula("J=C \\cdot i \\cdot t");
	}

	private String formulaMontante()
	{
		return ParCor.formula("M=J+C");
	}

	public String resultado()
	{
		Racional x = null;
		switch(tipoJuros) {
			case XCIT:
				x = j;
				x.fatoracao(2);
				return x.toString();
			case JXIT:
				x = c;
				x.fatoracao(2);
				return x.toString();
			case JCXT:
				return i.numerador+"\\%";
			case JCIX:
				x = t;
				x.fatoracao(2);
				return x.toString();
			case XJCIT:
				x = m;
				x.fatoracao(2);
				return x.toString();
		}

		return "";
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$j"))
			pergunta = pergunta.replace("$j", j.toString());
		if(pergunta.contains("$c"))
			pergunta = pergunta.replace("$c", c.toString());
		if(pergunta.contains("$i"))
			pergunta = pergunta.replace("$i", i.porcentagem());
		if(pergunta.contains("$t"))
			pergunta = pergunta.replace("$t", t.toString());

		return pergunta;
	}

	public ProblemaJurosSimples clone()
	{
		return new ProblemaJurosSimples(texto, tipoJuros);
	}
}
