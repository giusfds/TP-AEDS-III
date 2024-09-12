import java.io.File;
import java.time.LocalDate;

public class IO {

    public static void main(String[] args) {
        Arquivo<Tarefa> arqTarefas;
        Tarefa c1 = new Tarefa("Aniversario Breno", LocalDate.of(2004, 9, 15), LocalDate.of(2004, 9, 16), (byte) 0,
                (byte) 1);
        Tarefa c2 = new Tarefa("teste1", LocalDate.of(2004, 9, 15), LocalDate.of(2004, 9, 16), (byte) 0,
                (byte) 1);
        Tarefa c3 = new Tarefa("teste3", LocalDate.of(2004, 9, 15), LocalDate.of(2004, 9, 16), (byte) 0,
                (byte) 1);

        try {

            // apaga o arquivo atual
            File f = new File(".\\dados\\Tarefas.db");
            File f2 = new File(".\\dados\\Tarefas.db.c.idx");
            File f3 = new File(".\\dados\\Tarefas.db.d.idx");
            f.delete();
            f2.delete();
            f3.delete();

            arqTarefas = new Arquivo<>("Tarefas.db", Tarefa.class.getConstructor());
            arqTarefas.create(c1);
            arqTarefas.create(c2);
            arqTarefas.create(c3);

            Tarefa c = arqTarefas.read(3);
            if (c != null)
                System.out.println(c);
            else
                System.out.println("\nTarefa não encontrado!");

            c = arqTarefas.read(1);
            if (c != null)
                System.out.println(c);
            else
                System.out.println("\nTarefa não encontrado!");

            c2.nome = "caio";
            arqTarefas.update(c2);
            c = arqTarefas.read(2);
            if (c != null)
                System.out.println(c);
            else
                System.out.println("\nTarefa não encontrado!");

            arqTarefas.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}