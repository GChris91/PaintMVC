package sample;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import javax.imageio.ImageIO;
import java.io.File;

public class Controller {
    View vue;
    Model mod;


        // Initialisation du controller
    public Controller(View v, Model m) {
        this.vue = v;
        this.mod = m;

        initListener();

    }

    public void initListener() {

        //Liste des listeners pour les inputs<
        vue.getCanvas().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(vue.getDrawFree().isSelected()) {
                    vue.getGc().setStroke(vue.getColorOut().getValue());
                    vue.getGc().beginPath();
                    vue.getGc().lineTo(mouseEvent.getX(), mouseEvent.getY());
                }

                else if(vue.getDrawLine().isSelected()) {
                    vue.getGc().setStroke(vue.getColorOut().getValue());
                    vue.getLine().setStartX(mouseEvent.getX());
                    vue.getLine().setStartY(mouseEvent.getY());
                }
                else if(vue.getRectOut().isSelected()) {
                    vue.getGc().setStroke(vue.getColorOut().getValue());
                    vue.getRect().setX(mouseEvent.getX());
                    vue.getRect().setY(mouseEvent.getY());
                }
                else if(vue.getRectIn().isSelected()) {
                    vue.getGc().setStroke(vue.getColorOut().getValue());
                    vue.getGc().setFill(vue.getColorIn().getValue());
                    vue.getRect().setX(mouseEvent.getX());
                    vue.getRect().setY(mouseEvent.getY());
                }
                else if(vue.getEllipseOut().isSelected()) {
                    vue.getGc().setStroke(vue.getColorOut().getValue());
                    vue.getGc().setFill(vue.getColorIn().getValue());
                    vue.getEllipse().setCenterX(mouseEvent.getX());
                    vue.getEllipse().setCenterY(mouseEvent.getY());
                    vue.getCercle().setCenterX(mouseEvent.getX());
                    vue.getCercle().setCenterY(mouseEvent.getY());
                }
                else if(vue.getEllipseIn().isSelected()) {
                    vue.getGc().setFill(vue.getColorIn().getValue());
                    vue.getEllipse().setCenterX(mouseEvent.getX());
                    vue.getEllipse().setCenterY(mouseEvent.getY());
                    vue.getCercle().setCenterX(mouseEvent.getX());
                    vue.getCercle().setCenterY(mouseEvent.getY());
                }
            }
        });

        // Drag event pour les cercles et draw free
        vue.getCanvas().setOnMouseDragged(e->{
            if(vue.getDrawFree().isSelected()) {
                vue.getGc().lineTo(e.getX(), e.getY());
                vue.getGc().stroke();
            }
            else if(vue.getEllipseOut().isSelected() && e.isControlDown()){
                vue.getCercle().setRadius((Math.abs(e.getX() - vue.getCercle().getCenterX()) + Math.abs(e.getY() - vue.getCercle().getCenterY())) / 2);

                if(vue.getCercle().getCenterX() > e.getX()) {
                    vue.getCercle().setCenterX(e.getX());
                }
                if(vue.getCercle().getCenterY() > e.getY()) {
                    vue.getCercle().setCenterY(e.getY());
                }

                vue.getGc().strokeOval(vue.getCercle().getCenterX(), vue.getCercle().getCenterY(), vue.getCercle().getRadius(), vue.getCercle().getRadius());
            }
        });

        // Lignes et formes

        vue.getCanvas().setOnMouseReleased(e->{
            if(vue.getDrawFree().isSelected()) {
                vue.getGc().lineTo(e.getX(), e.getY());
                vue.getGc().stroke();
                vue.getGc().closePath();
            }
            else if(vue.getDrawLine().isSelected()) {
                vue.getLine().setEndX(e.getX());
                vue.getLine().setEndY(e.getY());
                vue.getGc().strokeLine(vue.getLine().getStartX(), vue.getLine().getStartY(), vue.getLine().getEndX(), vue.getLine().getEndY());
            }
            else if(vue.getRectOut().isSelected()) {
                vue.getRect().setWidth(Math.abs((e.getX() - vue.getRect().getX())));
                vue.getRect().setHeight(Math.abs((e.getY() - vue.getRect().getY())));
                if(vue.getRect().getX() > e.getX()) {
                    vue.getRect().setX(e.getX());
                }
                if(vue.getRect().getY() > e.getY()) {
                    vue.getRect().setY(e.getY());
                }

                vue.getGc().strokeRect(vue.getRect().getX(), vue.getRect().getY(), vue.getRect().getWidth(), vue.getRect().getHeight());

            }
            else if(vue.getRectIn().isSelected()) {
                vue.getRect().setWidth(Math.abs((e.getX() - vue.getRect().getX())));
                vue.getRect().setHeight(Math.abs((e.getY() - vue.getRect().getY())));
                if(vue.getRect().getX() > e.getX()) {
                    vue.getRect().setX(e.getX());
                }
                if(vue.getRect().getY() > e.getY()) {
                    vue.getRect().setY(e.getY());
                }

                vue.getGc().fillRect(vue.getRect().getX(), vue.getRect().getY(), vue.getRect().getWidth(), vue.getRect().getHeight());
                vue.getGc().strokeRect(vue.getRect().getX(), vue.getRect().getY(), vue.getRect().getWidth(), vue.getRect().getHeight());

            }
            else if(vue.getEllipseOut().isSelected()) {
                vue.getEllipse().setRadiusX(Math.abs(e.getX() - vue.getEllipse().getCenterX()));
                vue.getEllipse().setRadiusY(Math.abs(e.getY() - vue.getEllipse().getCenterY()));

                if(vue.getEllipse().getCenterX() > e.getX()) {
                    vue.getEllipse().setCenterX(e.getX());
                }
                if(vue.getEllipse().getCenterY() > e.getY()) {
                    vue.getEllipse().setCenterY(e.getY());
                }

                vue.getGc().strokeOval(vue.getEllipse().getCenterX(), vue.getEllipse().getCenterY(), vue.getEllipse().getRadiusX(), vue.getEllipse().getRadiusY());

            }

            else if(vue.getEllipseIn().isSelected()) {
                vue.getEllipse().setRadiusX(Math.abs(e.getX() - vue.getEllipse().getCenterX()));
                vue.getEllipse().setRadiusY(Math.abs(e.getY() - vue.getEllipse().getCenterY()));

                if(vue.getEllipse().getCenterX() > e.getX()) {
                    vue.getEllipse().setCenterX(e.getX());
                }
                if(vue.getEllipse().getCenterY() > e.getY()) {
                    vue.getEllipse().setCenterY(e.getY());
                }

                vue.getGc().strokeOval(vue.getEllipse().getCenterX(), vue.getEllipse().getCenterY(), vue.getEllipse().getRadiusX(), vue.getEllipse().getRadiusY());
                vue.getGc().fillOval(vue.getEllipse().getCenterX(), vue.getEllipse().getCenterY(), vue.getEllipse().getRadiusX(), vue.getEllipse().getRadiusY());

            }

        });

    }

            // Fonction d'enregistrement du canvas
    public void save(){
        try{
            Image snapshot = vue.getCanvas().snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paintMVC.png"));
        } catch (Exception e){
            System.out.println("Erreur lors de la sauvegarde" + e);
        }
    }

    //Fonction pour quitter basic

    public void exit(){
        Platform.exit();
    }
}
