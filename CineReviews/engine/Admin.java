/**
 * Class Admin - extends UserClass.
 * Admin users are responsible for adding information about the shows and artists.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.ArrayList;
import java.util.List;

public class Admin extends UserClass {

    /**
     * The password to access the account.
     */
    private final String password;

    /**
     * Collection of all shows that the user aploaded into the application.
     */
    private final List<Show> uploadedShows;

    /**
     * Admin constructor.
     * Creates a new Admin user.
     *
     * @param username the username.
     * @param password the password.
     * @pre username != null && password != null
     */
    public Admin(String username, String password) {
        super(username);
        this.password = password;
        uploadedShows = new ArrayList<>();
    }

    /**
     * Verifys if the given password is the correct one.
     *
     * @param password the given password to check.
     * @return true if is the same password, and false if it's not te same.
     * @pre password != null
     */
    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }

    /**
     * Returns the number of uploaded shows by the admin user.
     *
     * @return the number of uploaded shows by the admin user.
     */
    public int getNumberShows() {
        return uploadedShows.size();
    }

    /**
     * Uploads a new show.
     * The new show is added to the collection of the admin user who uploaded the show.
     *
     * @param show the show.
     * @pre show != null
     */
    public void uploadShow(Show show) {
        uploadedShows.add(show);
    }
}
