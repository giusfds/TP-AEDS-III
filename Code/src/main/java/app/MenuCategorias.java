package app;

import java.util.Scanner;

import model.Categoria;
import service.ArquivoCategoria;

public class MenuCategorias 
{
    ArquivoCategoria arqCategorias;
    private static Scanner console = new Scanner( System.in );

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
                System.out.println("AEDs-III 1.0             ");
                System.out.println("-----------------------\n");
                System.out.println("> Início > Categorias    ");
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
                        // buscarCategoria( );
                        break;
                    case 2:
                        incluirCategoria( );
                        break;
                    case 3:
                        // alterarCategoria( );
                        break;
                    case 4:
                        // excluirCategoria( );
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

    public void incluirCategoria( ) 
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

        if( nome.length( ) == 0 ) {
            return;
        } // end if

        System.out.println( "Confirma a inclusão da categoria? (S/N) " );
        char resp = console.nextLine().charAt(0);
        if( resp=='S' || resp=='s' ) 
        {
            try 
            {
                Categoria c = new Categoria(nome);
                arqCategorias.create(c);
                System.out.println("Categoria criada com sucesso.");
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível criar a categoria!");
            } // end try
        } // end if
    } // end incluirCategoria ( )

    public boolean buscarCategoria( )
    {
        boolean result = false;
        System.out.println( "\nBuscar categoria:" );
        return result;
    } // end buscarCategoria ( )

    public boolean alterarCategoria( )
    {
        boolean result = false;
        System.out.println( "\nAlterar categoria:" );
        return result;
    } // end alterarCategoria ( )

    public boolean excluirCategoria( )
    {
        boolean result = false;
        System.out.println( "\nExcluir categoria:" );
        return result;
    } // end excluirCategoria ( )

} // end class MenuCategorias