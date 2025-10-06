/**
 * Excecao NoContactsException.
 * @author Margarida Lopes, n64557
 */

package exceptions;

/**
 * Excecao para quando nao existirem contactos na agenda.
 */
public class NoContactsException extends RuntimeException {

    /**
     * Construtor da excecao.
     */
    public NoContactsException () {
        super ();
    }
}
