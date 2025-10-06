/**
 * Class Critic - extends Reviewer.
 * The reviews made by critics are 5 times more important than the reviews made by the audience.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public class Critic extends Reviewer {

    /**
     * Critic constructor.
     * Creates a new Critic user.
     *
     * @param username the username.
     * @pre username != null
     */
    public Critic(String username) {
        super(username);
    }

    @Override
    public int getReviewImportance() {
        return 5;
    }
}
