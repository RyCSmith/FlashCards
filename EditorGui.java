
package flashCards;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class provides a GUI for the editor perspective of the Flash Cards program.
 * @author Ryan Smith and Rajveer Parikh
 *
 */
public class EditorGui extends JFrame {
	//declares all component variables (that will be manipulated by operations)
	private JFrame frame;
	private JMenuBar bar;
	private JMenu menu;
	private JMenuItem loadMI;
    private JMenuItem saveMI;
    private JMenuItem saveASMI;
    private JMenuItem switchMI;
    private JMenuItem quitMI;
    private JComboBox opSelection;
    private JTextField inputTextBox;
    private JTextField messagesTextBox;
    private JButton enterButton;
    private ButtonGroup radioGroup;
    private JRadioButton instructionsRadio;
    private JRadioButton contentsRadio;
    private JTextArea displayArea;
    //declares all operation variables
    private StudyList studylist = new StudyList();
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private Iterator< Item > iterator;
    private boolean saveIsCurrent = true;

/**
 * Main method. Creates new EditorGUI and calls run().
 * 
 */  
	public static void main(String[] args) { 
    	//creates a new EditorGui and runs it
    	new EditorGui().run();
    }
	
/**
 * Calls packageGUI() and operate(). This draws the GUI and begins functionality of action listeners for components.
 */    
	public void run(){ 
    	//sets up the GUI and performs operations based on ActionListeners
    	packageGUI();
    	operate();
    }

/**
 * Creates components and draws GUI.
 */
    public void packageGUI(){
    	//sets size and basic settings for JFrame shell
    	frame = new JFrame("Editor View");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1000, 500);
    	
    	//creates JMenuBar, JMenu "Start", JMenuItems and adds them to the menu
    	bar = new JMenuBar();
        menu = new JMenu("Start");
        loadMI = new JMenuItem("Load");
        saveMI = new JMenuItem("Save");
        saveASMI = new JMenuItem("Save As");
        switchMI = new JMenuItem("Switch Perspective");
        quitMI = new JMenuItem("Quit");
        bar.add(menu);
        menu.add(loadMI);
        menu.add(saveMI);
        menu.add(saveASMI);
        menu.add(switchMI);
        menu.add(quitMI);
        frame.setJMenuBar(bar);
    	
    	//creates JPanel for TOP FRAME - includes label, dropdown menu, input textfield, enter button, radio buttons and message TextField
    	JPanel j1 = new JPanel();
    	j1.setLayout(new BorderLayout());
    	JLabel firstLabel = new JLabel("                                 Input");
    	firstLabel.setPreferredSize(new Dimension(800, 20));
    	inputTextBox = new JTextField();
    	inputTextBox.setPreferredSize(new Dimension(600, 40));
    	enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(100, 60));
        opSelection = new JComboBox(new String[]
                {"Add", "Find", "Find Partial", "Edit", "Delete"} );
        opSelection.setPreferredSize(new Dimension(100,60));
        
        JPanel j2 = new JPanel();
        j2.setLayout(new BorderLayout());
        messagesTextBox = new JTextField();
        messagesTextBox.setPreferredSize(new Dimension(600, 50));
        
        contentsRadio = new JRadioButton("Contents");
        instructionsRadio = new JRadioButton("Instructions");
        instructionsRadio.setPreferredSize(new Dimension(120,20));
       
       
        radioGroup = new ButtonGroup();
        radioGroup.add(contentsRadio);
        radioGroup.add(instructionsRadio);
        
        JLabel messagesLabel = new JLabel("   Messages");
        messagesLabel.setPreferredSize(new Dimension(800, 20));
        
        JPanel j5 = new JPanel();
        j5.setLayout(new BorderLayout());
        j5.add(instructionsRadio, BorderLayout.NORTH);
        j5.add(contentsRadio, BorderLayout.SOUTH);
        //JPanel for radio Buttons and Message
        j2.add(messagesTextBox, BorderLayout.WEST);
        j2.add(j5, BorderLayout.EAST);
        j2.add(messagesLabel, BorderLayout.NORTH);
        
        //JPanel for TOP FRAME
    	j1.add(firstLabel, BorderLayout.NORTH);
    	j1.add(inputTextBox, BorderLayout.CENTER);
    	j1.add(enterButton, BorderLayout.EAST);
    	j1.add(opSelection, BorderLayout.WEST);
    	
    	JPanel j4 = new JPanel();
    	j4.setLayout(new BorderLayout());
    	j4.add(j1, BorderLayout.SOUTH);
    	j4.add(j2, BorderLayout.NORTH);
    	
    	
    	JPanel j3 = new JPanel();
        j3.setLayout(new BorderLayout());
    	JLabel blankLabel = new JLabel("");
        blankLabel.setPreferredSize(new Dimension(800, 20));
        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(800, 550));

        j3.add(blankLabel, BorderLayout.NORTH);
        j3.add(scrollPane, BorderLayout.SOUTH);
    	
   
    	frame.add(j4, BorderLayout.NORTH);
    	frame.add(j3, BorderLayout.SOUTH);
    	
    	//sets visibility
        frame.pack();
        frame.setVisible(true);
    }

