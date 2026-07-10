package dao.usuario;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.usuario.FiltroProduto;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.publicacao.PerfilCriador;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Produto;
import modelo.usuario.TipoPagamento;

public class ProdutoDAO extends DAO<Produto>
{
	private static final long serialVersionUID = 1L;

	public ProdutoDAO()
	{
		super(Produto.class);
	}

	public List<Produto> buscar(FiltroProduto filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> fromProduto = query.from(Produto.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicates.add(builder.like(fromProduto.get("nome"), "%" + filtro.getNome() + "%"));

		if(filtro.getAtivo() != null)
			predicates.add(builder.equal(fromProduto.get("ativo"), filtro.getAtivo().booleanValue()));

		TypedQuery<Produto> typedQuery = em.createQuery(
			query.select(fromProduto).where(predicates.toArray(new Predicate[0])));

		return typedQuery.getResultList();
	}

	public Produto buscarPorPerfilUsuario(PerfilUsuario perfilUsuario, TipoPagamento tipoPagamento)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> fromProduto = query.from(Produto.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromProduto.get("perfilUsuario"), perfilUsuario));
		predicates.add(builder.equal(fromProduto.get("tipoPagamento"), tipoPagamento));
		predicates.add(builder.equal(fromProduto.get("ativo"), true));

		TypedQuery<Produto> typedQuery = em.createQuery(
			query.select(fromProduto).where(predicates.toArray(new Predicate[0])));

		List<Produto> resultado = typedQuery.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}

	public Produto buscarPorPerfilCriador(PerfilCriador perfilCriador, TipoPagamento tipoPagamento)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> fromProduto = query.from(Produto.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromProduto.get("perfilCriador"), perfilCriador));
		predicates.add(builder.equal(fromProduto.get("tipoPagamento"), tipoPagamento));
		predicates.add(builder.equal(fromProduto.get("ativo"), true));

		TypedQuery<Produto> typedQuery = em.createQuery(
			query.select(fromProduto).where(predicates.toArray(new Predicate[0])));

		List<Produto> resultado = typedQuery.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}

	public Produto buscarPorPerfilAvaliacao(PerfilAvaliacao perfilAvaliacao, TipoPagamento tipoPagamento)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> fromProduto = query.from(Produto.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromProduto.get("perfilAvaliacao"), perfilAvaliacao));
		predicates.add(builder.equal(fromProduto.get("tipoPagamento"), tipoPagamento));
		predicates.add(builder.equal(fromProduto.get("ativo"), true));

		TypedQuery<Produto> typedQuery = em.createQuery(
			query.select(fromProduto).where(predicates.toArray(new Predicate[0])));

		List<Produto> resultado = typedQuery.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}

	public Produto buscarPorIdHotmart(String idHotmart)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> fromProduto = query.from(Produto.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromProduto.get("idHotmart"), idHotmart));
		predicates.add(builder.equal(fromProduto.get("ativo"), true));

		TypedQuery<Produto> typedQuery = em.createQuery(
			query.select(fromProduto).where(predicates.toArray(new Predicate[0])));

		List<Produto> resultado = typedQuery.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}
}
