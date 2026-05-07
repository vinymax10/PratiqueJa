package service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;

import auxiliar.ClasseAux;
import dao.AuditoriaEventoDAO;
import filtro.auditoria.FiltroAuditoria;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.AuditoriaEvento;
import modelo.auditoria.GeneroGramatical;
import modelo.auditoria.TipoEvento;
import modelo.usuario.Usuario;
import session.Sessao;

@Stateless
public class AuditoriaService implements Serializable
{
	@Inject
	private AuditoriaEventoDAO auditoriaEventoDAO;

	@PersistenceContext(unitName = "LojaRoupa")
	private EntityManager em;

	private final Javers javers = JaversBuilder.javers().build();

	private static final DateTimeFormatter DATE_FORMATTER =
    DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public List<AuditoriaEvento> carregar(FiltroAuditoria filtro)
	{
		return auditoriaEventoDAO.buscar(filtro);
	}

	public <T extends Entidade> void registrarEdicao(Class<T> classe, Long entidadeId, T entidadeAtual) 
	{
		Long ini = System.currentTimeMillis();

		T anterior = em.find(classe, entidadeId);

		Diff diff = javers.compare(anterior, entidadeAtual);

		if(diff.hasChanges())
		{
			String resumo = gerarResumo(classe, diff,anterior,entidadeAtual);
			if(!resumo.isBlank())
			{
				AuditoriaEvento evento = criarEventoBase(classe, entidadeId, TipoEvento.EDICAO);
				evento.setResumo(resumo);
				auditoriaEventoDAO.adicionar(evento);
			}
		}
		Long fim = System.currentTimeMillis();

		System.out.println("tempo: " + (fim - ini) + " ms");
	}
	

	public <T extends Entidade> void registrarCriacao(Class<?> classe, Long entidadeId, T entidadeAtual) 
	{
		AuditoriaEvento evento = criarEventoBase(classe, entidadeId, TipoEvento.CRIACAO);
		evento.setResumo(imprimirComAuditLabel(entidadeAtual));
		auditoriaEventoDAO.adicionar(evento);
	}

	public <T extends Entidade> void registrarExclusao(Class<?> classe, Long entidadeId, T entidadeAtual) 
	{
		AuditoriaEvento evento = criarEventoBase(classe, entidadeId, TipoEvento.EXCLUSAO);
		evento.setResumo(imprimirComAuditLabel(entidadeAtual));
		auditoriaEventoDAO.adicionar(evento);
	}

	private AuditoriaEvento criarEventoBase(Class<?> classe, Long entidadeId, TipoEvento tipo)
	{
		AuditoriaEvento evento = new AuditoriaEvento();
		evento.setEntidade(classe.getSimpleName());
		evento.setEntidadeId(entidadeId);
		evento.setTipo(tipo);

		Usuario usuario = (Usuario) Sessao.get("UsuarioLogado");
		evento.setUsuario(usuario);
		evento.setDataEvento(LocalDateTime.now());

		HttpServletRequest request = (HttpServletRequest) Sessao.externalContext().getRequest();
		String userAgent = request.getHeader("User-Agent");
		String ip = request.getRemoteAddr();
		evento.setUserAgent(userAgent);
		evento.setIp(ip);

		return evento;
	}

	private String gerarResumo(Class<?> classe, Diff diff, Object antigo, Object novo)
	{
	    StringBuilder resumo = new StringBuilder();
	    Map<String, ValueChange> changesFiltrados = new LinkedHashMap<>();

	    for(ValueChange change : diff.getChangesByType(ValueChange.class))
	    {
	        String path = change.getPropertyNameWithPath();
	        String root = path.split("[/\\.]")[0];
	        String propriedade = change.getPropertyName();
	       
//	        System.out.println("path: "+path); 
//	        System.out.println("root: "+root);
//	        System.out.println("propriedade: "+propriedade);
	        
	        Field rootField = ClasseAux.getField(classe, root);
	        AuditLabel rootLabel = rootField != null ? rootField.getAnnotation(AuditLabel.class) : null;
//	        System.out.println("rootLabel: "+rootLabel);
	        
	        // 🔥 REGRA: se tem atributo, ignora filhos
	        if(rootLabel != null && !rootLabel.atributo().isEmpty())
	        {
	            // 🔥 guarda o PRIMEIRO change encontrado para esse root
	        	changesFiltrados.putIfAbsent(root, change);
	            continue;
	        }

	        // 🔥 chave única por campo real
	        String chave = root + "." + propriedade;

	        changesFiltrados.put(chave, change);
	    }

	    for(ValueChange change : changesFiltrados.values())
	    {
//	    	System.out.println("change: "+change);
	        String texto = obterResumo(classe, change, antigo, novo);
//	        System.out.println("texto: "+texto);
	        if(!texto.isBlank())
	            resumo.append(texto).append("\n");
	    }

	    return resumo.toString();
	}
	