/**
 * Provides action listeners for all components in the GUI and determines their behavior.        
 */
    public void operate(){
    	//instructions Radio Button
    	instructionsRadio.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) 
        	{ 
        	    if (instructionsRadio.isSelected()){
        	    	displayInstructions();
        	    }
        		
        	}});
    
    contentsRadio.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent e) 
    	{ 
    	    if (contentsRadio.isSelected()){
    	    	displayArea.setText("");
    	    	displayContents();
    	    }
    		
    	}});
    
  
    //Load menu item
    
    loadMI.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent e) 
    	{ 
    		try {
    			studylist.load();
    			itemList = studylist.getItemList();
    		} catch (IOException e1) {
    			//Message Window - Let's user know there was an error reading the file in
    			JOptionPane messageWindow = new JOptionPane();
    			JOptionPane.showMessageDialog(messageWindow,
    		            "There was an error reading in your file");        		} 
    	}});
    
  //Quit menu item
    quitMI.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent e) 
    	{ 
    	    if (saveIsCurrent){
    	    	frame.dispose();
    	    }
    	    else{
    	    	//Confirmation Window, asks users if they are sure they want to quit without saving
    	    	JOptionPane confirmWindow = new JOptionPane();
    	    	int yesNo = JOptionPane.showConfirmDialog(confirmWindow, "Are you sure you'd like to quit without saving?");
    	    	if (yesNo == JOptionPane.YES_OPTION) {
    	    		frame.dispose();    }
    	    }
    		
    	}});
    
    switchMI.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent e) 
    	{ 
			messagesTextBox.setText("");
    	    if (saveIsCurrent){
    	    	frame.dispose();
    	    	RunFlashCards.main(null);
    	    }
    	    else{
    	    	//Confirmation Window, asks users if they are sure they want to quit without saving
    	    	JOptionPane confirmWindow = new JOptionPane();
    	    	int yesNo = JOptionPane.showConfirmDialog(confirmWindow, "Are you sure you'd like to quit without saving?");
    	    	if (yesNo == JOptionPane.YES_OPTION) {
    	    		frame.dispose(); 
    	    		RunFlashCards.main(null);
    	    	}
    	    }
    		
    	}});
	    frame.addWindowListener(new WindowAdapter() {
	    	
    		@Override public void windowClosing(WindowEvent e) {
    			if(saveIsCurrent == true){
    				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			}
    			else{
    				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you'd like to quit without saving?", "Quit",JOptionPane.YES_NO_OPTION);
    			
    				if (confirmed == JOptionPane.YES_OPTION){ 
    					frame.dispose();
    				}
    				else{
    					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    				}
    			}
    		}
    	});
    //Enter button
    enterButton.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent e) 
    	{ 
			messagesTextBox.setText("");
    		String selection = (String) opSelection.getSelectedItem();
    		if (selection.equalsIgnoreCase("add")){
    			try{
    				studylist.addHelper(inputTextBox.getText());
    				saveIsCurrent = false;
    				inputTextBox.setText("");
    			}
    			catch (ArithmeticException e1){
    				messagesTextBox.setText("Improper Format for adding, try again.");
    				inputTextBox.setText("");
    			}
    		}
    		else if (selection.equalsIgnoreCase("find")){
    			Item returnedItem = studylist.find(inputTextBox.getText());
    			if (returnedItem.getStimulus().equalsIgnoreCase("NothingHere")){
    				messagesTextBox.setText("Your search did not produce any results.");
    				inputTextBox.setText("");
    			}
    			else{
    				displayArea.setText(returnedItem.getStimulus() + ": " + returnedItem.getResponse());
    				inputTextBox.setText("");
    			}
    		}			
    		else if (selection.equalsIgnoreCase("find partial")){
    			String returnString = studylist.findPartial(inputTextBox.getText());
    			if (returnString.equalsIgnoreCase("Your search did not produce any results.")){
    				displayArea.setText("");
    				messagesTextBox.setText(returnString);
    			}
    			else{
    				messagesTextBox.setText("");
    				displayArea.setText(returnString);
    			}
	
    		}
    		else if (selection.equalsIgnoreCase("edit")){
    			try {
    				studylist.modifyHelper(inputTextBox.getText());
    				inputTextBox.setText("");
        			saveIsCurrent = false;
    			}
    			catch (ArithmeticException e5){
    				messagesTextBox.setText("Improper Format for editing, try again.");
    				inputTextBox.setText("");
    			}
    			
    		}
    		else if (selection.equalsIgnoreCase("delete")){
    			try {
    				studylist.deleteHelper(inputTextBox.getText());
    				inputTextBox.setText("");
        			saveIsCurrent = false;
    			}
    			catch (ArithmeticException e5){
    				messagesTextBox.setText("Improper number, try again.");
    				inputTextBox.setText("");
    			}
    			saveIsCurrent = false;
    		}
    	}});
    
    // Save Menu Item
    saveMI.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent e) 
    	{ 
			messagesTextBox.setText("");
    		try {
				studylist.save();
				saveIsCurrent = true;
			} catch (IOException e1) {
				//Message Window - Let's user know there was an error saving the file
    			JOptionPane messageWindow = new JOptionPane();
    			JOptionPane.showMessageDialog(messageWindow,
    		            "There was an error saving your file");
			}
    		
    	}});
    saveASMI.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent e) 
    	{ 
			messagesTextBox.setText("");
    		try {
				studylist.saveAs();
				saveIsCurrent = true;
			} catch (IOException e1) {
				//Message Window - Let's user know there was an error saving the file
    			JOptionPane messageWindow = new JOptionPane();
    			JOptionPane.showMessageDialog(messageWindow,
    		            "There was an error saving your file");
			}
    		
    	}});
}

