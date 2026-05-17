package bean.usuario;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.usuario.TipoPagamento;

@Named
@ApplicationScoped
public class TipoPagamentoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final List<TipoPagamento> opcoes = Arrays.asList(TipoPagamento.values());
}
