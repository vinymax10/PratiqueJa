package service.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.configuracao.ConfigDAO;
import dao.email.EmailDAO;
import filtro.email.FiltroEmail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import modelo.DocumentoFile;
import modelo.configuracao.Config;
import modelo.email.Email;
import modelo.email.StatusEmail;

@ApplicationScoped
public class EmailService
{
	/**
	 * Quantas tentativas de envio um e-mail tem antes de virar {@code FALHA_DEFINITIVA}.
	 *
	 * <p>Morava no {@code EnvioEmailService} e viajava como parâmetro. Passou para cá porque
	 * quem conta a tentativa agora é o {@link #prepararPendentes()} — e a regra tem que ficar
	 * junto de quem a aplica.</p>
	 */
	public static final int LIMITE_TENTATIVA_ENVIO = 5;

	/** Teto de e-mails por ciclo — ver {@code EmailDAO.listarPendentes(int)}. */
	public static final int LIMITE_POR_CICLO = 50;

	@Inject
	private EmailDAO emailDAO;

	@Inject
	private ConfigDAO configDAO;

	@Inject
	@Push(channel = "email")
	private PushContext push;

	/**
	 * Grava os bytes do anexo em disco (nunca no banco) e devolve o {@link DocumentoFile} pronto
	 * para anexar a um {@link Email}. Nome em disco é prefixado com um UUID para não colidir
	 * com outros anexos do mesmo lote (ex.: vários posts com o mesmo nome de arquivo).
	 */
	public DocumentoFile criarAnexo(String nomeArquivo, byte[] dados)
	{
		// Anexo vazio é sempre sintoma de geração que falhou; barra aqui para o e-mail
		// nunca chegar ao usuário com um arquivo de 0 byte.
		if(dados == null || dados.length == 0)
			throw new IllegalArgumentException("Anexo de e-mail vazio: " + nomeArquivo);

		DocumentoFile anexo = new DocumentoFile();
		anexo.setEndDocumentacao(nomeArquivo);
		try
		{
			Config config = configDAO.buscar();
			Path dir = Path.of(config.getEnderecoAnexoEmail());
			Files.createDirectories(dir);

			String nomeUnico = UUID.randomUUID().toString().replace("-", "") + "_" + nomeArquivo;
			Path destino = dir.resolve(nomeUnico);
			Files.write(destino, dados);

			anexo.setCaminhoArquivo(destino.toAbsolutePath().toString());
		}
		catch(IOException e)
		{
			throw new RuntimeException("Falha ao gravar anexo de e-mail em disco: " + nomeArquivo, e);
		}
		return anexo;
	}

	public List<Email> listarPendentes()
	{
		return emailDAO.listarPendentes();
	}

	public List<Email> buscar(FiltroEmail filtro)
	{
		return emailDAO.buscar(filtro);
	}

	public void adicionar(String destinatario, String assunto, String mensagem)
	{
		Email email = new Email();
		email.setDestinatario(destinatario);
		email.setAssunto(assunto);
		email.setMensagem(mensagem);
		adicionar(email);
	}

	public void adicionar(String destinatario, String subject, String msg, List<DocumentoFile> documentosFile)
	{
		Email email = new Email();
		email.setDestinatario(destinatario);
		email.setAssunto(subject);
		email.setMensagem(msg);
		email.setDocumentosFile(documentosFile);

		adicionar(email);
	}

	public void adicionar(Email email)
	{
		emailDAO.adicionar(email);
        push.send("email-adicionado");
	}

	public Email salvar(Email email)
	{
		return emailDAO.salvar(email);
	}

	/**
	 * Reserva os pendentes do ciclo: conta a tentativa de cada um e devolve o que enviar, já
	 * com os bytes dos anexos lidos, tudo dentro de uma transação — para que o envio por SMTP
	 * aconteça fora dela.
	 *
	 * <p><b>Contar antes de enviar.</b> O contador subia só na falha, e depois do envio. O SMTP
	 * não tem rollback: uma vez que a mensagem sai, ela saiu. Se a gravação seguinte falhasse —
	 * o {@code registrarEnvio} e, no {@code catch}, o {@code registrarFalha} usam o mesmo banco,
	 * então uma queda derruba os dois —, o registro ficava PENDENTE com o contador intacto, e a
	 * mesma mensagem saía de novo no minuto seguinte, sem nunca alcançar o limite. Com o
	 * incremento confirmado aqui, o número avança mesmo que tudo depois dele se perca.</p>
	 *
	 * <p>O custo é o oposto: uma falha passageira gasta uma das tentativas. É a troca certa —
	 * enviar cinco vezes é ruim, enviar para sempre é pior.</p>
	 *
	 * <p>O teto por ciclo importa aqui mais que na outra fila: este método lê do disco os anexos
	 * de tudo o que carregar e segura os bytes na memória até o fim do ciclo.</p>
	 */
	@Transactional
	public List<EmailParaEnvio> prepararPendentes()
	{
		List<EmailParaEnvio> prontos = new ArrayList<>();

		for(Email email : emailDAO.listarPendentes(LIMITE_POR_CICLO))
		{
			email.incrementaTetativa();

			// Rede de segurança: o caminho normal de desistência é o registrarFalha. Aqui só
			// cai o registro cujas tentativas foram consumidas sem chegar a registrar nada —
			// uma queda entre a reserva e a gravação do resultado.
			if(email.getTentativaEnvio() > LIMITE_TENTATIVA_ENVIO)
			{
				email.setStatus(StatusEmail.FALHA_DEFINITIVA);
				emailDAO.salvar(email);

				continue;
			}

			emailDAO.salvar(email);

			EmailParaEnvio dto = new EmailParaEnvio(email.getId(), email.getDestinatario(),
			email.getAssunto(), email.getMensagem());

			for(DocumentoFile documentoFile : email.getDocumentosFile())
			{
				try
				{
					String caminho = documentoFile.getCaminhoArquivo();
					byte[] dados = caminho == null ? new byte[0] : Files.readAllBytes(Path.of(caminho));
					dto.adicionarAnexo(documentoFile.getEndDocumentacao(), dados);
				}
				catch(IOException e)
				{
					throw new RuntimeException("Falha ao ler anexo do e-mail " + email.getId(), e);
				}
			}

			prontos.add(dto);
		}

		return prontos;
	}

	@Transactional
	public void registrarEnvio(Long id)
	{
		Email email = emailDAO.carrega(id);
		if(email == null)
			return;

		email.setStatus(StatusEmail.ENVIADO);
		email.setDataEnvio(LocalDateTime.now());
		email.setErro(null);
		emailDAO.salvar(email);
	}

	/**
	 * Guarda o motivo da falha e decide se ainda vale tentar.
	 *
	 * <p><b>Não mexe no contador</b>: ele já foi incrementado e confirmado no
	 * {@link #prepararPendentes()}, antes de a mensagem ir para o SMTP. Incrementar aqui
	 * contaria a mesma tentativa duas vezes.</p>
	 */
	@Transactional
	public void registrarFalha(Long id, String erro)
	{
		Email email = emailDAO.carrega(id);
		if(email == null)
			return;

		email.setErro(erro);
		email.setDataEnvio(LocalDateTime.now());

		if(email.getTentativaEnvio() >= LIMITE_TENTATIVA_ENVIO)
			email.setStatus(StatusEmail.FALHA_DEFINITIVA);
		// caso contrário permanece PENDENTE e será reprocessado no próximo ciclo.

		emailDAO.salvar(email);
	}

	public void remover(Email email)
	{
		emailDAO.remover(email);
        push.send("email-removido");
	}
}
