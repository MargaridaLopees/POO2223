
/**
 * Pessoa.
 * Cada pessoa tem associado um nome, um email e um estado.
 * @autor Margarida Lopes 64557 LEI
 */

public class PessoaClass implements Pessoa {

    private static final int DEFAULT = 50;

    private String nome;
    private String email;
    private String estado;
    private Pessoa[] amigos;
    private int nrAmigos;


    public PessoaClass (String nome, String email, String estado) {
        this.nome = nome;
        this.email = email;
        this.estado = estado;
        amigos = new PessoaClass[DEFAULT];
        nrAmigos = 0;
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public String getEmail() { return email; }

    @Override
    public String getEstado() { return estado; }

    @Override
    public int getNrAmigos() { return nrAmigos; }

    @Override
    public Pessoa[] getAmigos() {
        return amigos;
    }

    @Override
    public void setEstado(String novoEstado) { estado = novoEstado; }


    @Override
    public void addAmizade(Pessoa amigo) {
        amigos[nrAmigos++] = amigo;
    }

    @Override
    public boolean isAmigos(String nome) {
        int i = 0;
        boolean result = true;
       while (i < nrAmigos && !amigos[i].getNome().equals(nome)) { i++; }
       if (i >= nrAmigos) { result = false; }
       return result;
    }
}
