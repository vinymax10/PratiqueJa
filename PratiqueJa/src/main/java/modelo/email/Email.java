package modelo.email;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.javers.core.metamodel.annotation.DiffIgnore;

import modelo.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Email implements Serializable, Entidade {
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(length = 255)
	@Size(max = 255)
	private String destinatario;
	
	@Column(length = 255)
	@Size(max = 255)
	private String assunto;
	
	@Column(length = 4097)
	@Size(max = 4097)
	private String mensagem;
	
	private int tentativaEnvio;
	
	private LocalDateTime dataEnvio;
	
	private String erro;
	
	@Enumerated(EnumType.STRING)
	private StatusEmail status=StatusEmail.PENDENTE;
	
	public void incrementaTetativa()
	{
		tentativaEnvio++;
	}
}
