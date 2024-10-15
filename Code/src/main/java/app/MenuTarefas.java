package app;

import java.util.Scanner;

import model.Tarefa;
import service.ArquivoTarefa;

public class MenuTarefas 
{
    ArquivoTarefa arqTarefas;
    private static Scanner console = new Scanner( System.in );

    public MenuTarefas ( ) throws Exception {
        arqTarefas = new ArquivoTarefa( );
    } // end MenuTarefas ( )

    public void menu ( ) 
    {
        try
        {
            int opcao = 0;
            do 
            {
                System.out.println("AEDs-III 1.0             ");
                System.out.println("-----------------------\n");
                System.out.println("> Início > Tarefas       ");
                System.out.println("1 - Buscar               ");
                System.out.println("2 - Incluir              ");
                System.out.println("3 - Alterar              ");
                System.out.println("4 - Excluir              ");
                System.out.println("0 - Voltar               ");
                System.out.print  ("Opção: "                  );
    
                try {
                    opcao = Integer.valueOf( console.nextLine( ) );
                } catch( NumberFormatException e ) {
                    opcao = -1;
                } // end try
    
                switch( opcao ) 
                {
                    case 1:
                        // buscarTarefa( );
                        break;
                    case 2:
                        // incluirTarefa( );
                        break;
                    case 3:
                        // alterarTarefa( );
                        break;
                    case 4:
                        // excluirTarefa( );
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                } // end switch

            } while( opcao != 0 ); // end do-while
        } catch ( Exception e ) {
            e.printStackTrace();
        } // end try
    } // end menu ( )

    public void incluirTarefa( ) 
    {

    } // end incluirTarefa ( )

    public boolean buscarTarefa( )
    {
        boolean result = false;
        System.out.println( "\nBuscar Tarefa:" );
        return result;
    } // end buscarTarefa ( )

    public boolean alterarTarefa( )
    {
        boolean result = false;
        System.out.println( "\nAlterar Tarefa:" );
        return result;
    } // end alterarTarefa ( )

    public boolean excluirTarefa( )
    {
        boolean result = false;
        System.out.println( "\nExcluir Tarefa:" );
        return result;
    } // end excluirTarefa ( )

} // end class MenuTarefas
