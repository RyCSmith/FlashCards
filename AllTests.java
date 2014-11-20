package flashCards;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Combines test cases for Item and StudyList classes.
 * @author Ryan Smith and Rajveer Parikh
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
        ItemTest.class, StudyListTest.class })
public class AllTests {

}
