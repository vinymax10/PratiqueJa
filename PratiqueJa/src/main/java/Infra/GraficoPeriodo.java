package Infra;

import java.util.ArrayList;
import java.util.List;

import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.dataset.BarDataset;

public class GraficoPeriodo
{
	public static String criarGraficoBarras(DadosGrafico dadosGrafico)
	{
		BarChart barModel = new BarChart();
		BarData data = new BarData();

		BarDataset barDataSet = new BarDataset();
		barDataSet.setLabel(dadosGrafico.getTitutlo());

		List<String> bgColor = new ArrayList<>();
		List<String> borderColor = new ArrayList<>();

		bgColor.add("rgba(255, 99, 132, 0.2)");
		bgColor.add("rgba(255, 159, 64, 0.2)");
		bgColor.add("rgba(255, 205, 86, 0.2)");
		bgColor.add("rgba(75, 192, 192, 0.2)");
		bgColor.add("rgba(54, 162, 235, 0.2)");

		borderColor.add("rgb(255, 99, 132)");
		borderColor.add("rgb(255, 159, 64)");
		borderColor.add("rgb(255, 205, 86)");
		borderColor.add("rgb(75, 192, 192)");
		borderColor.add("rgb(54, 162, 235)");

		barDataSet.setData(dadosGrafico.getValues());
		barDataSet.setBackgroundColor(bgColor);
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addDataset(barDataSet);

		data.setLabels(dadosGrafico.getLabels());
		barModel.setData(data);

		// Options
//		BarOptions options = new BarOptions();
//		CartesianScales cScales = new CartesianScales();
//		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
//		linearAxes.setOffset(true);
//		CartesianLinearTicks ticks = new CartesianLinearTicks();
////		ticks.setBeginAtZero(true);
//		linearAxes.setTicks(ticks);
//		cScales.addYAxesData(linearAxes);
//		options.setScales(cScales);
//
//		Title title = new Title();
//		title.setDisplay(true);
////		title.setText("Q"+questao.getId());
//		options.setTitle(title);
//
//		Legend legend = new Legend();
//		legend.setDisplay(true);
//		legend.setPosition("top");
//		LegendLabel legendLabels = new LegendLabel();
//		legendLabels.setFontStyle("bold");
//		legendLabels.setFontColor("#2980B9");
//		legendLabels.setFontSize(24);
//		legend.setLabels(legendLabels);
//		options.setLegend(legend);
//
//		// disable animation
//		Animation animation = new Animation();
//		animation.setDuration(0);
//		options.setAnimation(animation);

//		barModel.setOptions(options);

		return barModel.toJson();
	}

	public static void main(String[] args)
	{
	}

}
