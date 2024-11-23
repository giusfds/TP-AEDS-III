package model;

import java.time.LocalDate;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import interfaces.Registro;

/**
 *  Classe Tarefa
 * 
 *  <p>Classe que representa uma tarefa.</p>
 *  <p>Implementa a interface Registro.</p>
 *  
 *  @see interfaces.Registro
 */
public class Tarefa implements Registro 
{
    private int       id;
    private String    nome;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private byte      status;
    private byte      prioridade;
    private int       idCategoria;

    public Tarefa( ) {
        this(-1, "", LocalDate.now(), LocalDate.now(), (byte) -1, (byte) -1);
    } // Tarefa ( )

    public Tarefa( String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade ) 
    {
        this.nome          = nome;
        this.dataCriacao   = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status        = status;
        this.prioridade    = prioridade;
    } // Tarefa ( )

    public Tarefa( int id, String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade )
    {
        this.id            = id;
        this.nome          = nome;
        this.dataCriacao   = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status        = status;
        this.prioridade    = prioridade;
    } // Tarefa ( )

    public Tarefa( String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade, int idCategoria ) 
    {
        this.nome          = nome;
        this.dataCriacao   = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status        = status;
        this.prioridade    = prioridade;
        this.idCategoria   = idCategoria;
    } // Tarefa ( )

    public Tarefa( int id, String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade, 
                    int idCategoria )
    {
        this.id            = id;
        this.nome          = nome;
        this.dataCriacao   = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status        = status;
        this.prioridade    = prioridade;
        this.idCategoria   = idCategoria;
    } // Tarefa ( )
    
    public int getId( ) {
        return this.id;
    } // getId ( )
    
    public void setId( int id ) {
        this.id = id;
    } // setId ( )
    
    public String getNome( ) {
        return this.nome;
    } // getNome ( )

    public void setNome( String nome ) {
        this.nome = nome;
    } // setNome ( )

    public LocalDate getDataCriacao( ) {
        return this.dataCriacao;
    } // getDataCriacao ( )

    public void setDataCriacao( LocalDate dataCriacao ) {
        this.dataCriacao = dataCriacao;
    } // setDataCriacao ( )

    public LocalDate getDataConclusao( ) {
        return this.dataConclusao;
    } // getDataConclusao ( )

    public void setDataConclusao( LocalDate dataConclusao ) {
        this.dataConclusao = dataConclusao;
    } // setDataConclusao ( )

    public byte getStatus( ) {
        return this.status;
    } // getStatus ( )

    public void setStatus( byte status ) {
        this.status = status;
    } // setStatus ( )

    public byte getPrioridade( ) {
        return this.prioridade;
    } // getPrioridade ( )

    public void setPrioridade( byte prioridade ) {
        this.prioridade = prioridade;
    } // setPrioridade ( )

    public int getIdCategoria( ) {
        return this.idCategoria;
    } // getIdCategoria ( )

    public void setIdCategoria( int idCategoria ) {
        this.idCategoria = idCategoria;
    } // setIdCategoria ( )

    private static String getStatusString( byte status ) 
    {
        switch( status ) 
        {
            case 1 : return "Pendente"       ;
            case 2 : return "Em andamento"   ;
            case 3 : return "Concluída"      ;
            case 4 : return "Cancelada"      ;
            case 5 : return "Atrasada"       ;
            default: return "Status inválido";
        } // switch
    } // getStatusString ( )

    private static String getPrioridadeString( byte prioridade ) 
    {
        switch( prioridade ) 
        {
            case 1 : return "Muito baixa"        ;
            case 2 : return "Baixa"              ;
            case 3 : return "Média"              ;
            case 4 : return "Alta"               ;
            case 5 : return "Urgente"            ;
            default: return "Prioridade inválida";
        } // switch
    } // getPrioridadeString ( )

    private static String getDataString( LocalDate data ) 
    {
        return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
    } // getDataString ( )

    public String toString ( ) 
    {
        return ("\nID...............: " + this.id                                +
                "\nNome.............: " + this.nome                              +
                "\nData de Criacao..: " + getDataString( this.dataCriacao )      +
                "\nData de Conclusao: " + getDataString( this.dataConclusao )    +
                "\nStatus...........: " + getStatusString( this.status )         +
                "\nPrioridade.......: " + getPrioridadeString( this.prioridade ) +
                "\nCategoria........: " + this.idCategoria
                );
    } // toString ( )

    public byte[] toByteArray( ) throws IOException 
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try
        {
            dos.writeInt (this.id);
            dos.writeUTF (this.nome); // nome
            dos.writeInt ((int)this.dataCriacao.toEpochDay()); // data de criação
            dos.writeInt ((int)this.dataConclusao.toEpochDay()); // data de conclusão
            dos.writeByte(this.status); // status
            dos.writeByte(this.prioridade); // prioridade
            dos.writeInt (this.idCategoria); // idCategoria
        } catch( IOException e ) {
            System.out.println( "Erro ao transformar objeto tarefa em byte[]: " + e.getMessage( ) );
        } // try-catch
        return baos.toByteArray();
    } // toByteArray ( )

    public void fromByteArray( byte[] b ) throws IOException 
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        try
        {
            this.id            = dis.readInt();
            this.nome          = dis.readUTF();
            this.dataCriacao   = LocalDate.ofEpochDay(dis.readInt());
            this.dataConclusao = LocalDate.ofEpochDay(dis.readInt());
            this.status        = dis.readByte();
            this.prioridade    = dis.readByte();
            this.idCategoria   = dis.readInt();
        } catch( IOException e ) {
            System.out.println("Erro ao converter byte[] em objeto tarefa: " + e.getMessage( ) );
        } // try-catch
    } // fromByteArray ( )

} // Tarefa