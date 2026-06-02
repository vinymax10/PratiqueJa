package bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import filtro.PeriodoPreset;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

@Data
@Named
@SessionScoped
public class PeriodoPresetBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<PeriodoPreset> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(PeriodoPreset.values());
	}
}
