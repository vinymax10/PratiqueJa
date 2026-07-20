package service.questao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.xmlbeans.impl.common.Levenshtein;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.academico.AnoDAO;
import dao.academico.BancaDAO;
import dao.academico.DisciplinaDAO;
import dao.academico.OrgaoDAO;
import dao.questao.QuestaoDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.academico.Ano;
import modelo.academico.Banca;
import modelo.academico.Disciplina;
import modelo.academico.Orgao;
import modelo.questao.Alternativa;
import modelo.questao.Dificuldade;
import modelo.questao.ImagemFile;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import scraping.QuestaoQ;

/**
 * Importador em massa de questões coletadas por scraping (arquivos {@code C://QuestoesQ//<n>.ser}).
 * Lógica de I/O de arquivo, desserialização e montagem de blob extraída do {@code GestaoQuestaoBean}
 * (que deve cuidar só de view/navegação). Casa cada questão com Ano/Banca/Órgão/Disciplina existentes
 * (por similaridade de Levenshtein) ou cria os que faltarem.
 */
@ApplicationScoped
public class QuestaoImportService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(QuestaoImportService.class);

	@Inject
	private QuestaoDAO questaoDAO;

	@Inject
	private AnoDAO anoDAO;

	@Inject
	private BancaDAO bancaDAO;

	@Inject
	private OrgaoDAO orgaoDAO;

	@Inject
	private DisciplinaDAO disciplinaDAO;

	/** Importa as questões serializadas de índice {@code [inicio, fim)}. Devolve quantas foram gravadas. */
	public int importarQuestoesSalvas(int inicio, int fim)
	{
		int importadas = 0;
		for(int index = inicio; index < fim; index++)
		{
			QuestaoQ questaoQ = deSerializar("" + index);
			if(questaoQ == null)
				continue;

			Questao questao = new Questao();
			questao.setRevisada(false);
			questao.setOrdemInsercao(index);
			questao.setChave(questaoQ.getId());
			questao.setProva(questaoQ.getProva());
			questao.setDificuldade(Dificuldade.Facil);

			questao.setAno(resolverAno(questaoQ.getAno().trim()));
			questao.setBanca(resolverBanca(questaoQ));
			questao.setOrgao(resolverOrgao(questaoQ.getOrgao().trim()));

			List<Disciplina> disciplinas = disciplinaDAO.filtrar(questaoQ.getDisciplina().trim());
			if(disciplinas.size() > 0)
				questao.setDisciplina(disciplinas.get(0));

			questaoDAO.salvar(questao);

			adicionarParagrafos(questao, questaoQ);
			questaoDAO.salvar(questao);

			adicionarAlternativas(questao, questaoQ);
			questaoDAO.salvar(questao);

			importadas++;
		}
		return importadas;
	}

	private Ano resolverAno(String nome)
	{
		List<Ano> anos = anoDAO.filtrar(nome);
		if(anos.size() > 0)
			return anos.get(0);

		Ano ano = new Ano();
		ano.setNome(nome);
		anoDAO.salvar(ano);
		return ano;
	}

	private Banca resolverBanca(QuestaoQ questaoQ)
	{
		String sigla = questaoQ.getBanca().trim();
		List<Banca> bancas = bancaDAO.filtrar(sigla);
		if(bancas.size() > 0)
			return maisProxima(bancas, Banca::getSigla, sigla);

		Banca banca = new Banca();
		banca.setNome(sigla);
		banca.setSigla(questaoQ.getOrgao().trim());
		bancaDAO.salvar(banca);
		return banca;
	}

	private Orgao resolverOrgao(String sigla)
	{
		List<Orgao> orgaos = orgaoDAO.filtrar(sigla);
		if(orgaos.size() > 0)
			return maisProxima(orgaos, Orgao::getSigla, sigla);

		Orgao orgao = new Orgao();
		orgao.setNome(sigla);
		orgao.setSigla(sigla);
		orgaoDAO.salvar(orgao);
		return orgao;
	}

	/** Entre vários candidatos, o de sigla com menor distância de Levenshtein ao valor buscado. */
	private <T> T maisProxima(List<T> candidatos, java.util.function.Function<T, String> sigla, String alvo)
	{
		if(candidatos.size() == 1)
			return candidatos.get(0);

		T melhor = candidatos.get(0);
		int distMin = Integer.MAX_VALUE;
		for(T candidato : candidatos)
		{
			int dist = Levenshtein.distance(sigla.apply(candidato), alvo);
			if(dist < distMin)
			{
				distMin = dist;
				melhor = candidato;
			}
		}
		return melhor;
	}

	private void adicionarParagrafos(Questao questao, QuestaoQ questaoQ)
	{
		for(scraping.Paragrafo paragrafoQ : questaoQ.getParagrafos())
		{
			Paragrafo paragrafo = new Paragrafo();
			paragrafo.setQuestao(questao);
			if(paragrafoQ.getTexto() != null)
			{
				paragrafo.setTexto(paragrafoQ.getTexto());
				paragrafo.setOrdem(questao.getParagrafos().size());
				questao.getParagrafos().add(paragrafo);
			}
			else if(paragrafoQ.getImagem() != null && !paragrafoQ.getImagem().equals(""))
			{
				ImagemFile imagemFile = new ImagemFile();
				imagemFile.setFile(getImage(paragrafoQ.getImagem()));
				imagemFile.setEndImagem(paragrafoQ.getImagem());

				paragrafo.setImagemFile(imagemFile);
				paragrafo.setOrdem(questao.getParagrafos().size());
				questao.getParagrafos().add(paragrafo);
			}
		}
	}

	private void adicionarAlternativas(Questao questao, QuestaoQ questaoQ)
	{
		for(scraping.Alternativa alternativaQ : questaoQ.getAlternativas())
		{
			if(alternativaQ.getTexto() == null || alternativaQ.getTexto().equals(""))
				continue;

			String str = alternativaQ.getTexto();
			if(str.charAt(str.length() - 1) == '.')
				str = str.substring(0, str.length() - 1);
			if(str.charAt(str.length() - 1) == ';')
				str = str.substring(0, str.length() - 1);

			Alternativa alternativa = new Alternativa();
			alternativa.setQuestao(questao);
			alternativa.setTexto(str);
			alternativa.setCorreta(alternativaQ.isCorreta());
			alternativa.setOrdem(questao.getAlternativas().size());
			questao.getAlternativas().add(alternativa);
		}
	}

	private QuestaoQ deSerializar(String nome)
	{
		try(FileInputStream fin = new FileInputStream("C://QuestoesQ//" + nome + ".ser");
			ObjectInputStream ois = new ObjectInputStream(fin))
		{
			return (QuestaoQ) ois.readObject();
		}
		catch(Exception ex)
		{
			LOG.error("Falha ao desserializar questão {}", nome, ex);
			return null;
		}
	}

	private SerialBlob getImage(String endImagem)
	{
		try
		{
			BufferedImage bi = ImageIO.read(new File("C://" + endImagem));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			String[] termos = endImagem.split("\\.");
			String extensao = termos[termos.length - 1];
			ImageIO.write(bi, extensao, baos);
			return new SerialBlob(baos.toByteArray());
		}
		catch(Exception e)
		{
			LOG.error("Falha ao carregar imagem {}", endImagem, e);
			return null;
		}
	}
}
