/**
 * Interface User.
 * Users can be admins (responsible for adding information about the shows and artists)
 * or reviewers (critics or audience).
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public interface User {

    /**
     * Returns the username.
     *
     * @return the username.
     */
    String getUserName();

}
