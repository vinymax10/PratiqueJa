package dao;

import jakarta.transaction.Transactional;
import modelo.Entidade;

public interface DAOInterface<T extends Entidade>
{
	@Transactional
	public void adicionar(T entidade);
	
	@Transactional
	public T salvar(T entidade);

	@Transactional
	public void remover(T entidade);
	
}

