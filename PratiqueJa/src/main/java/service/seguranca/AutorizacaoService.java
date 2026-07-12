package service.seguranca;

import modelo.usuario.Usuario;

public class AutorizacaoService
{
	public static boolean podeVerAuditoria(Usuario usuario)
	{
		if(usuario!=null)
			return usuario.isAdmin();

		return false;
	}

	public static boolean podeVerAdministracao(Usuario usuario)
	{
		if(usuario!=null)
			return usuario.isAdmin();

		return false;
	}
}
