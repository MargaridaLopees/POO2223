/**
 * Interface CloudSharing.
 *
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

import DataStructure.Iterator;

public interface CloudSharing {


    /**
     * Verifica se um dado utilizador se encontra na cloud.
     *
     * @param email o email que identifica o utilizador.
     * @return true se o utilizador existir, false se nao existir.
     * @pre email != null
     */
    boolean hasUser(String email);

    /**
     * Adiciona um novo utilizador.
     *
     * @param email o email do utilizador.
     * @param type  o tipo de conta a ser criada.
     * @pre email != null && type != null
     */
    void addUser(String email, String type);

    /**
     * Verifica se o utilizador possui um dado ficheiro.
     *
     * @param user o utilizaodr.
     * @param file o nome do ficheiro.
     * @return true se o utilizador possuir o ficheiro, false se nao possuir.
     * @pre user != null && file != null
     */
    boolean hasOwnedFile(String user, String file);

    /**
     * Verifica se o utilizador possui espaco para realizar um carregamento de um ficheiro.
     *
     * @param user o utilizador.
     * @param size o tamanho do ficheiro a ser carregado.
     * @return true se o utilizaodr possuir espaco suficiente, false se nao possuir.
     * @pre user != null && size != null
     */
    boolean hasCapacity(String user, int size);

    /**
     * Adiciona um ficheiro a conta do utilizador.
     *
     * @param user o utilizaodr.
     * @param file o nome do ficheiro.
     * @param size o tamanho do ficheiro.
     * @pre user != null && file != null && size != null
     */
    void addFile(String user, String file, int size);

    /**
     * Verifica se e possivel realizar a partilha do ficheiro.
     *
     * @param owner o utilizador que possui o ficheiro.
     * @return true se for possivel partilhar o ficheiro, false se nao fo possivel.
     * @pre owner != null
     */
    boolean allowsSharing(String owner);

    /**
     * Verifica se o ficheiro ja foi partilhado entre os utilizadores.
     *
     * @param owner o dono do ficheiro.
     * @param other o utilizador que ira receber a partilha do ficheiro.
     * @param file  o nome do ficheiro.
     * @return true se a partilha ja foi realizada, false se ainda nao foi realizada.
     * @pre owner != null && other != null && file != null
     */
    boolean hasSharedFile(String owner, String other, String file);

    /**
     * Verifica se existe capacidade suficiente para realizar a partilha.
     *
     * @param owner o dono do ficheiro.
     * @param other o utilizador que ira receber a partilha.
     * @param file  o nome do ficheiro.
     * @return true se existir capacidade possivel para realizar a partilha, false se nao for possivel.
     * @pre owner != null && other != null && file != null
     */
    boolean hasSharingCapacity(String owner, String other, String file);

    /**
     * Realiza a partilha do ficheiro.
     *
     * @param owner o dono do ficheiro.
     * @param other o utilizador que ire receber a partilha.
     * @param file  o nome d ficheiro.
     * @pre owner != null && other != null && file != null
     */
    void shareFile(String owner, String other, String file);

    /**
     * Lista todos os ficheiros de um dado utilizador.
     *
     * @param user o utilizador.
     * @return um iterador para os ficheiros de um dado utilizaodr.
     * @pre user != null
     */
    Iterator<File> listFiles(String user);

    /**
     * Lista todos os utilizadores da CloudSharing.
     *
     * @return um iterador para todos os utilizadores da CloudSharing.
     */
    Iterator<User> listAll();
}