	private String obterResumo(Class<?> entidadeRaiz, ValueChange change, Object antigo, Object novo)
	{
	    String path = change.getPropertyNameWithPath();

	    List<AuditLabel> anotacoes = obterAnotacoes(entidadeRaiz, path);

	    if(anotacoes == null || anotacoes.isEmpty())
	        return "";

	    AuditLabel raizLabel = anotacoes.get(0);

	    Object valorAntigo;
	    Object valorNovo;

	    // 🔥 Se tiver atributo (ex: produto.nome)
	    if(raizLabel.atributo() != null && !raizLabel.atributo().isEmpty())
	    {
	        String root = path.split("[/\\.]")[0];

	        Object objAntigo = ClasseAux.getValorAtributo(antigo, root);
	        Object objNovo = ClasseAux.getValorAtributo(novo, root);

	        valorAntigo = ClasseAux.getValorAtributo(objAntigo, raizLabel.atributo());
	        valorNovo = ClasseAux.getValorAtributo(objNovo, raizLabel.atributo());
	    }
	    else
	    {
	        valorAntigo = ClasseAux.getValorAtributo(antigo, path.replace("/", "."));
	        valorNovo = ClasseAux.getValorAtributo(novo, path.replace("/", "."));
	    }

	    // 🔥 Evita falso positivo
	    if(valorAntigo instanceof BigDecimal && valorNovo instanceof BigDecimal)
	    {
	        BigDecimal bd1 = (BigDecimal) valorAntigo;
	        BigDecimal bd2 = (BigDecimal) valorNovo;

	        if(bd1.compareTo(bd2) == 0)
	            return "";
	    }
	    else if(Objects.equals(valorAntigo, valorNovo))
	    {
	        return "";
	    }

	    // 🔥 Montagem do texto
	    String texto;

	    if(anotacoes.size() > 1 && raizLabel.atributo().isEmpty())
	    {
	        AuditLabel campo;

	        if(anotacoes.size() >= 3)
	            campo = anotacoes.get(anotacoes.size() - 2); // 🔥 ignora "nome"
	        else
	            campo = anotacoes.get(anotacoes.size() - 1);

	        texto = campo.value() + contexto(raizLabel);
	    }
	    else
	    {
	        texto = raizLabel.value();
	    }

	    AuditLabel labelParaGenero;

	    if(anotacoes.size() > 1 && raizLabel.atributo().isEmpty())
	    {
	        if(anotacoes.size() >= 3)
	            labelParaGenero = anotacoes.get(anotacoes.size() - 2);
	        else
	            labelParaGenero = anotacoes.get(anotacoes.size() - 1);
	    }
	    else
	    {
	        labelParaGenero = raizLabel;
	    }
	    
	    String verbo = verboAlterado(labelParaGenero);
	    
	    texto += " " + verbo + " de '" 
	            + formatarValor(valorAntigo) 
	            + "' para '" 
	            + formatarValor(valorNovo) 
	            + "';";

	    return texto;
	}
	
	private List<AuditLabel> obterAnotacoes(Class<?> entidadeRaiz, String path)
	{
//		System.out.println("-----------obterAnotacoes-----------");
//		System.out.println("path: " + path);
		String[] partes = path.split("[/\\.]");
		Class<?> classeAtual = entidadeRaiz;
		Field field = null;
		Type tipoGenerico;
		List<AuditLabel> anotacoes = new ArrayList<>();

		for(String parte : partes)
		{
//			System.out.println("classeAtual: " + classeAtual.getSimpleName());
//			System.out.println("parte: " + parte);

			field = ClasseAux.getField(classeAtual, parte);

			if(field == null)
			{
//			    System.out.println("Campo não encontrado: " + parte + " em " + classeAtual);
			    return null;
			}
			
			field.setAccessible(true);
			AuditLabel auditLabel = field.getAnnotation(AuditLabel.class);
			if(auditLabel != null)
				anotacoes.add(auditLabel);

			if(!List.class.isAssignableFrom(field.getType()))
			{
//				System.out.println("if 1");
				classeAtual = field.getType();
			}
			else if(field.getGenericType() instanceof ParameterizedType pt)
			{
				tipoGenerico = pt.getActualTypeArguments()[0];

//				System.out.println("if 2 tipoGenerico: " + tipoGenerico);
				classeAtual = (Class<?>) tipoGenerico;
			}
		}
//		System.out.println("anotacoes: " + anotacoes);
//		System.out.println("----------------------");
		return anotacoes;
	}
	
	private String verboAlterado(AuditLabel label)
	{
		if(label != null && label.genero() == GeneroGramatical.FEMININO)
		{
			return "alterada";
		}
		return "alterado";
	}

