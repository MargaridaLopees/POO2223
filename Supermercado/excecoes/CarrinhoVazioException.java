/**
 * Excecao CarrinhoVazioException
 * @author Margarida Lopes 64557
 */

package excecoes;

/**
 * Excecao para quando o carrihno de encontra vasio, sem artigos adicionados.
 */
public class CarrinhoVazioException extends RuntimeException {

    /**
     * Construtor da excecao.
     */
    public CarrinhoVazioException () { super (); }
}
