package view;

import java.util.List;

import controller.ArquivoCategoria;
import model.Categoria;

/**
 *  Classe CategoriasView
 * 
 *  <p>Classe que representa a interface de usuário para manipulação de categorias.</p>
 * 
 *  @see PrincipalView
 */
public class CategoriasView extends PrincipalView
{
    private static ArquivoCategoria arqCategorias;

    public CategoriasView ( ) throws Exception {
        arqCategorias = new ArquivoCategoria( );
    } // MenuCategorias ( )

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
            } while( opcao != 0 ); // do-while
        } catch ( Exception e ) {
            e.printStackTrace();
        } // try-catch
    } // menu ( )

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
    } // opcoes_menu ( )

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
        } // switch
    } // executar_opcao ( )

    private static void listarCategorias( List<Categoria> lista )
    {
        if( lista != null ) 
        {
            System.out.println( "\nLista de categorias:" );
            int tam = lista.size( );
            for( int i = 0; i < tam; i++ ) {
                System.out.println( (i+1) + ": " + lista.get(i).getNome() );
            } // for
        } // if
    } // listarCategorias ( )

    private static Categoria ler_Categoria( )
    {
        String nome = "";
        boolean dadosCompletos = false;
        do 
        {
            System.out.print( "\nNome da categoria (min. de 5 letras): " );
            nome = console.nextLine( );

            if( nome.length( ) >= 5 || nome.length( ) == 0 ) {
                dadosCompletos = true;
            } else {
                System.err.println( "O nome da categoria deve ter no mínimo 5 caracteres." );
            } // if
        } while( dadosCompletos == false ); // do-while
        return ( new Categoria( nome ) );
    } // ler_Categoria ( )

    public static void incluirCategoria( ) 
    {
        System.out.println( "\n> Incluir categoria:" );
        
        Categoria novaCategoria = ler_Categoria( );

        if( novaCategoria != null && novaCategoria.getNome().length() > 0 ) 
        {
            System.out.println( "Confirma a inclusão da categoria? (S/N)" );
            char resp = console.nextLine().charAt(0);

            if( resp=='S' || resp=='s' ) 
            {
                try 
                {
                    arqCategorias.create( novaCategoria );
                    System.out.println( GREEN + "Categoria criada com sucesso." + RESET);
                } catch( Exception e ) {
                    System.out.println( RED + "Erro do sistema. Não foi possível criar a categoria!" + RESET );
                } // try-catch
            } // if
        } else {
            System.out.println( RED + "Operação cancelada!" + RESET );
        } // if 
    } // incluirCategoria ( )

    public static void buscarCategoria( ) 
    {
        System.out.println( "\n> Buscar Categoria:" );
        
        try 
        {
            List<Categoria> lista = arqCategorias.readAll( -1 );

            if( lista.isEmpty( ) ) {
                System.out.println( RED + "Não há categoria cadastrada." + RESET );
            } 
            else 
            {
                listarCategorias( lista );

                System.out.print( "Id da Categoria: " );
                String nome = console.nextLine(); 
                
                if( nome.length() > 0 ) 
                {
                    Categoria categoriaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size( );
                    for( int i = 0; i < tam && !encontrada; i++ )
                    {
                        if( lista.get(i).getNome().equalsIgnoreCase(nome) ) 
                        { 
                            categoriaEncontrada = lista.get(i);
                            encontrada = true;
                        }//if
                    } // for
        
                    if( categoriaEncontrada != null ) {
                        System.out.println( GREEN + categoriaEncontrada + RESET );
                    } else {
                        System.out.println( RED + "Categoria não encontrada." + RESET );
                    }//if
                } else {
                    System.out.println( RED + "Operação cancelada!" + RESET );
                } // if
            } // if

        } catch( Exception e ) {
            System.err.println( RED + "Erro no sistema. Não foi possível buscar a categoria!" + RESET );
        } //try
    } // buscarCategoria ( )

    public static void alterarCategoria( ) 
    {
        System.out.println( "\n> Alterar Categoria:" );
    
        try 
        {
            List<Categoria> lista = arqCategorias.readAll( -1 );

            if( lista.isEmpty( ) ) {
                System.out.println( RED + "Não há categoria cadastrada." + RESET );
            } 
            else
            {
                System.out.println( "\nLista de categorias:" );
                listarCategorias( lista );

                System.out.print( "Nome da Categoria: " );
                String nome = console.nextLine(); 

                if( nome.length() > 0 ) 
                {
                    Categoria categoriaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size( );
                    for( int i = 0; i < tam && !encontrada; i++ )
                    {
                        if( lista.get(i).getNome().equalsIgnoreCase(nome) ) 
                        { 
                            categoriaEncontrada = lista.get(i);
                            encontrada = true;
                        }//if
                    } // for

                    if( categoriaEncontrada != null ) 
                    {
                        Categoria novaCategoria = ler_Categoria();
                        
                        if( novaCategoria != null && novaCategoria.getNome().length() > 0 )
                        {  
                            novaCategoria.setId( categoriaEncontrada.getId() );
                            arqCategorias.update( novaCategoria );
                            System.out.println( GREEN + "Categoria alterada com sucesso." + RESET );
                        } else {
                            System.out.println( RED + "Operação cancelada!" + RESET );
                        }// if
                    } else {
                        System.out.println( RED + "Categoria não encontrada." + RESET );
                    }//if
                } else {
                    System.out.println( RED + "Operação cancelada!" + RESET );
                } // if
            } // if

        } catch( Exception e ) {
            System.out.println( RED + "Erro no sistema. Não foi possível alterar a categoria!" + RESET );
        }//try
    } // alterarCategoria
    
    public static void excluirCategoria( ) 
    {
        System.out.println( "\n> Excluir Categoria:" );

        try 
        {
            List<Categoria> lista = arqCategorias.readAll( -1 );

            if( lista.isEmpty( ) ) {
                System.out.println( RED + "Não há categoria cadastrada." + RESET );
            } 
            else
            {
                System.out.println( "\nLista de categorias:" );
                listarCategorias( lista );

                System.out.print("Nome da categoria: ");
                String nome = console.nextLine(); 

                if( nome.length() > 0 )
                {
                    Categoria categoriaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size( );
                    for( int i = 0; i < tam && !encontrada; i++ )
                    {
                        if( lista.get(i).getNome().equalsIgnoreCase(nome) ) 
                        { 
                            categoriaEncontrada = lista.get(i);
                            encontrada = true;
                        }//if
                    } // for
        
                    if( categoriaEncontrada != null ) 
                    {
                        System.out.print  ( "\nCategoria:" );
                        System.out.println( categoriaEncontrada );

                        System.out.println("\nConfirma a exclusão da categoria? (S/N)");
                        char resp = console.nextLine().charAt(0);  
            
                        if( resp == 'S' || resp == 's' ) 
                        {
                            boolean sucesso = arqCategorias.delete( categoriaEncontrada.getId() );

                            if( sucesso ) {
                                System.out.println( GREEN + "Categoria excluída com sucesso." + RESET );
                            } else {
                                System.out.println( RED + "Erro: Não foi possível excluir a categoria." + RESET );
                            }// if
                        }// if
                    } else {
                        System.out.println(RED + "Categoria não encontrada." + RESET);
                    } //if
                } else {
                    System.out.println( RED + "Operação cancelada!" + RESET );
                } // if
            } // if

        } catch( Exception e ) {
            System.out.println( RED + "Erro no sistema. Não foi possível excluir a categoria!" + RESET );
        } // try
    } //excluirCategoria
    
} // class MenuCategorias1