package matematica.avancado.jurosdesconto.nivel3package;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.Racional;

public class ProblemaJurosComposto
{
	public String texto;
	
	public TipoJurosComposto tipoJuros;
	
	public Racional j,c,i,t,m;
	
	public ProblemaJurosComposto(String texto,  TipoJurosComposto tipoJuros)
	{
		super();
		this.texto = texto;
		this.tipoJuros=tipoJuros;
	}
	
	public void gerarValores()
	{
		Random rand=new Random();
		i = new Racional(1 + rand.nextInt(10),100);
		t = new Racional(2 + rand.nextInt(4));
		Racional parcial;
		
		switch(tipoJuros)
		{
			case XCIT: 
				m = new Racional((1 + rand.nextInt(100))*100);
				parcial = new Racional(1).add(i).pow(t);
				c=m.div(parcial);
				j=m.minus(c);
				break;
				
			case XXCIT,XMXIT: 
				j = new Racional((1 + rand.nextInt(100))*100);
				parcial = new Racional(1).add(i).pow(t);
				c=j.div(parcial.minus(new Racional(1)));
				m=c.add(j);
				break;
				
			case MXIT,MCIX:
				c = new Racional((1 + rand.nextInt(100))*100);
				parcial = new Racional(1).add(i).pow(t);
				m=c.mult(parcial);
				j=m.minus(c);
				break;
		}
	}
	
	public String resolucao()
	{
		String resolucaoLatex="";
		DecimalFormat deci = new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.FRANCE));

		switch(tipoJuros)
		{
			case XCIT: 	resolucaoLatex+=formulaJurosComposto()+"\\\\";
						resolucaoLatex+="C="+c.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
						resolucaoLatex+="M="+c.decimalMoeda()+" \\cdot (1 + "+i.decimalStr()+")^{"+t+"}=\\\\";
						resolucaoLatex+=c.decimalMoeda()+" \\cdot ("+i.add(new Racional(1)).decimalStr()+")^{"+t+"}=";
						resolucaoLatex+=c.decimalMoeda()+" \\cdot "+i.add(new Racional(1)).pow(t).decimalStr()+"\\\\";
						resolucaoLatex+="M="+m;

			break;
			
			case MXIT:
			resolucaoLatex+=formulaJurosComposto()+"\\\\";
			resolucaoLatex+="M="+m.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
			resolucaoLatex+=m.decimalMoeda()+"= C \\cdot (1 + "+i.decimalStr()+")^{"+t+"}\\\\";
			resolucaoLatex+="C = \\dfrac{"+m.decimalMoeda()+"}{ ("+i.add(new Racional(1)).decimalStr()+")^{"+t+"}}=";
			resolucaoLatex+="\\dfrac{"+m.decimalMoeda()+"}{"+i.add(new Racional(1)).pow(t).decimalStr()+"}=";
			resolucaoLatex+=c;
			break;
			
			case MCIX:
			resolucaoLatex+=formulaJurosComposto()+"\\\\";
			resolucaoLatex+="M="+m.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad C="+c.decimalMoeda()+"\\\\";
			resolucaoLatex+=m.decimalMoeda()+"= "+c.decimalMoeda()+" \\cdot (1 + "+i.decimalStr()+")^{t}\\\\";
			resolucaoLatex+="\\dfrac{"+m.decimalMoeda()+"}{"+c.decimalMoeda()+"}= ("+i.add(new Racional(1)).decimalStr()+")^{t}\\\\";
			double mD=m.decimal();
			double cD=c.decimal();
			double resultado=mD/cD;
			resolucaoLatex+=deci.format(resultado)+"= ("+i.add(new Racional(1)).decimalStr()+")^{t}\\\\";
			resolucaoLatex+="\\log("+deci.format(resultado)+")= \\log("+i.add(new Racional(1)).decimalStr()+"^{t})\\\\";
			resolucaoLatex+="\\log("+deci.format(resultado)+")= t \\cdot \\log("+i.add(new Racional(1)).decimalStr()+")\\\\";
			resolucaoLatex+="t = \\dfrac{\\log("+deci.format(resultado)+")}{ \\log("+i.add(new Racional(1)).decimalStr()+")}=";
			double denominador=i.add(new Racional(1)).decimal();
			resolucaoLatex+="\\dfrac{"+deci.format(Math.log(resultado))+"}{ "+deci.format(Math.log(denominador))+"}=";
			resolucaoLatex+=t;
			break;
			
			case XXCIT: 	
			resolucaoLatex+=formulaJurosComposto()+"\\\\";
			resolucaoLatex+="C="+c.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
			resolucaoLatex+="M="+c.decimalMoeda()+" \\cdot (1 + "+i.decimalStr()+")^{"+t+"}=\\\\";
			resolucaoLatex+=c.decimalMoeda()+" \\cdot ("+i.add(new Racional(1)).decimalStr()+")^{"+t+"}=";
			resolucaoLatex+=c.decimalMoeda()+" \\cdot "+i.add(new Racional(1)).pow(t).decimalStr()+"\\\\";
			resolucaoLatex+="M="+m.decimalMoeda()+"\\\\";
			resolucaoLatex+=formulaJuros()+"\\\\";
			resolucaoLatex+="J="+m.decimalMoeda()+"-"+c.decimalMoeda()+"="+j.decimalMoeda();
			break;
			
			case XMXIT:
				resolucaoLatex+=formulaJurosComposto()+"\\\\";
				resolucaoLatex+="M="+m.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
				resolucaoLatex+=m.decimalMoeda()+"= C \\cdot (1 + "+i.decimalStr()+")^{"+t+"}\\\\";
				resolucaoLatex+="C = \\dfrac{"+m.decimalMoeda()+"}{ ("+i.add(new Racional(1)).decimalStr()+")^{"+t+"}}=";
				resolucaoLatex+="\\dfrac{"+m.decimalMoeda()+"}{"+i.add(new Racional(1)).pow(t).decimalStr()+"}=";
				resolucaoLatex+=c.decimalMoeda()+"\\\\";
				resolucaoLatex+=formulaJuros()+"\\\\";
				resolucaoLatex+="J="+m.decimalMoeda()+"-"+c.decimalMoeda()+"="+j.decimalMoeda();
		}
		return resolucaoLatex;

	}
	
	private String formulaJurosComposto()
	{
		return ParCor.formula("M=C \\cdot (1 + i)^{t}");
	}
	
	private String formulaJuros()
	{
		return ParCor.formula("J=M-C");
	}
	
	public String resultado()
	{
		String resultado="";
		switch(tipoJuros)
		{
			case XCIT: 
				resultado= m.toString();
				break;
			case MXIT: 
				resultado= c.toString();
				break;
			case MCIX: 
				resultado= t.toString();
				break;
			case XXCIT,XMXIT:
				resultado= j.toString();
				break;
		}
		return resultado;
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		if(pergunta.contains("$j"))
			pergunta=pergunta.replace("$j", j.toString());
		if(pergunta.contains("$m"))
			pergunta=pergunta.replace("$m", m.decimalMoeda());
		if(pergunta.contains("$c"))
			pergunta=pergunta.replace("$c", c.decimalMoeda());
		if(pergunta.contains("$i"))
			pergunta=pergunta.replace("$i", i.porcentagem());
		if(pergunta.contains("$t"))
			pergunta=pergunta.replace("$t", t.toString());

		return pergunta;
	}
	
	public ProblemaJurosComposto clone()
	{
		return new ProblemaJurosComposto(texto,tipoJuros);
	}
}
