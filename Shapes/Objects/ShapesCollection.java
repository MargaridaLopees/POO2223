package Objects;

import Objects.Iterator;

/**
 * A colecao das formas. Para varios tipos de formas (circulos e retangulos).
 * @author Margarida Lopes 64557 LEI
 */

public interface ShapesCollection {

    /**
     * Verifica se existe alguma forma na colacao.
     * @return true caso exista alguma forma na colecao;
     *          false caso a colecao esteja vazia.
     */
    boolean isEmpty();

    /**
     * Verifica se uma determinada forma existe na colecao.
     * @param ID a forma a verificar se existe na colecao.
     * @return true caso a forma exista na colecao;
     *          false caso a figura nao exista na colecao.
     * @pre shape != null
     */
    boolean hasShape(String ID);

    /**
     * Adiciona um novo circulo a colecao.
     * @param ID o nome do circulo a adicionar.
     * @param x o valor da abcissa da posicao do circulo.
     * @param y o valor da ordenada na origem do circulo.
     * @param radius o valor do raio do circulo.
     * @pre ID != null && abcissa != null && ordenada != null & raio != null
     */
    void addCircle(String ID, int x, int y, int radius);

    /**
     * Adiciona um novo retangulo a colecao.
     * @param ID o nome do retangulo.
     * @param x o valor da abcissa do rectangulo.
     * @param y o valor da ordenada do centro do retangulo.
     * @param height o valor da altura do retangulo.
     * @param width o valor da largura do rectangulo.
     * @pre ID != null && abcissa != null && ordenada != null && altura != null && largura != null
     */
    void addRectangle (String ID, int x, int y, int height, int width);

    /**
     * Movimenta uma determinada figura.
     * @param ID o nome da figura a movimentar.
     * @param x o valor da nova abcissa da figura.
     * @param y o valor da nova ordenada da figura.
     * @pre name != null && abcissa != null && oredenada != null
     */
    void move(String ID, int x, int y);

    /**
     * Devolve a figura com menor area da colecao.
     * @return a figura com menor area da colecao.
     */
    Shape smallestArea();

    /**
     * Devolve um iterador para todas as figuras da colecao.
     * @return um iterador para todas as figuras da colecao.
     */
    Iterator allShapesIterator();

}
