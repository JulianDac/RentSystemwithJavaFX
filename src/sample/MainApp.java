package sample;

import javafx.application.Application;
import javafx.stage.Stage;


// beans - for property management
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


// Events
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


// Controls
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;


// Layouts
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

import javafx.geometry.Pos;


// Scenes
import javafx.scene.Scene;
import javafx.scene.paint.Color;



// Odd collection of technology demonstrations
// Includes a ListView (from an ObservableList)
// Dynamically created window
// property observer

public class MainApp extends Application {

    Model model = Model.getInstance();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        VBox vBox = new VBox(); //vbox-main
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        HBox welcome_message_hbox = new HBox();
        welcome_message_hbox.setAlignment(Pos.TOP_CENTER);
        Label welcome_message_text = new Label("Welcome to ThriftyRent Store!");
        welcome_message_hbox.getChildren().add(welcome_message_text);

        HBox AddCar_Quit_buttons_hbox = new HBox(); // Hold two buttons in an AddCar_Quit_buttons_hbox
        Button addCarButton = new Button("Add Car");
        Button quitButton = new Button("Quit");
        AddCar_Quit_buttons_hbox.getChildren().add(quitButton);
        AddCar_Quit_buttons_hbox.getChildren().add(addCarButton);
        AddCar_Quit_buttons_hbox.setAlignment(Pos.CENTER);

        HBox labelButton = new HBox();
        labelButton.setAlignment(Pos.CENTER);
        Label label = new Label("New value for list  ");
        Button addValueButton = new Button("Add");
        labelButton.getChildren().add(label);
        labelButton.getChildren().add(addValueButton);

        vBox.getChildren().add(welcome_message_hbox); //add hbox-welcomemessage into vbox-main and place 1st
        vBox.getChildren().add(labelButton); //add the entire hbox-labelButton into the vbox-main
        vBox.getChildren().add(AddCar_Quit_buttons_hbox); //add the entire hbox into the vbox-main

        TextField newValueToAdd = new TextField();
        vBox.getChildren().add(newValueToAdd);

        ListView<String> listView = new ListView<String>();
        listView.setItems(model.getValues());
        vBox.getChildren().add(listView);

        addValueButton.setOnAction(
                (event) -> {
                    // exclude empty strings
                    String value = newValueToAdd.getText().trim();
                    if (! value.equals("")) {
                        model.getValues().add(value);
                        newValueToAdd.setText("");
                    }
                }
        );


        quitButton.setOnAction(
                (e) -> {
                    System.out.println("bye bye!");
                    System.exit(0);
                }
        );


        // Create a dialog box that has a single (dismiss) button on it.
        Stage stageDialogBox = new Stage();
        stageDialogBox.setTitle("Car entry dialog box");

        // dialog components
        TextField yearField = new TextField();
        yearField.setPromptText("Year");
        yearField.setId("year");

        TextField modelField = new TextField();
        modelField.setPromptText("Model");
        modelField.setId("model");

        TextField makeField = new TextField();
        makeField.setPromptText("Make");
        makeField.setId("make");

        TextField numSeatsField = new TextField();
        numSeatsField.setPromptText("Number of seats");
        numSeatsField.setId("numOfSeats");

        HBox dialogButtons = new HBox();
        dialogButtons.setAlignment(Pos.CENTER);
        Button dialogOKButton = new Button("OK");
        Button dialogCancelButton = new Button("Cancel");
        dialogButtons.getChildren().add(dialogCancelButton);
        dialogButtons.getChildren().add(dialogOKButton);

        // layout the dialog components
        VBox dialogVBox = new VBox();
        dialogVBox.getChildren().add(yearField);
        dialogVBox.getChildren().add(modelField);
        dialogVBox.getChildren().add(makeField);
        dialogVBox.getChildren().add(numSeatsField);
        dialogVBox.getChildren().add(dialogButtons);

        Scene sceneDialogScene = new Scene(dialogVBox, 200, 200);
        stageDialogBox.setScene(sceneDialogScene);

        addCarButton.setOnAction( //click addCarButton to start this new stageDialogBox
                (e) -> {
                    stageDialogBox.show();
                }
        );

        // a simple action
        // this should be in a separate controller
        // it should then call on a model action to create a car,
        // then store a car.

        dialogOKButton.setOnAction(new DialogOKController(stageDialogBox));
        dialogCancelButton.setOnAction(new DialogCancelController(stageDialogBox));

        // would you like to get notified about -any- change to the field?
        modelField.textProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        System.out.println(" Text Changed to  " + newValue + ")\n");
                    }
                }
        );

        // set up the window components
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(vBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);
        Scene scene = new Scene(borderPane, 400, 600);
        primaryStage.setTitle("ThriftyRent Store"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage

        primaryStage.show(); // Display the stage
    }

}
