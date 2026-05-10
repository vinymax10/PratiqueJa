package util.grafico;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import software.xdev.chartjs.model.color.RGBAColor;

@Data
public class Cores
{
	private List<String> bgColor = new ArrayList<>();
	private List<String> borderColor = new ArrayList<>();

	public Cores()
	{
		String cores[]= {
		"54, 162, 235", //azul
		"255, 99, 132", //vermelho
		"153, 102, 255", //lilais
		"201, 203, 207", //cinza
		"255, 159, 64",//laranja
		"75, 192, 192",//verde
		"255, 205, 86" //amarelo
		};
		
		for(int i = 0; i < cores.length; i++)
		{
			bgColor.add("rgba("+cores[i]+", 0.2)");
			borderColor.add("rgb("+cores[i]+")");
		}
	}
	
	public RGBAColor getBgColor(int index) {
	    RGBAColor[] cores = new RGBAColor[]{
	        new RGBAColor(255, 99, 132, 0.2),
	        new RGBAColor(54, 162, 235, 0.2),
	        new RGBAColor(255, 205, 86, 0.2)
	    };
	    return cores[index % cores.length];
	}

	public RGBAColor getBorderColor(int index) {
	    RGBAColor[] cores = new RGBAColor[]{
	        new RGBAColor(255, 99, 132),
	        new RGBAColor(54, 162, 235),
	        new RGBAColor(255, 205, 86)
	    };
	    return cores[index % cores.length];
	}

}
