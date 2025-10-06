/**
 * Classe ArtigoClass - implementa a interface Artigo
 * @author Margarida Lopes 64557
 */
package system;


public class ArtigoClass implements Artigo {

    private final String nome;
    private final int volume;
    private final int preco;

    public ArtigoClass (String nome, int preco, int volume) {
        this.nome = nome;
        this.volume = volume;
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public int getPreco() {
        return preco;
    }
}
