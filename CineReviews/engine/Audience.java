/**
 * Class Audience - extends Reviewer.
 * The reviews made by audience are 5 times less important than the reviews made by the critics.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public class Audience extends Reviewer {

    /**
     * Audience constructor.
     * Creates a new Audience user.
     *
     * @param username the username.
     * @pre username != null
     */
    public Audience(String username) {
        super(username);
    }

    @Override
    public int getReviewImportance() {
        return 1;
    }

}
