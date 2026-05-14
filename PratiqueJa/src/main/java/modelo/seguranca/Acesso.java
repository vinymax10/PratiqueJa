package modelo.seguranca;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.Entidade;
import modelo.usuario.Usuario;

@Data
@Entity
public class Acesso implements Serializable, Entidade
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
	private Long id;

	@ManyToOne
	private Usuario usuario;

	private LocalDateTime inicio;
	private LocalDateTime termino;

//	duração em segundos
	private long duracao;

	private String idSessao;

	private StatusAcesso status;

}
