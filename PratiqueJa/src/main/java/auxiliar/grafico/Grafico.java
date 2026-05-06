package auxiliar.grafico;

import java.util.List;

import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.charts.LineChart;
import software.xdev.chartjs.model.charts.PieChart;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.data.LineData;
import software.xdev.chartjs.model.data.PieData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.dataset.PieDataset;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.LineOptions;
import software.xdev.chartjs.model.options.PieOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianScaleOptions;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianTickOptions;
import software.xdev.chartjs.model.options.scale.cartesian.linear.LinearScaleOptions;

public class Grafico
{
	
	public static String criarGraficoPie(String titulo, DadoGrafico dadosGrafico) {

	    Cores cores = new Cores();

	    // Converter Number -> BigDecimal

	    PieChart pieChart = new PieChart()
	        .setData(new PieData()
	            .addDataset(new PieDataset()
	                .setData(dadosGrafico.getValues())
	                .addBackgroundColors(cores.getBgColor())   // adaptar sua classe
	                .setLabel(titulo)
	            )
	            .setLabels(dadosGrafico.getLabels())
	        );
	    
	    PieOptions options = new PieOptions(); // ou PieOptions options = new PieOptions<>()...

	    options.setPlugins(new Plugins()
	        .setTitle(new Title()
	            .setDisplay(true)
	            .setText(titulo)
	        )
	    );
        
	    pieChart.setOptions(options);

	    return pieChart.toJson();
	}
	
	public static String criarGraficoBarra(String titulo, DadoGrafico dadoGrafico) {

	    Cores cores = new Cores();

	    BarData data = new BarData()
	            .addDataset(new BarDataset()
	                    .setLabel(titulo)
	                    .setData(dadoGrafico.getValues())
	                    .setBackgroundColor(cores.getBgColor())     // array de cores
	                    .setBorderColor(cores.getBorderColor())     // array de cores
	                    .setBorderWidth(1)
	            )
	            .setLabels(dadoGrafico.getLabels());

	    BarChart chart = new BarChart()
	            .setData(data)
	            .setOptions(new BarOptions()
	                    .setResponsive(true)
	                    .setMaintainAspectRatio(false)
	                    .setScales(new Scales()
		                    .addScale(Scales.ScaleAxis.Y, new CartesianScaleOptions()
	                        .setStacked(true)
	                        .setTicks(new CartesianTickOptions())
	                        )
	                    )
	                    .setPlugins(new Plugins()
	                            .setTitle(new Title()
	                                    .setDisplay(true)
	                                    .setText(titulo)
	                            )
	                    )
	            );

	    return chart.toJson();
	}
	
	public static String criarGraficoLinha(String titulo, List<DadoGrafico> dadosGrafico) {

	    Cores cores = new Cores();

	    LineData data = new LineData();

	    int cont = 0;

	    for (DadoGrafico dado : dadosGrafico) 
	    {

	        LineDataset dataset = new LineDataset()
	                .setLabel(dado.getTitutlo())
	                .setData(dado.getValues())
	                .setBorderColor(cores.getBorderColor(cont))
	                .setBackgroundColor(cores.getBgColor(cont))
	                .setBorderWidth(2)
	                .setLineTension(0.1f)
	                .setFill(false);

	        data.addDataset(dataset);

	        // Labels só no primeiro
	        if (cont == 0) {
	            data.setLabels(dado.getLabels());
	        }

	        cont++;
	    }

	    LineChart chart = new LineChart()
	            .setData(data)
	            .setOptions(new LineOptions()
	                    .setMaintainAspectRatio(false)
	                    .setResponsive(true)
	                    .setScales(new Scales()
	                            .addScale(Scales.ScaleAxis.Y,
	                                    new LinearScaleOptions()
	                                            .setPosition(software.xdev.chartjs.model.enums.ScalesPosition.LEFT)
	                            )
	                    )
	                    .setPlugins(new Plugins()
	                            .setTitle(new Title()
	                                    .setDisplay(true)
	                                    .setText(titulo)
	                            )
	                    )
	            );

	    return chart.toJson();
	}
	
	public static void main(String[] args)
	{
	}

}
