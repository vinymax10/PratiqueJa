package util.grafico;

import java.util.List;

public class Grafico
{
	public static String criarGraficoPie(String titulo, DadoGrafico dadosGrafico)
	{
		Cores cores = new Cores();
		List<String> labels = dadosGrafico.getLabels();
		List<Number> values = dadosGrafico.getValues();
		List<String> bgColors = cores.getBgColor();

		StringBuilder lb = new StringBuilder();
		StringBuilder dt = new StringBuilder();
		StringBuilder bg = new StringBuilder();

		for(int i = 0; i < labels.size(); i++)
		{
			if(i > 0) { lb.append(","); dt.append(","); bg.append(","); }
			lb.append("\"").append(escape(labels.get(i))).append("\"");
			dt.append(i < values.size() ? values.get(i) : 0);
			bg.append("\"").append(bgColors.get(i % bgColors.size())).append("\"");
		}

		return "{\"type\":\"pie\",\"data\":{\"labels\":[" + lb + "],"
			+ "\"datasets\":[{\"label\":\"" + escape(titulo) + "\","
			+ "\"data\":[" + dt + "],"
			+ "\"backgroundColor\":[" + bg + "]}]},"
			+ "\"options\":{\"responsive\":true,\"maintainAspectRatio\":false,"
			+ "\"plugins\":{\"title\":{\"display\":true,\"text\":\"" + escape(titulo) + "\"}}}}";
	}

	public static String criarGraficoBarra(String titulo, DadoGrafico dadoGrafico)
	{
		Cores cores = new Cores();
		List<String> labels = dadoGrafico.getLabels();
		List<Number> values = dadoGrafico.getValues();
		List<String> bgColors = cores.getBgColor();
		List<String> bdColors = cores.getBorderColor();

		StringBuilder lb = new StringBuilder();
		StringBuilder dt = new StringBuilder();
		StringBuilder bg = new StringBuilder();
		StringBuilder bd = new StringBuilder();

		for(int i = 0; i < labels.size(); i++)
		{
			if(i > 0) { lb.append(","); dt.append(","); bg.append(","); bd.append(","); }
			lb.append("\"").append(escape(labels.get(i))).append("\"");
			dt.append(i < values.size() ? values.get(i) : 0);
			bg.append("\"").append(bgColors.get(i % bgColors.size())).append("\"");
			bd.append("\"").append(bdColors.get(i % bdColors.size())).append("\"");
		}

		return "{\"type\":\"bar\",\"data\":{\"labels\":[" + lb + "],"
			+ "\"datasets\":[{\"label\":\"" + escape(titulo) + "\","
			+ "\"data\":[" + dt + "],"
			+ "\"backgroundColor\":[" + bg + "],"
			+ "\"borderColor\":[" + bd + "],"
			+ "\"borderWidth\":1}]},"
			+ "\"options\":{\"responsive\":true,\"maintainAspectRatio\":false,"
			+ "\"scales\":{\"y\":{\"stacked\":true}},"
			+ "\"plugins\":{\"title\":{\"display\":true,\"text\":\"" + escape(titulo) + "\"}}}}";
	}

	public static String criarGraficoLinha(String titulo, List<DadoGrafico> dadosGrafico)
	{
		Cores cores = new Cores();

		String labels = null;
		StringBuilder datasets = new StringBuilder();

		for(int cont = 0; cont < dadosGrafico.size(); cont++)
		{
			DadoGrafico dado = dadosGrafico.get(cont);

			if(cont == 0)
			{
				StringBuilder lb = new StringBuilder();
				for(int i = 0; i < dado.getLabels().size(); i++)
				{
					if(i > 0) lb.append(",");
					lb.append("\"").append(escape(dado.getLabels().get(i))).append("\"");
				}
				labels = lb.toString();
			}

			StringBuilder dt = new StringBuilder();
			for(int i = 0; i < dado.getValues().size(); i++)
			{
				if(i > 0) dt.append(",");
				dt.append(dado.getValues().get(i));
			}

			if(cont > 0) datasets.append(",");
			datasets.append("{\"label\":\"").append(escape(dado.getTitutlo())).append("\",")
				.append("\"data\":[").append(dt).append("],")
				.append("\"borderColor\":\"").append(cores.getBorderColor(cont)).append("\",")
				.append("\"backgroundColor\":\"").append(cores.getBgColor(cont)).append("\",")
				.append("\"borderWidth\":2,\"tension\":0.1,\"fill\":false}");
		}

		return "{\"type\":\"line\",\"data\":{\"labels\":[" + labels + "],"
			+ "\"datasets\":[" + datasets + "]},"
			+ "\"options\":{\"maintainAspectRatio\":false,\"responsive\":true,"
			+ "\"plugins\":{\"title\":{\"display\":true,\"text\":\"" + escape(titulo) + "\"}}}}";
	}

	private static String escape(String s)
	{
		if(s == null) return "";
		return s.replace("\\", "\\\\").replace("\"", "\\\"");
	}
}
