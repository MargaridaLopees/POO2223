package Objects;

/**
 * Classe que extende a classe bastrata ShapeClass.
 * Forma: RECTANGULO.
 * @author Margarida Lopes 64557 LEI
 */

public class RecClass extends ShapeClass {

    private static final String SHAPE = "RECTANGLE";
    private int height;
    private int width;

    public RecClass (String ID, int x, int y, int height, int width) {
        super(ID, x, y, SHAPE);
        this.height = height;
        this.width = width;
    }

    @Override
    public double area() {
        double area = height*width;
        return area;
    }
}