	private String contexto(AuditLabel label)
	{
		if(label != null)
		{
			if(label.genero() == GeneroGramatical.FEMININO)
				return " da " + label.value();
			else
				return " do " + label.value();
		}
		return "";
	}

	public String imprimirComAuditLabel(Object entidade)
	{
	    if(entidade == null)
	        return "";

	    StringJoiner joiner = new StringJoiner(", ");
	    Class<?> clazz = entidade.getClass();

	    while(clazz != null && clazz != Object.class)
	    {
	        for(Field field : clazz.getDeclaredFields())
	        {
	            AuditLabel auditLabel = field.getAnnotation(AuditLabel.class);
	            if(auditLabel == null)
	                continue;

	            field.setAccessible(true);
	            try
	            {
	                Object valor = field.get(entidade);
	                String textoValor = extrairValorAuditavel(valor, auditLabel.atributo());

	                if(textoValor != null && !textoValor.isBlank())
	                {
	                    joiner.add(auditLabel.value() + "=" + textoValor);
	                }
	            }
	            catch(Exception e)
	            {
	                // opcional: logar erro
	            }
	        }
	        clazz = clazz.getSuperclass();
	    }
//	    System.out.println("joiner.toString(): "+joiner.toString());
	    return joiner.toString();
	}

	private String extrairValorAuditavel(Object valor, String atributo)
	{
	    if(valor == null)
	        return "";

	    // 🔥 suporte a atributo com path (ex: produto.nome)
	    if(atributo != null && !atributo.isEmpty())
	    {
	        String v = obterValorAtributo(valor, atributo);
	        return (v == null || v.equals("null")) ? "" : v;
	    }

	    StringJoiner joiner = new StringJoiner(", ");
	    Class<?> clazz = valor.getClass();
	    boolean encontrouCampoAuditavel = false;

	    while(clazz != null && clazz != Object.class)
	    {
	        for(Field field : clazz.getDeclaredFields())
	        {
	            AuditLabel subLabel = field.getAnnotation(AuditLabel.class);
	            if(subLabel == null)
	                continue;

	            field.setAccessible(true);

	            try
	            {
	                Object subValor = field.get(valor);
	                String textoSubValor = extrairValorAuditavel(subValor, subLabel.atributo());

	                // 🔥 ignora lixo (null, vazio, etc)
	                if(textoSubValor != null && !textoSubValor.isBlank())
	                {
	                    encontrouCampoAuditavel = true;
	                    joiner.add(subLabel.value() + "=" + textoSubValor);
	                }
	            }
	            catch(Exception e)
	            {
	                // opcional
	            }
	            
	            
	        }
	        clazz = clazz.getSuperclass();
	    }

	    // 🔥 se encontrou campos úteis, retorna eles
	    if(encontrouCampoAuditavel)
	        return joiner.toString();

	    // 🔥 evita mostrar objeto vazio tipo "SkuProduto@123"
	    String valorFormatado = formatarValor(valor);

	    if(valorFormatado == null || valorFormatado.isBlank() || valorFormatado.contains("@"))
	        return "";

	    return valorFormatado;
	}

	private String obterValorAtributo(Object objeto, String caminho)
	{
	    if(objeto == null || caminho == null || caminho.isEmpty())
	        return "null";

	    try
	    {
	        Object valorAtual = objeto;

	        for(String parte : caminho.split("\\."))
	        {
	            if(valorAtual == null)
	                return "null";

	            Field campo = ClasseAux.getField(valorAtual.getClass(), parte);
	            if(campo == null)
	                return "null";

	            campo.setAccessible(true);
	            valorAtual = campo.get(valorAtual);
	        }

	        return formatarValor(valorAtual);
	    }
	    catch(Exception e)
	    {
	        return "null";
	    }
	}

	private String formatarValor(Object valor)
	{
	    if(valor == null)
	        return "null";

	    if(valor instanceof LocalDate)
	        return ((LocalDate) valor).format(DATE_FORMATTER);

	    if(valor instanceof LocalDateTime)
	        return ((LocalDateTime) valor).format(DATE_FORMATTER);

	    if(valor instanceof Boolean)
	        return ((Boolean) valor) ? "sim" : "não";

	    if(valor instanceof Enum<?>)
	    {
	        try
	        {
	            Method metodo = valor.getClass().getMethod("getNome");
	            Object nome = metodo.invoke(valor);
	            return nome != null ? nome.toString() : valor.toString();
	        }
	        catch(Exception e)
	        {
	            return valor.toString();
	        }
	    }
	    
	    // 🔥 CORREÇÃO DO PROBLEMA
	    if(valor instanceof BigDecimal)
	    {
	        BigDecimal bd = (BigDecimal) valor;
	        return bd.setScale(2, RoundingMode.HALF_UP).toString();
	    }

	    return valor.toString();
	}
	
}
