import java.time.LocalDate;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Date;

public class Tarefa {
    private String nome;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private String status;
    private byte prioridade;

    public Tarefa(String nome, LocalDate dataCriacao, LocalDate dataConclusao, String status, byte prioridade) {
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.prioridade = prioridade;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeUTF(this.nome); // nome
        dos.writeInt((int) this.dataCriacao.toEpochDay()); // data de criação
        dos.writeInt((int)this.dataConclusao.toEpochDay()); // data de conclusão
        dos.writeUTF(this.status); // status
        dos.writeByte(this.prioridade); // prioridade

        return baos.toByteArray();
        
    }
}