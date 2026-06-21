package matematica.avancado.funcaoexponencial;

// Variantes estruturais de modelagem exponencial:
//   CRESCIMENTO: N(t) = N0 · b^t      (população que dobra/triplica por período)
//   MEIAVIDA:    M    = M0 · (1/2)^k  (decaimento radioativo por meia-vida)
//   JUROS:       M    = C · (1 + i)^t (juros compostos)
public enum TipoFuncaoExponencial
{
	CRESCIMENTO, MEIAVIDA, JUROS
}
