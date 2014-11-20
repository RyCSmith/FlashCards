package flashCards;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Ryan Smith and Rajveer Parikh
 * 
 * This class provides a GUI for the study perspective of the Flash Cards program.
 */
public class StudyGui extends JFrame {
    //declares all component variables (that will be manipulated by operations)
	private JFrame frame;
	private JMenuBar bar;
	private JMenu menu;
	private JMenuItem loadMI;
    private JMenuItem saveMI;
    private JMenuItem switchMI;
    private JMenuItem quitMI;
    private JTextField firstTextBox;
    private JTextField secondTextBox;
    private JButton enterButton;
    private JTextField answerField1;
    private JTextField answerField2;
    private JButton nextButton;
    //declares all operation variables
    private StudyList studylist;
    private ArrayList<Item> itemList;
    private Iterator< Item > iterator;
    private Item currentItem;
    private boolean readyToContinue;
    private boolean saveIsCurrent = true;
    
/**
 * Main method. Creates new StudyGUI and calls run().
 * 
 */
    public static void main(String[] args) { 
    	//creates a new StudyGui and runs it
    	new StudyGui().run();
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
    public void packageGUI(){ //this sets up the GUI and all components
    	
    	//sets size and basic settings for JFrame shell
    	frame = new JFrame("Study View");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1000, 500);
    	
    	//creates JMenuBar, JMenu "Start", JMenuItems and adds them to the menu
    	bar = new JMenuBar();
        menu = new JMenu("Start");
        loadMI = new JMenuItem("Load");
        saveMI = new JMenuItem("Save");
        switchMI = new JMenuItem("Switch Perspective");
        quitMI = new JMenuItem("Quit");
        bar.add(menu);
        menu.add(loadMI);
        menu.add(saveMI);
        menu.add(switchMI);
        menu.add(quitMI);
        frame.setJMenuBar(bar);

        //creates JPanel with BorderLayout for 1 Label and 1 Text Box (whole NORTH FRAME)
        JPanel j1 = new JPanel();
        j1.setLayout(new BorderLayout());
        firstTextBox = new JTextField();
        firstTextBox.setPreferredSize(new Dimension(700, 175));
        JLabel firstLabel = new JLabel("   Here is your stimulus:");
        firstLabel.setPreferredSize(new Dimension(700, 25));
        j1.add(firstLabel, BorderLayout.NORTH);
        j1.add(firstTextBox, BorderLayout.SOUTH);
        

        //creates a JPanel component with 1 Label and 1 Text Box to use in CENTER FRAME
        JPanel j2 = new JPanel();
        j2.setLayout(new BorderLayout());
        JLabel secondLabel = new JLabel("   Please enter your answer here:");
        secondLabel.setPreferredSize(new Dimension(500, 25));
        secondTextBox = new JTextField();
        secondTextBox.setPreferredSize(new Dimension(500, 175));
        j2.add(secondLabel, BorderLayout.NORTH);
        j2.add(secondTextBox, BorderLayout.SOUTH);
        
        //Second JPanel component with 1 button (and 1 blank label) to use in CENTER FRAME
        JPanel j21 = new JPanel();
        j21.setLayout(new BorderLayout());
        JLabel blankLabel = new JLabel(""); //This is just used as a spacer
        blankLabel.setPreferredSize(new Dimension(200, 25));
        enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(200, 175));
        j21.add(blankLabel, BorderLayout.NORTH);
        j21.add(enterButton, BorderLayout.SOUTH);
        
        //Packages 2 JPanel components together for CENTER FRAME
        JPanel j22 = new JPanel();
        j22.setLayout(new BorderLayout());
        j22.add(j2, BorderLayout.WEST);
        j22.add(j21, BorderLayout.EAST);
        
        //Packages 1 Label and 1 Text Box for LOWER FRAME
        JPanel j31 = new JPanel();
        j31.setLayout(new BorderLayout());
        JLabel answerLabel1 = new JLabel("   Your answer:");
        answerLabel1.setPreferredSize(new Dimension(700, 25));
        answerField1 = new JTextField();
        answerField1.setPreferredSize(new Dimension(700, 75));
        j31.add(answerLabel1, BorderLayout.NORTH);
        j31.add(answerField1, BorderLayout.SOUTH);
       
