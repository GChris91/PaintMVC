package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean confirmation;

    public static boolean display(String title, String message){
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle(title);
        primaryStage.setMinWidth(250);

        HBox layout = new HBox();
        layout.setSpacing(10);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Oui");
        yesButton.setStyle("-fx-font-size: 10pt;");
        Button noButton = new Button("Non");
        noButton.setStyle("-fx-font-size: 10pt;");

        yesButton.setOnAction(e -> {
            confirmation = true;
            primaryStage.close();
        });

        noButton.setOnAction(e -> {
            confirmation = false;
            primaryStage.close();
        });


        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();

        return confirmation;
    }
}