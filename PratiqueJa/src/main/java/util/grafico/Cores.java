package util.grafico;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cores
{
	private static final String[] CORES = {
		"54, 162, 235",
		"255, 99, 132",
		"153, 102, 255",
		"201, 203, 207",
		"255, 159, 64",
		"75, 192, 192",
		"255, 205, 86"
	};

	private List<String> bgColor = new ArrayList<>();
	private List<String> borderColor = new ArrayList<>();

	public Cores()
	{
		for(String cor : CORES)
		{
			bgColor.add("rgba(" + cor + ", 0.2)");
			borderColor.add("rgb(" + cor + ")");
		}
	}

	public String getBgColor(int index) {
		return "rgba(" + CORES[index % CORES.length] + ", 0.2)";
	}

	public String getBorderColor(int index) {
		return "rgb(" + CORES[index % CORES.length] + ")";
	}

}
