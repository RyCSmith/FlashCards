
package flashCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Cases for Item class.
 * @author Ryan Smith and Rajveer Parikh
 */
public class ItemTest {
	Item item1;
	Item item2;
	Item item3;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    	item1 = new Item("stim1", "resp1");
    	item2 = new Item("stim2", "resp2");
    	item3 = new Item("stim3", "resp3");
    }


    /**
     * Test method for {@link flashCards.Item#setStimulus(java.lang.String)} and
     * {@link flashCards.Item#getStimulus()} (combined).
     */
    @Test
    public final void testSetAndGetStimulus() {
        assertEquals("stim1", item1.getStimulus());
        item1.setStimulus("newStim1");
        assertEquals("newStim1", item1.getStimulus());
        assertEquals("stim2", item2.getStimulus());
        item2.setStimulus("newStim2");
        assertEquals("newStim2", item2.getStimulus());
        assertEquals("stim3", item3.getStimulus());
        item3.setStimulus("newStim3");
        assertEquals("newStim3", item3.getStimulus());
        
    }

    /**
     * Test method for {@link flashCards.Item#setResponse(java.lang.String)} and
     * {@link flashCards.Item#getResponse()} (combined).
     */
    @Test
    public final void testSetAndGetResponse() {
        assertEquals("resp1", item1.getResponse());
        item1.setResponse("newResp1");
        assertEquals("newResp1", item1.getResponse());
        assertEquals("resp2", item2.getResponse());
        item2.setResponse("newResp2");
        assertEquals("newResp2", item2.getResponse());
        assertEquals("resp3", item3.getResponse());
        item3.setResponse("newResp3");
        assertEquals("newResp3", item3.getResponse());
    }

    /**
     * Test method for {@link flashCards.Item#setTimesCorrect(int)} and
     * {@link flashCards.Item#getTimesCorrect()} (combined).
     */
    @Test
    public final void testSetAndGetTimesCorrect() {
    	assertEquals(0, item1.getTimesCorrect());
        item1.setTimesCorrect(3);
        assertEquals(3, item1.getTimesCorrect());
        item2.setTimesCorrect(2);
        assertEquals(2, item2.getTimesCorrect());
        item3.setTimesCorrect(7);
        assertEquals(7, item3.getTimesCorrect());
    }

}
