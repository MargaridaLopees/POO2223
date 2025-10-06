/**
 * Interface Carrinho.
 * @author Margarida Lopes 64557
 */

package system;

import java.util.Iterator;

public interface Carrinho {

    /**
     * Devolve o identificador do carrinho.
     * @return o identificador do carrinho.
     */
    String getID ();

    /**
     * Devolve a capacidade do carrinho.
     * @return a capacidade do carrinho.
     */
    int getCapacidade();

    /**
     * Adiciona um novo artigo ao carrinho.
     * @param artigo o artigo a adicionar.
     * @pre artigo != null
     */
    void addArtigo (Artigo artigo);

    /**
     * Verifica se é possivel adicionar um novo artigo.
     * @param volume o volume do artigo.
     * @return true se for possivel e false se nao for.
     * @pre volume != null
     */
    boolean hasCapacidade (int volume);

    /**
     * Verifica se um dado artigo esta no carrinho.
     * @param artigo o artigo a verificar.
     * @return true se existir o artigo no carrinho e false se nao existir.
     * @pre artigo != null
     */
    boolean hasArtigo (Artigo artigo);

    /**
     * Remove um dado artigo do carrinho.
     * @param artigo o artigo a remover.
     * @pre hasArtigo(artigo) && artigo != null
     */
    void removeArtigo (Artigo artigo);

    /**
     * Devolve um iterador para os artigos existentes no carrinho.
     * @return um iterador para os artigos existentes no carrinho.
     */
    Iterator<Artigo> artigosIterator();

    /**
     * Devolve o valor total da conta do carrinho.
     * @return o valor total da conta do carrinho.
     */
    int getTotal();

    /**
     * Elimina todos os produtos do carrinho.
     */
    void limparCarrinho();

    /**
     * Verifica se o carrinho possui algum artigo.
     * @return true se existirem artigos no carrigo e false se nao existirem.
     */
    boolean hasArtigos();
}
