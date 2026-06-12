package matematica.basico.areaperimetro;

import matematica.GeradorExercicio;

public class AreaPerimetroNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Exercícios de círculo puro (Image8, Image19) migrados para intermediario.circulo
		int[] tipos = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
		int tipo = tipos[rand.nextInt(tipos.length)];
		delegar(instanciar(".nivel1package.Image" + tipo));
	}
}
