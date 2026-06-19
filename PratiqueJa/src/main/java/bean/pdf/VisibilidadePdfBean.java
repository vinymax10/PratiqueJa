package bean.pdf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.pdf.Visibilidade;

@Named
@ApplicationScoped
public class VisibilidadePdfBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final List<Visibilidade> opcoes = Arrays.asList(Visibilidade.values());
}
