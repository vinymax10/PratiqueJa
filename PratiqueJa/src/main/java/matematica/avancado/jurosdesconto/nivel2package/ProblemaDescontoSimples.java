package matematica.avancado.jurosdesconto.nivel2package;

import java.util.Random;

import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.Racional;

public class ProblemaDescontoSimples
{
	public String texto;
	
	public TipoDescontoSimples tipoDesconto;
	
	public Racional d,n,i,t,a;
	
	public ProblemaDescontoSimples(String texto,  TipoDescontoSimples tipoJuros)
	{
		super();
		this.texto = texto;
		this.tipoDesconto=tipoJuros;
	}
	
	public void gerarValores()
	{
		Random rand=new Random();
		n = new Racional((1 + rand.nextInt(100))*100);
		t = new Racional(2 + rand.nextInt(11));
		i = new Racional(1 + rand.nextInt((int)((double)1/t.numerador*100)),100);
		d=n.mult(i).mult(t);
		d.fatoracao(2);
		a=n.minus(d);
	}
	
	public String resolucao()
	{
		String resolucaoLatex="";
		Racional resultado;
		switch(tipoDesconto)
		{
			case XNIT: 	resolucaoLatex+=formulaDescontoSimples()+"\\\\";
						resolucaoLatex+="N="+n+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
						resultado=n.mult(i).mult(t);
						resolucaoLatex+="D="+n+" \\cdot "+i.showDfrac()+" \\cdot "+t+"="+resultado.showDfrac();
						resultado.fatoracao(2);
						if(resultado.isSimplificou())
							resolucaoLatex+="="+resultado;
			break;
			
			case XDNIT:
			resolucaoLatex+=formulaDescontoSimples()+"\\\\";
			resolucaoLatex+="N="+n+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
			resultado=n.mult(i).mult(t);
			resolucaoLatex+="D="+n+" \\cdot "+i.showDfrac()+" \\cdot "+t+"="+resultado.showDfrac();
			resultado.fatoracao(2);
			if(resultado.isSimplificou())
				resolucaoLatex+="="+resultado.showDfrac()+"\\\\";
			resolucaoLatex+=formulaAtual()+"\\\\";
			resolucaoLatex+="A="+n.showDfrac()+"-"+resultado.showDfrac()+"="+n.minus(resultado).showDfrac();
			break;

			case DXIT:
			resolucaoLatex+=formulaDescontoSimples()+"\\\\";
			resolucaoLatex+="D="+d.showDfrac()+", \\quad  i="+i.porcentagem()+"\\%"+", \\quad t="+t.showDfrac()+"\\\\";
			resolucaoLatex+=d.showDfrac()+"=N \\cdot "+i.showDfrac()+" \\cdot "+t.showDfrac()+"\\\\";
			resolucaoLatex+="N=\\dfrac{"+d+"}{"+i.showDfrac()+" \\cdot "+t.showDfrac()+"}";
			resultado=i.mult(t);
			resultado.fatoracao(2);
			resolucaoLatex+="=\\dfrac{"+d+"}{"+resultado.showDfrac()+"}="+n.showDfrac();
			break;
			
			case DNXT: 	
			resolucaoLatex+=formulaDescontoSimples()+"\\\\";
			resolucaoLatex+="N="+n.showDfrac()+", \\quad D="+d.showDfrac()+", \\quad t="+t.showDfrac()+"\\\\";
			resultado=d.div(n).div(t);
			resolucaoLatex+=d.showDfrac()+"="+n+" \\cdot i \\cdot "+t+"\\\\";
			resolucaoLatex+="i=\\dfrac{"+d+"}{"+n+" \\cdot "+t+"}";
			resolucaoLatex+="=\\dfrac{"+d+"}{"+n.mult(t)+"}="+i.showDfrac();
			break;
			
			case DNIX:
			resolucaoLatex+=formulaDescontoSimples()+"\\\\";
			resolucaoLatex+="D="+d.showDfrac()+", \\quad  N="+n.showDfrac()+", \\quad i="+i.porcentagem()+"\\%"+"\\\\";
			resolucaoLatex+=d.showDfrac()+"="+n.showDfrac()+" \\cdot "+i.showDfrac()+" \\cdot t"+"\\\\";
			resolucaoLatex+="t=\\dfrac{"+d+"}{"+n.showDfrac()+" \\cdot "+i.showDfrac()+"}";
			resultado=n.mult(i);
			resultado.fatoracao(2);
			resolucaoLatex+="=\\dfrac{"+d+"}{"+resultado.showDfrac()+"}="+t.showDfrac();
			break;
		}
		return resolucaoLatex;

	}
	
	private String formulaDescontoSimples()
	{
		return ParCor.formula("D=N \\cdot i \\cdot t");
	}
	
	private String formulaAtual()
	{
		return ParCor.formula("A=N-D");
	}
	
	public String resultado()
	{
		Racional x=null;
		switch(tipoDesconto)
		{
			case XNIT: x=d;x.fatoracao(2);return x.toString();
			case DXIT: x=n;x.fatoracao(2);return x.toString();
			case DNXT: return i.numerador+"\\%";
			case DNIX: x=t;x.fatoracao(2);return x.toString();
			case XDNIT:x=a;x.fatoracao(2);return x.toString();
		}
		
		return "";
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		if(pergunta.contains("$d"))
			pergunta=pergunta.replace("$d", d.toString());
		if(pergunta.contains("$n"))
			pergunta=pergunta.replace("$n", n.toString());
		if(pergunta.contains("$i"))
			pergunta=pergunta.replace("$i", i.porcentagem());
		if(pergunta.contains("$t"))
			pergunta=pergunta.replace("$t", t.toString());

		return pergunta;
	}
	
	public ProblemaDescontoSimples clone()
	{
		return new ProblemaDescontoSimples(texto,tipoDesconto);
	}
}
