/**
 * Class ReviewClaa - implements Review.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public class ReviewClass implements Review {

    /**
     * The user who wroted the review.
     */
    private final User reviewer;

    /**
     * The title of the show the review belongs to.
     */
    private final String showTitle;

    /**
     * The comment associated with the review.
     */
    private final String comment;

    /**
     * The classification of the review.
     */
    private final String classification;

    /**
     * ReviewClass constructor.
     * Creates a new review.
     *
     * @param reviewer       the user who wroted the review
     * @param showTitle      the title of the show the review belongs to.
     * @param comment        the comment associated with the review.
     * @param classification the classification of the review.
     * @pre reviewerUsername != null && showTitle != null && comment != null && classification != null
     */
    public ReviewClass(User reviewer, String showTitle, String comment, String classification) {
        this.reviewer = reviewer;
        this.showTitle = showTitle;
        this.comment = comment;
        this.classification = classification;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getClassification() {
        return classification;
    }

    @Override
    public String getReviewerType() {
        String type = null;
        if (reviewer instanceof Critic) {
            type = Constants.CRITIC;
        } else if (reviewer instanceof Audience) {
            type = Constants.AUDIENCE;
        }
        return type;
    }

    @Override
    public User getReviewer() {
        return reviewer;
    }

    @Override
    public int getScore() {
        int score = 0;
        switch (classification) {
            case Constants.EXCELLENT -> score = 5;
            case Constants.GOOD -> score = 4;
            case Constants.AVERAGE -> score = 3;
            case Constants.POOR -> score = 2;
            case Constants.TERRIBLE -> score = 1;
        }
        return score;
    }

    /**
     * Compares this review with other one.
     * It will compare the reviews by first the critics
     * and then the audience members, sorted from the highest to the lowest score
     * and then alphabetically by username.
     *
     * @param other the object to be compared.
     * @return If this review is from a critic and the other is from audience, returns a negative int,
     * if this review is from audience and the other one is from critic, returns a positive int.
     * If this review has a higher score, returns a negative int, if it has a lower score, returns a positive int.
     * Then, compare both reviewer names in alphabetic order.
     * @pre other != null
     */
    @Override
    public int compareTo(Review other) {
        if (this.getReviewerType().equals(Constants.CRITIC) && other.getReviewerType().equals(Constants.AUDIENCE)) {
            return -1;
        }
        if (this.getReviewerType().equals(Constants.AUDIENCE) && other.getReviewerType().equals(Constants.CRITIC)) {
            return 1;
        }
        if (this.getScore() < other.getScore()) {
            return 1;
        }
        if (this.getScore() > other.getScore()) {
            return -1;
        }
        return reviewer.getUserName().compareTo(other.getReviewer().getUserName());
    }


}
