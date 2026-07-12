package bean.publicacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.publicacao.EstiloPost;

@Named
@ApplicationScoped
public class EstiloPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final List<EstiloPost> opcoes = Arrays.asList(EstiloPost.values());
}
