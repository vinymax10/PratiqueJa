package modelo.questao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "imagemFile", "questao" })
@Data
@Entity
public class Paragrafo implements Serializable, Entidade, Comparable<Paragrafo>
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 4095)
	@Size(max = 4095)
	@AuditLabel(value = "texto")
	private String texto;

	@AuditLabel(value = "ordem")
	private int ordem;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemFile imagemFile;

	@DiffIgnore
	@ManyToOne
	private Questao questao;

	public boolean isTipoImagem()
	{
		return imagemFile != null;
	}

	@Override
	public int compareTo(Paragrafo o)
	{
		return ordem - o.ordem;
	}
}
