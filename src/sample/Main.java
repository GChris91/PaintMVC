package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


//                  ======= STAGE =======

        primaryStage.setTitle("PaintMVC in JavaFX");


//                  ======= SCENE =======

        BorderPane layout  = new BorderPane();
        Scene scene = new Scene(layout, 800, 600, Color.WHITE);
        primaryStage.show();
        primaryStage.setScene(scene);

        VBox topContainer = new VBox();
        MenuBar menuBar = new MenuBar();
        ToolBar toolBar = new ToolBar();

        topContainer.getChildren().addAll(menuBar,toolBar);
        layout.setTop(topContainer);


//                  ======= MENUBAR =======

        Menu file = new Menu("Fichier");

        MenuItem openFile = new MenuItem("Ouvrir");
        MenuItem save = new MenuItem("Enregistrer");
        MenuItem saveUnder = new MenuItem("Enregistrer sous");
        MenuItem close = new MenuItem("Fermer");
        MenuItem quitter = new MenuItem("Quitter");

        quitter.setOnAction(e -> {
            boolean confirmation = ConfirmBox.display("Quitter", "Voulez-vous vraiment quitter PaintMVC?");
        });



        menuBar.getMenus().add(file);
        file.getItems().addAll(openFile, save, saveUnder, close, quitter);

        layout.setTop(topContainer);


//              ======= BUTTONS =======

        //ToggleGroup groupCouleurs = new ToggleGroup();
        ToggleGroup groupLines = new ToggleGroup();
        ToggleGroup groupBin = new ToggleGroup();
        ToggleGroup groupDraw = new ToggleGroup();
        ToggleGroup groupMove = new ToggleGroup();
        ToggleGroup groupPlans = new ToggleGroup();

        ColorPicker colorOut = new ColorPicker(Color.BLACK);
        ColorPicker colorIn = new ColorPicker(Color.TRANSPARENT);


        ToggleButton lineSmall = new ToggleButton();
        Image imgLineSmall = new Image(getClass().getResourceAsStream("Icones/LineSmall.png"));
        lineSmall.setGraphic(new ImageView(imgLineSmall));
        //lineSmall.setOnAction(e -> System.out.println("Hello World"));

        ToggleButton lineMed = new ToggleButton();
        Image imgLineMed = new Image(getClass().getResourceAsStream("Icones/LineMed.png"));
        lineMed.setGraphic(new ImageView(imgLineMed));

        ToggleButton lineBig = new ToggleButton();
        Image imgLineBig = new Image(getClass().getResourceAsStream("Icones/LineBig.png"));
        lineBig.setGraphic(new ImageView(imgLineBig));

        ToggleButton bbin = new ToggleButton();
        Image imgBin = new Image(getClass().getResourceAsStream("Icones/Bin.png"));
        bbin.setGraphic(new ImageView(imgBin));

        ToggleButton drawFree = new ToggleButton();
        Image imgDrawFree= new Image(getClass().getResourceAsStream("Icones/DrawFree.png"));
        drawFree.setGraphic(new ImageView(imgDrawFree));

        ToggleButton drawLine = new ToggleButton();
        Image imgDrawLine = new Image(getClass().getResourceAsStream("Icones/DrawLine.png"));
        drawLine.setGraphic(new ImageView(imgDrawLine));

        ToggleButton rectOut = new ToggleButton();
        Image imgRectOut = new Image(getClass().getResourceAsStream("Icones/RectOut.png"));
        rectOut.setGraphic(new ImageView(imgRectOut));

        ToggleButton rectIn = new ToggleButton();
        Image imgRectIn = new Image(getClass().getResourceAsStream("Icones/RectIn.png"));
        rectIn.setGraphic(new ImageView(imgRectIn));

        ToggleButton ellipseOut = new ToggleButton();
        Image imgEllipseOut = new Image(getClass().getResourceAsStream("Icones/EllipseOut.png"));
        ellipseOut.setGraphic(new ImageView(imgEllipseOut));

        ToggleButton ellipseIn = new ToggleButton();
        Image imgEllipseIn = new Image(getClass().getResourceAsStream("Icones/EllipseIn.png"));
        ellipseIn.setGraphic(new ImageView(imgEllipseIn));

        ToggleButton polyOut = new ToggleButton();
        Image imgPolyOut = new Image(getClass().getResourceAsStream("Icones/PolyOut.png"));
        polyOut.setGraphic(new ImageView(imgPolyOut));

        ToggleButton polyIn = new ToggleButton();
        Image imgPolyIn = new Image(getClass().getResourceAsStream("Icones/PolyIn.png"));
        polyIn.setGraphic(new ImageView(imgPolyIn));

        ToggleButton move = new ToggleButton();
        Image imgMove = new Image(getClass().getResourceAsStream("Icones/Move.png"));
        move.setGraphic(new ImageView(imgMove));

        ToggleButton planAvant = new ToggleButton();
        Image imgPlanAvant = new Image(getClass().getResourceAsStream("Icones/PlanAvant.png"));
        planAvant.setGraphic(new ImageView(imgPlanAvant));

        ToggleButton planPremier = new ToggleButton();
        Image imgPlanPremier = new Image(getClass().getResourceAsStream("Icones/PlanPremier.png"));
        planPremier.setGraphic(new ImageView(imgPlanPremier));

        ToggleButton planArriere = new ToggleButton();
        Image imgPlanArriere = new Image(getClass().getResourceAsStream("Icones/PlanArriere.png"));
        planArriere.setGraphic(new ImageView(imgPlanArriere));

        ToggleButton planDernier = new ToggleButton();
        Image imgPlanDernier = new Image(getClass().getResourceAsStream("Icones/PlanDernier.png"));
        planDernier.setGraphic(new ImageView(imgPlanDernier));

        toolBar.getItems().addAll(colorOut,colorIn, lineSmall, lineMed, lineBig, bbin, drawFree, drawLine, rectOut, rectIn, ellipseOut, ellipseIn, polyOut, polyIn, move, planAvant, planPremier, planArriere, planDernier);

        //groupCouleurs.getToggles().addAll(colorOut, colorIn);
        groupLines.getToggles().addAll(lineSmall, lineMed, lineBig);
        groupBin.getToggles().addAll(bbin);
        groupDraw.getToggles().addAll(drawFree, drawLine, rectOut, rectIn, ellipseOut, ellipseIn, polyOut, polyIn);
        groupMove.getToggles().addAll(move);
        groupPlans.getToggles().addAll(planAvant, planPremier, planArriere, planDernier);





//                  ======= CANVAS =======

        Canvas canvas = new Canvas(800,600);
        /*GraphicsContext gc;
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);

        Line line = new Line();
        Rectangle rect = new Rectangle();
        Circle circle = new Circle();
        Ellipse ellipse = new Ellipse();*/


        layout.setCenter(canvas);

    }


//                  ======= END ======

    public static void main(String[] args) {
        launch(args);
    }
}
