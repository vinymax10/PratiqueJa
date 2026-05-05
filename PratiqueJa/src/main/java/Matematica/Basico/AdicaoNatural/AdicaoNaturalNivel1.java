package Matematica.Basico.AdicaoNatural;

import jakarta.persistence.Entity;

import Matematica.Basico.ResolucaoNatural.ResolucaoNatural;
import Modelo.Matematica.Conta;

@Entity
public class AdicaoNaturalNivel1 extends Conta {
	private static final long serialVersionUID = 1L;

	public AdicaoNaturalNivel1(int indice) {
		super(indice);

		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);

		textLatex=a+"+"+b+"=";

		resultadoCorreto = "" + (a+b);
		
		resolucaoLatex=a+"+"+b+"="+(a+b);

	}

	public AdicaoNaturalNivel1() {
	}

	public static void main(String[] args) {
		String resolucaoLatex=ResolucaoNatural.divisao(30,2,true);
//		System.out.println(resolucaoLatex);
	}
}
