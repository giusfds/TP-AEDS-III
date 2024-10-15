package service;

import model.Categoria;

/**
 *  ArquivoCategoria: Classe que representa um arquivo de categorias.
 */
public class ArquivoCategoria extends Arquivo<Categoria>
{
    Arquivo<Categoria> arqTarefa;
    HashExtensivel<ParIDCategoriaNome> indiceIndiretoNome;

    public ArquivoCategoria ( ) throws Exception 
    {
        super( "categorias", Categoria.class.getConstructor() );
        indiceIndiretoNome = new HashExtensivel<>
        (
            ParIDCategoriaNome.class.getConstructor(), 
            4, 
            ".\\Codigo\\src\\main\\data\\indiceNome.hash_d.db", 
            ".\\Codigo\\src\\main\\data\\indiceNome.hash_c.db"
        );
    } // end ArquivoCategoria ( )

    /*  @Override
    public int create ( Categoria t ) throws Exception 
    {
        int id = super.create(t);
        indiceIndiretoNome.create(new ParIDCategoriaNome(t.getNome(), id));
        return id;
    } // end create ( )

    public Categoria read ( String nome ) throws Exception 
    {
        ParIDCategoriaNome picit = indiceIndiretoNome.read(ParIDCategoriaNome.hash(nome));
        if( picit == null ) {
            return null;
        } // end if
        return read(picit.getIDTarefa());
    } // end read ( )
    
    public boolean delete ( int nome ) throws Exception 
    {
        boolean result = false;
        ParIDCategoriaNome picit = indiceIndiretoNome.read(ParIDCategoriaNome.hash(nome));
        if( picit != null ) 
        {
            if( delete(picit.getIDTarefa()) ) {
                result = indiceIndiretoNome.delete(ParIDCategoriaNome.hash(nome));
            } // end if
        } // end if
        return result;
    } // end delete ( )

    @Override
    public boolean update ( Categoria novaTarefa ) throws Exception 
    {
        boolean result = false;
        Categoria tarefaAntiga = read( novaTarefa.getIdCategoria() );
        if( super.update(novaTarefa) ) 
        {
            if( novaTarefa.getIdCategoria() != tarefaAntiga.getIdCategoria() ) 
            {
                indiceIndiretoNome.delete(ParIDCategoriaNome.hash(tarefaAntiga.getIdCategoria()));
                indiceIndiretoNome.create(new ParIDCategoriaNome(novaTarefa.getIdCategoria(), novaTarefa.getId()));
            } // end if
            result = true;
        } // end if
        return result;
    } // end update ( )
 */
} // end class ArquivoCategoria
