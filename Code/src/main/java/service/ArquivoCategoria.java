package service;

import java.util.ArrayList;

import model.Categoria;

/**
 *  ArquivoCategoria: Classe que representa um arquivo de categorias.
 */
public class ArquivoCategoria extends Arquivo<Categoria>
{
    Arquivo<Categoria> arqTarefa;
    ArvoreBMais<ParIDCategoriaNome> indiceIndiretoNome;

    public ArquivoCategoria ( ) throws Exception 
    {
        super( "Categorias.db", Categoria.class.getConstructor() );
        indiceIndiretoNome = new ArvoreBMais<>
        ( 
            ParIDCategoriaNome.class.getConstructor(),
            5, 
            ".\\Code\\src\\main\\data\\indiceIndiretoNome.bptree.db" 
        );
    } // end ArquivoCategoria ( )

    @Override
    public int create ( Categoria obj ) throws Exception 
    {
        int id = super.create(obj);
        indiceIndiretoNome.create( new ParIDCategoriaNome(id, obj.getNome()) );
        return id;
    } // end create ( )

    public Categoria read ( String nome ) throws Exception 
    {
        ArrayList<ParIDCategoriaNome> picn = indiceIndiretoNome.read( new ParIDCategoriaNome(-1, nome) );
        return super.read(picn.get(0).getIDCategoria());
        
    } // end read ( )
    
    public boolean delete ( int nome ) throws Exception 
    {
        boolean result = false;
        Categoria obj = super.read(nome);
        if( obj != null ) 
        {
            if( indiceIndiretoNome.delete( new ParIDCategoriaNome(obj.getId(), obj.getNome()) ) ) {
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
                if( indiceIndiretoNome.delete( new ParIDCategoriaNome(novaCategoria.getId(), categoriaAntiga.getNome()) ) ) 
                {
                    indiceIndiretoNome.create( new ParIDCategoriaNome(novaCategoria.getId(), novaCategoria.getNome()) );
                } // end if
                result = true;
            } // end if
        } // end if
        return result;
    } // end update ( )

} // end class ArquivoCategoria
