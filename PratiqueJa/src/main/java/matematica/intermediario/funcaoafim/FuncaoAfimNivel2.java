package matematica.intermediario.funcaoafim;

import matematica.GeradorExercicio;

public class FuncaoAfimNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String[] tipos = {".nivel2package.Image1", ".nivel2package.Image2",
			".nivel2package.Expressao1", ".nivel2package.Expressao2"};
		delegar(instanciar(tipos[rand.nextInt(tipos.length)]));
	}
}
