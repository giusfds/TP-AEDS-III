package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import model.Categoria;
import model.Tarefa;
import service.ArquivoCategoria;
import service.ArquivoTarefa;

/**
 * MenuTarefas: Classe de interação com o usuário para manipulação de tarefas.
 */
public class MenuTarefas extends IO {
    private static ArquivoTarefa arqTarefas;
    private static ArquivoCategoria arqCategorias;

    public MenuTarefas() throws Exception {
        arqTarefas = new ArquivoTarefa();
        arqCategorias = new ArquivoCategoria();
    } // end MenuTarefas ( )

    public void menu() {
        try {
            int opcao = 0;
            do {
                opcoes_menu();
                opcao = ler_opcao();
                executar_opcao(opcao);
            } while (opcao != 0); // end do-while
        } catch (Exception e) {
            e.printStackTrace();
        } // end try-catch
    } // end menu ( )

    protected static void opcoes_menu() {
        System.out.println(RESET + "\nAEDs-III 1.0           ");
        System.out.println("-------------------------");
        System.out.println("> Início > Tarefas       ");
        System.out.println("1 - Buscar               ");
        System.out.println("2 - Incluir              ");
        System.out.println("3 - Alterar              ");
        System.out.println("4 - Excluir              ");
        System.out.println("0 - Voltar               ");
        System.out.print("Opção: ");
    } // end opcoes_menu ( )

    protected static void executar_opcao(int opcao) {
        switch (opcao) {
            case 0:
                break;
            case 1:
                buscarTarefa();
                break;
            case 2:
                incluirTarefa();
                break;
            case 3:
                alterarTarefa();
                break;
            case 4:
                excluirTarefa();
                break;

            default:
                System.out.println(RED + "Opção inválida!" + RESET);
                break;
        } // end switch
    } // end executar_opcao ( )

    public static LocalDate formatarData(String dataEmString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;
        try {
            data = LocalDate.parse(dataEmString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(RED + "\nFormato de data inválido. Por favor, use o formato dd/MM/yyyy." + RESET);
        }
        return data;
    } // end formatarData ( )

    private static void listar_status() {
        System.out.println("\nEscolha um status:");
        System.out.println("1 - Pendente        ");
        System.out.println("2 - Em Andamento    ");
        System.out.println("3 - Concluída       ");
        System.out.println("4 - Cancelada       ");
        System.out.println("5 - Atrasada        ");
        System.out.print("Status: ");
    } // end listar_status ( )

    private static void listar_prioridades() {
        System.out.println("\nEscolha uma prioridade:");
        System.out.println("0 - Muito Baixa          ");
        System.out.println("1 - Baixa                ");
        System.out.println("2 - Média                ");
        System.out.println("3 - Alta                 ");
        System.out.println("4 - Urgente              ");
        System.out.print("Opção: ");
    } // end listar_prioridades ( )

    private static void listar_categorias() {
        try {
            System.out.println("\nCategorias:");
            // listar todas as categorias
            arqCategorias.readAll().forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
        } catch (Exception e) {
            // Printar o erro
            System.err.println("Erro ao listar categorias: " + e.getMessage());
            e.printStackTrace(); // Isso printa a stack trace do erro, útil para depuração
        }
    } // end listar_categorias()

    public static Tarefa ler_Tarefa() {
        Tarefa tarefa = null;
        try {
            System.out.print("Nome: ");
            String nome = console.nextLine();

            System.out.print("\nData de Criação: ");
            String dc1 = console.nextLine();
            LocalDate dataCriacao = formatarData(dc1);

            System.out.print("\nData de Conclusão: ");
            String dc2 = console.nextLine();
            LocalDate dataConclusao = formatarData(dc2);

            listar_status();
            byte status = Byte.parseByte(console.nextLine());

            listar_prioridades();
            byte prioridade = Byte.parseByte(console.nextLine());

            listar_categorias();
            int idCategoria = Integer.parseInt(console.nextLine());

            tarefa = new Tarefa(nome, dataCriacao, dataConclusao, status, prioridade, idCategoria);
        } catch (Exception e) {
            System.out.println(RED + "\nErro ao ler tarefa!" + RESET);
        } // end try-catch
        return tarefa;
    } // end ler_Tarefa ( )

    public static void incluirTarefa() {
        System.out.println("\nIncluir Tarefa:");

        try {
            Tarefa novaTarefa = ler_Tarefa();
            if (novaTarefa != null) {
                System.out.println("\nConfirma inclusão da tarefa? (S/N)");
                char resp = console.nextLine().charAt(0);
                if (resp == 'S' || resp == 's') {
                    try {
                        arqTarefas.create(novaTarefa);

                        System.out.println(GREEN + "Tarefa incluída com sucesso!" + RESET);
                    } catch (Exception e) {
                        System.out.println(RED + "Erro do sistema. Não foi possível criar a tarefa!" + RESET);
                    } // end try-catch
                } else {
                    System.out.println(RED + "Inclusão cancelada!" + RESET);
                } // end if
            } // end if
        } catch (Exception e) {
            System.out.println(RED + "Erro ao incluir tarefa!" + RESET);
        } // end try-catch
    } // end incluirTarefa ( )

    public static boolean buscarTarefa() {
        boolean result = false;
        System.out.println("\nBuscar Tarefa:");
        return result;
    } // end buscarTarefa ( )

    // listarTarefas

    private static void listarTarefas(List<Tarefa> lista) {
        if (lista != null) {
            System.out.println("\nLista de tarefas:");
            int tam = lista.size();
            for (int i = 0; i < tam; i++) {
                System.out.println((i + 1) + ": " + lista.get(i).getNome());
            } // end for
        } // end if
    } // end listarTarefas ( )

    // criar de fato o modo alterarTarefa, criar ele de verdade, sem ser só um print
    public static void alterarTarefa() {
        System.out.println("\n> Alterar Tarefa:");

        try {
            List<Tarefa> lista = arqTarefas.readAll();

            if (lista.isEmpty()) {
                System.out.println(RED + "Não há tarefa cadastrada." + RESET);
            } else {
                listarTarefas(lista);

                System.out.print("Nome da Tarefa: ");
                String nome = console.nextLine();

                if (nome.length() > 0) {
                    Tarefa tarefaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size();
                    for (int i = 0; i < tam && !encontrada; i++) {
                        if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                            tarefaEncontrada = lista.get(i);
                            encontrada = true;
                        } // end if
                    } // end for

                    if (tarefaEncontrada != null) {
                        Tarefa novaTarefa = ler_Tarefa();

                        if (novaTarefa != null && novaTarefa.getNome().length() > 0) {
                            novaTarefa.setId(tarefaEncontrada.getId());
                            arqTarefas.update(novaTarefa);
                            System.out.println(GREEN + "Tarefa alterada com sucesso." + RESET);
                        } else {
                            System.out.println(RED + "Operação cancelada!" + RESET);
                        } // end if
                    } else {
                        System.out.println(RED + "Tarefa não encontrada." + RESET);
                    } // end if
                } else {
                    System.out.println(RED + "Operação cancelada!" + RESET);
                } // end if
            } // end if

        } catch (Exception e) {
            System.out.println(RED + "Erro no sistema. Não foi possível alterar a Tarefa!" + RESET);
        } // end try
    } // end alterarTarefa

