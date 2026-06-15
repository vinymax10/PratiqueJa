package matematica.avancado.funcaoexponencial.config;

import java.awt.image.BufferedImage;

import static matematica.ConfigImagem.IMG_H;
import static matematica.ConfigImagem.IMG_W;

public class ConfigFuncaoExp
{
	public int    indice;
	public boolean mostrarPonto;
	public String  labelPonto;
	public DadosFuncaoExp dados;

	public ConfigFuncaoExp(DadosFuncaoExp dados)
	{
		this.dados = dados;
	}

	public BufferedImage criarImagem()
	{
		BufferedImage image = new BufferedImage(IMG_W, IMG_H, BufferedImage.TYPE_INT_ARGB);
		GraficoFuncaoExp g = new GraficoFuncaoExp(dados);

		var g2 = g.carregamentoInicial(image);
		g.inserirEixos(g2);
		g.inserirCurva(g2);

		if (mostrarPonto)
			g.inserirPontoDestacado(g2, labelPonto);

		return image;
	}
}
