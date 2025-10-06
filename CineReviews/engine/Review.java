/**
 * Interface Review.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public interface Review extends Comparable<Review> {

    /**
     * Returns the comment associated with the review.
     *
     * @return the comment associated with the review.
     */
    String getComment();

    /**
     * Returns the classification of the review (excellent, good, average, poor, or terrible).
     *
     * @return the classification of the review.
     */
    String getClassification();

    /**
     * Returns the type of the reviewer (critic or audience).
     *
     * @return the type of the reviewer (critic or audience).
     */
    String getReviewerType();

    /**
     * Returns the user that made the review.
     *
     * @return the user that made the review.
     */
    User getReviewer();

    /**
     * Returns the score of the review.
     * If the classification is 'excellent' the score is 5, if it is 'good' the score is 4, 'average' is 3,
     * 'poor' is 2 and 'terrible' is 1.
     *
     * @return the score of the review.
     */
    int getScore();
}
