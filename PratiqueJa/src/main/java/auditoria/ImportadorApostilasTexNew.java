package auditoria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Importa as apostilas já compiladas em {@code tex-new/<modulo>/<assunto>/<assunto>.pdf}:
 * copia cada PDF para o diretório de PDFs do sistema ({@code Config.enderecoPdf}/teoria/<chave>/)
 * e cria o registro {@code Pdf} correspondente (tipo=Teoria, visibilidade=Basico — liberado para
 * qualquer perfil de usuário).
 *
 * <p>Roda fora do container (sem EntityManager, já que a persistence-unit é JTA e só resolve
 * dentro do WildFly) — usa JDBC puro direto no MySQL. Aloca IDs a partir de
 * {@code pdf_seq.next_val} (a tabela de sequência do Hibernate) e a atualiza ao final, pra não
 * colidir com o próximo lote que o Hibernate reservar quando a aplicação rodar de novo. Não rode
 * isto com a aplicação escrevendo na tabela {@code pdf} ao mesmo tempo.
 *
 * <p>Idempotente: pula qualquer assunto que já tenha um Pdf tipo=Teoria cadastrado.
 *
 * Execução: mvn -q compile exec:java -Dexec.mainClass=auditoria.ImportadorApostilasTexNew
 */
public class ImportadorApostilasTexNew
{
	private static final Path TEX_NEW_DIR = Path.of("tex-new");

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pratiqueja?serverTimezone=America/Sao_Paulo";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "tiquinho10";

	private static final String[] MODULOS = { "basico", "intermediario", "avancado" };

	// tex-new usa nomes de pasta que não batem com Assunto.chave nestes 2 casos (o resto bate
	// por comparação case-insensitive).
	private static final Map<String, String> ALIAS_PASTA_PARA_CHAVE = Map.of(
		"planoCoordenado", "PlanoCartesiano",
		"inequacoes2grau", "InequacoesSegundoGrau"
	);

