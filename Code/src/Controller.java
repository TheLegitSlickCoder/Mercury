package sample;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Controller {

    private FileManager fm = new FileManager();

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField input;
    @FXML
    private Label output;

    @FXML
    public void initialize() {
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'sample.fxml'.";
        assert output != null : "fx:id=\"output\" was not injected: check your FXML file 'sample.fxml'.";
    }

    //Code:

    @FXML
    public void update(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String userInput = input.getText();

            if (fm.pathExists(userInput)) {
                File path = new File(userInput);
                try {
                    Desktop.getDesktop().open(path);
                }
                catch (Exception e) {
                    return;
                }
            }
            if (!fm.pathExists(userInput)) {
                try {
                    String googleSearch = userInput.replaceAll(" ", "+");
                    Desktop.getDesktop().browse(new URI("https://www.google.com/#q=" + googleSearch));
                }
                catch (Exception e) {
                    System.out.println(e.toString());
                    return;
                }
            }

            return;
        }

        String userInput = input.getText();
        boolean exists = fm.pathExists(userInput);

        if (exists) {
            updateOutputText("Computer: " + userInput);
            return;
        }

        if (!exists) {
            updateOutputText("Search: " + userInput);
            return;
        }
    }

    private void updateOutputText(String text) {
        output.setText(text);
    }
}