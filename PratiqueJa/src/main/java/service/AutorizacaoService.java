package service;

import jakarta.enterprise.context.ApplicationScoped;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;

@ApplicationScoped
public class AutorizacaoService
{
	public static boolean podeVerAuditoria(Usuario usuario)
	{
		if(usuario!=null)
			return usuario.getPerfil()==PerfilUsuario.Admin;
		
		return false;
	}
	
	public static boolean podeVerAdministracao(Usuario usuario)
	{
		if(usuario!=null)
			return usuario.getPerfil()==PerfilUsuario.Admin;
		
		return false;
	}
}
