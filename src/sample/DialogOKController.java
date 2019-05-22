package sample;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.stage.Stage;

import javafx.scene.Scene;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

// note this has not been tested!

public class DialogOKController implements EventHandler<ActionEvent>  {

    private Stage dialogBox;

    public DialogOKController(Stage dialogBox) {
        this.dialogBox = dialogBox;
    }

    @Override // Override the handle method
    public void handle(ActionEvent e) {
        System.out.println("Ok!");

        Scene scene = dialogBox.getScene();


        TextField yearField = (TextField) scene.lookup("#year");
        TextField modelField = (TextField) scene.lookup("#model");
        TextField makeField = (TextField) scene.lookup("#make");
        TextField numOfSeatsField = (TextField) scene.lookup("#numOfSeats");


        System.out.println(yearField.getText());
        System.out.println(modelField.getText());
        System.out.println(makeField.getText());
        System.out.println(numOfSeatsField.getText());


        dialogBox.close();
    }

}



