package modelo;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@MappedSuperclass
public abstract class Config implements Serializable
{
	@AuditLabel(value = "ativo")
	protected boolean ativo=true;
	
	@DiffIgnore
	private int ordem;
	
	public void ativoToggle()
	{
		ativo =!ativo;
	}
	
}
