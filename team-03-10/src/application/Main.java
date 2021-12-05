package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.UnaryOperator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import application.Entry;


public class Main extends Application {
	
	// String Validator
	public boolean isValidStr(String s){      
	     String regex="^[a-zA-Z0-9\\s\\/.,!?]+$";      
	     //returns true if input matches otherwise, false;
	      return s.matches(regex);
	 }

	//Start method
	@Override
	public void start(Stage primaryStage) {
		
		//-----------------Home Screen------------------------
		
		//Title
		Label title = new Label("Log Log");
		title.setStyle("-fx-text-fill: white;");
		title.setFont(new Font("Times New Roman", 50));
		title.setPadding(new Insets(75, 75, 0, 75));
		
		//Record your Journey Button
		Button btn1 = new Button("Record Your Journey");
		btn1.setAlignment(Pos.CENTER);
		btn1.setFont(Font.font("Times New Roman", 20));
		
		//History Button
		Button btn2 = new Button("History");
		btn2.setAlignment(Pos.CENTER);		
		btn2.setFont(Font.font("Times New Roman", 20));
		
		//Home Screen Layout
		VBox homeScreen = new VBox();
		homeScreen.setAlignment(Pos.TOP_CENTER);
		homeScreen.setSpacing(100);
		homeScreen.setStyle("-fx-background-color: #607658;");
		
		//Home scene
		homeScreen.getChildren().addAll(title, btn1, btn2);
		Scene homeScene = new Scene(homeScreen, 1200, 800);

		////--------END---Home Screen------------------------
		
		
		
		
		
		//-----------------Record Your Journey Screen------------------------

		/**
		 * Text Fields  
		 * 			name of journal
		 * 			Date
		 * 			distance
		 * 			time elapsed
		 * 			Journal entry
		 */	
		
		//Title Label
		Label label2 = new Label("Record Your Journey");
		label2.setStyle("-fx-text-fill: white;");
		label2.setFont(new Font("Times New Roman", 50));
		label2.setPadding(new Insets(75, 75, 0, 75));
		
		//Gridpane to hold text boxes
		GridPane gpforText = new GridPane();
		gpforText.setPadding(new Insets(10, 10, 10, 10));
		gpforText.setVgap(5);
		gpforText.setHgap(5);
		gpforText.setAlignment(Pos.CENTER);
		
		//Name Text Field
		TextField name = new TextField ();
		name.setPromptText("Journal Name");
		name.setPrefWidth(100);
		gpforText.setConstraints(name, 0, 0);
		gpforText.getChildren().add(name);
		
		//Date Text Field
		TextField date = new TextField ();
		date.setPromptText("Enter Date: DD/MM/YYYY");
		date.setPrefWidth(100);
		gpforText.setConstraints(date, 0, 1);
		gpforText.getChildren().add(date);
		
		//Distance Text Field
		TextField distance = new TextField ();
		distance.setPromptText("Miles Traversed");
		distance.setPrefWidth(100);
		gpforText.setConstraints(distance, 0, 2);
		gpforText.getChildren().add(distance);
		
		//Time Text Field
		TextField time = new TextField ();
		time.setPromptText("Time Elapsed");
		time.setPrefWidth(100);
		gpforText.setConstraints(time, 0, 3);
		gpforText.getChildren().add(time);
		
		//Entry Text Field
		TextArea entry = new TextArea ();
		entry.setPromptText("Journal Entry");
		entry.setWrapText(true);
		entry.setPrefWidth(500);
		entry.setPrefHeight(100);
		gpforText.setConstraints(entry, 0, 4);
		gpforText.getChildren().add(entry);
		
		// Submit button
		Button submit = new Button("Submit");
		submit.setPrefWidth(500);
		gpforText.setConstraints(submit, 0, 5);
		gpforText.getChildren().add(submit);
		
		//Home Button 1
		Button sceneButton = new Button("Go Home");		
		sceneButton.setFont(Font.font("Times New Roman", 20));
		sceneButton.setPrefWidth(175);
		
		//Home button 2
		Button goHome = new Button("Go home");
		goHome.setAlignment(Pos.CENTER);
		goHome.setPrefWidth(500);
		
		//Add home button to gridpane
		gpforText.setConstraints(goHome, 0, 6);
		gpforText.getChildren().add(goHome);

		//Layout for record Your journey
		VBox layout2 = new VBox(20);
		layout2.getChildren().addAll(label2, gpforText);
		layout2.setAlignment(Pos.TOP_CENTER);
		layout2.setSpacing(100);
		
		//Journey Scene
		Scene addJourney = new Scene(layout2, 1200, 800);
		layout2.setStyle("-fx-background-color: #607658;");

		//--------END----------Record Your Journey Screen----------------
		

		//-----------------History Screen------------------------
		
		//History Title Label
		Label history = new Label("History");
		history.setStyle("-fx-text-fill: white;");
		history.setFont(new Font("Times New Roman", 50));
		history.setPadding(new Insets(75, 75, 0, 75));
		
		// Table to show History of elements
		TableView<Entry> table = new TableView<>();
		
		//Handles Table Columns
		TableColumn<Entry, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Entry, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Entry, String> distCol = new TableColumn<>("Distance Hiked");
        distCol.setCellValueFactory(new PropertyValueFactory<>("distance"));
        nameCol.setPrefWidth(200);
        dateCol.setPrefWidth(200);
        distCol.setPrefWidth(200);
        
        //Table Attributes
        table.getColumns().addAll(nameCol, dateCol, distCol);
        table.setMaxSize(600, 600);
        table.setEditable(true);
        
        
        //------------ Begin extraction from database file --------------
        
        //ArrayList of lines from database file
        ArrayList<String> line = new ArrayList<>();
        
        //Array of Single strings
        String[] words = new String[5];
        
        //Observable list to hold Entry objects
        ObservableList<Entry> obsList = FXCollections.observableArrayList();	
        
         try {
        	
        	//Create new Scanner object
        	Scanner in = new Scanner(new File("entries.txt")).useDelimiter("\t");

        	//Loops through lines in file
        	while (in.hasNextLine()) {

        		//Appends line to the line ArrayList
        		line.add(in.nextLine());
				
        	}
        	
        	//For loop splits line into 5 different Strings
        	for(int i = 0; i < line.size(); i++) {
				
        		//Split the line into 5 strings with pertinent data
				words = line.get(i).split("\t");
				
				//Create new entry with the data from the file
				Entry testt = new Entry();
    			testt.setName(words[0]);
    			testt.setDate(words[1]);
    			testt.setDuration(words[2]);
    			testt.setDistance(words[3]);
    			testt.setJournal(words[4]);
    			
    			//Add the entry to the observable list & table
    			obsList.add(testt);
    			table.getItems().add(testt);

			}

        }catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
         
         //------END------ End extraction from database file ------END-------
         
        
        //Delete Button
        Button deleteButton = new Button("Delete");
        deleteButton.setPrefWidth(175);
        deleteButton.setFont(Font.font("Times New Roman", 20));
        deleteButton.setAlignment(Pos.CENTER);
        
        //View button
        Button view = new Button("View");
        view.setPrefWidth(175);
        view.setFont(Font.font("Times New Roman", 20));
        view.setAlignment(Pos.CENTER);
        
        //Hbox for buttons
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(deleteButton, sceneButton, view);
        hbox.setAlignment(Pos.TOP_CENTER);
        
        //History Screen layout
		VBox history1 = new VBox(20);
		history1.getChildren().addAll(history, table, hbox);
		history1.setAlignment(Pos.TOP_CENTER);
		history1.setSpacing(10);
		
		//History Scene
		Scene viewHistory = new Scene(history1, 1200, 800);
		history1.setStyle("-fx-background-color: #607658;");
		
		//--------END------History Screen-------END----------------------
		
		
		
		//----------------------View Entry ----------------
		
		//Vbox to show a detailed view of Selected Entry
		VBox popupBox = new VBox(20);
		popupBox.setAlignment(Pos.CENTER);
		popupBox.setSpacing(20);
		popupBox.setStyle("-fx-background-color: #607658;");
		popupBox.setPrefWidth(500);
		popupBox.setPrefHeight(500);
		
		//Scene for detailed view
		Scene pop = new Scene(popupBox, 1200, 800);
		
		//Back button
		Button back = new Button("Back");
		
		//Add back button to Layout
		popupBox.getChildren().add(back);
		
		//--------END------View Entry-------END----------------------
		
				
		
		//----------------------Button Handlers----------------
		
		//Change scene to Add Journal entry
		btn1.setOnAction(e -> primaryStage.setScene(addJourney));
		
		//Change scene to Add Home Screen
		sceneButton.setOnAction(e -> primaryStage.setScene(homeScene));
		goHome.setOnAction(e -> primaryStage.setScene(homeScene));
		
		//Change scene to View History
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent t) {
				primaryStage.setScene(viewHistory);
				}
			});
		
		//Submit button handler
		submit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent t) {

				// Entry( Name, Date, Duration, Distance, Entry)
				String entryField = entry.getText();
				Entry temp = new Entry(name.getText(), date.getText(), time.getText(), distance.getText(), entryField );
				
				//If statement to validate user input
				if(isValidStr(name.getText()) && isValidStr(date.getText())
						&& isValidStr(time.getText()) &&  isValidStr(distance.getText()) && isValidStr(entryField)){
					
					//Add entry to table and observable list backup
					table.getItems().add(temp);
					obsList.add(temp);
					
					//Write new entry to database file
					try {
						FileOutputStream out = new FileOutputStream("entries.txt", true);
						
						byte[] bytesArr = temp.toString().getBytes();
						
						try {
							out.write(bytesArr);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Successful Alert
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Journal Entry");
					alert.setContentText("Your Journal entry was succesfully logged!");
					alert.setHeaderText("Journal Entry Submitted");
					alert.showAndWait();
					
					//Return to home screen upon successful entry
					primaryStage.setScene(homeScene);
				}
				else {
					//Failed Alert
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setContentText("All entries must be filled, and contain numbers, letters, or punctuation");
					alert.setHeaderText("Invalid Input");
					alert.showAndWait();
				}
				
				//Clear form
				name.clear();
				date.clear();
				distance.clear();
				time.clear();
				entry.clear();
								

			}
			
		}); // END Submit Button Handler
		
		// Delete button handler
		deleteButton.setOnAction(e -> {
			
			//Remove Selected item from table and observable list
		    Object selectedItem = table.getSelectionModel().getSelectedItem();
		    table.getItems().remove(selectedItem);
		    obsList.remove(selectedItem);
		    
		    // Overwrite deleted entries
		    try {
				PrintWriter pw = new PrintWriter("entries.txt");
				pw.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    // Re-add the non-deleted entries
		    for (int i = 0; i < obsList.size(); i++) {
		    	
		    	try {
					FileOutputStream out = new FileOutputStream("entries.txt", true);
					
					byte[] bytesArr = obsList.get(i).toString().getBytes();
					
					try {
						out.write(bytesArr);
					} catch (IOException g) {
						// TODO Auto-generated catch block
						g.printStackTrace();
					}
				} catch (FileNotFoundException g) {
					// TODO Auto-generated catch block
					g.printStackTrace();
				}
		    	
		    }
		    
		    
		}); // END Delete Button Handler
		
		// View Button handler
		view.setOnAction(new EventHandler<ActionEvent>() {
			
			// Shows a detailed view of a single entry
			@Override
			public void handle(ActionEvent event) {
				
				Entry viewE = table.getSelectionModel().getSelectedItem();
		        
		        Label viewName = new Label("Name: " + viewE.getName());
		        Label viewDist = new Label("Distance: " + viewE.getDistance());
		        Label viewDur = new Label("Duration: " + viewE.getDuration());
		        Label viewDate = new Label("Date: " + viewE.getDate());
		        Text viewEntry = new Text("Journal Entry:\n" + viewE.getJournal());
		        
		        viewEntry.wrappingWidthProperty().set(500);
		        
		        
		        //Format Font
		        viewName.setFont(Font.font("Times New Roman", 20));
		        viewDist.setFont(Font.font("Times New Roman", 20));
		        viewDur.setFont(Font.font("Times New Roman", 20));
		        viewDate.setFont(Font.font("Times New Roman", 20));
		        viewEntry.setFont(Font.font("Times New Roman", 20));
		        viewName.setTextFill(Color.web("#ffffff"));
		        viewDist.setTextFill(Color.web("#ffffff"));
		        viewDur.setTextFill(Color.web("#ffffff"));
		        viewDate.setTextFill(Color.web("#ffffff"));
		        viewEntry.setFill(Color.WHITE);
		        
		        //Add the entry elements to the vBox
		        popupBox.getChildren().add(0, viewEntry);
		        popupBox.getChildren().add(0, viewDist);
		        popupBox.getChildren().add(0, viewDur);
		        popupBox.getChildren().add(0, viewDate);
		        popupBox.getChildren().add(0, viewName);
				
		        //Change scene to the detailed entry view
		        primaryStage.setScene(pop);
		        
		       
			}
		}); // END View Button Handler
		
		//Back button handler
		back.setOnAction(e -> {
			
			//Return to history scene, refresh the detailed view scene
			primaryStage.setScene(viewHistory);
			popupBox.getChildren().clear();
			popupBox.getChildren().add(back);
			
		});// END back Button Handler
		
		//-----------------END---Button Handlers------END-------
		
		//Set default scene
		primaryStage.setScene(homeScene);
		primaryStage.show();
	}
	
	// Main - Launches program
	public static void main(String[] args) {
		launch(args);
	}
	
}


