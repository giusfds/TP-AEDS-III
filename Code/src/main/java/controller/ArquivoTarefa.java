package controller;

import java.util.ArrayList;

import model.Tarefa;

/**
 *  Classe ArquivoTarefa
 * 
 *  <p>Classe que representa um arquivo de tarefas.</p>
 *  <p>Implementa a interface RegistroArvoreBMais.</p>
 *  <p>Extende a classe Arquivo.</p>
 *  
 *  @see Arquivo
 *  @see Tarefa
 *  @see ParIDCategoriaIDTarefa
 */
public class ArquivoTarefa extends Arquivo<Tarefa> 
{
    ArvoreBMais<ParIDCategoriaIDTarefa> arvore;

    public ArquivoTarefa( ) throws Exception 
    {
        super( "Tarefas.db", Tarefa.class.getConstructor() );
        arvore = new ArvoreBMais<> ( 
            ParIDCategoriaIDTarefa.class.getConstructor(),
            5, 
            ".\\Code\\src\\main\\data\\Tarefas.db.arvore.idx" 
        );
    } // ArquivoTarefa ( )

    @Override
    public int create( Tarefa tarefa ) throws Exception 
    {
        int id = super.create( tarefa );
        tarefa.setId( id );
        try {
            arvore.create( new ParIDCategoriaIDTarefa(tarefa.getIdCategoria(), tarefa.getId()) );
        } catch( Exception e ) {
            System.out.print( "Erro create ArqTarefa" );
        } // try-catch
        return id;
    } // create ( )

    @Override
    public Tarefa read( int idCategoria ) throws Exception 
    {
        ArrayList<ParIDCategoriaIDTarefa> parIdId = arvore.read( new ParIDCategoriaIDTarefa(idCategoria, -1) );
        return super.read( parIdId.get(0).getIDTarefa() );
    } // end read ( )

    public ArrayList<Tarefa> readAll( int idCategoria ) throws Exception 
    {
        ArrayList<ParIDCategoriaIDTarefa> listaIdId = null;
        ArrayList<Tarefa> tarefas = new ArrayList<>( );
        try
        {
            listaIdId = arvore.read( new ParIDCategoriaIDTarefa(idCategoria, -1) );
            for( ParIDCategoriaIDTarefa parIdId : listaIdId ) 
            {
                tarefas.add( super.read( parIdId.getIDTarefa() ) );
            } // for
        } catch( Exception e ) {
            System.out.print( e.getMessage() );
        } // try-catch
        return tarefas;
    } // read ( )

    @Override
    public boolean update ( Tarefa novaTarefa ) throws Exception 
    {
        boolean result = false;
        Tarefa tarefaAntiga = super.read( novaTarefa.getId( ) );
        if( super.update(novaTarefa) ) 
        {
            if( novaTarefa.getId() != tarefaAntiga.getId() ) 
            {
                arvore.delete( new ParIDCategoriaIDTarefa(tarefaAntiga.getIdCategoria(), tarefaAntiga.getId()) );
                arvore.create( new ParIDCategoriaIDTarefa(novaTarefa.getIdCategoria(), novaTarefa.getId()) );
            } // if
            result = true;
        } // if
        return result;
    } // update ( )

    @Override
    public boolean delete( int id ) throws Exception 
    {
        boolean result = false;
        Tarefa tarefa = super.read( id );
        if( super.delete( id ) ) 
        {
            arvore.delete( new ParIDCategoriaIDTarefa(tarefa.getIdCategoria(), id) );
            result = true;
        } // if
        return result;
    } // delete ( )

} // ArquivoTarefa
