/**
 * Iterador para a colecao de amigos.
 * @autor Margarida Lopes 64557 LEI
 */

public class IteradorAmigos implements Iterador {

    private Pessoa[] amigos;
    private int nrAmigos;
    private int next;

    public IteradorAmigos (Pessoa pessoa, int nrAmigos) {
        amigos = pessoa.getAmigos();
        this.nrAmigos = nrAmigos;
        next=0;
    }

    @Override
    public boolean hasNext() {
        return (next < nrAmigos);
    }

    @Override
    public Pessoa next() {
        return amigos[next++];
    }
}
