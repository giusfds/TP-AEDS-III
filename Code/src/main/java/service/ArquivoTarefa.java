package service;

import model.Tarefa;

/**
 *  ArquivoTarefa: Classe que representa um arquivo de tarefas.
 */
public class ArquivoTarefa extends Arquivo<Tarefa>
{
    Arquivo<Tarefa> arqTarefa;
    HashExtensivel<ParIDCategoriaIDTarefa> indiceIndiretoIDCategoria;

    public ArquivoTarefa ( ) throws Exception 
    {
        super( "tarefas", Tarefa.class.getConstructor() );
        indiceIndiretoIDCategoria = new HashExtensivel<>
        (
            ParIDCategoriaIDTarefa.class.getConstructor(), 
            4, 
            ".\\Codigo\\src\\main\\data\\indiceIDCategoria.hash_d.db", 
            ".\\Codigo\\src\\main\\data\\indiceIDCategoria.hash_c.db"
        );
    } // end ArquivoTarefa ( )

    @Override
    public int create ( Tarefa t ) throws Exception 
    {
        int id = super.create(t);
        indiceIndiretoIDCategoria.create(new ParIDCategoriaIDTarefa(t.getIdCategoria(), id));
        return id;
    } // end create ( )

    public Tarefa read ( int idCategoria ) throws Exception 
    {
        ParIDCategoriaIDTarefa picit = indiceIndiretoIDCategoria.read(ParIDCategoriaIDTarefa.hash(idCategoria));
        if( picit == null ) {
            return null;
        } // end if
        return read(picit.getIDTarefa());
    } // end read ( )
    
    public boolean delete ( int idCategoria ) throws Exception 
    {
        boolean result = false;
        ParIDCategoriaIDTarefa picit = indiceIndiretoIDCategoria.read(ParIDCategoriaIDTarefa.hash(idCategoria));
        if( picit != null ) 
        {
            if( delete(picit.getIDTarefa()) ) {
                result = indiceIndiretoIDCategoria.delete(ParIDCategoriaIDTarefa.hash(idCategoria));
            } // end if
        } // end if
        return result;
    } // end delete ( )

    @Override
    public boolean update ( Tarefa novaTarefa ) throws Exception 
    {
        boolean result = false;
        Tarefa tarefaAntiga = read( novaTarefa.getIdCategoria() );
        if( super.update(novaTarefa) ) 
        {
            if( novaTarefa.getIdCategoria() != tarefaAntiga.getIdCategoria() ) 
            {
                indiceIndiretoIDCategoria.delete(ParIDCategoriaIDTarefa.hash(tarefaAntiga.getIdCategoria()));
                indiceIndiretoIDCategoria.create(new ParIDCategoriaIDTarefa(novaTarefa.getIdCategoria(), novaTarefa.getId()));
            } // end if
            result = true;
        } // end if
        return result;
    } // end update ( )

} // end class ArquivoTarefa
