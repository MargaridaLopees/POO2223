/**
 * Interface User.
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

import DataStructure.Iterator;

public interface User {

    /**
     * Devolve o nome do utilizador.
     * @return o nome do utilizador.
     */
     String getName();

    /**
     * Devolve o tipo da conta (basic ou premium).
     * @return o tipo da conta.
     */
     String getType();

    /**
     * Devolve o espaço disponivel para ficheiros na conta.
     * @return o espaço disponivel para ficheiros na conta.
     */
     int getSpace();

    /**
     * Devolve o ficheiro com o nome dado.
     * @param name o nome do ficheiro.
     * @return o ficheiro com o nome dado.
     * @pre name != null
     */
     File getFile(String name);

    /**
     * Verifica se o utilizador pode partilhar ficheiros.
     * @return true se o utilizador puder partilhar ficheiros, e false se nao puder.
     */
     boolean canShare();

    /**
     * Verifica se o utilizador ja possui o ficheiro.
     * @param file o ficheiro.
     * @return true se o utilizaodr possuir o ficheiro na sua conta, e false se nao possuir.
     * @pre file != null
     */
     boolean hasOwnedFile(String file);

    /**
     * Verifica se o utilizaor possui o ficheiro partilhado.
     * @param file o ficheiro.
     * @param owner o nme do dono do ficheiro.
     * @return true se o utilizaodr possuir a partilha, e false se nao posuir.
     * @pre file != null && owner != null
     */
    boolean hasSharedFile (String file, String owner);

    /**
     * Adiciona um ficheiro à conta do utilizador.
     * @param name o nome do ficheiro.
     * @param size o tamanho do ficheiro.
     * @param owner o nome do dono do ficheiro.
     * @pre name != null && size != null && owner != null
     */
    void addFile(String name, int size, String owner);

    /**
     * Verifica se o utilizador possui espaco para receber a partilha do ficheiro.
     * @param size o tamanho do ficheiro a ser partilhado.
     * @return true se possuir espaco e false se nao possuir.
     * @pre size != null
     */
    boolean hasSharedCapacity (int size);


    void setSpaceSharedFile(int size);


    void setSpaceAddFile(int size);

    /**
     * Adiciona um ficheiro partilhado.
     * @param file o ficheiro.
     * @pre file != null
     */
    void addShareFile(File file);

    /**
     * Devolce um iterador para todos os ficheiros da conta do utilizador.
     * @return um iterador.
     */
    Iterator<File> iterator();

}
