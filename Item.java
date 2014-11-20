
package flashCards;

/**
 * Item class. Items contain pairs of stimuli and responses. 
 * @author Ryan Smith and Rajveer Parikh
 *
 */
public class Item {

    private String response;
    private String stimulus;
    private int timesCorrect = 0;

/**
 * Default constructor.
 * @param stimulus String specifying stimulus.
 * @param response String specifying response.
 */
    public Item(String stimulus, String response) {
        this.stimulus = stimulus;
    	this.response = response;
    }

/**
 * Returns stimulus.
 * @return String - stimulus.
 */
    public String getStimulus() {
        return stimulus;
    }
    
/**
 * Allows user to set stimulus of the item.
 * @param stimulus String to set stimulus.
 */
    public void setStimulus(String stimulus) {
        this.stimulus = stimulus;
    }

/**
 * Returns response.
 * @return String - response.
 */
    public String getResponse() {
        return response;
    }
 
/**
 * Allows user to set response of the item.
 * @param response String to set response.
 */
    public void setResponse(String response) {
        this.response = response;
    }

/**
 * Returns int representing number of times in a row current item has been answered correctly.
 * @return int - timesCorrect.
 */
    public int getTimesCorrect() {
        return timesCorrect;
    }

/**
 * Allows user to set number of times in a row the item has been answered correctly.
 * @param times int to set timesCorrect.
 */
    public void setTimesCorrect(int times) {
        timesCorrect = times;
    }
}
