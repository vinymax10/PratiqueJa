package modelo.permissao;

import java.io.Serializable;

import lombok.Data;
import modelo.usuario.Usuario;

@Data
public class Permissao <T> implements Serializable
{
    protected boolean podeEditar;
    protected boolean podeRemover;
    protected boolean podeCarregar;
    protected boolean podeAdicionar;

    public void update(T entidade, Usuario usuario) {}
    
    protected void negarTudo()
    {
        this.podeEditar = false;
        this.podeRemover = false;
        this.podeCarregar = false;
        this.podeAdicionar = false;
    }
}