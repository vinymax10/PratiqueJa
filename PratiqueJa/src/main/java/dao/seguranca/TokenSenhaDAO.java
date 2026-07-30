package dao.seguranca;

import java.time.LocalDateTime;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import modelo.seguranca.TokenSenha;
import modelo.usuario.Usuario;

public class TokenSenhaDAO extends DAO<TokenSenha>
{
	private static final long serialVersionUID = 1L;

	public TokenSenhaDAO()
	{
		super(TokenSenha.class);
	}

	public TokenSenha buscarPorHash(String hash)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TokenSenha> query = builder.createQuery(TokenSenha.class);
		Root<TokenSenha> from = query.from(TokenSenha.class);

		TypedQuery<TokenSenha> typedQuery = em.createQuery(
			query.select(from).where(builder.equal(from.get("hash"), hash)));

		List<TokenSenha> lista = typedQuery.getResultList();
		return lista.isEmpty() ? null : lista.get(0);
	}

	/**
	 * Apaga os tokens do usuário (um novo pedido invalida os anteriores) e, de carona, os
	 * já expirados de todo mundo — evita precisar de uma rotina de limpeza só para isso.
	 */
	@Transactional
	public void limpar(Usuario usuario)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<TokenSenha> delete = builder.createCriteriaDelete(TokenSenha.class);
		Root<TokenSenha> from = delete.from(TokenSenha.class);

		em.createQuery(delete.where(builder.or(
			builder.equal(from.get("usuario"), usuario),
			builder.lessThan(from.get("expiracao"), LocalDateTime.now())))).executeUpdate();
	}
}
