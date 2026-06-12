package matematica.intermediario.funcaoafim;

import matematica.GeradorExercicio;

public class FuncaoAfimNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String[] tipos = {".nivel3package.Image1", ".nivel3package.Image2",
			".nivel3package.Expressao1", ".nivel3package.Expressao2"};
		delegar(instanciar(tipos[rand.nextInt(tipos.length)]));
	}
}