        //Packages 1 Label and 1 Text Box for LOWER FRAME
        JPanel j32 = new JPanel();
        j32.setLayout(new BorderLayout());
        JLabel answerLabel2 = new JLabel("   Correct answer:");
        answerLabel2.setPreferredSize(new Dimension(700, 25));
        answerField2 = new JTextField();
        answerField2.setPreferredSize(new Dimension(700, 75));
        j32.add(answerLabel2, BorderLayout.NORTH);
        j32.add(answerField2, BorderLayout.SOUTH);
        
        //Packages above 2 JPanels for LOWER FRAME
        JPanel j3 = new JPanel();
        j3.setLayout(new BorderLayout());
        j3.add(j31, BorderLayout.NORTH);
        j3.add(j32, BorderLayout.SOUTH);
        
        //Create JPanel for EAST FRAME
        JPanel j4 = new JPanel();
        j4.setLayout(new GridLayout(1,1));
        nextButton = new JButton("Next");
        j4.add(nextButton);
        j4.setPreferredSize(new Dimension(80,600));

        //Package NORTH, CENTER and LOWER FRAMES
        JPanel firstClump = new JPanel();
        firstClump.setLayout(new GridLayout(3,1));
        firstClump.add(j1);
        firstClump.add(j22);
        firstClump.add(j3);
        
        //Adds final packages to JFrame shell
        frame.add(firstClump, BorderLayout.CENTER);
        frame.add(j4, BorderLayout.EAST);
        
        //sets visibility
        frame.pack();
        frame.setVisible(true);
    }
    
/**
 * Provides action listeners for all components in the GUI and determines their behavior.        
 */
   public void operate(){
       studylist = new StudyList();
	   itemList = new ArrayList<Item>(); 
	   
	   //Load menu item
        loadMI.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) 
        	{ 
        		try {
        			studylist.load();
        			studylist.generateListOf20();
        			itemList = studylist.getTwentyRandItems();
        			iterator = itemList.iterator();
        			if (iterator.hasNext()){
        				currentItem = iterator.next();
            			firstTextBox.setText(currentItem.getStimulus());
        			}
        			else{
        				throw new IOException(); //does not throw. why not?
        			}
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
        
        //Window listener warns user of closing without saving
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
        
      //Switch Perspective menu item
        switchMI.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) 
        	{ 
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
        
       // Enter Button
        enterButton.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) 
        	{ 
        		answerField1.setText(secondTextBox.getText());
        	   if (secondTextBox.getText().equalsIgnoreCase(currentItem.getResponse())){
        		   secondTextBox.setText("");
        		   answerField2.setText("CORRECT!");
        		   answerField1.setBackground(Color.GREEN);
        		   currentItem.setTimesCorrect(currentItem.getTimesCorrect() + 1);
        		   readyToContinue = true;
        		   saveIsCurrent = false;
        	   }
        	   else{
        		   answerField2.setText("Please enter to continue: \"" + currentItem.getResponse() + "\"");
        		   secondTextBox.setText("");
        		   currentItem.setTimesCorrect(0);
        		   answerField1.setBackground(Color.RED);
        		   readyToContinue = false;
        		   saveIsCurrent = false;
        	   }
        		
        	}});
        
        // Next Button - only works when readyToContinue = true; this is following a correct answer and Enter button press
        nextButton.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) 
        	{ 
        		if (readyToContinue){
        			if (iterator.hasNext()){
        				resetForNextItem();
        			}
        			else{
        				//shuffles list and restarts iterator if all items have been viewed.
        				Collections.shuffle(itemList);
        				iterator = itemList.iterator();
        				resetForNextItem();
        			}
        		}
        		
        	}});
        // Save Menu Item
        saveMI.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) 
        	{ 
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
        
    }
   
/**
 *  Clears text fields, updates iterator, and restores background color. Used following "Next Button" press.
 */
   public void resetForNextItem(){
	   currentItem = iterator.next();
		while (currentItem.getTimesCorrect() > 3){
			currentItem = iterator.next();
		}
		firstTextBox.setText(currentItem.getStimulus());
		answerField1.setBackground(Color.WHITE);
		answerField1.setText("");
		answerField2.setText("");
		secondTextBox.setText("");
		readyToContinue = false;
   }
}


