package bean.pdf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.pdf.VisibilidadePdf;

@Named
@ApplicationScoped
public class VisibilidadePdfBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final List<VisibilidadePdf> opcoes = Arrays.asList(VisibilidadePdf.values());
}
