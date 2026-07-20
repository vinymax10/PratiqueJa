package bean.usuario;

import java.io.Serializable;

import org.primefaces.PrimeFaces;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import web.session.Sessao;

@Data
@Named
@SessionScoped
public class ControleAcessoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String mensagem;

	public boolean verificaEstaLogado()
	{
		if(Sessao.estaLogado())
			return true;

		PrimeFaces.current().executeScript("PF('loginWidget').show()");
		return false;
	}

	public void showUpgrade(String mensagem)
	{
		this.mensagem = mensagem;
		PrimeFaces.current().executeScript("PF('upgradeWidget').show()");
	}

	/** Conteúdo Premium exige plano pago (qualquer perfil acima do Básico). */
	public boolean podeAcessarPremium()
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		return usuario != null && usuario.getPerfil() != PerfilUsuario.Basico;
	}
}
