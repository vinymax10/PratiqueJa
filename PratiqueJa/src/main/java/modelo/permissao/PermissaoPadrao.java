package modelo.permissao;

import modelo.usuario.Usuario;

public class PermissaoPadrao<T> extends Permissao<T>   {

    private static final long serialVersionUID = 1L;
    
    @Override
    public void update(T entidade, Usuario usuario) {
        // libera tudo por padrão
        this.podeEditar = true;
        this.podeRemover = true;
        this.podeCarregar = true;
        this.podeAdicionar = true;
    }
}
