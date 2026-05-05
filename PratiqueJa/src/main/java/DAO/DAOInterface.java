package DAO;

import java.util.List;

import jakarta.transaction.Transactional;

import Modelo.Entidade;

public interface DAOInterface<T extends Entidade>
{
	@Transactional
	public T salvar(T entidade);

	@Transactional
	public void remover(T entidade);
	
	public List<T> listaTudo();
}
