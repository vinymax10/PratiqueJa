package web.session;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@SessionScoped
@Named
public class TabStateManager implements Serializable
{
	private static final Logger LOG = LoggerFactory.getLogger(TabStateManager.class);

	private final Map<String, Map<String, Object>> statePorAba = new ConcurrentHashMap<>();

	public <T> T getState(Class<T> type)
	{
		String tabId = getTabId();
		LOG.debug("getState tabId: {}", tabId);
		if( tabId!=null)
		{
			String key = type.getName();
			return type.cast(statePorAba.getOrDefault(tabId, Map.of()).get(key));
		}
		return null;
	}

	public <T> void putState(T value)
	{
		String tabId = getTabId();
		LOG.debug("putState tabId: {}", tabId);
		if(tabId!=null)
		{
			String key = value.getClass().getName();
			statePorAba.computeIfAbsent(tabId, k -> new ConcurrentHashMap<>()).put(key, value);
		}
	}

	public boolean hasState(Class<?> type)
	{
		String tabId = getTabId();
		String key = type.getName();
		return tabId!=null&&statePorAba.containsKey(tabId)
        && statePorAba.get(tabId).containsKey(key);
	}
	
	public void clearTab(String tabId)
	{
		statePorAba.remove(tabId);
	}

	private String getTabId()
	{
		return FacesContext.getCurrentInstance()
		.getExternalContext()
		.getRequestParameterMap()
		.get("tabId");
	}

//	 
}
