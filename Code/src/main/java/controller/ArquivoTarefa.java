package controller;

import java.util.ArrayList;

import model.ArvoreBMais;
import model.ParIDCategoriaIDTarefa;
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
        arvore = new ArvoreBMais<> 
        ( 
            ParIDCategoriaIDTarefa.class.getConstructor(),
            5, 
            ".\\Code\\src\\main\\data\\Tarefas.db.arvore.idx" 
        );
    } // ArquivoTarefa ( )

    @Override
    public int create( Tarefa tarefa ) throws Exception 
    {
        int id = super.create( tarefa );
        try {
            arvore.create( new ParIDCategoriaIDTarefa( tarefa.getIdCategoria(), tarefa.getId() ) );
        } catch( Exception e ) {
            System.out.print( "Erro ao criar tarefa no índice: " + e.getMessage( ) );
        } // try-catch
        return ( id );
    } // create ( )

    public ArrayList<Tarefa> readAll( ) throws Exception {
        return readAll( -1 );
    } // readAll ( )

    public ArrayList<Tarefa> readAll( int idCategoria ) throws Exception 
    {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try 
        {
            ArrayList<ParIDCategoriaIDTarefa> listaTarefas;
            
            if( idCategoria == -1 ) {
                listaTarefas = arvore.read( new ParIDCategoriaIDTarefa(-1, -1) );
            } else {
                listaTarefas = arvore.read( new ParIDCategoriaIDTarefa(idCategoria, -1) );
            } // if

            for( ParIDCategoriaIDTarefa parIdId : listaTarefas ) {
                tarefas.add( super.read( parIdId.getIDTarefa( ) ) );
            } // for

        } catch( Exception e ) {
            System.out.println("Erro ao ler tarefas: " + e.getMessage());
        } // try-catch
        return ( tarefas );
    } // readAll ( )

    @Override
    public boolean update ( Tarefa novaTarefa ) throws Exception 
    {
        boolean result = false;
        Tarefa tarefaAntiga = super.read( novaTarefa.getId( ) );
        result = super.update( novaTarefa );
        if( result ) 
        {
            try
            {
                if( novaTarefa.getId() != tarefaAntiga.getId() ) 
                {
                    arvore.delete( new ParIDCategoriaIDTarefa( tarefaAntiga.getIdCategoria(), tarefaAntiga.getId() ) );
                    arvore.create( new ParIDCategoriaIDTarefa( novaTarefa.getIdCategoria(), novaTarefa.getId() ) );
                } // if
            } catch( Exception e ) {
                System.out.println( "Erro ao atualizar o índice: " + e.getMessage( ) );
            } // try-catch
        } // if
        return ( result );
    } // update ( )

    @Override
    public boolean delete( int id ) throws Exception 
    {
        boolean result = false;
        Tarefa tarefa = super.read( id );
        if( super.delete( id ) ) 
        {
            try 
            {
                arvore.delete( new ParIDCategoriaIDTarefa( tarefa.getIdCategoria(), id ) );
                result = true;
            } catch( Exception e ) {
                System.out.println( "Erro ao deletar tarefa do índice: " + e.getMessage( ) );
            } // try-catch
        } // if
        return ( result );
    } // delete ( )

} // ArquivoTarefa
