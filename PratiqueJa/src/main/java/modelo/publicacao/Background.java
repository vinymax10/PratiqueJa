package modelo.publicacao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class Background implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "endereço")
	private String endereco;

	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	@AuditLabel(value = "feed")
	private boolean feed;

	/** URL do thumbnail (10% da dimensão): mesma pasta do original + "thumb/" + nome do arquivo.
	 *  Usado no grid da configuração para carregar leve; o clique abre a imagem cheia ({@code endereco}). */
	public String getEnderecoThumb()
	{
		if(endereco == null)
			return null;
		int i = endereco.lastIndexOf('/');
		return i < 0 ? endereco : endereco.substring(0, i + 1) + "thumb/" + endereco.substring(i + 1);
	}
}
