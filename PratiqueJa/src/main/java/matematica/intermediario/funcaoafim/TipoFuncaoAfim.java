package matematica.intermediario.funcaoafim;

// Variantes estruturais do problema de modelagem por função do 1º grau:
//   CRESCENTE:    f(x) = a·x + b   (valor inicial b, cresce a por período/unidade)
//   DECRESCENTE:  f(x) = -a·x + b  (valor inicial b, decresce a por período/unidade)
// Em ambas pergunta-se o valor de x (nº de períodos/unidades) que leva ao alvo k.
public enum TipoFuncaoAfim
{
	CRESCENTE, DECRESCENTE
}
