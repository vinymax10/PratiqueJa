package service.teste;

import java.lang.reflect.InvocationTargetException;

import dao.teste.TesteDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import matematica.ExercicioFactory;
import modelo.exercicio.Exercicio;
import modelo.teste.ConteudoTeste;
import modelo.teste.EtapaTeste;
import modelo.teste.Teste;

@ApplicationScoped
public class TesteService
{
	@Inject
	private TesteDAO testeDAO;

	public void construirTeste(Teste teste)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException
	{
		Exercicio conta;
		int index = 1;
		for(ConteudoTeste conteudoTeste : teste.getTestePadrao().getConteudosTeste())
		{
			EtapaTeste etapaTeste = new EtapaTeste();
			etapaTeste.setTeste(teste);
			etapaTeste.setExercicioPadrao(conteudoTeste.getExercicioPadrao());

			for(int i = 0; i < conteudoTeste.getQuantidade(); i++)
			{
				do
				{
					conta = ExercicioFactory.gerar(conteudoTeste.getExercicioPadrao().getClasse(), index);
					// TODO religar conta à etapaTeste no novo modelo (sem etapaTeste/tipoExercicio do Conta).
				}
				while(etapaTeste.getContas().contains(conta));
				etapaTeste.getContas().add(conta);
				index++;
			}
			teste.getEtapasTeste().add(etapaTeste);
		}

		teste.setDuracao(teste.getTestePadrao().getDuracao());
		teste.setTempo(teste.getTestePadrao().getDuracao() * 60);
		teste.setNotaMinima(teste.getTestePadrao().getNotaMinima());

		testeDAO.salvar(teste);
	}
}
