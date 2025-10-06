/**
 * Interface Supermercado.
 * @author Margarida Lopes 64557
 */
package system;

import excecoes.*;

import java.util.Iterator;

public interface Supermercado {

    /**
     * Adiciona um novo carrinho ao supermercado.
     * @param ID o identificador do carrinho.
     * @param capacidade a capacidade do carrinho.
     * @throws CarroJaExistenteException caso o identificador ja esteja em uso.
     * @pre ID != null && capacidade != null
     */
    void addCarrinho (String ID, int capacidade) throws CarroJaExistenteException;

    /**
     * Adiciona um novo artio ao supermercado.
     * @param nome o nome do artigo.
     * @param volume o volume do artigo.
     * @param preco o preço do artigo.
     * @throws CarroJaExistenteException caso o nome do artigo ja esteja em uso.
     * @pre nome != null && volume != null && preco != null
     */
    void addArtigo (String nome, int preco, int volume) throws ArtigoJaExistenteException;

    /**
     * Adiciona um artigo no carrinho.
     * @param artigo o nome do artigo.
     * @param carrinho o identificador do carrinho.
     * @throws CarroNaoExistenteException caso o carrinho ou o artigo nao existam.
     * @throws CapacidadeExcedidaException caso o carrinho esteja demasiado cheio.
     * @pre artigo != null && carrinho != null
     */
    void depositarArtigo (String artigo, String carrinho) throws CarroNaoExistenteException,
            CapacidadeExcedidaException, ArtigoNaoExistenteException;

    /**
     * Remove o artigo do carrinho.
     * @param artigo o artigo a remover.
     * @param carrinho o carrinho.
     * @throws CarroNaoExistenteException caso o carrinho nao exista.
     * @throws ArtigoNaoExistenteException caso nao exista o artigo no carrinho.
     * @pre artigo != null && carrinho != null
     */
    void removeArtigo(String artigo, String carrinho) throws CarroNaoExistenteException, ArtigoNaoExistenteException;

    /**
     * lista todos os artigos presentes num carrinho.
     * @param carrinho o carrinho.
     * @return a lista de artigos existentes no carrinho.
     * @pre carrinho != null
     */
    Iterator<Artigo> listaArtigos(String carrinho);

    /**
     * Elimina todos os artigos do carrinho e devolve o valor ca conta final.
     * @param carrinho o carrinho.
     * @return o valor ca conta final.
     * @pre carrinho != null
     */
    int pagarConta(String carrinho);
}
