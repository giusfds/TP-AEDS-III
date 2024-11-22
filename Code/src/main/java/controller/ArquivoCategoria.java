package controller;

import java.util.ArrayList;

import model.Categoria;

/**
 *  Classe ArquivoCategoria
 *  
 *  <p>Classe que representa um arquivo de categorias.</p>
 *  <p>Implementa a interface RegistroArvoreBMais.</p>
 *  <p>Extende a classe Arquivo.</p>
 *  
 *  @see Arquivo
 *  @see Categoria
 *  @see ParNomeIDCategoria
 *  @see ArvoreBMais
 *  @see RegistroArvoreBMais
 */
public class ArquivoCategoria extends Arquivo<Categoria>
{
    ArvoreBMais<ParNomeIDCategoria> arvore;

    public ArquivoCategoria( ) throws Exception 
    {
        super( "Categorias.db", Categoria.class.getConstructor() );
        arvore = new ArvoreBMais<>
        ( 
            ParNomeIDCategoria.class.getConstructor(),
            5, 
            ".\\Code\\src\\main\\data\\Categorias.db.arvore.idx" 
        );
    } // ArquivoCategoria ( )

    @Override
    public int create( Categoria categoria ) throws Exception 
    {
        int id = super.create( categoria );
        try
        {
            arvore.create( new ParNomeIDCategoria( categoria.getNome(), categoria.getId() ) );
        } catch( Exception e ) {
            System.out.print( "Erro create ArqCategoria" );
        } // try-catch
        return id;
    } // create ( )

    @Override
    public Categoria read( int id ) throws Exception 
    {
        Categoria categoria = super.read( id );
        return categoria;
    } // read ( )

    public ArrayList<Categoria> readAll( int id ) throws Exception
    {
        ArrayList<ParNomeIDCategoria> listaNomeId = null;
        ArrayList<Categoria> categorias = new ArrayList<>( );
        try
        {
            listaNomeId = arvore.read( new ParNomeIDCategoria("", id) );
            for( ParNomeIDCategoria parNomeId : listaNomeId ) 
            {
                categorias.add( super.read( parNomeId.getIDCategoria() ) );
            } // for
        } catch( Exception e ) {
            System.out.print( e.getMessage( ) );
        } // try-catch
        return categorias;
    } // readAll ( )

    @Override
    public boolean update( Categoria novaCategoria ) throws Exception 
    {
        boolean result = false;
        Categoria categoriaAntiga = super.read( novaCategoria.getId() );
        if( super.update( novaCategoria ) )
        {
            if( novaCategoria.getId() != categoriaAntiga.getId() )
            {
                arvore.delete( new ParNomeIDCategoria( categoriaAntiga.getNome(), categoriaAntiga.getId() ) );
                arvore.create( new ParNomeIDCategoria( novaCategoria.getNome(), novaCategoria.getId() ) );
            } // if
        } // if
        return result;
    } // update ( )

    @Override
    public boolean delete( int id ) throws Exception 
    {
        boolean result = false;
        Categoria categoria = super.read( id );
        if( super.delete( id ) )
        {
            arvore.delete( new ParNomeIDCategoria( categoria.getNome(), categoria.getId() ) );
            result = true;
        } // if
        return result;
    } // delete ( )

} // ArquivoCategoria
