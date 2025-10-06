/**
 * Exception UserCannotReviewShowsException.
 * Exception thrown when the user is an admin user. Admin users cannot review shows.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package exceptions;

public class UserCannotReviewShowsException extends Exception {

    /**
     * UserCannotReviewShowsException constructor.
     */
    public UserCannotReviewShowsException() {
        super();
    }
}
