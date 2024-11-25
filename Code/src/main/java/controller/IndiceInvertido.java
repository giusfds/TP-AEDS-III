package controller;

import java.util.ArrayList;

import model.ListaInvertida;
import model.ElementoLista;
import model.StopWords;

import util.IO;

public class IndiceInvertido 
{
    private static StopWords      stopWords;
    private static ListaInvertida listaInvertida;

    public IndiceInvertido( ) 
    {
        try
        {
            stopWords = new StopWords( );
            listaInvertida = new ListaInvertida( 
                4,
                ".\\code\\src\\main\\data\\ListaInvertida.db.dict.idx",
                ".\\code\\src\\main\\data\\ListaInvertida.db.bloc.idx"
            );
        } catch( Exception e ) {
            System.out.println( "Erro ao criar o índice invertido." );
        } // try-catch
    } // IndiceInvertido ( )

    public ArrayList<String> getPalavras( String titulo ) 
    {
        ArrayList<String> palavras = new ArrayList<String>( );
        String[] partes = titulo.split( " " );
        for( int i = 0; i < partes.length; i++ )
        {
            String palavra = IO.strnormalize( partes[i] );
            if( !stopWords.isStopWord( palavra ) )
            {
                palavras.add( palavra );
            } // if
        } // for
        return palavras;
    } // getPalavras ( )

    private static float calcularFrequencia( String palavra, ArrayList<String> palavras ) 
    {
        float frequencia = 0;
        int tamTotal = palavras.size( );
        for( int i = 0; i < tamTotal; i++ )
        {
            if( palavra.equals( palavras.get( i ) ) ) {
                frequencia++;
            } // if
        } // for
        return ( (float)frequencia / tamTotal );
    } // calcularFrequencia ( )

    public void insert( String titulo, int id ) 
    {
        try
        {
            ArrayList<String> palavras = getPalavras( titulo );
            for( String palavra : palavras )
            {
                float frequencia = calcularFrequencia( palavra, palavras );
                // System.out.println( "Palavra: " + palavra + " - Frequência: " + frequencia );
                listaInvertida.create( palavra, new ElementoLista( id, frequencia ) );
            } // for
        } catch( Exception e ) {
            System.out.println( "Erro ao criar o índice invertido." );
        } // try-catch
    } // insert ( )

    public boolean update( String tituloAntigo, String tituloNovo, int id ) 
    {
        boolean result = false;
        try
        {
            ArrayList<String> palavrasAntigas = getPalavras( tituloAntigo );
            for( String palavra : palavrasAntigas )
            {
                listaInvertida.delete( palavra, id );
            } // for
            this.insert( tituloNovo, id );
            result = true;
        } catch( Exception e ) {
            System.out.println( "Erro ao atualizar o índice invertido." );
        } // try-catch
        return ( result );
    } // update ( )

    public boolean delete( String palavra, int id ) 
    {
        boolean result = false;
        try
        {
            ArrayList<String> palavras = getPalavras( palavra );
            for( String palavraAtual : palavras )
            {
                result = listaInvertida.delete( palavraAtual, id );
            } // for
            result = true;
        } catch( Exception e ) {
            System.out.println( "Erro ao deletar o índice invertido." );
        } // try-catch
        return ( result );
    } // delete ( )

} // IndiceInvertido
