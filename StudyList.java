
package flashCards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ryan Smith and Rajveer Parikh
 * This class provides the template for a study list object and it's methods.
 */
public class StudyList {
	
	private final int NUM_PER_ROUND = 20;
	private static Random rand = new Random();

	private ArrayList<String> readLines = new ArrayList<String>();
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private ArrayList<String> saveList = new ArrayList<String>();
    private ArrayList<Item> twentyRandItems = new ArrayList<Item>();
    private ArrayList<Item> findList = new ArrayList<Item>();

/**
 * Default constructor.    
 */
    public StudyList() {
       //this does not require variable initialization. Files can be loaded and new files can be created using
    	//load and saveAs functions. 
    }
/**
 * Appends an item to the study list.    
 * @param item Item object
 */
    public void add(Item item) {
    	itemList.add(item);  
    }

/**
 * Takes a string and creates a new item object. Calls add to add item to the study list.
 * @param string String from which to create new Item object.
 * @throws java.lang.ArithmeticException
 */
    public void addHelper(String string){
    	String[] stringItems = string.split(" *\\|\\| *"); 
    	if (stringItems.length != 2){
    		throw new ArithmeticException();
    	}
		String stimulus = stringItems[0].trim();
		String response = stringItems[1].trim();
		if (Character.isLetterOrDigit(response.charAt(0)) == false){
			throw new ArithmeticException();
		}
		for (Item item: itemList){
			if (stimulus.equalsIgnoreCase(item.getStimulus())){
				throw new ArithmeticException();
			}
		}
		Item currentItem = new Item(stimulus, response);
		add(currentItem);
    }
 
/**
 * Finds first item whose stimulus or response contains the search string
 * @param stimulusOrResponse String containing a stimulus of response for search.
 * @return Item containing the provided stimulus or response. If no item found creates a new Item("NothingHere", "NothingHere").
 */
    public Item find(String stimulusOrResponse){
    	String titles = null;
    	int index = 0;
    	stimulusOrResponse = stimulusOrResponse.toLowerCase().trim();
    	for (Item item : itemList)
        {
               titles = item.getStimulus();  
               titles = titles + item.getResponse();
               titles = titles.toLowerCase().trim();
               index = titles.indexOf(stimulusOrResponse);
                if (index != -1)
                     return item;
        }
    	return (new Item("NothingHere", "NothingHere"));
    }
/**
 * Finds search term in item stimuli and responses and returns string of them from items in which it is found.
 * @param stimulusOrResponse String that takes the character of words to search for.
 * @return Returns a String listing the stimulus and response of each item containing the search term.
 */
    public String findPartial(String stimulusOrResponse) {
    		
    		findList.clear();
              String needle = stimulusOrResponse;
              String haystack = null;
               int index = 0;
              needle = needle.toLowerCase().trim();
               for (Item item : itemList)
              {
                     haystack = item.getStimulus();  
                     haystack = haystack + item.getResponse();
                     haystack = haystack.toLowerCase().trim();
                     index = haystack.indexOf(needle);
                      if (index != -1)
                           findList.add(item);
              }
               String holder = "";
   				if (findList.size() > 0){
   					for (Item item : findList){
   						holder = holder + item.getStimulus() + ":   " + item.getResponse() + "\n";
   					}
   				}
   				else{
   					holder = "Your search did not produce any results.";
   				}
			return holder; 
    }

/**
 * Removes an Item object from the study list.
 * @param item Item object to be removed from the study list.
 */
    public void delete(Item item) {
        itemList.remove(item);
    }

/**
 * Verifies input and find the item in the study list at the given index. Call delete() to remove the item.    
 * @param string Index of object to be removed.
 * @throws java.lang.ArithmeticException
 */
    public void deleteHelper(String string){
    	for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
    			throw new ArithmeticException();
            }
        }
    	if (Integer.parseInt(string.trim()) > itemList.size() - 1){
    		throw new ArithmeticException();
    	}
    	delete(itemList.get(Integer.parseInt(string.trim())));
    }
