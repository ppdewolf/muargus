package muargus.model;

/**
 * Model class containing relevant information for rank swapping. An instance
 * for each numerical variable of this class will exist.
 *
 * @author Statistics Netherlands
 */
public class RankSwappingSpec extends ReplacementSpec {

    private final double percentage;

    /**
     * Constructor of the model class Microaggregation. Makes empty arraylists
     * for the variables and the microaggregations.
     *
     * @param percentage Double containing the rank swapping percentage.
     */
    public RankSwappingSpec(double percentage) {
        this.percentage = percentage;
    }

    /**
     * Gets the rank swapping percentage.
     *
     * @return Double containing the rank swapping percentage.
     */
    public double getPercentage() {
        return percentage;
    }

}
