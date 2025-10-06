/**
 * A Rede Social. Para várias pessoas.
 * @autor Margarida Lopes 64557 LEI
 */
public class RedeSocialClass implements RedeSocial {

    private static final int DEFAULT = 500;

    private Pessoa[] registados;
    private int nrRegistados;

    public RedeSocialClass () {
        registados = new Pessoa[DEFAULT];
        nrRegistados = 0;
    }

    public int getNrRegistados() {
        return nrRegistados;
    }

    /**
     * Devolve o vetor de pessoas registadas na rede social.
     * @return o vetor de pessoas registadas na rede social.
     */
    private Pessoa[] getReg() {
        return registados;
    }

    /**
     * Devolve uma pessoa que esta registada na rede social.
     * @param nome o nome da pessoa registada.
     * @return a pessoa registada.
     * @pre nome != null
     */
    private Pessoa pessoaRegistada (String nome) {
        int i = 0;
        while (i < nrRegistados-1 && !registados[i].getNome().equals(nome)){
            i++;
        } return registados[i];
    }

    @Override
    public boolean isPessoaRegistada(String nome) {
        int i = 0;
        boolean result = true;
        while (i < nrRegistados && !registados[i].getNome().equals(nome)) { i++; }
        if (i >= nrRegistados) { result = false; }
        return result;
    }

    @Override
    public boolean registaPessoa(String nome, String email, String estado) {
        boolean result = false;
        if(!isPessoaRegistada(nome)) {
            registados[nrRegistados++] = new PessoaClass(nome, email, estado);
            result = true;
        } return result;
    }

    @Override
    public boolean isAmigos(String nome1, String nome2) {
        return pessoaRegistada(nome1).isAmigos(nome2);
    }

    @Override
    public int criarAmizade(String nome1, String nome2) {
        int i = 3;
        if (!nome1.equals(nome2)) {
            i--;
            if (isPessoaRegistada(nome1) && isPessoaRegistada(nome2)) {
                i--;
                if (!isAmigos(nome1, nome2)) {
                    i--;
                    pessoaRegistada(nome1).addAmizade(pessoaRegistada(nome2));
                    pessoaRegistada(nome2).addAmizade(pessoaRegistada(nome1));
                }
            }
        } return i;
    }

    @Override
    public boolean setEstado(String nome, String estado) {
        boolean result = false;
        if (isPessoaRegistada(nome)) {
            pessoaRegistada(nome).setEstado(estado);
            result = true;
        } return result;
    }

    @Override
    public String getEstado(String nome) {
        String estado = null;
        if (isPessoaRegistada(nome)) {
            estado = pessoaRegistada(nome).getEstado();
        } return estado;
    }

    @Override
    public int getNrAmigos(String nome) {
        return (pessoaRegistada(nome).getNrAmigos());
    }

    @Override
    public IteradorAmigos iteradorAmg(String nome) {
        Pessoa pessoa = pessoaRegistada(nome);
        int amigos = pessoa.getNrAmigos();
        return new IteradorAmigos(pessoa, amigos);
    }

    @Override
    public IteradorRegistados iteradorReg() {
        return new IteradorRegistados(registados, nrRegistados);
    }
}