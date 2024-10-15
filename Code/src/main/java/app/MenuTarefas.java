package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import model.Tarefa;
import service.ArquivoCategoria;
import service.ArquivoTarefa;

/**
 * MenuTarefas: Classe de interação com o usuário para manipulação de tarefas.
 */
public class MenuTarefas extends IO
{
    private static ArquivoTarefa arqTarefas;
    private static ArquivoCategoria arqCategorias;

    public MenuTarefas ( ) throws Exception 
    {
        arqTarefas    = new ArquivoTarefa( );
        arqCategorias = new ArquivoCategoria( );
    } // end MenuTarefas ( )

    public void menu ( ) 
    {
        try
        {
            int opcao = 0;
            do 
            {
                opcoes_menu( );
                opcao = ler_opcao( );
                executar_opcao(opcao);
            } while( opcao != 0 ); // end do-while
        } catch ( Exception e ) {
            e.printStackTrace();
        } // end try-catch
    } // end menu ( )

    protected static void opcoes_menu( )
    {
        System.out.println(RESET+"\nAEDs-III 1.0           ");
        System.out.println("-------------------------");
        System.out.println("> Início > Tarefas       ");
        System.out.println("1 - Buscar               ");
        System.out.println("2 - Incluir              ");
        System.out.println("3 - Alterar              ");
        System.out.println("4 - Excluir              ");
        System.out.println("0 - Voltar               ");
        System.out.print  ("Opção: "                  );
    } // end opcoes_menu ( )

    protected static void executar_opcao( int opcao )
    {
        switch( opcao ) 
        {
            case 0:
                break;
            case 1:
                buscarTarefa( );
                break;
            case 2:
                incluirTarefa( );
                break;
            case 3:
                alterarTarefa( );
                break;
            case 4:
                excluirTarefa( );
                break;
            
            default:
                System.out.println( RED + "Opção inválida!" + RESET );
                break;
        } // end switch
    } // end executar_opcao ( )

    public static LocalDate formatarData ( String dataEmString ) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;
        try {
            data = LocalDate.parse(dataEmString, formatter);
        } catch( DateTimeParseException e ) {
            System.out.println( RED + "\nFormato de data inválido. Por favor, use o formato dd/MM/yyyy." + RESET );
        }
        return data;
    } // end formatarData ( )

    private static void listar_status( )
    {
        System.out.println( "\nStatus:"        );
        System.out.println( "1 - Pendente"     );
        System.out.println( "2 - Em Andamento" );
        System.out.println( "3 - Concluída"    );
        System.out.println( "4 - Cancelada"    );
        System.out.println( "5 - Atrasada"     );
        System.out.print  ("Opção: "           );
    } // end listar_status ( )

    private static void listar_prioridades( )
    {
        System.out.println( "\nPrioridades:" );
        System.out.println( "0 - Muito Baixa");
        System.out.println( "1 - Baixa"     );
        System.out.println( "2 - Média"     );
        System.out.println( "3 - Alta"      );
        System.out.println( "4 - Urgente"   );
        System.out.print  ("Opção: "        );
    } // end listar_prioridades ( )

    private static void listar_categorias( )
    {
        System.out.println( "\nCategorias:" );
        /* FAZER UM METODO PARA LER DO 'arqCategoria' E QUE RETORNA TODAS AS CATEGORIAS EXISTENTES */
        /* System.out.println( "1 - Trabalho"  );
        System.out.println( "2 - Estudo"    );
        System.out.println( "3 - Lazer"     );
        System.out.println( "4 - Saúde"     );
        System.out.println( "5 - Outros"    );
        System.out.print  ("Opção: "        ); */
    } // end listar_categorias ( )

    public static Tarefa ler_Tarefa ( )
    {
        Tarefa tarefa = null;
        try
        {
            System.out.print( "Nome: " );
            String nome = console.nextLine( );

            System.out.print( "\nData de Criação: " );
            String dc1 = console.nextLine( );
            LocalDate dataCriacao = formatarData(dc1);

            System.out.print( "\nData de Conclusão: " );
            String dc2 = console.nextLine( );
            LocalDate dataConclusao = formatarData(dc2);

            listar_status( );
            byte status = Byte.parseByte( console.nextLine( ) );

            listar_prioridades( );
            byte prioridade = Byte.parseByte( console.nextLine( ) );

            // listar_categorias( );
            int idCategoria = Integer.parseInt( console.nextLine( ) );

            tarefa = new Tarefa( nome, dataCriacao, dataConclusao, status, prioridade, idCategoria );
        } catch ( Exception e ) {
            System.out.println( RED + "\nErro ao ler tarefa!" + RESET );
        } // end try-catch
        return tarefa;
    } // end ler_Tarefa ( )

    public static void incluirTarefa( ) 
    {
        System.out.println( "\nIncluir Tarefa:" );

        try
        {
            Tarefa novaTarefa = ler_Tarefa( );
            if ( novaTarefa != null ) 
            {
                System.out.println( "\nConfirma inclusão da tarefa? (S/N)" );
                String confirmacao = console.nextLine( );

                if( confirmacao.equalsIgnoreCase( "S" ) ) 
                {
                    arqTarefas.create( novaTarefa );
                    System.out.println( GREEN + "Tarefa incluída com sucesso!" + RESET );
                } else {
                    System.out.println( RED + "Inclusão cancelada!" + RESET );
                } // end if
            }// end if
            
        } catch ( Exception e ) {
            System.out.println( RED + "Erro ao incluir tarefa!" + RESET );
        } // end try-catch
    } // end incluirTarefa ( )

    public static boolean buscarTarefa( )
    {
        boolean result = false;
        System.out.println( "\nBuscar Tarefa:" );
        return result;
    } // end buscarTarefa ( )

    public static boolean alterarTarefa( )
    {
        boolean result = false;
        System.out.println( "\nAlterar Tarefa:" );
        return result;
    } // end alterarTarefa ( )

    public static boolean excluirTarefa( )
    {
        boolean result = false;
        System.out.println( "\nExcluir Tarefa:" );
        return result;
    } // end excluirTarefa ( )

} // end class MenuTarefas
