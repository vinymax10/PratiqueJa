package matematica.avancado.funcaologaritmica.config;

import java.awt.image.BufferedImage;

import static matematica.ConfigImagem.IMG_H;
import static matematica.ConfigImagem.IMG_W;

public class ConfigFuncaoLog
{
	public int          indice;
	public boolean      mostrarPonto;
	public String       labelPonto;
	public DadosFuncaoLog dados;

	public ConfigFuncaoLog(DadosFuncaoLog dados)
	{
		this.dados = dados;
	}

	public BufferedImage criarImagem()
	{
		BufferedImage image = new BufferedImage(IMG_W, IMG_H, BufferedImage.TYPE_INT_ARGB);
		GraficoFuncaoLog g = new GraficoFuncaoLog(dados);

		var g2 = g.carregamentoInicial(image);
		g.inserirEixos(g2);
		g.inserirCurva(g2);

		if (mostrarPonto)
			g.inserirPontoDestacado(g2, labelPonto);

		return image;
	}
}
