package service;

import java.util.ArrayList;

import model.Categoria;

/**
 *  ArquivoCategoria: Classe que representa um arquivo de categorias.
 */
public class ArquivoCategoria extends Arquivo<Categoria>
{
    Arquivo<Categoria> arqTarefa;
    ArvoreBMais<ParNomeIDCategoria> indiceIndiretoNome;

    public ArquivoCategoria ( ) throws Exception 
    {
        super( "Categorias.db", Categoria.class.getConstructor() );
        indiceIndiretoNome = new ArvoreBMais<>
        ( 
            ParNomeIDCategoria.class.getConstructor(),
            5, 
            ".\\Code\\src\\main\\data\\indiceIndiretoNome.bptree.db" 
        );
    } // end ArquivoCategoria ( )


    public ArrayList<Categoria> readAll( ) throws Exception 
    {
        arquivo.seek(TAM_CABECALHO);
        byte lapide;
        short tam;
        byte[] b;

        Categoria c;
        ArrayList<Categoria> categorias = new ArrayList<>();

        // Lê até o final do arquivo
        while( arquivo.getFilePointer() < arquivo.length() ) 
        {
            lapide = arquivo.readByte();
            tam = arquivo.readShort();
            b = new byte[tam];
            arquivo.read(b);

            if( lapide != '*' ) 
            {
                c = new Categoria();
                c.fromByteArray(b);
                categorias.add(c);
            } // end if
        } // end while

        // Collections.sort(Comparator.comparing(Categoria::getNome));

        return ( categorias );  
    } // end readAll ( )

} // end class ArquivoCategoria
