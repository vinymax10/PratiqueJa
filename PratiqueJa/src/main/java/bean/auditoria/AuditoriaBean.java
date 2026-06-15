package bean.auditoria;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import bean.exporter.AuditoriaExporter;
import filtro.auditoria.FiltroAuditoria;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.Entidade;
import modelo.auditoria.AuditoriaEvento;
import modelo.auditoria.EntidadeAuditavel;
import modelo.auditoria.TipoEvento;
import pdfAntigo.relatorio.AuditoriaPDF;
import service.auditoria.AuditoriaService;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class AuditoriaBean implements Serializable
{
	private String nome="Auditoria";
	
	@Inject
	private AuditoriaService auditoriaService;

	private List<AuditoriaEvento> auditorias;

	private String nomeTitle;
	
	@Inject
	private FiltroAuditoria filtro;
	
	protected boolean mostrarFiltro;

	public void filtrar()
	{
		this.auditorias = auditoriaService.carregar(filtro);
	}
	
	public void filtrarInitEntidade()
	{
		filtro.limparEntidade();
		filtrar();
	}
	
	public void carregar(Entidade object, String nomeTitle)
	{
		this.nomeTitle = nomeTitle;
		filtro.limparEntidade();
		filtro.setEntidade(object.getClass().getSimpleName());
		filtro.setEntidadeId(object.getId());
		this.auditorias = auditoriaService.carregar(filtro);
	}

	public void carregar(Entidade object1, Entidade object2, String nomeTitle)
	{
		this.nomeTitle = nomeTitle;
		filtro.limparEntidade();
		filtro.setEntidade(object1.getClass().getSimpleName());
		filtro.setEntidadeId(object1.getId());
		filtro.setEntidade2(object2.getClass().getSimpleName());
		filtro.setEntidadeId2(object2.getId());
		this.auditorias = auditoriaService.carregar(filtro);
	}
	
	public void filtrarEmpresa()
	{
		this.auditorias = auditoriaService.carregar(filtro);
	}
	
	public void filtrarInitEmpresa()
	{
		filtro.limparEmpresa();
		filtrarEmpresa();
	}
	
	public String getEntidadePorSimpleName(AuditoriaEvento evento)
	{
		return EntidadeAuditavel.getNomePorSimpleName(evento.getEntidade());
	}
	
	public TipoEvento[] getTiposEvento()
	{
		return TipoEvento.values();
	}
	
	public EntidadeAuditavel[] getEntidadesAuditavel()
	{
		return EntidadeAuditavel.values();
	}
	
	public StreamedContent pdf()
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			AuditoriaPDF.exportar(auditorias, filtro, out);
			return DefaultStreamedContent.builder().name("auditoria.pdf").contentType("application/pdf")
			.stream(() -> new ByteArrayInputStream(out.toByteArray())).build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public StreamedContent excel()
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			AuditoriaExporter.exportarExcel(auditorias, out);
			
			return DefaultStreamedContent.builder().name("auditoria.xlsx").contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
			.stream(() -> new ByteArrayInputStream(out.toByteArray())).build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public StreamedContent csv()
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			AuditoriaExporter.exportarCSV(auditorias, out);

			return DefaultStreamedContent.builder().name("auditoria.csv").contentType("text/csv").stream(() -> new ByteArrayInputStream(out.toByteArray()))
			.build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
