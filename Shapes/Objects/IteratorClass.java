package Objects;

import Objects.Iterator;

/**
 * Iterador para todas a formas da colecao.
 * @author Margarida Lopes 64557 LEI
 */

public class IteratorClass implements Iterator {

    private Shape[] shapes;
    private int nrShapes;
    private int next;

    public IteratorClass (Shape shapes[], int nrShapes) {
        this.shapes = shapes;
        this.nrShapes = nrShapes;
        next = 0;
    }

    @Override
    public boolean hasNext() {
        return next < nrShapes;
    }

    @Override
    public Shape next() {
        return shapes[next++];
    }
}