    public static void excluirTarefa() {
        System.out.println("\n> Excluir Tarefa:");

        try {
            List<Tarefa> lista = arqTarefas.readAll();

            if (lista.isEmpty()) {
                System.out.println(RED + "Não há tarefa cadastrada." + RESET);
            } else {
                System.out.println("\nLista de tarefas:");
                listarTarefas(lista);

                System.out.print("Nome da tarefa: ");
                String nome = console.nextLine();

                if (nome.length() > 0) {
                    Tarefa tarefaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size();
                    for (int i = 0; i < tam && !encontrada; i++) {
                        if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                            tarefaEncontrada = lista.get(i);
                            encontrada = true;
                        } // end if
                    } // end for

                    if (tarefaEncontrada != null) {
                        System.out.print("\nTarefa:");
                        System.out.println(tarefaEncontrada);

                        System.out.println("\nConfirma a exclusão da tarefa? (S/N)");
                        char resp = console.nextLine().charAt(0);

                        if (resp == 'S' || resp == 's') {
                            boolean sucesso = arqTarefas.delete(tarefaEncontrada.getId());

                            if (sucesso) {
                                System.out.println(GREEN + "Tarefa excluída com sucesso." + RESET);
                            } else {
                                System.out.println(RED + "Erro: Não foi possível excluir a tarefa." + RESET);
                            } // end if
                        } // end if
                    } else {
                        System.out.println(RED + "Tarefa não encontrada." + RESET);
                    } // end if
                } else {
                    System.out.println(RED + "Operação cancelada!" + RESET);
                } // end if
            } // end if

        } catch (Exception e) {
            // printar excecao
            System.out.println(e.getMessage());
            System.out.println(RED + "Erro no sistema. Não foi possível excluir a tarefa!" + RESET);
        } // end try
    } // end excluirTarefa

} // end class MenuTarefas
