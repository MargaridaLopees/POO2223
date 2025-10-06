package Objects;

/**
 * A colecao das formas. Para varios tipos de formas (circulos e retangulos).
 * @author Margarida Lopes 64557 LEI
 */

public class ShapesCollectionClass implements ShapesCollection {

    private static final int DEFAULT = 500;

    private int nrShapes;
    private Shape[] shapes;


    public ShapesCollectionClass () {
        nrShapes = 0;
        shapes = new Shape[DEFAULT];
    }


    @Override
    public boolean isEmpty() {
        return nrShapes<=0;
    }

    @Override
    public boolean hasShape(String ID) {
        int i = 0;
        boolean result = true;
        while (i < nrShapes && !shapes[i].getID().equals(ID)) {
            i++;
        }
        if (i >= nrShapes) {
            result = false;
        }
        return result;
    }

    @Override
    public void addCircle(String ID, int x, int y, int radius) {
        shapes[nrShapes++] = new CircleClass(ID, x, y, radius);
    }

    @Override
    public void addRectangle(String ID, int x, int y, int height, int width) {
        shapes[nrShapes++] = new RecClass(ID, x, y, height, width);
    }


    @Override
    public void move(String ID, int x, int y) {
        int i = 0;
        while (!shapes[i].getID().equals(ID)) {
            i++;
        }
        shapes[i].move(x,y);
    }

    @Override
    public Shape smallestArea() {
        int i = 0;
        double minArea = Integer.MAX_VALUE;
        int shape = 0;
        while (i < nrShapes) {
            if (shapes[i].area() <= minArea) {
                minArea = shapes[i].area();
                shape = i;
            }
            i++;
        }
        return shapes[shape];
    }

    @Override
    public Iterator allShapesIterator() {
        return new IteratorClass(shapes, nrShapes);
    }
}
