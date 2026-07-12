package bean.usuario;

import java.util.EnumSet;

import bean.PaiBean;
import dao.usuario.AssinaturaDAO;
import filtro.usuario.FiltroAssinatura;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Assinatura;
import modelo.usuario.StatusAssinatura;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class AssinaturaBean extends PaiBean<Assinatura, AssinaturaDAO, PermissaoPadrao<Assinatura>>
{
	@Inject
	private FiltroAssinatura filtro;

	public AssinaturaBean()
	{
		super(Assinatura.class, "Assinatura");

		urlCadastro = "/administracao/usuarios/assinatura/form.xhtml";
		urlLista = "/administracao/usuarios/assinatura/list.xhtml";

		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public void filtrar()
	{
		this.lista = entidadeDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	@PostConstruct
	public void init()
	{
		if(tabState.hasState(FiltroAssinatura.class))
			filtro = tabState.getState(FiltroAssinatura.class);
	}

	public StatusAssinatura[] getOpcoesStatus()
	{
		return StatusAssinatura.values();
	}
}
