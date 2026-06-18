package bean.pdf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import pdf.questao.TipoGabarito;

@Data
@Named
@SessionScoped
public class TipoGabaritoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<TipoGabarito> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(TipoGabarito.values());
	}
}
