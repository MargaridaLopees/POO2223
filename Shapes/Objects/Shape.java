package Objects;

/**
 * Forma. Cada forma pode ser um circulo ou um retangulo.
 * @author Margarida Lopes 64557 LEI
 */

public interface Shape {


    /**
     * Devolve o identificador da figura.
     * @return o identificador da figura.
     */
    String getID();

    /**
     * Devolve o tipo da figura.
     * @return o tipo da figura.
     */
    String getShape();

    /**
     * Retorna o valor da abcissa da figura.
     * @return o valor da abcissa da figura.
     */
    int getCenterX();

    /**
     * Retorna o valor da ordenada da figura.
     * @return o valor da ordenada da figura.
     */
    int getCenterY();

    /**
     * Move a figura para as coordenadas fornecidas.
     * @param x o valor da nova abcissa da figura.
     * @param y o valor da nova ordenada na origem da figura.
     * @pre abcissa != null && ordenada != null
     */
    void move(int x, int y);

    /**
     * Calcula e devolve o valor da area da figura.
     * @return o valor da area da figura.
     */
    double area();
}