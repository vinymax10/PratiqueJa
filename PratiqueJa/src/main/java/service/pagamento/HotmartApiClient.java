package service.pagamento;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import infra.HotmartApiConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/**
 * Cliente da API da Hotmart usada para cancelar, do lado do sistema, uma assinatura
 * ativa do comprador (ex.: ao ativar uma troca de plano automaticamente).
 * Requer um App Hotmart com credenciais próprias (HotmartApiConfig), separado do
 * hottok usado só para validar o webhook recebido (HotmartConfig).
 */
@ApplicationScoped
public class HotmartApiClient
{
	private static final Logger LOG = LoggerFactory.getLogger(HotmartApiClient.class);

	private static final String URL_TOKEN = "https://api-sec-vlc.hotmart.com/security/oauth/token";
	private static final String URL_CANCELAR_ASSINATURA = "https://developers.hotmart.com/payments/api/v1/subscriptions/%s/cancel";

	private final HttpClient httpClient = HttpClient.newHttpClient();

	private String tokenCache;
	private Instant tokenExpiraEm = Instant.EPOCH;

	public boolean cancelarAssinatura(String subscriberCode)
	{
		if(subscriberCode == null || subscriberCode.isBlank())
			return false;

		if(!HotmartApiConfig.configurado())
		{
			LOG.warn("Cancelamento Hotmart ignorado: HotmartApiConfig sem clientId/clientSecret configurados");
			return false;
		}

		try
		{
			String token = obterToken();
			if(token == null)
				return false;

			String corpo = Json.createObjectBuilder()
				.add("send_mail", true)
				.build()
				.toString();

			String url = String.format(URL_CANCELAR_ASSINATURA, URLEncoder.encode(subscriberCode, StandardCharsets.UTF_8));

			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(corpo))
				.build();

			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

			if(response.statusCode() >= 200 && response.statusCode() < 300)
			{
				LOG.info("Assinatura Hotmart '{}' cancelada com sucesso", subscriberCode);
				return true;
			}

			LOG.error("Falha ao cancelar assinatura Hotmart '{}': HTTP {} - {}",
				subscriberCode, response.statusCode(), response.body());
			return false;
		}
		catch(Exception e)
		{
			LOG.error("Erro ao cancelar assinatura Hotmart '" + subscriberCode + "'", e);
			return false;
		}
	}

	private synchronized String obterToken() throws Exception
	{
		if(tokenCache != null && Instant.now().isBefore(tokenExpiraEm))
			return tokenCache;

		String credenciais = HotmartApiConfig.getClientId() + ":" + HotmartApiConfig.getClientSecret();
		String basic = Base64.getEncoder().encodeToString(credenciais.getBytes(StandardCharsets.UTF_8));

		String url = URL_TOKEN
			+ "?grant_type=client_credentials"
			+ "&client_id=" + URLEncoder.encode(HotmartApiConfig.getClientId(), StandardCharsets.UTF_8)
			+ "&client_secret=" + URLEncoder.encode(HotmartApiConfig.getClientSecret(), StandardCharsets.UTF_8);

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.header("Authorization", "Basic " + basic)
			.header("Content-Type", "application/json")
			.POST(BodyPublishers.noBody())
			.build();

		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

		if(response.statusCode() < 200 || response.statusCode() >= 300)
		{
			LOG.error("Falha ao obter token OAuth da Hotmart: HTTP {} - {}", response.statusCode(), response.body());
			return null;
		}

		try(JsonReader reader = Json.createReader(new java.io.StringReader(response.body())))
		{
			JsonObject json = reader.readObject();
			tokenCache = json.getString("access_token");

			int expiraEmSegundos = json.containsKey("expires_in") ? json.getInt("expires_in") : 3600;
			tokenExpiraEm = Instant.now().plusSeconds(Math.max(60, expiraEmSegundos - 60));

			return tokenCache;
		}
	}
}