/**
 * Displays use instructions when called by component.
 */
    public void displayInstructions(){
    	String instructions = "\n  To Edit a collection of flash cards, complete the following steps:\n\n"
    			+ "     1 - Load the file you would like to manipulate by choosing Load from the Start Menu.\n"
    			+ "     2 - To view the contents of the file you loaded, click the Contents Radio Button.\n"
    			+ "         *Note - You can return to view these instructions at any time by clicking the Instruction Radio Button.\n"
    			+ "     3 - In case the Contents Radio Button is already selected, select the Instructions Radio button and select \n"
    			+ "			the Contents Radio button again to display the contents \n"
    			+ " 	4 - Select edit from the drop down and enter the item you wish to edit in exactly the following format No. || Stimulus || Response and click enter \n"
    			+ "		5 - Save the file, load it again from the Start menu and click on the Contents radio button to view changes \n"
    			+ " \n To Add an item to a collection of flash cards, complete the following steps: \n\n"
    			+ "     1 - Load the file you would like to add item to by choosing Load from the Start Menu.\n"
    			+ "     2 - To view the contents of the file you loaded, click the Contents Radio Button.\n"
    			+ "         *Note - You can return to view these instructions at any time by clicking the Instruction Radio Button.\n"
    			+ "     3 - In case the Contents Radio Button is already selected, select the Instructions Radio button and select \n"
    			+ "			the Contents Radio button again to display the contents \n"
    			+ " 	4 - Select add from the drop down and enter the item you wish to add in exactly the following format Stimulus || Response and click enter \n"
    			+ "		5 - Save the file, load it again and click on the Contents radio button to view the added item at the borrom of the list\n"
    			+ " \n To delete an item to a collection of flash cards, complete the following steps: \n\n"
    			+ "     1 - Load the file you would like to delete item from by choosing Load from the Start Menu.\n"
    			+ "     2 - To view the contents of the file you loaded, click the Contents Radio Button.\n"
    			+ "         *Note - You can return to view these instructions at any time by clicking the Instruction Radio Button.\n"
    			+ "     3 - In case the Contents Radio Button is already selected, select the Instructions Radio button and select \n"
    			+ "			the Contents Radio button again to display the contents \n"
    			+ " 	4 - Enter the item you wish to delete by entering the number you want to delete and click enter \n"
    			+ "		5 - Save the file, load it again and click on the Contents radio button to view the changes in the list\n"
    			+ " \n To find an item to a collection of flash cards, complete the following steps: \n\n"
    			+ "     1 - Load the file you would like to find an item from by choosing Load from the Start Menu.\n"
    			+ "     2 - To view the contents of the file you loaded, click the Contents Radio Button.\n"
    			+ "         *Note - You can return to view these instructions at any time by clicking the Instruction Radio Button.\n"
    			+ "     3 - In case the Contents Radio Button is already selected, select the Instructions Radio button and select \n"
    			+ "			the Contents Radio button again to display the contents \n"
    			+ " 	4 - Select find from the drop down menu\n"
    			+ " 	5 - To find a specific item, enter the stimulus in the text box and click enter \n"
    			+ "		6 - The required item should be displayed\n"
    			+ " \n To search for a partial string from a collection of flash cards, complete the following steps: \n\n"
    			+ "     1 - Load the file you would like to find an item from by choosing Load from the Start Menu.\n"
    			+ "     2 - To view the contents of the file you loaded, click the Contents Radio Button.\n"
    			+ "         *Note - You can return to view these instructions at any time by clicking the Instruction Radio Button.\n"
    			+ "     3 - In case the Contents Radio Button is already selected, select the Instructions Radio button and select \n"
    			+ "			the Contents Radio button again to display the contents \n"
    			+ "		4 - Select find partial from the drop down\n"
    			+ " 	5 - To search for a specific string, enter the string in the text box and click enter \n"
    			+ "		6 - The required item should be displayed\n";
    	displayArea.setText(instructions);
    }

/**
 * Displays the contents of the file that was last loaded and the index of the Item when called by a component.
 */
    public void displayContents(){
    	int counter = 0;
    	Item currentItem;
    	String displayString = "";
    	iterator = itemList.iterator();
		while (iterator.hasNext()){
			currentItem = iterator.next();
			displayString = displayString + Integer.toString(counter) + ":   " + currentItem.getStimulus() + ":     " 
			+ currentItem.getResponse() + "\n";
			counter++;
		}
		displayArea.setText(displayString);
    }
    
}

