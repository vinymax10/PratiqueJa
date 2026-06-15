package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import modelo.matematica.Exercicio;
import modelo.matematica.ParagrafoExercicio;

//TODO remover depois
public class AtualizarLayoutListaMain
{
	private static final String URL  = "jdbc:mysql://localhost:3306/pratiqueja?serverTimezone=America/Sao_Paulo";
	private static final String USER = "user";
	private static final String PASS = "Tiquinho*10";

	public static void main(String[] args) throws Exception
	{
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS))
		{
			List<Registro> registros = buscarRegistros(conn);
			System.out.println("Total de ExercicioPadrao: " + registros.size() + "\n");

			int padrao = 0, espacoso = 0, erro = 0;

			for (Registro r : registros)
			{
				String layout = detectarLayout(r);
				atualizarLayout(conn, r.id, layout);

				System.out.printf("ID=%6d  %-65s → %s%n", r.id, r.classe, layout);

				if ("ESPACOSO".equals(layout))    espacoso++;
				else if ("ERRO".equals(layout))   erro++;
				else                              padrao++;
			}

			System.out.printf("%nPADRAO=%d  ESPACOSO=%d  ERRO(mantido PADRAO)=%d%n", padrao, espacoso, erro);
			System.out.println("Concluído.");
		}
	}

	// ── Consulta ─────────────────────────────────────────────────

	private static List<Registro> buscarRegistros(Connection conn) throws Exception
	{
		List<Registro> lista = new ArrayList<>();
		String sql =
			"SELECT ep.id, ep.nome, a.chave, a.modulo " +
			"FROM exerciciopadrao ep " +
			"JOIN assunto a ON ep.assunto_id = a.id " +
			"ORDER BY a.modulo, a.chave, ep.nome";

		try (Statement st = conn.createStatement();
		     ResultSet rs = st.executeQuery(sql))
		{
			while (rs.next())
			{
				Registro r = new Registro();
				r.id     = rs.getLong("id");
				r.nome   = rs.getString("nome");
				r.chave  = rs.getString("chave").toLowerCase();
				r.modulo = resolverModulo(rs.getString("modulo"));
				r.classe = "matematica." + r.modulo + "." + r.chave + "." + r.nome;
				lista.add(r);
			}
		}
		return lista;
	}

	private static String resolverModulo(String valor)
	{
		if (valor == null) return "basico";
		return switch (valor)
		{
			case "0" -> "basico";
			case "1" -> "intermediario";
			case "2" -> "avancado";
			case "3" -> "expert";
			default  -> valor.toLowerCase();
		};
	}

	// ── Detecção de imagem ────────────────────────────────────────

	private static String detectarLayout(Registro r)
	{
		try
		{
			Class<?> cls = Class.forName(r.classe);
			for (int i = 0; i < 5; i++)
			{
				GeradorExercicio g = (GeradorExercicio) cls.getDeclaredConstructor().newInstance();
				Exercicio e = g.gerar();
				for (ParagrafoExercicio p : e.getParagrafos())
					if (p.isTipoImagem())
						return "ESPACOSO";
			}
			return "PADRAO";
		}
		catch (Throwable t)
		{
			System.err.println("  ERRO [" + r.classe + "]: " + t.getMessage());
			return "ERRO";
		}
	}

	// ── Update ────────────────────────────────────────────────────

	private static void atualizarLayout(Connection conn, long id, String layout) throws Exception
	{
		String valor = "ERRO".equals(layout) ? "PADRAO" : layout;
		try (PreparedStatement ps = conn.prepareStatement(
				"UPDATE exerciciopadrao SET layoutLista = ? WHERE id = ?"))
		{
			ps.setString(1, valor);
			ps.setLong(2, id);
			ps.executeUpdate();
		}
	}

	// ── DTO ───────────────────────────────────────────────────────

	private static class Registro
	{
		long   id;
		String nome;
		String chave;
		String modulo;
		String classe;
	}
}
