package matematica.avancado.funcoestrigonometricas;

import matematica.GeradorExercicio;

public class FuncoesTrigonometricasNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Exercicio1",
		".nivel3package.Exercicio2",
		".nivel3package.Exercicio3",
		".nivel3package.Exercicio4",
		".nivel3package.Exercicio5",
		".nivel3package.Exercicio6",
		".nivel3package.Exercicio7",
		".nivel3package.Exercicio8",
		".nivel3package.Exercicio9"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
