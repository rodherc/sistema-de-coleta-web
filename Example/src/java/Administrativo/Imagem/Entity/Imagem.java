package Administrativo.Imagem.Entity;

/**
 *
 * @author aluno
 */
public class Imagem {
    private int codImagem;
    private String nome;
    private String localizacao;

    public Imagem(){}
    
    public Imagem(int codImagem, String nome, String localizacao) {
        this.codImagem = codImagem;
        this.nome = nome;
        this.localizacao = localizacao;
    }



    public int getCodImagem() {
        return codImagem;
    }

    public void setCodImagem(int codImagem) {
        this.codImagem = codImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    
    
}
