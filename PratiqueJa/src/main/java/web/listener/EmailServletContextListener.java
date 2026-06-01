package web.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

// Obsoleto: EnvioSpamService agora usa @Inject ServletContext diretamente via CDI.
@WebListener
public class EmailServletContextListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent sce) {}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
