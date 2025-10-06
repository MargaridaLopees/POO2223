/**
 * Iterador para a colecao de pessoas registadas na rede social.
 * @autor Margarida Lopes 64557 LEI
 */

public class IteradorRegistados implements Iterador {

    private Pessoa[] registados;
    private int nrRegistados;
    private int next;

    public IteradorRegistados(Pessoa[] registados, int nrRegistados) {
        this.registados = registados;
        this.nrRegistados = nrRegistados;
        next = 0;
    }

    @Override
    public boolean hasNext() {
        return (next < nrRegistados);
    }

    @Override
    public Pessoa next() {
        return registados[next++];
    }

}