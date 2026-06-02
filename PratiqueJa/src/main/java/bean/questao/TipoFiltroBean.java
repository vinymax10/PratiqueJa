package bean.questao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import modelo.questao.TipoFiltro;


@Data
@Named
@SessionScoped
public class TipoFiltroBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<TipoFiltro> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(TipoFiltro.values());
	}
}
