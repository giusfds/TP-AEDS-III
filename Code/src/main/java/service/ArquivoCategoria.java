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

    @Override
    public int create ( Categoria obj ) throws Exception 
    {
        int id = super.create(obj);
        indiceIndiretoNome.create( new ParNomeIDCategoria(obj.getNome(), id) );
        return id;
    } // end create ( )

    public Categoria read ( String nome ) throws Exception 
    {
        ArrayList<ParNomeIDCategoria> picn = indiceIndiretoNome.read( new ParNomeIDCategoria(nome, -1) );
        return super.read(picn.get(0).getIDCategoria());
    } // end read ( )

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

    public boolean delete ( int nome ) throws Exception 
    {
        boolean result = false;
        Categoria obj = super.read(nome);
        if( obj != null ) 
        {
            if( indiceIndiretoNome.delete( new ParNomeIDCategoria(obj.getNome(), obj.getId()) ) ) {
                result = super.delete(obj.getId());
            } // end if
        } // end if
        return result;
    } // end delete ( )

    @Override
    public boolean update ( Categoria novaCategoria ) throws Exception 
    {
        boolean result = false;
        Categoria categoriaAntiga = super.read( novaCategoria.getId() );
        if( super.update(novaCategoria) ) 
        {
            if( novaCategoria.getNome() != categoriaAntiga.getNome() ) 
            {
                if( indiceIndiretoNome.delete( new ParNomeIDCategoria(categoriaAntiga.getNome(), categoriaAntiga.getId()) ) ) 
                {
                    indiceIndiretoNome.create( new ParNomeIDCategoria(novaCategoria.getNome(), novaCategoria.getId()) );
                } // end if
                result = true;
            } // end if
        } // end if
        return result;
    } // end update ( )

} // end class ArquivoCategoria
