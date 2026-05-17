package modelo.auditoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.Entidade;
import modelo.usuario.Usuario;

@Data
@Entity
@Table(indexes = {
@Index(name = "idx_auditoria_usuario", columnList = "usuario_id"),
@Index(name = "idx_auditoria_data", columnList = "dataEvento"),
@Index(name = "idx_auditoria_tipo", columnList = "tipo"),
@Index(name = "idx_auditoria_entidade", columnList = "entidade, entidadeId")})
public class AuditoriaEvento implements Entidade
{
	@DiffIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	private String entidade;

	@DiffIgnore
	private Long entidadeId;

	@DiffIgnore
	@Enumerated(EnumType.STRING)
	private TipoEvento tipo;

	@DiffIgnore
	@ManyToOne
	private Usuario usuario;

	@DiffIgnore
	private LocalDateTime dataEvento;

	@DiffIgnore
	@Column(length = 4095)
	private String resumo;

	@DiffIgnore
	@Column(length = 45)
	private String ip;

	@DiffIgnore
	@Column(length = 512)
	private String userAgent;

	public List<String> getItensResumo()
	{
		List<String> itens = new ArrayList<>();
		if(resumo == null || resumo.isBlank())
			return itens;

		String[] partes = resumo.split(";");

		for(String parte : partes)
		{
			String item = parte.trim();
			if(!item.isEmpty())
				itens.add(item);
		}

		return itens;
	}
}
