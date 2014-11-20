package flashCards;

import javax.swing.JOptionPane;

/**
 * @author Ryan Smith and Rajveer Parikh
 * This class runs the flash Cards program.
 * It asks the user what function they would like to perform and then calls a corresponding GUI.
 */
public class RunFlashCards {
	
/**
 * Main method. Launches JOptionPane to select GUI.
 */
	public static void main(String[] args) {
	    Object[] options = new String[] {"Edit", "Study"};
	    JOptionPane messageWindow = new JOptionPane();
	    int option = JOptionPane.showOptionDialog(messageWindow,
	            "Hi, what would you like to do?",
	            "Flash Cards!",
	            JOptionPane.YES_NO_OPTION,    // Or JOptionPane.YES_NO_CANCEL_OPTION
	            JOptionPane.QUESTION_MESSAGE, // Choose an icon, or...
	            null,                         // ...you can use a custom Icon here
	            options,
	            options[0]);
	        
		if (option == 1){
			StudyGui.main(args);
		}
		else{
			EditorGui.main(args);
		}
	    
	}
}


