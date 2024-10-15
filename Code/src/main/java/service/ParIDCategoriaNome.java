package service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import interfaces.RegistroHashExtensivel;

/**
 *  ParIDCategoriaNome: Classe que representa um par de ID Categoria e Nome.
 *  Implementa a interface RegistroHashExtensivel.
 */
public class ParIDCategoriaNome implements RegistroHashExtensivel<ParIDCategoriaNome> 
{
    private int id;
    private String nome;
    private final short TAMANHO = 12; // tamanho em bytes

    public ParIDCategoriaNome ( ) 
    {
        this.id = -1;
        this.nome = "";
    } // end ParIDCategoriaNome ( )

    public ParIDCategoriaNome ( int id, String end ) 
    {
        this.id = id;
        this.nome = end;
    } // end ParIDCategoriaNome ( )

    public int getId ( ) {
        return id;
    } // end getId ( )

    public String getNome ( ) {
        return nome;
    } // end getNome ( )

    @Override
    public int hashCode ( ) {
        return this.id;
    } // end hashCode ( )

    public short size ( ) {
        return this.TAMANHO;
    } // end size ( )

    public String toString ( ) {
        return "(" + this.id + ";" + this.nome + ")";
    } // end toString ( )

    public byte[] toByteArray ( ) throws IOException 
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.id);
        dos.writeBytes(this.nome);
        
        return ( baos.toByteArray() );
    } // end toByteArray ( )

    public void fromByteArray ( byte[] ba ) throws IOException 
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
    
        this.id = dis.readInt();
        byte[] b = new byte[dis.available()];
        dis.read(b);
        this.nome = new String(b);
    } // end fromByteArray ( )
    
} // end class ParIDCategoriaNome
