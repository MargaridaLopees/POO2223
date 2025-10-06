/**
 * Interface Iterador.
 * Utilizada pelas duas classes: IteradorAmigos e IteradorRegistados.
 * @autor Margarida Lopes 64557 LEI
 */

public interface Iterador {

    /**
     * Verifica se existe mais algum amigo a iterar.
     * @return true, se houver mais amigos a iterar,
     *         false caso nao haja mais amigos a iterar.
     */
    boolean hasNext();

    /**
     * Devolva o proximo amigo da colacao.
     * @pre hasNext();
     * @return o proximo amigo da colecao.
     */
    Pessoa next();
}
