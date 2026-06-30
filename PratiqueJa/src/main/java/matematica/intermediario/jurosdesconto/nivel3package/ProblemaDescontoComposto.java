package matematica.intermediario.jurosdesconto.nivel3package;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.Racional;

public class ProblemaDescontoComposto
{
	public String texto;
	
	public TipoDescontoComposto tipoDesconto;
	
	public Racional d,n,i,t,a;
	
	public ProblemaDescontoComposto(String texto,  TipoDescontoComposto tipoDesconto)
	{
		super();
		this.texto = texto;
		this.tipoDesconto=tipoDesconto;
	}
	
	public void gerarValores()
	{
		Random rand=new Random();
		i = new Racional(1 + rand.nextInt(10),100);
		t = new Racional(2 + rand.nextInt(4));
		Racional parcial;
		
		switch(tipoDesconto)
		{
			case XNIT: 
				a = new Racional((1 + rand.nextInt(100))*100);
				parcial = new Racional(1).minus(i).pow(t);
				n=a.div(parcial);
				d=n.minus(a);
				break;
			case AXIT,ANIX: 
				n = new Racional((1 + rand.nextInt(100))*100);
				parcial = new Racional(1).minus(i).pow(t);
				a=n.mult(parcial);
				d=n.minus(a);
				break;
				
			case XAXIT,XXNIT:
				d = new Racional((1 + rand.nextInt(100))*100);
				parcial = new Racional(1).minus(i).pow(t);
				n=d.div(new Racional(1).minus(parcial));
				a=n.minus(d);
				break;
		}
	}
	
