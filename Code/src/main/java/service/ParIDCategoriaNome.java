package service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import interfaces.RegistroArvoreBMais;

/**
 *  ParIDCategoriaNome: Classe que representa um par de ID Categoria e Nome.
 *  Implementa a interface RegistroArvoreBMais.
 */
public class ParIDCategoriaNome implements RegistroArvoreBMais<ParIDCategoriaNome> 
{
    private int idCategoria;
    private String nome;
    private final short TAMANHO = 32; // tamanho em bytes

    public ParIDCategoriaNome ( ) 
    {
        this( -1,"" );
    } // end ParIDCategoriaNome ( )

    public ParIDCategoriaNome ( int idCategoria ) 
    {
        this( idCategoria, "" );
    } // end ParIDCategoriaNome ( )

    public ParIDCategoriaNome ( int idCategoria, String nome ) 
    {
        this.idCategoria = idCategoria;
        this.nome = nome;
    } // end ParIDCategoriaNome ( )

    public int getIDCategoria ( ) {
        return idCategoria;
    } // end getId ( )

    public String getNome ( ) {
        return nome;
    } // end getNome ( )

    @Override
    public ParIDCategoriaNome clone ( ) 
    {
        ParIDCategoriaNome ret = null;
        try {
            ret = new ParIDCategoriaNome(this.idCategoria, this.nome);
        } // end try
        catch ( Exception e ) {
        } // end catch
        return ret;
    } // end clone ( )

    public int compareTo ( ParIDCategoriaNome picn ) 
    {
        int result = 0xFFFFFF7;
        if( this.idCategoria != picn.idCategoria ) {
            result = this.idCategoria - picn.idCategoria;
        } else {
            result = this.nome.compareTo("") == 0 ? 0 : this.nome.compareTo(picn.nome);
        } // end if
        return result;
    } // end compareTo ( )

    public short size ( ) {
        return this.TAMANHO;
    } // end size ( )

    public String toString ( ) {
        return String.format("%3d", this.idCategoria) + ";" + this.nome + ")";
    } // end toString ( )

    public byte[] toByteArray ( ) throws IOException 
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.idCategoria);
        dos.writeBytes(this.nome);
        
        return ( baos.toByteArray() );
    } // end toByteArray ( )

    public void fromByteArray ( byte[] ba ) throws IOException 
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
    
        this.idCategoria = dis.readInt();
        byte[] b = new byte[28];
        dis.read(b);
        this.nome = new String(b);
    } // end fromByteArray ( )
    
} // end class ParIDCategoriaNome