/**
 * Modifies the details of an item in the study list.    
 * @param item Item to be modified.
 * @param newStimulus String with new stimulus for selected item.
 * @param newResponse String with new response for selected item.
 */
    public void modify(Item item, String newStimulus, String newResponse) {
        item.setStimulus(newStimulus);
        item.setResponse(newResponse);
        item.setTimesCorrect(0);
    }

/**
 * Breaks user input into materials for Item and then calls modify.
 * @param string String taking the index of the number to modify and new stimulus and response from user.
 * @throws java.lang.ArithmeticException
 */
    public void modifyHelper(String string){
    	String[] stringItems = string.split(" *\\|\\| *"); 
    	if (stringItems.length != 3){
    		throw new ArithmeticException();
    	}
    	for (int i = 0; i < stringItems[0].length(); i++) {
            if (!Character.isDigit(stringItems[0].charAt(i))) {
    			throw new ArithmeticException();
            }
        }
    	if (Integer.parseInt(stringItems[0].trim()) > itemList.size() - 1){
    		throw new ArithmeticException();
    	}
		String stimulus = stringItems[1].trim();
		String response = stringItems[2].trim();
		if (Character.isLetterOrDigit(response.charAt(0)) == false){
			throw new ArithmeticException();
		}
		if (Character.isLetterOrDigit(stimulus.charAt(0)) == false){
			throw new ArithmeticException();
		}
    	Item currentItem = itemList.get(Integer.parseInt(stringItems[0].trim()));
		modify(currentItem, stimulus, response);
    }

/**
 * Randomly picks 20 items for a study session and stores them in a list.    
 */
    public void generateListOf20(){
    	//loops until 20 random items have been added to twentyRandItemsList
    	int numberAddedToList = 0;
    	while (numberAddedToList <= NUM_PER_ROUND){
    		Item currentItem = itemList.get(rand.nextInt(itemList.size()-1));
    		if (currentItem.getTimesCorrect() < 4){
        		twentyRandItems.add(currentItem);
        		numberAddedToList++;
    		}
    	}
    }

/**
 * Loads a file.
 * @throws java.io.IOException
 */
    public void load() throws IOException {
    	readLines.clear();
    	itemList.clear();
    	saveList.clear();
    	twentyRandItems.clear();
    	readLines = SimpleIO.load();
    	for (String s: readLines){
    		String[] stringItems = s.split(" *\\|\\| *"); 
    		String stimulus = stringItems[0].trim();
    		String response = stringItems[1].trim();
    		Item currentItem = new Item(stimulus, response);
    		if (stringItems.length > 2){
    			currentItem.setTimesCorrect(Integer.parseInt(stringItems[2].trim()));
    		}
    		itemList.add(currentItem);
    	}    
    }

/**
 * Saves changes to original document.    
 * @throws java.io.IOException
 */
    public void save() throws IOException {
    	for (Item item : itemList){
    		String currentLine = item.getStimulus() + " || " + item.getResponse();
    		if (item.getTimesCorrect() > 0){
    			currentLine = currentLine + " || " + String.valueOf(item.getTimesCorrect());
    		}
    		saveList.add(currentLine);
    	}
		SimpleIO.save(saveList);        
    }

/**
 * Saves changes to document in a new file.
 * @throws IOException Throws exception when I/O error occurs.
 */
    public void saveAs() throws IOException {
    	for (Item item : itemList){
    		String currentLine = item.getStimulus() + " || " + item.getResponse();
    		if (item.getTimesCorrect() > 0){
    			currentLine = currentLine + " || " + String.valueOf(item.getTimesCorrect());
    		}
    		saveList.add(currentLine);
    	}
    	SimpleIO.saveAs(saveList);
        
    }

/**
 * Returns list of twenty randomly selected items.
 * @return list of twenty random items.
 */
	public ArrayList<Item> getTwentyRandItems() {
		return twentyRandItems;
	}

/**
 * Returns list of all items read from file.	
 * @return list of all items read from file.
 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}
}
