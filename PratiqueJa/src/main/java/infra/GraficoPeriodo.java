package infra;

import java.util.List;

public class GraficoPeriodo
{
	private static final String[] BG_COLORS = { "rgba(255, 99, 132, 0.2)", "rgba(255, 159, 64, 0.2)", "rgba(255, 205, 86, 0.2)", "rgba(75, 192, 192, 0.2)",
	"rgba(54, 162, 235, 0.2)" };
	private static final String[] BORDER_COLORS = { "rgb(255, 99, 132)", "rgb(255, 159, 64)", "rgb(255, 205, 86)", "rgb(75, 192, 192)", "rgb(54, 162, 235)" };

	public static String criarGraficoBarras(DadosGrafico dados)
	{
		List<String> labels = dados.getLabels();
		List<Number> values = dados.getValues();

		StringBuilder lb = new StringBuilder();
		StringBuilder dt = new StringBuilder();
		StringBuilder bg = new StringBuilder();
		StringBuilder bd = new StringBuilder();

		for(int i = 0; i < labels.size(); i++)
		{
			if(i > 0)
			{
				lb.append(",");
				dt.append(",");
				bg.append(",");
				bd.append(",");
			}
			lb.append("\"").append(labels.get(i)).append("\"");
			dt.append(i < values.size() ? values.get(i) : 0);
			bg.append("\"").append(BG_COLORS[i % BG_COLORS.length]).append("\"");
			bd.append("\"").append(BORDER_COLORS[i % BORDER_COLORS.length]).append("\"");
		}

		StringBuilder json = new StringBuilder();
		json.append("{\"type\":\"bar\",\"data\":{\"labels\":[").append(lb).append("],").append("\"datasets\":[{\"label\":\"").append(escape(dados.getTitulo()))
		.append("\",").append("\"data\":[").append(dt).append("],").append("\"backgroundColor\":[").append(bg).append("],").append("\"borderColor\":[")
		.append(bd).append("],").append("\"borderWidth\":1}]},").append("\"options\":{\"responsive\":true,\"maintainAspectRatio\":false,");

		if(dados.getIndexAxis() != null)
			json.append("\"indexAxis\":\"").append(dados.getIndexAxis()).append("\",");

		json.append("\"plugins\":{\"title\":{\"display\":").append(dados.getTitulo() != null).append(",\"text\":\"").append(escape(dados.getTitulo()))
		.append("\"}}");

		if(dados.getTituloEixoX() != null || dados.getTituloEixoY() != null)
		{
			json.append(",\"scales\":{");
			boolean primeiro = true;
			if(dados.getTituloEixoX() != null)
			{
				json.append("\"x\":{\"title\":{\"display\":true,\"text\":\"").append(escape(dados.getTituloEixoX())).append("\"}}");
				primeiro = false;
			}
			if(dados.getTituloEixoY() != null)
			{
				if(!primeiro)
					json.append(",");
				json.append("\"y\":{\"title\":{\"display\":true,\"text\":\"").append(escape(dados.getTituloEixoY())).append("\"}}");
			}
			json.append("}");
		}

		json.append("}}");

		return json.toString();
	}

	private static String escape(String s)
	{
		if(s == null)
			return "";
		return s.replace("\\", "\\\\").replace("\"", "\\\"");
	}
}
