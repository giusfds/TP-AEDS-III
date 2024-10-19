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

    public static ArrayList<Categoria> listarCategoria( )
    {
        try{
            ArrayList<Categoria> lista = arqCategorias.readAll();
            for(int i = 0; i < lista.size(); i++){
                System.out.println(lista.get(i).getNome());
            }
            return lista;
        }catch(Exception e){
            System.out.println(RED + "ERROR!! Não foi possivel listar a categoria." + RESET);
            return null;
        }
    } // end listarCategoria

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
                    System.out.println(c);
                    arqCategorias.create(c);
                    System.out.println( GREEN + "Categoria criada com sucesso." + RESET);
                } catch(Exception e) {
                    System.out.println(RED + "Erro do sistema. Não foi possível criar a categoria!" + RESET);
                    e.printStackTrace();
                } // end try
            } // end if
        } // end if

    } // end incluirCategoria ( )

    public static void buscarCategoria( ) 
    {
        System.out.println("\nLista de categoria:");
        
        try 
        {
            ArrayList<Categoria> lista = listarCategoria();
            System.out.print("Nome da categoria a ser buscada: ");
            String nome = console.nextLine(); 
            Categoria categoriaEncontrada = null;

            for (Categoria categoria : lista) {
                if (categoria.getNome().equalsIgnoreCase(nome)) { 
                    categoriaEncontrada = categoria;
                    break; 
                }//end if
            }//end for

            if (categoriaEncontrada != null) {
                System.out.println(GREEN + "Categoria encontrada: " + categoriaEncontrada.getNome() + RESET);
            } else {
                System.out.println(RED + "Categoria não encontrada." + RESET);
            }//end if
        } 
        catch (Exception e) 
        {
            System.err.println(RED + "Erro ao buscar a categoria." + RESET);
            e.printStackTrace();  
        }//end try
    } // end buscarCategoria


    public static void alterarCategoria( ) 
    {
        System.out.println("\nLista de categoria:");
    
        try 
        {
            ArrayList<Categoria> lista = listarCategoria();
            System.out.print("Nome da categoria a ser alterada: ");
            String nome = console.nextLine(); 
            Categoria categoriaEncontrada = null;
    
            for (Categoria categoria : lista) {
                if (categoria.getNome().equalsIgnoreCase(nome)) { 
                    categoriaEncontrada = categoria;
                    break; 
                }//end if
            }// end for
    
            if (categoriaEncontrada != null) {
                System.out.print("Novo nome da categoria (min. de 5 letras): ");
                String novoNome = console.nextLine(); 
                
                if (novoNome.length() >= 5) {  
                    categoriaEncontrada.setNome(novoNome);  
                    arqCategorias.update(categoriaEncontrada); 
                    System.out.println(GREEN + "Categoria alterada com sucesso." + RESET);
                } else {
                    System.err.println("O nome da categoria deve ter no mínimo 5 caracteres.");
                }//end if
            } else {
                System.out.println(RED + "Categoria não encontrada." + RESET);
            }//end if
        } 
        catch (Exception e) 
        {
            System.out.println(RED + "Erro ao alterar a categoria." + RESET);
            e.printStackTrace(); 
        }//end try
    } // end alterarCategoria
    

    public static void excluirCategoria( ) 
    {
        System.out.println("\nLista de categoria:");
        try 
        {
            ArrayList<Categoria> lista = listarCategoria();
            System.out.print("Nome da categoria a ser excluída: ");
            String nome = console.nextLine(); 
            Categoria categoriaEncontrada = null;
    
            for (Categoria categoria : lista) {
                if (categoria.getNome().equalsIgnoreCase(nome)) { 
                    categoriaEncontrada = categoria;
                    break;  
                }// end if
            }// end for
    
            if (categoriaEncontrada != null) {
                System.out.println("Categoria encontrada: " + categoriaEncontrada.getNome() + " (ID: " + categoriaEncontrada.getId() + ")");
                System.out.println("Confirma a exclusão da categoria? (S/N)");
                char resp = console.nextLine().charAt(0);  
    
                if (resp == 'S' || resp == 's') {
                    boolean sucesso = arqCategorias.delete(categoriaEncontrada.getId());
    
                    if (sucesso) {
                        System.out.println(GREEN + "Categoria excluída com sucesso." + RESET);
                    } else {
                        System.out.println(RED + "Erro: Não foi possível excluir a categoria." + RESET);
                    }// end if
                }// end if
            } else {
                System.out.println(RED + "Categoria não encontrada." + RESET);
            } //end if
        } 
        catch (Exception e) 
        {
            System.out.println(RED + "Erro ao excluir a categoria: " + e.getMessage() + RESET);
            e.printStackTrace();
        }// end try
    } //end excluirCategoria
    

} // end class MenuCategorias1