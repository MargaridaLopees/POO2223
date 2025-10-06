package Objects;

/**
 * Classe abstrata.
 * Forma. Cada forma pode ser um circulo ou um retangulo.
 * @author Margarida Lopes 64557 LEI
 */

public abstract class ShapeClass implements Shape {

    public ShapeClass (String ID, int x, int y, String shape){
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.shape = shape;
    }

    private final String shape;
    private final String ID;
    private int x;
    private int y;


    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getShape(){
        return shape;
    }

    @Override
    public int getCenterX() {
        return x;
    }

    @Override
    public int getCenterY() {
        return y;
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    @Override
    public abstract double area();



}
