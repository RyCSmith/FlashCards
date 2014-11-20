FlashCards
==========

Design: This concept and initial structure was created as an assignment by Professor Dave Matuszek at the University of Pennsylvania. All implementation, GUI design and implementation developed by Ryan Smith and Rajveer Parikh.

Overview: Program to create sets of flash cards and then study from them.

Use: This program is launched from the RunFlashCards file which presents the user with the choice of opening the study perspective or editor perspective. Each perspective consists of a GUI that allows the user to interact with the program. 

Editor GUI: Allows user to create a study file for the first time or to load a study file and add/edit/remove items from it. 
-A new blank file can be created in a text editor and then loaded for editing.
-Alternatively, upon opening the editor perspective, a user can commence adding items through the GUI. When complete, Save As can be used to generate a .txt file is the proper format which can later be loaded for editing or studying.

Study GUI: Allows user to load in a study file from which a list of 20 randomly selected questions will be loaded. The program will present a question. The user enters the answer in the provided text field and then presses Enter. 
-If the question was answered correctly it will be indicated and the user can press Next to continue to the next question. Following a correct answer, the record of “times correct” will be incremented. When this reaches 4, the item will not be presented again as a question. 
-If the user answered incorrectly it will be indicated and the user will need to enter the correct answer before continuing. Additionally, the “times correct” counter for the question will be reset to zero.
-When the user has reviewed all 20 questions, they will be reshuffled and those answered correctly less than 4 times will be presented again.
-If the user chooses to Save, the study list will be updated with the current “times correct” per question.
-The user can also choose to Save As in order to avoid modifying the original list while saving a copy with the updated “times correct”. This will include all items in the original file, not only the random study selection and, as such, is a useful feature for retaining results from various study sessions. The “times correct” is easily viewable in the saved .txt file. 

Other Info: A sample study set is provided titled “StudySet1.txt” and can be used for demonstration. Pictures showing the GUI interfaces are included. API documentation for this program and tests is also included in this repository and is titled FlashCards_API_Documentation.zip. To view, unzip and open index.html. 
