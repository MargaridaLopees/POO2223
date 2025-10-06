package Objects;

/**
 * Classe que extende a classe bastrata ShapeClass.
 * Forma: CIRCULO.
 * @author Margarida Lopes 64557 LEI
 */

public class CircleClass extends ShapeClass {

    private static final String SHAPE = "CIRCLE";
    private int radius;

    public CircleClass (String ID, int x, int y, int radius) {
        super(ID, x, y, SHAPE);
        this.radius = radius;
    }

    @Override
    public double area() {
        double area = Math.PI*(radius*radius);
        return area;
    }
}
