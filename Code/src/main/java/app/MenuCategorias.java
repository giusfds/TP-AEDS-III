package app;

import java.util.ArrayList;

import model.Categoria;
import service.ArquivoCategoria;

/**
 *  MenuCategorias: Classe de interação com o usuário para manipulação de categorias.
 */
public class MenuCategorias extends IO
{
    private static ArquivoCategoria arqCategorias;

    public MenuCategorias ( ) throws Exception {
        arqCategorias = new ArquivoCategoria( );
    } // end MenuCategorias ( )

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
        System.out.println("\nAEDs-III 1.0           ");
        System.out.println("-------------------------");
        System.out.println("> Início > Categorias    ");
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
                buscarCategoria( );
                break;
            case 2:
                incluirCategoria( );
                break;
            case 3:
                alterarCategoria( );
                break;
            case 4:
                excluirCategoria( );
                break;
            
            default:
                System.out.println( RED + "Opção inválida!" + RESET );
                break;
        } // end switch
    } // end executar_opcao ( )

    public static void incluirCategoria( ) 
    {
        String  nome = "";
        boolean dadosCompletos = false;

        System.out.println( "\nInclusão de categoria:" );
        do 
        {
            System.out.print("\nNome da categoria (min. de 5 letras): ");
            nome = console.nextLine( );

            if( nome.length( ) >= 5 || nome.length( ) == 0 ) {
                dadosCompletos = true;
            } else {
                System.err.println("O nome da categoria deve ter no mínimo 5 caracteres.");
            } // end if

        } while( dadosCompletos == false ); // end do-while

        if( nome.length( ) > 0 ) 
        {
            System.out.println( "Confirma a inclusão da categoria? (S/N)" );
            char resp = console.nextLine().charAt(0);

            if( resp=='S' || resp=='s' ) 
            {
                try 
                {
                    Categoria c = new Categoria(nome);
                    arqCategorias.create(c);
                    System.out.println( GREEN + "Categoria criada com sucesso." + RESET);
                } catch(Exception e) {
                    System.out.println(RED + "Erro do sistema. Não foi possível criar a categoria!" + RESET);
                } // end try
            } // end if
        } // end if

    } // end incluirCategoria ( )

    public static boolean buscarCategoria( )
    {
        boolean result = false;
        System.out.println( "\nBuscar categoria:" );
        return result;
    } // end buscarCategoria ( )

    public static boolean alterarCategoria( )
    {
        boolean result = false;
        System.out.println( "\nAlterar categoria:" );
        return ( result );
    } // end alterarCategoria ( )

    public static boolean excluirCategoria( )
    {
        boolean result = false;
        System.out.println( "\nExcluir categoria:" );
        return ( result );
    } // end excluirCategoria ( )

} // end class MenuCategorias