	public static void main(String[] args) throws Exception
	{
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS))
		{
			con.setAutoCommit(false);
			try
			{
				importar(con);
				con.commit();
			}
			catch(Exception e)
			{
				con.rollback();
				throw e;
			}
		}
	}

	private static void importar(Connection con) throws SQLException, IOException
	{
		String enderecoRaiz = buscarEnderecoConfig(con);
		Path pdfBase = Path.of(enderecoRaiz, "pdf", "teoria");

		List<Path> arquivos = descobrirPdfs();
		System.out.printf("Encontrados %d PDFs em %s%n%n", arquivos.size(), TEX_NEW_DIR.toAbsolutePath());

		long proximoId = buscarProximoId(con);
		int importados = 0, ignorados = 0, semAssunto = 0;

		for(Path origemPdf : arquivos)
		{
			String pasta = origemPdf.getParent().getFileName().toString();
			String chaveTentativa = ALIAS_PASTA_PARA_CHAVE.getOrDefault(pasta, pasta);

			Assunto assunto = buscarAssuntoPorChave(con, chaveTentativa);
			if(assunto == null)
			{
				System.err.println("SEM ASSUNTO CORRESPONDENTE: pasta=" + pasta);
				semAssunto++;
				continue;
			}

			if(existePdfTeoria(con, assunto.id))
			{
				System.out.println("já importado, pulando: " + assunto.chave);
				ignorados++;
				continue;
			}

			String chaveLower = assunto.chave.substring(0, 1).toLowerCase() + assunto.chave.substring(1);
			Path destDir = pdfBase.resolve(chaveLower);
			Files.createDirectories(destDir);
			Path destino = destDir.resolve(chaveLower + ".pdf");
			Files.copy(origemPdf, destino, StandardCopyOption.REPLACE_EXISTING);

			long id = proximoId++;
			inserirPdf(con, id, assunto, destino.toString());
			System.out.println("importado: " + assunto.chave + " (id=" + id + ") -> " + destino);
			importados++;
		}

		atualizarProximoId(con, proximoId);

		System.out.printf("%nImportados: %d | Já existiam: %d | Sem assunto: %d%n", importados, ignorados, semAssunto);
	}

	// ── Descoberta dos arquivos ────────────────────────────────────────────────

	private static List<Path> descobrirPdfs() throws IOException
	{
		List<Path> arquivos = new ArrayList<>();
		for(String modulo : MODULOS)
		{
			Path moduloDir = TEX_NEW_DIR.resolve(modulo);
			if(!Files.isDirectory(moduloDir))
				continue;

			try(var stream = Files.list(moduloDir))
			{
				for(Path assuntoDir : stream.filter(Files::isDirectory).sorted().toList())
				{
					String pasta = assuntoDir.getFileName().toString();
					Path pdf = assuntoDir.resolve(pasta + ".pdf");
					if(Files.isRegularFile(pdf))
						arquivos.add(pdf);
				}
			}
		}
		return arquivos;
	}

	// ── Acesso a dados (JDBC puro) ──────────────────────────────────────────────

	private static String buscarEnderecoConfig(Connection con) throws SQLException
	{
		try(PreparedStatement ps = con.prepareStatement("SELECT endereco FROM config LIMIT 1");
			ResultSet rs = ps.executeQuery())
		{
			if(!rs.next())
				throw new IllegalStateException("Nenhuma linha em config — endereço-raiz não configurado.");
			return rs.getString(1);
		}
	}

	private static Assunto buscarAssuntoPorChave(Connection con, String chave) throws SQLException
	{
		try(PreparedStatement ps = con.prepareStatement(
			"SELECT id, chave FROM assunto WHERE LOWER(chave) = LOWER(?)"))
		{
			ps.setString(1, chave);
			try(ResultSet rs = ps.executeQuery())
			{
				if(!rs.next())
					return null;
				return new Assunto(rs.getLong("id"), rs.getString("chave"));
			}
		}
	}

	private static boolean existePdfTeoria(Connection con, long assuntoId) throws SQLException
	{
		try(PreparedStatement ps = con.prepareStatement(
			"SELECT 1 FROM pdf WHERE assunto_id = ? AND tipo = 'Teoria' LIMIT 1"))
		{
			ps.setLong(1, assuntoId);
			try(ResultSet rs = ps.executeQuery())
			{
				return rs.next();
			}
		}
	}

	private static void inserirPdf(Connection con, long id, Assunto assunto, String caminho) throws SQLException
	{
		try(PreparedStatement ps = con.prepareStatement(
			"INSERT INTO pdf (id, caminho, descricao, visibilidade, assunto_id, nivel, tipo, ordem) "
			+ "VALUES (?, ?, ?, 'Basico', ?, NULL, 'Teoria', 0)"))
		{
			ps.setLong(1, id);
			ps.setString(2, caminho);
			ps.setString(3, "Apostila - " + assunto.chave);
			ps.setLong(4, assunto.id);
			ps.executeUpdate();
		}
	}

	/** ID inicial disponível: o próximo valor que o Hibernate ainda não reservou (pdf_seq.next_val). */
	private static long buscarProximoId(Connection con) throws SQLException
	{
		try(PreparedStatement ps = con.prepareStatement("SELECT next_val FROM pdf_seq FOR UPDATE");
			ResultSet rs = ps.executeQuery())
		{
			if(!rs.next())
				throw new IllegalStateException("Tabela pdf_seq vazia — não há como alocar IDs com segurança.");
			return rs.getLong(1);
		}
	}

	/** Move pdf_seq.next_val pra além do maior ID que usamos, pro próximo lote do Hibernate não colidir. */
	private static void atualizarProximoId(Connection con, long novoProximoId) throws SQLException
	{
		try(PreparedStatement ps = con.prepareStatement("UPDATE pdf_seq SET next_val = ?"))
		{
			ps.setLong(1, novoProximoId);
			ps.executeUpdate();
		}
	}

	private static class Assunto
	{
		final long id;
		final String chave;

		Assunto(long id, String chave)
		{
			this.id = id;
			this.chave = chave;
		}
	}
}
