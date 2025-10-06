/**
 * Interface File.
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

public interface File {

    /**
     * Devolve o nome do ficheiro.
     * @return o nome do ficheiro.
     */
     String getName();

    /**
     * Devolve o nome do dono do ficheiro.
     * @return o nome do dono do ficheiro.
     */
     String getOwnerName();

    /**
     * Devolve o tamanho do ficheiro.
     * @return o tamanho do ficheiro.
     */
    int getSize();

}
