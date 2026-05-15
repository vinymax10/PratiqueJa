package web.listener;

import bean.email.EnvioSpamBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class EmailServletContextListener implements ServletContextListener {

    @Inject
    private EnvioSpamBean envioSpamBean;
    
    private static ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Contexto da aplicação inicializado.");
        // A instância de MeuBeanDeInicializacao já foi criada e seu método
        // @PostConstruct já foi chamado pelo CDI no momento da injeção.
        // Você pode interagir com o bean aqui se precisar fazer algo
        // específico no contexto do ServletContext.
        System.out.println("Mensagem do bean inicializado: ");
        servletContext = sce.getServletContext();
        envioSpamBean.setServletContext(servletContext);
//        envioSpamBean.enviarProgramacao();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Contexto da aplicação destruído.");
        // Lógica de limpeza da aplicação, se necessário.
        // Os beans CDI com escopo de aplicação serão destruídos pelo contêiner.
    }

//	public EnvioPostBean getEnvioPostBean()
//	{
//		return envioPostBean;
//	}
//
//	public void setEnvioPostBean(EnvioPostBean envioPostBean)
//	{
//		this.envioPostBean = envioPostBean;
//	}

}
