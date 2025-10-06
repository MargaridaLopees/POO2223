/**
 * A Rede Social. Para várias pessoas.
 * @autor Margarida Lopes 64557 LEI
 */
public interface RedeSocial {

    /**
     * Devolve o numero de pessoas registadas na rede social.
     * @return o numero de pessoas registadas na rede social.
     */
    int getNrRegistados();

    /**
     * Verifica se uma dada pessoa esta registada na rede social.
     * @pre nome != null
     * @return true caso a pessoa esteja registada,
     *         false caso nao esteja.
     */
    boolean isPessoaRegistada (String nome);

    /**
     * Eegista uma nova pessoa na rede social.
     * @param nome o nome da pessoa a registar.
     * @param email o email da pessoa a registar.
     * @param estado o estado inicial da pessoa a registar.
     * @return true se a pessoa foi registada,
     *         false se a pessoa ja existir.
     * @pre nome != null && email != null && estado != null && !isPessoaRegistada(nome).
     */
    boolean registaPessoa(String nome, String email, String estado);

    /**
     * Verifica se duas pessoas sao amigas na rede social.
     * @param nome1 o nome da primeira pessoa a verificar se tem amizade com a segunda pessoa.
     * @param nome2 o nome da segunda pessoa a verificar se tem amizade com a primeira pessoa.
     * @return true se existir amizade entre ambas as pessoas,
     *         false se nao existir amizade.
     * @pre nome1 != null && nome2 != null
     */
    boolean isAmigos (String nome1, String nome2);

    /**
     * Cria a amizade entre duas pessoas.
     * @param nome1 o nome da primeira pessoa para a amizade.
     * @param nome2 o nome da segunda pessoa para a amizade.
     * @return "0" amizade criada com sucesso,
     *         "1" uma das pessoas nao esta registada na rede social,
     *         "2" amizade ja existe,
     *         "3" amizade invalida.
     */
    int criarAmizade (String nome1, String nome2);

    /**
     * Altera o estado da pessoa.
     * @param nome o nome da pessoa a qual se vai alterar o estado.
     * @param estado o novo estado da pessoa.
     * @return true se a opreacao foi realizada com sucesso,
     *         false em caso contrario, pela pessoa nao estar registada na rede social.
     * @pre nome != null & estado != null
     */
    boolean setEstado (String nome, String estado);

    /**
     * Devolve o estado da pessoa.
     * @param nome o nome da pessoa a devolver o estado.
     * @return o estado da pessoa.
     * @pre nome != null
     */
    String getEstado(String nome);

    /**
     * Devolve o numero de amigos da pessoa.
     * @param nome o nome da pessoa.
     * @return o numero de amigos da pessoa.
     * @pre nome != null
     */
    int getNrAmigos (String nome);

    /**
     * Devolve um iterador para os amigos da pessoa dada como argumento.
     * @param nome o nome da pessoa.
     * @return um iterador que percorre o vetor dos amigos da pessoa.
     * @pre nome != null
     */
    public IteradorAmigos iteradorAmg (String nome);

    /**
     * Devolve todas as pessoas registadas na rede social.
     * @return um iterador que percorre o vetor das pessoas rgisadas na rede social.
     */
    public IteradorRegistados iteradorReg();
}
