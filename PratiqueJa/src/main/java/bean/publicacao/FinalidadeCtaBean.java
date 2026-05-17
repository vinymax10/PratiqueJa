package bean.publicacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.publicacao.FinalidadeCta;

@Named
@ApplicationScoped
public class FinalidadeCtaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final List<FinalidadeCta> opcoes = Arrays.asList(FinalidadeCta.values());
}
