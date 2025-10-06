/**
 * Class UserClass - implements User.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public abstract class UserClass implements User {

    /**
     * The username.
     * Each registered user is characterised by a username (unique identifier).
     */
    private final String username;

    /**
     * Creates a new User.
     *
     * @param username the username.
     * @pre username != null
     */
    public UserClass(String username) {
        this.username = username;
    }

    @Override
    public String getUserName() {
        return username;
    }


}
