
package flashCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Cases for StudyList class.
 * @author Ryan Smith and Rajveer Parikh
 */
public class StudyListTest {

    StudyList studylist;
    
    @Before
    public void setUp() throws Exception {
    	studylist = new StudyList();
    	studylist.addHelper("First || 1st");
    	studylist.addHelper("Second || 2nd");
    	studylist.addHelper("Third || 3rd");
    	studylist.addHelper("Fourth || 4th");
    	studylist.addHelper("Fifth || 5th");

    }


    /**
     * Test method for {@link flashCards.StudyList#add(flashCards.Item)}.
     */
    @Test
    public final void testAdd() {
        assertEquals(5, studylist.getItemList().size());
    	studylist.addHelper("test || test");
        assertEquals(6, studylist.getItemList().size());
        studylist.addHelper("test2 || test2");
        assertEquals(7, studylist.getItemList().size());
    }

    /**
     * Test method for {@link flashCards.StudyList#find(java.lang.String)}.
     */
    @Test
    public final void testFind() {
    	assertEquals(studylist.getItemList().get(1), studylist.find("Second"));
    	assertEquals(studylist.getItemList().get(2), studylist.find("Third"));
    	assertEquals(studylist.getItemList().get(3), studylist.find("Fourth"));
    }

    /**
     * Test method for {@link flashCards.StudyList#delete(flashCards.Item)}.
     */
    @Test
    public final void testDelete() {
    	assertEquals(5, studylist.getItemList().size());
    	assertEquals("Fourth", studylist.getItemList().get(3).getStimulus());
    	studylist.deleteHelper("3");
    	assertEquals(4, studylist.getItemList().size());
    	assertNotEquals("Fourth", studylist.getItemList().get(3).getStimulus());
    }

    /**
     * Test method for {@link flashCards.StudyList#modify(flashCards.Item, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testModify() {
    	studylist.modifyHelper("2 || Sixth || 6th");
    	assertEquals("Sixth", studylist.getItemList().get(2).getStimulus());
    	assertEquals("6th", studylist.getItemList().get(2).getResponse());
    	studylist.modifyHelper("3 || Seventh|| 7th");
    	assertEquals("Seventh", studylist.getItemList().get(3).getStimulus());
    	assertEquals("7th", studylist.getItemList().get(3).getResponse());
    }

}
