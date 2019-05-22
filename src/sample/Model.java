package sample;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Model {

    private static Model instance;

    // note: don't use "new"!
    private ObservableList<String> names = FXCollections.observableArrayList(
            "Jessica Fletcher",
            "Chook Norris",
            "Tiddle Chick",
            "Chook-a-Sauras",
            "Moop",
            "Doop",
            "Apparat-chick"
    );


    // implement the Singleton pattern.
    // only one of these objects for the entire program.

    private Model() {    } //empty constructor

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public ObservableList<String> getValues() {
        return this.names;
    }

}