	public String resolucao()
	{
		String resolucaoLatex="";
		DecimalFormat deci = new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.FRANCE));

		switch(tipoDesconto)
		{
			case XNIT: 	resolucaoLatex+=formulaDescontoComposto()+"\\\\";
						resolucaoLatex+="N="+n.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
						resolucaoLatex+="A="+n.decimalMoeda()+" \\cdot (1 - "+i.decimalStr()+")^{"+t+"}=\\\\";
						resolucaoLatex+=n.decimalMoeda()+" \\cdot ("+new Racional(1).minus(i).decimalStr()+")^{"+t+"}=";
						resolucaoLatex+=n.decimalMoeda()+" \\cdot "+new Racional(1).minus(i).pow(t).decimalStr()+"=\\\\";
						resolucaoLatex+="\\mathbf{"+a+"}";

			break;
			
			case AXIT:
			resolucaoLatex+=formulaDescontoComposto()+"\\\\";
			resolucaoLatex+="A="+a.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
			resolucaoLatex+=a.decimalMoeda()+"= N \\cdot (1 - "+i.decimalStr()+")^{"+t+"}\\\\";
			resolucaoLatex+="N = \\dfrac{"+a.decimalMoeda()+"}{ ("+new Racional(1).minus(i).decimalStr()+")^{"+t+"}}=";
			resolucaoLatex+="\\dfrac{"+a.decimalMoeda()+"}{"+new Racional(1).minus(i).pow(t).decimalStr()+"}=";
			resolucaoLatex+="\\mathbf{"+n+"}";
			break;
			
			case ANIX:
			resolucaoLatex+=formulaDescontoComposto()+"\\\\";
			resolucaoLatex+="A="+a.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad N="+n.decimalMoeda()+"\\\\";
			resolucaoLatex+=a.decimalMoeda()+"= "+n.decimalMoeda()+" \\cdot (1 - "+i.decimalStr()+")^{t}\\\\";
			resolucaoLatex+="\\dfrac{"+a.decimalMoeda()+"}{"+n.decimalMoeda()+"}= ("+new Racional(1).minus(i).decimalStr()+")^{t}\\\\";
			double mD=a.decimal();
			double cD=n.decimal();
			double resultado=mD/cD;
			resolucaoLatex+=deci.format(resultado)+"= ("+new Racional(1).minus(i).decimalStr()+")^{t}\\\\";
			resolucaoLatex+="\\log("+deci.format(resultado)+")= \\log("+new Racional(1).minus(i).decimalStr()+"^{t})\\\\";
			resolucaoLatex+="\\log("+deci.format(resultado)+")= t \\cdot \\log("+new Racional(1).minus(i).decimalStr()+")\\\\";
			resolucaoLatex+="t = \\dfrac{\\log("+deci.format(resultado)+")}{ \\log("+new Racional(1).minus(i).decimalStr()+")}=";
			double denominador=new Racional(1).minus(i).decimal();
			resolucaoLatex+="\\dfrac{"+deci.format(Math.log(resultado))+"}{ "+deci.format(Math.log(denominador))+"}=";
			resolucaoLatex+="\\mathbf{"+t+"}";
			break;
			
			case XXNIT: 	
			resolucaoLatex+=formulaDescontoComposto()+"\\\\";
			resolucaoLatex+="N="+n.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
			resolucaoLatex+="A="+n.decimalMoeda()+" \\cdot (1 - "+i.decimalStr()+")^{"+t+"}=\\\\";
			resolucaoLatex+=n.decimalMoeda()+" \\cdot ("+new Racional(1).minus(i).decimalStr()+")^{"+t+"}=";
			resolucaoLatex+=n.decimalMoeda()+" \\cdot "+new Racional(1).minus(i).pow(t).decimalStr()+"\\\\";
			resolucaoLatex+="A="+a.decimalMoeda()+"\\\\";
			resolucaoLatex+=formulaDesconto()+"\\\\";
			resolucaoLatex+="D="+n.decimalMoeda()+"-"+a.decimalMoeda()+"=\\mathbf{"+d.decimalMoeda()+"}";
			break;
			
			case XAXIT:
				resolucaoLatex+=formulaDescontoComposto()+"\\\\";
				resolucaoLatex+="A="+a.decimalMoeda()+", \\quad i="+i.porcentagem()+"\\%"+", \\quad t="+t+"\\\\";
				resolucaoLatex+=a.decimalMoeda()+"= N \\cdot (1 - "+i.decimalStr()+")^{"+t+"}\\\\";
				resolucaoLatex+="N = \\dfrac{"+a.decimalMoeda()+"}{ ("+new Racional(1).minus(i).decimalStr()+")^{"+t+"}}=";
				resolucaoLatex+="\\dfrac{"+a.decimalMoeda()+"}{"+new Racional(1).minus(i).pow(t).decimalStr()+"}=";
				resolucaoLatex+=n.decimalMoeda()+"\\\\";
				resolucaoLatex+=formulaDesconto()+"\\\\";
				resolucaoLatex+="D="+n.decimalMoeda()+"-"+a.decimalMoeda()+"=\\mathbf{"+d.decimalMoeda()+"}";
		}
		return resolucaoLatex;

	}
	
	private String formulaDescontoComposto()
	{
		return ParCor.formula("A=N \\cdot (1 - i)^{t}");
	}
	
	private String formulaDesconto()
	{
		return ParCor.formula("D=N-A");
	}
	
	public String resultado()
	{
		String resultado="";
		switch(tipoDesconto)
		{
			case XNIT: 
				resultado= a.toString();
				break;
			case AXIT: 
				resultado= n.toString();
				break;
			case ANIX: 
				resultado= t.toString();
				break;
			case XAXIT,XXNIT:
				resultado= d.toString();
				break;
		}
		return resultado;
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		if(pergunta.contains("$d"))
			pergunta=pergunta.replace("$d", d.toString());
		if(pergunta.contains("$a"))
			pergunta=pergunta.replace("$a", a.decimalMoeda());
		if(pergunta.contains("$n"))
			pergunta=pergunta.replace("$n", n.decimalMoeda());
		if(pergunta.contains("$i"))
			pergunta=pergunta.replace("$i", i.porcentagem());
		if(pergunta.contains("$t"))
			pergunta=pergunta.replace("$t", t.toString());

		return pergunta;
	}
	
	public ProblemaDescontoComposto clone()
	{
		return new ProblemaDescontoComposto(texto,tipoDesconto);
	}
}
