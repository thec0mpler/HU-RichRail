package org.hu.richrail.fxmlController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.hu.richrail.cli.Client;
import org.hu.richrail.cli.command.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TerminalController extends Controller implements Initializable {
    @FXML
    private TextArea logTextArea;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button closeButton;

    @Override
    void init(Window window) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Terminal.fxml"));
            Stage terminalStage = new Stage();

            terminalStage.setTitle("Terminal");
            terminalStage.setScene(new Scene(root));
            terminalStage.setResizable(false);
            terminalStage.initModality(Modality.APPLICATION_MODAL);

            if (window != null)
                terminalStage.initOwner(window);

            terminalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Client client = Client.getInstance();

        client.addCommand(new AddToCommand());
        client.addCommand(new DeleteTrainCommand());
        client.addCommand(new DeleteWagonCommand());
        client.addCommand(new GetNumSeatsTrainCommand());
        client.addCommand(new GetNumSeatsWagonCommand());
        client.addCommand(new GetWagonsCommand());
        client.addCommand(new NewTrainCommand());
        client.addCommand(new NewWagonCommand());
        client.addCommand(new RemoveFromCommand());

        inputTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    String input = inputTextField.getText();

                    logTextArea.appendText("> " + input + "\n");
                    logTextArea.appendText(
                            client.execute(input) + "\n"
                    );

                    inputTextField.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        closeButton.setOnAction(event -> close());
    }

    private void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
