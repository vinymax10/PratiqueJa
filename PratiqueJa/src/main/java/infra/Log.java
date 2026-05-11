package infra;

import jakarta.faces.context.FacesContext;
import modelo.usuario.Usuario;
import web.session.Sessao;
import web.session.SessionContext;

public class Log
{
	public static void escrever(String mensagem)
	{
		if(FacesContext.getCurrentInstance() != null
		&&SessionContext.getInstance().getAttribute("UsuarioLogado")!=null)
		{
			Usuario usuario = (Usuario) Sessao.get("UsuarioLogado");
			System.out.println("usuário id: "+usuario.getId()+" nome: "+usuario.getNome()+" "+mensagem);
		}
		else
			System.out.println(mensagem);
	}
}
