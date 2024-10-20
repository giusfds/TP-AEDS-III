package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.IO;
import model.Categoria;
import model.Tarefa;

import java.io.IOException;

/**
 * ArquivoTarefa: Classe que representa um arquivo de tarefas.
 */
public class ArquivoTarefa extends Arquivo<Tarefa> {
    ArvoreBMais<ParIDCategoriaIDTarefa> indiceIndiretoIDCategoria;

    public ArquivoTarefa() throws Exception {
        super("Tarefas.db", Tarefa.class.getConstructor());
        indiceIndiretoIDCategoria = new ArvoreBMais<>(
                ParIDCategoriaIDTarefa.class.getConstructor(),
                5,
                ".\\Code\\src\\main\\data\\indiceIndiretoIDCategoria.bptree.db");
    } // end ArquivoTarefa ( )

    @Override
    public int create(Tarefa obj) throws Exception {
        int id = super.create(obj);
        indiceIndiretoIDCategoria.create(new ParIDCategoriaIDTarefa(obj.getIdCategoria(), id));
        return id;
    } // end create ( )

    @Override
    public Tarefa read(int idCategoria) throws Exception {
        ArrayList<ParIDCategoriaIDTarefa> picit = indiceIndiretoIDCategoria
                .read(new ParIDCategoriaIDTarefa(idCategoria, -1));
        return super.read(picit.get(0).getIDTarefa());
    } // end read ( )

    // readall
    public List<Tarefa> readAll() throws Exception {
        List<Tarefa> tarefas = new ArrayList<>();

        arquivo.seek(TAM_CABECALHO);
        byte lapide = ' ';
        short tam = 0;
        byte[] b = null;

        Tarefa c = null;

        // Lê até o final do arquivo
        while (arquivo.getFilePointer() < arquivo.length()) {
            lapide = arquivo.readByte();
            tam = arquivo.readShort();
            b = new byte[tam];
            arquivo.read(b);

            if (lapide != '*') {
                c = new Tarefa();
                c.fromByteArray(b);
                tarefas.add(c);
            } // end if
        } // end while

        return (tarefas);
    } // end readAll ( )

    // criar metodo isEmpty
    public boolean isEmpty() throws IOException {
        return super.isEmpty();
    } // end isEmpty ( )

    @Override
    public boolean update(Tarefa novaTarefa) throws Exception {
        boolean result = false;
        Tarefa tarefaAntiga = super.read(novaTarefa.getIdCategoria());
        if (super.update(novaTarefa)) {
            if (novaTarefa.getIdCategoria() != tarefaAntiga.getIdCategoria()) {
                indiceIndiretoIDCategoria
                        .delete(new ParIDCategoriaIDTarefa(tarefaAntiga.getIdCategoria(), tarefaAntiga.getId()));
                indiceIndiretoIDCategoria
                        .create(new ParIDCategoriaIDTarefa(novaTarefa.getIdCategoria(), novaTarefa.getId()));
            } // end if
            result = true;
        } // end if
        return result;
    } // end update ( )

    // criar metodo update com 2 parametros
    public boolean update(Tarefa novaTarefa, int id) throws Exception {
        boolean result = false;
        Tarefa tarefaAntiga = super.read(id);
        if (super.update(novaTarefa)) {
            if (novaTarefa.getIdCategoria() != tarefaAntiga.getIdCategoria()) {
                indiceIndiretoIDCategoria
                        .delete(new ParIDCategoriaIDTarefa(tarefaAntiga.getIdCategoria(), tarefaAntiga.getId()));
                indiceIndiretoIDCategoria
                        .create(new ParIDCategoriaIDTarefa(novaTarefa.getIdCategoria(), novaTarefa.getId()));
            } // end if
            result = true;
        } // end if
        return result;
    } // end update ( )

} // end class ArquivoTarefa
