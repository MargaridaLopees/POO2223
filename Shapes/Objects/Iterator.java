package Objects;

/** Margarida Lopes 64557 LEI */

public interface Iterator {

    /**
     * Verifica se existe mais uma figura para iterar.
     * @return true se existir mais um forma a iterar,
     *         false se nao existir mais nenhuma forma a iterar.
     */
    boolean hasNext();

    /**
     * Devolve a proxima figura da colecao.
     * @return a proxima figura da colecao.
     */
    Shape next();
}
