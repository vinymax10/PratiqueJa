package modelo;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@MappedSuperclass
public abstract class Ativo implements Serializable
{
	@AuditLabel(value = "ativo")
	protected boolean ativo=true;
	
	public void ativoToggle()
	{
		ativo =!ativo;
	}
}
