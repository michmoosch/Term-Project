package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import application.Entry;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		
		//-----------------Home Screen------------------------
		
		//Title
		Label title = new Label("Log Log");
		title.setStyle("-fx-text-fill: white;");
		title.setFont(new Font("Times New Roman", 50));
		title.setPadding(new Insets(75, 75, 0, 75));
		
		
		//1st Button
		Button btn1 = new Button("Record Your Journey");
		btn1.setAlignment(Pos.CENTER);
		btn1.setFont(Font.font("Times New Roman", 20));
		
		//2nd Button
		Button btn2 = new Button("History");
		btn2.setAlignment(Pos.CENTER);		
		btn2.setFont(Font.font("Times New Roman", 20));
		
	
		//Home Screen Layout
		VBox homeScreen = new VBox();
		homeScreen.setAlignment(Pos.TOP_CENTER);
		homeScreen.setSpacing(100);
		homeScreen.setStyle("-fx-background-color: #607658;");
		
		//Layout 1
		homeScreen.getChildren().addAll(title, btn1, btn2);
		Scene homeScene = new Scene(homeScreen, 1200, 800);
		
		
		
		////--------END---Home Screen------------------------
		
		
		
		
		
		//-----------------Record Your Journey Screen------------------------
		Label label2 = new Label("Record Your Journey");
		label2.setStyle("-fx-text-fill: white;");
		label2.setFont(new Font("Times New Roman", 50));
		label2.setPadding(new Insets(75, 75, 0, 75));
		
		/**
		 * Text Fields  
		 * 			name of journal
		 * 			Date
		 * 			distance
		 * 			time elapsed
		 * 			??? uplaod picutrees??
		 * 			Journal entry
		 */	
		
		GridPane gpforText = new GridPane();
		
		gpforText.setPadding(new Insets(10, 10, 10, 10));
		gpforText.setVgap(5);
		gpforText.setHgap(5);
		
		
		TextField name = new TextField ();
		name.setPromptText("Journal Name");
		name.setPrefWidth(100);
		gpforText.setConstraints(name, 0, 0);
		gpforText.getChildren().add(name);
		
		
		TextField date = new TextField ();
		date.setPromptText("Enter Date: DD/MM/YYYY");
		date.setPrefWidth(100);
		gpforText.setConstraints(date, 0, 1);
		gpforText.getChildren().add(date);
		
		
		TextField distance = new TextField ();
		distance.setPromptText("Miles Traversed");
		distance.setPrefWidth(100);
		gpforText.setConstraints(distance, 0, 2);
		gpforText.getChildren().add(distance);
		
		
		TextField time = new TextField ();
		time.setPromptText("Time Elapsed");
		time.setPrefWidth(100);
		gpforText.setConstraints(time, 0, 3);
		gpforText.getChildren().add(time);
		
		
		TextArea entry = new TextArea ();
		entry.setPromptText("Journal Entry");
		entry.setWrapText(true);
		entry.setPrefWidth(500);
		entry.setPrefHeight(100);
		gpforText.setConstraints(entry, 0, 4);
		gpforText.getChildren().add(entry);
		
		// Submit text fields
		Button submit = new Button("Submit");
		submit.setPrefWidth(500);
		gpforText.setConstraints(submit, 0, 5);
		gpforText.getChildren().add(submit);
		
		//Change scene to home Screen
		Button sceneButton = new Button("Go to Home Screen");
		sceneButton.setAlignment(Pos.CENTER);		
		sceneButton.setFont(Font.font("Times New Roman", 20));
		
		
		gpforText.setAlignment(Pos.CENTER);
		
		//Layout 2
		VBox layout2 = new VBox(20);
		layout2.getChildren().addAll(label2, gpforText, sceneButton);
		layout2.setAlignment(Pos.TOP_CENTER);
		layout2.setSpacing(100);
		
		Scene addJourney = new Scene(layout2, 1200, 800);
		layout2.setStyle("-fx-background-color: #607658;");
		

		
		//--------END----------Record Your Journey Screen----------------
		

		//-----------------History Screen------------------------
		
		
		Label history = new Label("History");
		history.setStyle("-fx-text-fill: white;");
		history.setFont(new Font("Times New Roman", 50));
		history.setPadding(new Insets(75, 75, 0, 75));
		
		// History table
		TableView table = new TableView<>();
		
		ArrayList<String> list = new ArrayList<>();
    	//ObservableList<String> words = FXCollections.observableList(list);	
		
		
		TableColumn<Entry, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
        TableColumn<Entry, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        TableColumn<Entry, String> distCol = new TableColumn<>("Distance Hiked");
        distCol.setCellValueFactory(new PropertyValueFactory<>("distance"));
        
        nameCol.setPrefWidth(200);
        dateCol.setPrefWidth(200);
        distCol.setPrefWidth(200);
        
        table.getColumns().addAll(nameCol, dateCol, distCol);
        
        
        table.setMaxSize(600, 400);
        
        table.setEditable(false);
        
        
        ArrayList<String> line = new ArrayList<>();
        String[] words = new String[100];
        
        
         try {
        	
        	//Create new Scanner object
        	Scanner in = new Scanner(new File("entries.txt")).useDelimiter("\t");

        	//Loops through STRINGS in file
        	while (in.hasNextLine()) {

        		//Appends string to line ArrayList
        		line.add(in.nextLine());
				//line.add(in.nextLine());
        	}
        	
        	//For loop splits line into 5 different
        	for(int i = 0; i < line.size(); i++) {
				
				words = line.get(i).split("\t");
				
				
				Entry testt = new Entry();
				
    			testt.setName(words[0]);
    			testt.setDate(words[1]);
    			testt.setDuration(words[2]);
    			testt.setDistance(words[3]);
    			testt.setJournal(words[4]);

    			table.getItems().add(testt);
				
			}
        	
        	
    
        	//For loop creates entry object 
        	
        	
			
			
        	
        }catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
       
        
       //System.out.println(line.get(2));
     	
     	/*
     	 * 
     	 * 
     	for(int i = 0; i < 9; i++) {
				System.out.println(line.get(i));
    		}
     	
     	
     	line = 
     	
     	Name
     	Date
     	Duration
     	Distacne
     	Journal
     	Name
     	Date
     	Duration
     	Distance
     	Journal
     	
     	
     	
			for (int j = 0; j <10; j++) {
				
			}
			
			System.out.println();
			
				
     	*/
         
        
		VBox history1 = new VBox(20);
		history1.getChildren().addAll(history, table, sceneButton);
		history1.setAlignment(Pos.TOP_CENTER);
		history1.setSpacing(100);
		
		Scene viewHistory = new Scene(history1, 1200, 800);
		history1.setStyle("-fx-background-color: #607658;");
		
		
		
				
		//--------END------History Screen-------------------------------------
		
				
				
		//----------------------Button Actions----------------
		
		//Change scene to Add Journal entry
		btn1.setOnAction(e -> primaryStage.setScene(addJourney));
		
		//Change scene to Add Home Screen
		sceneButton.setOnAction(e -> primaryStage.setScene(homeScene));
		
		//Change scene to View History
		//btn2.setOnAction(e -> primaryStage.setScene(viewHistory));
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent t) {
				primaryStage.setScene(viewHistory);
				table.getColumns().clear();
				table.getColumns().addAll(nameCol, dateCol, distCol);
				}
			});
		
		//Gather and input text fields into entry object
		submit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent t) {
				
				//Alert
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Journal Entry");
				alert.setContentText("Your Journal entry was succesfully logged!");
				alert.setHeaderText("Journal Entry Submitted");
				alert.showAndWait();
				
				// Entry( Name, Date, Duration, Distance, Entry)
				String entryField = entry.getText();
				Entry temp = new Entry(name.getText(), date.getText(), time.getText(), distance.getText(), entryField );
				
				
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
				
				
				name.clear();
				date.clear();
				distance.clear();
				time.clear();
				entry.clear();
								
				
				primaryStage.setScene(homeScene);
				
							
				
			}
			
		});
		
		//-----------------END---Button Actions----------------
		
		//Set default scene
		primaryStage.setScene(homeScene);
		primaryStage.show();
	}
	
	// Main - Launches program
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}





