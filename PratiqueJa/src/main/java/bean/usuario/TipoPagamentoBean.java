package bean.usuario;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import modelo.usuario.TipoPagamento;


@Named
@SessionScoped
public class TipoPagamentoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<TipoPagamento> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(TipoPagamento.values());
	}

	public List<TipoPagamento> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<TipoPagamento> opcoes)
	{
		this.opcoes = opcoes;
	}
}
