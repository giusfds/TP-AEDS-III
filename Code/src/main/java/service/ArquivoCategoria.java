package service;

import java.util.ArrayList;

import model.Categoria;

/**
 * ArquivoCategoria: Classe que representa um arquivo de categorias.
 */
public class ArquivoCategoria extends Arquivo<Categoria> {
    Arquivo<Categoria> arqTarefa;
    ArvoreBMais<ParIDCategoriaNome> indiceIndiretoNome;

<<<<<<< Updated upstream
    public ArquivoCategoria ( ) throws Exception 
    {
        super( "Categorias.db", Categoria.class.getConstructor() );
        indiceIndiretoNome = new ArvoreBMais<>
        ( 
            ParIDCategoriaNome.class.getConstructor(),
            5, 
            ".\\Code\\src\\main\\data\\indiceIndiretoNome.bptree.db" 
        );
=======
    public ArquivoCategoria() throws Exception {
        super("Categorias.db", Categoria.class.getConstructor());
        indiceIndiretoNome = new ArvoreBMais<>(
                ParNomeIDCategoria.class.getConstructor(),
                5,
                ".\\Code\\src\\main\\data\\indiceIndiretoNome.bptree.db");
>>>>>>> Stashed changes
    } // end ArquivoCategoria ( )

    @Override
    public int create(Categoria obj) throws Exception {
        int id = super.create(obj);
<<<<<<< Updated upstream
        indiceIndiretoNome.create( new ParIDCategoriaNome(id, obj.getNome()) );
        return id;
    } // end create ( )

    public Categoria read ( String nome ) throws Exception 
    {
        ArrayList<ParIDCategoriaNome> picn = indiceIndiretoNome.read( new ParIDCategoriaNome(-1, nome) );
=======
        indiceIndiretoNome.create(new ParNomeIDCategoria(obj.getNome(), id));
        return id;
    } // end create ( )

    public Categoria read(String nome) throws Exception {
        ArrayList<ParNomeIDCategoria> picn = indiceIndiretoNome.read(new ParNomeIDCategoria(nome, -1));
>>>>>>> Stashed changes
        return super.read(picn.get(0).getIDCategoria());
        
    } // end read ( )

    // metodo para listar todas as categorias
    public ArrayList<Categoria> readAll() throws Exception {
        ArrayList<Categoria> categorias = new ArrayList<>();
        for (int i = 0; i <= super.readNextId(); i++) {
            Categoria categoria = super.read(i);
            if (categoria != null) {
                categorias.add(categoria);
            } // end if
        } // end for
        return categorias;
    } // end readAll ( )

    public boolean delete(int nome) throws Exception {
        boolean result = false;
        Categoria obj = super.read(nome);
<<<<<<< Updated upstream
        if( obj != null ) 
        {
            if( indiceIndiretoNome.delete( new ParIDCategoriaNome(obj.getId(), obj.getNome()) ) ) {
=======
        if (obj != null) {
            if (indiceIndiretoNome.delete(new ParNomeIDCategoria(obj.getNome(), obj.getId()))) {
>>>>>>> Stashed changes
                result = super.delete(obj.getId());
            } // end if
        } // end if
        return result;
    } // end delete ( )

    @Override
    public boolean update(Categoria novaCategoria) throws Exception {
        boolean result = false;
<<<<<<< Updated upstream
        Categoria categoriaAntiga = super.read( novaCategoria.getId() );
        if( super.update(novaCategoria) ) 
        {
            if( novaCategoria.getNome() != categoriaAntiga.getNome() ) 
            {
                if( indiceIndiretoNome.delete( new ParIDCategoriaNome(novaCategoria.getId(), categoriaAntiga.getNome()) ) ) 
                {
                    indiceIndiretoNome.create( new ParIDCategoriaNome(novaCategoria.getId(), novaCategoria.getNome()) );
=======
        Categoria categoriaAntiga = super.read(novaCategoria.getId());
        if (super.update(novaCategoria)) {
            if (novaCategoria.getNome() != categoriaAntiga.getNome()) {
                if (indiceIndiretoNome
                        .delete(new ParNomeIDCategoria(categoriaAntiga.getNome(), categoriaAntiga.getId()))) {
                    indiceIndiretoNome.create(new ParNomeIDCategoria(novaCategoria.getNome(), novaCategoria.getId()));
>>>>>>> Stashed changes
                } // end if
                result = true;
            } // end if
        } // end if
        return result;
    } // end update ( )

} // end class ArquivoCategoria
