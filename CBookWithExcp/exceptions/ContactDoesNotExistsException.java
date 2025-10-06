/**
 * Excecao ContactDoesNotExistsException.
 * @author Margarida Lopes, n64557
 */

package exceptions;

/**
 * Excecao para quando o contacto nao existir na agenda.
 */
public class ContactDoesNotExistsException extends RuntimeException{

    /**
     * Construtor da excecao.
     */
    public ContactDoesNotExistsException () {
        super();
    }
}
