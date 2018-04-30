package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean confirmation;

    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Oui");
        Button noButton = new Button("Non");

        yesButton.setOnAction(e -> {
            confirmation = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            confirmation = false;
            window.close();
        });
        Scene scene = new Scene();
        window.setScene(scene);
        window.showAndWait();


    }
}
