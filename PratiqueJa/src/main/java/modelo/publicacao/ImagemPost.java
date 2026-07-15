package modelo.publicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "configPost", "configPostLogo", "programacoes" })
@Data
@Entity
public class ImagemPost implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "endereço da imagem")
	private String endImagem;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	@DiffIgnore
	@ManyToOne
	private ConfigPost configPost;

	@AuditLabel(value = "feed")
	private boolean feed;

	@AuditLabel(value = "ordem")
	private int ordem;

	@DiffIgnore
	@OneToOne(mappedBy = "logo")
	private ConfigPost configPostLogo;

	@DiffIgnore
	@OneToMany(mappedBy = "background")
	private List<ProgramacaoPost> programacoes = new ArrayList<ProgramacaoPost>();

	/** URL do thumbnail (10% da dimensão): mesma pasta do original + "thumb/" + nome do arquivo. */
	public String getEnderecoThumb()
	{
		if(endImagem == null)
			return null;
		int i = endImagem.lastIndexOf('/');
		return i < 0 ? endImagem : endImagem.substring(0, i + 1) + "thumb/" + endImagem.substring(i + 1);
	}
}
