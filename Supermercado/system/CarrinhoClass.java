/**
 * Classe CarrinhoClass - implementa a interface Carrinho;
 * @author Margarida Lopes 64557
 */

package system;

import java.util.Iterator;
import java.util.LinkedList;

public class CarrinhoClass implements Carrinho {

    private final String ID;
    private int capacidade;

    private LinkedList<Artigo> artigos;


    public CarrinhoClass (String ID, int capacidade) {
        this.ID = ID;
        this.capacidade = capacidade;
        artigos = new LinkedList<>();
    }


    @Override
    public String getID() {
        return ID;
    }

    @Override
    public int getCapacidade() {
        return capacidade;
    }

    @Override
    public void addArtigo(Artigo artigo) {
        artigos.add(artigo);
        capacidade = capacidade - artigo.getVolume();
    }

    @Override
    public boolean hasCapacidade(int volume) {
        int volumeFinal = capacidade - volume;
        boolean hasCapacidade = true;
        if (volumeFinal < 0)
            hasCapacidade = false;
        return hasCapacidade;
    }

    @Override
    public boolean hasArtigo(Artigo artigo) {
        return artigos.contains(artigo);
    }


    @Override
    public void removeArtigo(Artigo artigo) {
        artigos.remove(artigos.remove(artigo));
    }

    @Override
    public Iterator<Artigo> artigosIterator() {
        return artigos.iterator();
    }

    @Override
    public int getTotal() {
        int total = 0;
        for (int i = 0; i != artigos.size(); i++) {
            total += artigos.get(i).getPreco();
        }
        return total;
    }

    @Override
    public void limparCarrinho() {
        for (int i = artigos.size()-1 ; i >= 0 ; i--) {
            artigos.remove(i);
        }
    }

    @Override
    public boolean hasArtigos() {
        return artigos.size() > 0;
    }
}
