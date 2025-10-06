/**
 * Pessoa.
 * Cada pessoa tem associado um nome, um email e um estado.
 * @autor Margarida Lopes 64557 LEI
 */

public interface Pessoa {

    /**
     * Devolve o nome da pessoa.
     * @return o nome da pessoa.
     */
    String getNome ();

    /**
     * Devolve o email da pessoa.
     * @return o email da pessoa.
     */
    String getEmail ();

    /**
     * Devolve o estado da pessoa.
     * @return o estado atual da pessoa.
     */
    String getEstado ();

    /**
     * Devolve o numero de amigos da pessoa.
     * @return o numero de amigos da pessoa.
     */
    int getNrAmigos ();

    /**
     * Devolve o vetor com todos os amigos da pessoa.
     * @return o vetor com tods os amigos da pessoa.
     */
    Pessoa[] getAmigos();

    /**
     * Altera o estado da pessoa.
     * @param novoEstado o novo estado da pessoa
     * @pre estado != null
     */
    void setEstado(String novoEstado);

    /**
     * Adiciona amizade.
     * @param amigo a pessoa a adicionar como novo amigo.
     * @pre amigo != null &&  isPessoaRegistada(amigo.getNome()).
     */
    void addAmizade (Pessoa amigo);

    /**
     * Verifica se duas pessoas sao amigas.
     * @param nome o nome da pessoa a verificar se e amiga.
     * @return true em caso de amizade,
     *         false em caso de nao amizade.
     * @pre nome != null
     */
    boolean isAmigos (String nome);
}
