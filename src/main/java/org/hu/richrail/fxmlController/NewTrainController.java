package org.hu.richrail.fxmlController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.hu.richrail.model.Train;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewTrainController extends Controller implements Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private Button addButton;

    @Override
    void init(Window window) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/NewTrain.fxml"));
            Stage stage = new Stage();

            stage.setTitle("New train");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            if (window != null) {
                parentWindow = window;

                stage.initOwner(window);
            }

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTextField.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    submit();
        });

        addButton.setOnAction(event ->
                submit()
        );
    }

    private void submit() {
        try {
            trainManager.addTrain(
                    new Train(nameTextField.getText())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        close();
    }

    private void close() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}
