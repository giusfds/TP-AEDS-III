package app;

import java.util.Scanner;

/**
 *  IO
 */
public class IO 
{
    private static Scanner console = new Scanner( System.in );;
    public static void main( String[] args ) 
    {
        try
        {
            int opcao = 0;
            do 
            {
                System.out.println("AEDs-III 1.0    ");
                System.out.println("--------------\n");
                System.out.println("> Início        ");
                System.out.println("1 - Categorias  ");
                System.out.println("2 - Clientes    ");
                System.out.println("0 - Sair        ");
                System.out.print  ("Opção: "         );

                try {
                    opcao = Integer.valueOf( console.nextLine() );
                } catch( NumberFormatException e ) {
                    opcao = -1;
                } // end try

                switch( opcao ) 
                {
                    case 1:
                        (new MenuCategorias( )).menu( );
                        break;
                    case 2:
                        // (new MenuCliente()).menu();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                } // end switch
                
            } while( opcao != 0 ); // end do-while
        } catch ( Exception e ) {
            e.printStackTrace();
        } // end try
    } // end main

} // end class IO
