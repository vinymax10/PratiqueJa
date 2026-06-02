package service.email;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import dao.email.ConfigSpamDAO;
import dao.email.ProgramacaoSpamDAO;
import dao.publicacao.CtaDAO;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import modelo.DocumentoFile;
import modelo.academico.AssuntoCurso;
import modelo.email.ConfigSpam;
import modelo.email.ProgramacaoSpam;
import modelo.publicacao.FinalidadeCta;
import service.ebook.EBookService;

@Singleton
@Startup
public class EnvioSpamService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(EnvioSpamService.class.getName());

	@Inject
	private ProgramacaoSpamDAO programacaoSpamDAO;

	@Inject
	private ConfigSpamDAO configSpamDAO;

	@Inject
	private ProgramacaoSpam programacaoSpam;

	@Inject
	private EmailService emailService;

	@Inject
	private CtaDAO ctaDAO;

	@Inject
	private EBookService ebookService;

	@Inject
	private ServletContext servletContext;

	private List<ProgramacaoSpam> programacoesSpams = new ArrayList<>();
	private ConfigSpam configSpam;

	@PostConstruct
	public void init()
	{
		logger.info("----------------Envio Spam iniciado----------------");
	}

	@Schedule(hour = "9-23", minute = "0", second = "0", persistent = false)
	public void enviarProgramacao()
	{
		programacoesSpams = programacaoSpamDAO.hoje();
		logger.fine("size: " + programacoesSpams.size());
		for(ProgramacaoSpam programacaoSpam : programacoesSpams)
		{
			this.programacaoSpam = programacaoSpam;
			configSpam = programacaoSpam.getConfigSpam();
			if(configSpam.podeGerar())
			{
				logger.fine("gerando conteudo para Spam");
				gerarListaExercicio();

				configSpam.setUltimoEnvio(LocalDate.now());
				configSpam = configSpamDAO.salvar(configSpam);
				programacaoSpam.setConfigSpam(configSpam);

				programacaoSpam.setOrdem(configSpam.getProgramacoesSpam().size() - 1);
				salvar(programacaoSpam);
			}
		}
	}

	public void acorda()
	{
		enviarProgramacao();
	}

	public void gerarListaExercicio()
	{
		AssuntoCurso assuntoCurso = programacaoSpam.getAssuntoCurso();
		String basePath = servletContext.getRealPath("");

		ByteArrayOutputStream exercicioOutputStream = ebookService.construirListasExercicios(assuntoCurso, configSpam, basePath, p -> {});
		InputStream inStream = new ByteArrayInputStream(exercicioOutputStream.toByteArray());

		DocumentoFile documentoFile = new DocumentoFile();
		try
		{
			SerialBlob serialBlob = new SerialBlob(IOUtils.toByteArray(inStream));
			documentoFile.setFile(serialBlob);
			documentoFile.setEndDocumentacao((assuntoCurso.getOrdem() + 1) + "_" + assuntoCurso.getNome() + ".pdf");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		enviarEmail("Lista de exercícios do Pratique Já", documentoFile, assuntoCurso.getNome());
	}

	private void enviarEmail(String assuntoEmail, DocumentoFile documentoFile, String assunto)
	{
		List<modelo.usuario.Usuario> usuarios = configSpamDAO.usuarioSpam();
		for(modelo.usuario.Usuario usuario : usuarios)
		{
			List<DocumentoFile> documentosFile = new ArrayList<>();
			documentosFile.add(documentoFile.clone());

			String mensagem = "Olá, " + usuario.getFirstNome() + ".\r\n\r\n";
			mensagem += "Segue em anexo a nova lista de exercícios sobre o assunto: " + assunto + ".\r\n\r\n";
			mensagem += "📝 Baixe agora e comece a estudar:\r\n\r\n";
			mensagem += ctaDAO.getAnyCta(FinalidadeCta.Spam) + "\r\n\r\n";
			mensagem += "Lembre-se: você receberá uma nova lista a cada 3 dias, para manter o ritmo e evoluir de forma constante.\r\n\r\n";
			mensagem += "Bons estudos!\r\n";
			mensagem += "Equipe do Pratique Já\r\n";
			mensagem += "📚 pratiqueja.com";

			emailService.adicionar(usuario.getEmail(), assuntoEmail, mensagem, documentosFile);
		}
	}

	public void salvar(ProgramacaoSpam programacaoSpam)
	{
		ConfigSpam configSpam = programacaoSpam.getConfigSpam();
		try
		{
			programacaoSpam = programacaoSpamDAO.salvar(programacaoSpam);
			configSpam.getProgramacoesSpam().remove(programacaoSpam);
			configSpam.getProgramacoesSpam().add(programacaoSpam.getOrdem(), programacaoSpam);

			organizarOrdem(configSpam);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void organizarOrdem(ConfigSpam configSpam)
	{
		List<ProgramacaoSpam> programacoesSpam = configSpam.getProgramacoesSpam();
		for(int i = 0; i < programacoesSpam.size(); i++)
		{
			ProgramacaoSpam ps = programacoesSpam.get(i);
			ps.setOrdem(i);
			ps.updateData();
			programacaoSpamDAO.salvar(ps);
		}
	}
}
