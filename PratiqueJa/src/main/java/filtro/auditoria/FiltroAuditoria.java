package filtro.auditoria;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.auditoria.TipoEvento;
import modelo.usuario.Usuario;

@Data
public class FiltroAuditoria implements Serializable
{
    private TipoEvento tipo;           
    private Usuario usuario;
	private String resumo; 
    private String entidade; 
    private Long entidadeId;
    private String entidade2; 
    private Long entidadeId2;
   
    private String ip;
    private String userAgent;
    
	private List<LocalDate> periodo;
	private PeriodoPreset preset;
	
	public void limparEntidade()
	{
		tipo=null;
		usuario=null;
		resumo="";
		ip="";
		userAgent="";
		periodo=null;
		preset=null;
	}
	
	public void limparEmpresa()
	{
		limparEntidade();
		entidade=null;
		entidadeId=null;
	}
	
	public void resetPreset()
	{
		preset=PeriodoPreset.PERSONALIZADO;
	}
	
	public void aplicarPreset() 
	{
		if(this.preset == null) 
            this.periodo = null; 

		else if(preset != PeriodoPreset.PERSONALIZADO) 
	    	periodo = preset.calcularIntervalo();
	}

}
