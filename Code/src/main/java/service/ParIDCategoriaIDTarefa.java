package service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import interfaces.RegistroHashExtensivel;

/**
 *  ParIDCategoriaIDTarefa: Classe que representa um par de ID de categoria e ID de tarefa.
 *  Implementa a interface RegistroHashExtensivel.
 */
public class ParIDCategoriaIDTarefa implements RegistroHashExtensivel<ParIDCategoriaIDTarefa> 
{
    private int idCategoria; // chave
    private int idTarefa;    // valor
    private final short TAMANHO = 15;  // tamanho em bytes

    public ParIDCategoriaIDTarefa ( ) 
    {
        this.idCategoria = -1;
        this.idTarefa    = -1;
    } // end ParIDCategoriaIDTarefa ( )

    public ParIDCategoriaIDTarefa ( int idCategoria, int idTarefa ) throws Exception 
    {
        this.idCategoria = idCategoria;
        this.idTarefa = idTarefa;
    } // end ParIDCategoriaIDTarefa ( )

    public int getIDCategoria ( ) {
        return idCategoria;
    } // end getIDCategoria ( )

    public int getIDTarefa ( ) {
        return idTarefa;
    } // end getIDTarefa ( )

    @Override
    public int hashCode ( ) {
        return hash(this.idCategoria);
    } // end hashCode ( )

    public short size ( ) {
        return this.TAMANHO;
    } // end size ( )

    public String toString ( ) {
        return "("+this.idCategoria + ";" + this.idTarefa+")";
    } // end toString ( )

    public byte[] toByteArray ( ) throws IOException 
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.idCategoria);
        dos.writeInt(this.idTarefa);

        return (baos.toByteArray());
    } // end toByteArray ( )

    public void fromByteArray ( byte[] ba ) throws IOException 
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
    
        this.idCategoria = dis.readInt( );
        this.idTarefa    = dis.readInt();
    } // end fromByteArray ( )

    public static int hash ( int idCategoria ) throws IllegalArgumentException {
        return Math.abs(idCategoria % (int)(1e9 + 7));
    } // end hash ( )
} // end class ParIDCategoriaIDTarefa