package web.webhook;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import infra.HotmartConfig;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.pagamento.HotmartWebhookService;

/**
 * Recebe as notificações (webhook) do Hotmart em compra aprovada/cancelada/reembolsada.
 * Configurado em: conta Hotmart > Ferramentas > Webhook, apontando para esta URL.
 */
@WebServlet("/webhook/hotmart")
public class HotmartWebhookServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(HotmartWebhookServlet.class);

	@Inject
	private HotmartWebhookService hotmartWebhookService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String hottokRecebido = request.getHeader("X-HOTMART-HOTTOK");

		if(!HotmartConfig.hottokValido(hottokRecebido))
		{
			LOG.warn("Webhook Hotmart rejeitado: hottok inválido ou não configurado");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		try(JsonReader jsonReader = Json.createReader(request.getInputStream()))
		{
			JsonObject corpo = jsonReader.readObject();
			hotmartWebhookService.processarNotificacao(corpo);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch(Exception e)
		{
			LOG.error("Falha ao processar webhook do Hotmart", e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
