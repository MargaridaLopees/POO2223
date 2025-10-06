/**
 * Class Reviewer - extends UserClass.
 * Reviewers can make reviews about shows.
 * There are two types of reviewers: Critic and Audience.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.ArrayList;
import java.util.List;

public abstract class Reviewer extends UserClass {

    /**
     * The collection of
     */
    private final List<Show> reviewedShows;

    /**
     * Reviewer constructor.
     * Creates a new Reviewer user.
     *
     * @param username the username.
     * @pre username != null
     */
    public Reviewer(String username) {
        super(username);
        reviewedShows = new ArrayList<>();
    }

    /**
     * Returns the importance rate of the review.
     * The importance rate of a review made by a critic user is '5' and audience is '1'.
     *
     * @return the importance rate of the review.
     */
    public abstract int getReviewImportance();

    /**
     * Returns the number of reviews made by the user.
     *
     * @return the number of reviews made by the user.
     */
    public int getNumberReviews() {
        return reviewedShows.size();
    }

    public void addReview(Show show) {
        reviewedShows.add(show);
    }

}
