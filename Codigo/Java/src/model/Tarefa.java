import java.time.LocalDate;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class Tarefa implements Registro {
    public int id;
    public String nome;
    public LocalDate dataCriacao;
    public LocalDate dataConclusao;
    public byte status;
    public byte prioridade;

    public Tarefa() {
        this(-1, "", LocalDate.now(), LocalDate.now(), (byte) -1, (byte) -1);
    }

    public Tarefa(String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade) {

        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.prioridade = prioridade;
    }


    public Tarefa(int id, String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.prioridade = prioridade;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "\nID........: " + this.id +
                "\nNome......: " + this.nome +
                "\nDATA CRIACAO.......: " + this.dataCriacao +
                "\nDATA CONCLUSAO...: " + this.dataConclusao +
                "\nSTATUS...: " + this.status +
                "\nPRIORIDADE...: " + this.prioridade;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.nome); // nome
        dos.writeInt((int) this.dataCriacao.toEpochDay()); // data de criação
        dos.writeInt((int) this.dataConclusao.toEpochDay()); // data de conclusão
        dos.writeByte(this.status); // status
        dos.writeByte(this.prioridade); // prioridade

        return baos.toByteArray();

    }

    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);

        this.id = dis.readInt();
        this.nome = dis.readUTF();
        this.dataCriacao = LocalDate.ofEpochDay(dis.readInt());
        this.dataConclusao = LocalDate.ofEpochDay(dis.readInt());
        this.status = dis.readByte();
        this.prioridade = dis.readByte();
    }


}