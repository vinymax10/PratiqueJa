package modelo.auditoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String entidade;      // Produto, Cliente, Preco...
    private Long entidadeId;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;           

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime dataEvento;

    @Column(length = 4095)
    private String resumo;         // Texto humano
    
    @Column(length = 45) // suporta IPv6
    private String ip;

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
