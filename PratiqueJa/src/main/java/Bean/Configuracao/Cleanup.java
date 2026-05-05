package Bean.Configuracao;

import java.io.Serializable;

public class Cleanup implements Runnable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private CleanupBean cleanupBean;
	
	public Cleanup(CleanupBean cleanupBean)
	{
		super();
		this.cleanupBean = cleanupBean;
	}
	
	private void dormir(int time)
	{
		try {
			Thread.sleep(time);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void run() 
	{
		while(true)
		{
			cleanupBean.removerTestesVencidos();
			cleanupBean.removerExerciciosVencidos();
			dormir(86400000);
//			dormir(1000);

		}
    }
}
