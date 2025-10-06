/**
 * Classe SupermercadoClass - implemena a interface Supermercado.
 * @author Margarida Lopes 64557
 */

package system;

import excecoes.*;

import java.util.Iterator;
import java.util.LinkedList;

public class SupermercadoClass implements Supermercado {

    private LinkedList<Carrinho> carrinhos;
    private LinkedList<Artigo> artigos;


    public SupermercadoClass () {
        carrinhos = new LinkedList<>();
        artigos = new LinkedList<>();
    }

    /**
     * Verifica se um dado carrinho existe.
     * @param carro o carrinho a verificar.
     * @return true se o carrinho existir e false se nao existir.
     * @pre carro != null
     */
    private boolean hasCarrinho (String carro) {
        boolean found = false;
        int i = 0;
        while (i != carrinhos.size() && !found) {
            if (carrinhos.get(i).getID().equals(carro))
                found = true;
            i++;
        }
        return found;
    }

    /**
     * Verifica se um dado artigo existe.
     * @param artigo o artigo a verificar.
     * @return true se o artigo existor e false se nao existir.
     * @pre artigo != null
     */
    private boolean hasArtigo (String artigo) {
        boolean found = false;
        int i = 0;
        while (i != artigos.size() && !found) {
            if (artigos.get(i).getNome().equals(artigo))
                found = true;
            i++;
        }
        return found;
    }

    @Override
    public void addCarrinho(String ID, int capacidade) throws CarroJaExistenteException {
        Carrinho carro = new CarrinhoClass(ID, capacidade);
        if (hasCarrinho(carro.getID()))
            throw new CarroJaExistenteException();
        else {
            carrinhos.add(carro);
        }
    }

    @Override
    public void addArtigo(String nome, int preco, int volume) throws ArtigoJaExistenteException {
        Artigo artigo = new ArtigoClass(nome, preco, volume);
        if (hasArtigo(artigo.getNome()))
            throw new ArtigoJaExistenteException();
        else {
            artigos.add(artigo);
        }
    }

    /**
     * Devolve o carrinho com o nome dado.
     * @param carrinho o nome do carinho.
     * @return o carrinho com o nome dado.
     * @pre carrinho != null
     */
    private Carrinho getCarrinho (String carrinho) {
        Carrinho c = null;
        boolean found = false;
        int i = 0;
        while (i < carrinhos.size() && !found) {
            if (carrinhos.get(i).getID().equals(carrinho)) {
                found = true;
                c = carrinhos.get(i);
            }
            i++;
        }
        return c;
    }

    /**
     * Devolve o artigo com o nome dado.
     * @param artigo o nome do artigo.
     * @return o artigo com o nome dado.
     * @pre artigo != null
     */
    private Artigo getArtigo (String artigo) {
        Artigo a = null;
        boolean found = false;
        int i = 0;
        while (i != artigos.size() && !found) {
            if (artigos.get(i).getNome().equals(artigo)) {
                found = true;
                a = artigos.get(i);
            }
            i++;
        }
        return a;
    }

    @Override
    public void depositarArtigo(String artigo, String carrinho) throws CarroNaoExistenteException,
            CapacidadeExcedidaException, ArtigoNaoExistenteException{
        if (!hasCarrinho(carrinho)) {
            throw new CarroNaoExistenteException();
        }  if (!hasArtigo(artigo)) {
            throw new ArtigoNaoExistenteException();
        }
        Artigo a = getArtigo(artigo);
        Carrinho c = getCarrinho(carrinho);
        if (!c.hasCapacidade(a.getVolume())) {
            throw new CapacidadeExcedidaException();
        } else {
            c.addArtigo(a);
        }
    }

    @Override
    public void removeArtigo(String artigo, String carrinho) throws CarroNaoExistenteException, ArtigoNaoExistenteException {
        if (!hasCarrinho(carrinho))
            throw new CarroNaoExistenteException();
        else if (!getCarrinho(carrinho).hasArtigo(getArtigo(artigo)))
            throw new ArtigoNaoExistenteException();
        else getCarrinho(carrinho).removeArtigo(getArtigo(artigo));
    }

    @Override
    public Iterator<Artigo> listaArtigos(String carrinho) {
        if (!hasCarrinho(carrinho)) {
            throw new CarroNaoExistenteException();
        }
        Carrinho c = getCarrinho(carrinho);
        if (!c.hasArtigos()) {
            throw new CarrinhoVazioException();
        }
        return c.artigosIterator();
    }

    @Override
    public int pagarConta(String carrinho) {
        if (!hasCarrinho(carrinho))
            throw new CarroNaoExistenteException();
        Carrinho c = getCarrinho(carrinho);
        if (!c.hasArtigos())
            throw new CarrinhoVazioException();
        else {
            int total = c.getTotal();
            c.limparCarrinho();
            return total;
        }
    }
}
