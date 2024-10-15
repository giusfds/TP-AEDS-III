package service;

import java.util.ArrayList;

import model.Tarefa;

/**
 *  ArquivoTarefa: Classe que representa um arquivo de tarefas.
 */
public class ArquivoTarefa extends Arquivo<Tarefa>
{
    ArvoreBMais<ParIDCategoriaIDTarefa> indiceIndiretoIDCategoria;

    public ArquivoTarefa ( ) throws Exception 
    {
        super( "Tarefas.db", Tarefa.class.getConstructor() );
        indiceIndiretoIDCategoria = new ArvoreBMais<>
        ( 
            ParIDCategoriaIDTarefa.class.getConstructor(),
            5, 
            ".\\Code\\src\\main\\data\\indiceIndiretoIDCategoria.bptree.db" 
        );
    } // end ArquivoTarefa ( )

    @Override
    public int create ( Tarefa obj ) throws Exception 
    {
        int id = super.create(obj);
        indiceIndiretoIDCategoria.create( new ParIDCategoriaIDTarefa(obj.getIdCategoria(), id) );
        return id;
    } // end create ( )

    @Override
    public Tarefa read ( int idCategoria ) throws Exception 
    {
        ArrayList<ParIDCategoriaIDTarefa> picit = indiceIndiretoIDCategoria.read( new ParIDCategoriaIDTarefa(idCategoria, -1) );
        return super.read(picit.get(0).getIDTarefa());
    } // end read ( )
    
    public boolean delete ( int idCategoria ) throws Exception 
    {
        boolean result = false;
        Tarefa obj = super.read(idCategoria);
        if( obj != null ) 
        {
            if( indiceIndiretoIDCategoria.delete( new ParIDCategoriaIDTarefa(obj.getIdCategoria(), obj.getId()) ) ) {
                result = super.delete(obj.getId());
            } // end if
        } // end if
        return result;
    } // end delete ( )

    @Override
    public boolean update ( Tarefa novaTarefa ) throws Exception 
    {
        boolean result = false;
        Tarefa tarefaAntiga = super.read( novaTarefa.getIdCategoria() );
        if( super.update(novaTarefa) ) 
        {
            if( novaTarefa.getIdCategoria() != tarefaAntiga.getIdCategoria() ) 
            {
                indiceIndiretoIDCategoria.delete( new ParIDCategoriaIDTarefa(tarefaAntiga.getIdCategoria(), tarefaAntiga.getId()) );
                indiceIndiretoIDCategoria.create( new ParIDCategoriaIDTarefa(novaTarefa.getIdCategoria(), novaTarefa.getId()) );
            } // end if
            result = true;
        } // end if
        return result;
    } // end update ( )

} // end class ArquivoTarefa
