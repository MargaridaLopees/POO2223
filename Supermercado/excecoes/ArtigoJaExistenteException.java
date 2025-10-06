/**
 * Excecao ArtigoJaExistenteException
 * @author Margarida Lopes 64557
 */

package excecoes;

/**
 * Excecao para quando o identificador para o artigo ja existir.
 */
public class ArtigoJaExistenteException extends RuntimeException{

    /**
     * Construtor da excecao.
     */
    public ArtigoJaExistenteException() { super (); }
}