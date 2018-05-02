package sample;

import javafx.application.Application;
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

public class View extends Application {


    //              ======= INTERFACE =======

    private BorderPane rootBP  = new BorderPane();
    private Scene s = new Scene(rootBP, 800, 600, Color.WHITE);

    private VBox topContainer = new VBox();
    private MenuBar menuBar = new MenuBar();
    private ToolBar toolBar = new ToolBar();

    private Menu file = new Menu("Fichier");

    private MenuItem openFile = new MenuItem("Ouvrir");
    private MenuItem save = new MenuItem("Enregistrer");
    private MenuItem saveUnder = new MenuItem("Enregistrer sous");
    private MenuItem close = new MenuItem("Fermer");
    private MenuItem quitter = new MenuItem("Quitter");


    //              ======= BUTTONS =======

    private ToggleGroup groupCouleurs = new ToggleGroup();
    private ToggleGroup groupLines = new ToggleGroup();
    private ToggleGroup groupBin = new ToggleGroup();
    private ToggleGroup groupDraw = new ToggleGroup();
    private ToggleGroup groupMove = new ToggleGroup();
    private ToggleGroup groupPlans = new ToggleGroup();


    private ColorPicker colorOut = new ColorPicker(Color.BLACK);
    private ColorPicker colorIn = new ColorPicker(Color.TRANSPARENT);


    private ToggleButton lineSmall = new ToggleButton();
    private Image imgLineSmall = new Image(getClass().getResourceAsStream("Icones/LineSmall.png"));

    private ToggleButton lineMed = new ToggleButton();
    private Image imgLineMed = new Image(getClass().getResourceAsStream("Icones/LineMed.png"));

    private ToggleButton lineBig = new ToggleButton();
    private Image imgLineBig = new Image(getClass().getResourceAsStream("Icones/LineBig.png"));

    private ToggleButton bbin = new ToggleButton();
    private Image imgBin = new Image(getClass().getResourceAsStream("Icones/Bin.png"));

    private ToggleButton drawFree = new ToggleButton();
    private Image imgDrawFree= new Image(getClass().getResourceAsStream("Icones/DrawFree.png"));

    private ToggleButton drawLine = new ToggleButton();
    private Image imgDrawLine = new Image(getClass().getResourceAsStream("Icones/DrawLine.png"));

    private ToggleButton rectOut = new ToggleButton();
    private Image imgRectOut = new Image(getClass().getResourceAsStream("Icones/RectOut.png"));

    private ToggleButton rectIn = new ToggleButton();
    private Image imgRectIn = new Image(getClass().getResourceAsStream("Icones/RectIn.png"));

    private ToggleButton ellipseOut = new ToggleButton();
    private Image imgEllipseOut = new Image(getClass().getResourceAsStream("Icones/EllipseOut.png"));

    private ToggleButton ellipseIn = new ToggleButton();
    private Image imgEllipseIn = new Image(getClass().getResourceAsStream("Icones/EllipseIn.png"));

    private ToggleButton polyOut = new ToggleButton();
    private Image imgPolyOut = new Image(getClass().getResourceAsStream("Icones/PolyOut.png"));

    private ToggleButton polyIn = new ToggleButton();
    private Image imgPolyIn = new Image(getClass().getResourceAsStream("Icones/PolyIn.png"));

    private ToggleButton move = new ToggleButton();
    private Image imgMove = new Image(getClass().getResourceAsStream("Icones/Move.png"));

    private ToggleButton planAvant = new ToggleButton();
    private Image imgPlanAvant = new Image(getClass().getResourceAsStream("Icones/PlanAvant.png"));

    private ToggleButton planPremier = new ToggleButton();
    private Image imgPlanPremier = new Image(getClass().getResourceAsStream("Icones/PlanPremier.png"));

    private ToggleButton planArriere = new ToggleButton();
    private Image imgPlanArriere = new Image(getClass().getResourceAsStream("Icones/PlanArriere.png"));

    private ToggleButton planDernier = new ToggleButton();
    private Image imgPlanDernier = new Image(getClass().getResourceAsStream("Icones/PlanDernier.png"));


    //              ======= CANVAS =======

    private Canvas canvas = new Canvas(800,600);
    private GraphicsContext gc;


    //              ======= SHAPES =======

    private Line line = new Line();
    private Rectangle rect = new Rectangle();
    private Circle cercle = new Circle();
    private Ellipse ellipse = new Ellipse();




    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        //               ======= INTERFACE =======

        topContainer.getChildren().addAll(menuBar,toolBar);
        rootBP.setTop(topContainer);

        menuBar.getMenus().add(file);
        file.getItems().addAll(openFile, save, saveUnder, close, quitter);

        rootBP.setTop(topContainer);


        //              ======= BUTTONS =======

        lineSmall.setGraphic(new ImageView(imgLineSmall));
        lineMed.setGraphic(new ImageView(imgLineMed));
        lineBig.setGraphic(new ImageView(imgLineBig));
        bbin.setGraphic(new ImageView(imgBin));
        drawFree.setGraphic(new ImageView(imgDrawFree));
        drawLine.setGraphic(new ImageView(imgDrawLine));
        rectOut.setGraphic(new ImageView(imgRectOut));
        rectIn.setGraphic(new ImageView(imgRectIn));
        ellipseIn.setGraphic(new ImageView(imgEllipseIn));
        ellipseOut.setGraphic(new ImageView(imgEllipseOut));
        polyOut.setGraphic(new ImageView(imgPolyOut));
        polyIn.setGraphic(new ImageView(imgPolyIn));
        move.setGraphic(new ImageView(imgMove));
        planAvant.setGraphic(new ImageView(imgPlanAvant));
        planPremier.setGraphic(new ImageView(imgPlanPremier));
        planArriere.setGraphic(new ImageView(imgPlanArriere));
        planDernier.setGraphic(new ImageView(imgPlanDernier));

        toolBar.getItems().addAll(colorOut,colorIn, lineSmall, lineMed, lineBig, bbin, drawFree, drawLine, rectOut, rectIn, ellipseOut, ellipseIn, polyOut, polyIn, move, planAvant, planPremier, planArriere, planDernier);

        //groupCouleurs.getToggles().addAll(colorOut, colorIn);
        groupLines.getToggles().addAll(lineSmall, lineMed, lineBig);
        groupBin.getToggles().addAll(bbin);
        groupDraw.getToggles().addAll(drawFree, drawLine, rectOut, rectIn, ellipseOut, ellipseIn, polyOut, polyIn);
        groupMove.getToggles().addAll(move);
        groupPlans.getToggles().addAll(planAvant, planPremier, planArriere, planDernier);



        //              ======= CANVAS =======

        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);


        //              ======= COLOR PICKER =======

        colorOut.setOnAction(e->{
            gc.setStroke(colorOut.getValue());
        });
        colorIn.setOnAction(e->{
            gc.setFill(colorIn.getValue());
        });


        rootBP.setCenter(canvas);



        //              ======= INTERFACE =======

        primaryStage.setTitle("Paint");
        primaryStage.setScene(s);

        Model model = new Model();
        Controller controller = new Controller(this, model);

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);


    }

    public BorderPane getRootBP() {
        return rootBP;
    }

    public Scene getS() {
        return s;
    }

    public VBox getTopContainer() {
        return topContainer;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public Menu getFile() {
        return file;
    }

    public MenuItem getOpenFile() {
        return openFile;
    }

    public MenuItem getSave() {
        return save;
    }

    public MenuItem getSaveUnder() {
        return saveUnder;
    }

    public MenuItem getClose() {
        return close;
    }

    public MenuItem getQuitter() {
        return quitter;
    }

    public ToggleGroup getGroupCouleurs() {
        return groupCouleurs;
    }

    public ToggleGroup getGroupLines() {
        return groupLines;
    }

    public ToggleGroup getGroupBin() {
        return groupBin;
    }

    public ToggleGroup getGroupDraw() {
        return groupDraw;
    }

    public ToggleGroup getGroupMove() {
        return groupMove;
    }

    public ToggleGroup getGroupPlans() {
        return groupPlans;
    }

    public ColorPicker getColorOut() {
        return colorOut;
    }

    public ColorPicker getColorIn() {
        return colorIn;
    }

    public ToggleButton getLineSmall() {
        return lineSmall;
    }

    public Image getImgLineSmall() {
        return imgLineSmall;
    }

    public ToggleButton getLineMed() {
        return lineMed;
    }

    public Image getImgLineMed() {
        return imgLineMed;
    }

    public ToggleButton getLineBig() {
        return lineBig;
    }

    public Image getImgLineBig() {
        return imgLineBig;
    }

    public ToggleButton getBbin() {
        return bbin;
    }

    public Image getImgBin() {
        return imgBin;
    }

    public ToggleButton getDrawFree() {
        return drawFree;
    }

    public Image getImgDrawFree() {
        return imgDrawFree;
    }

    public ToggleButton getDrawLine() {
        return drawLine;
    }

    public Image getImgDrawLine() {
        return imgDrawLine;
    }

    public ToggleButton getRectOut() {
        return rectOut;
    }

    public Image getImgRectOut() {
        return imgRectOut;
    }

    public ToggleButton getRectIn() {
        return rectIn;
    }

    public Image getImgRectIn() {
        return imgRectIn;
    }

    public ToggleButton getEllipseOut() {
        return ellipseOut;
    }

    public Image getImgEllipseOut() {
        return imgEllipseOut;
    }

    public ToggleButton getEllipseIn() {
        return ellipseIn;
    }

    public Image getImgEllipseIn() {
        return imgEllipseIn;
    }

    public ToggleButton getPolyOut() {
        return polyOut;
    }

    public Image getImgPolyOut() {
        return imgPolyOut;
    }

    public ToggleButton getPolyIn() {
        return polyIn;
    }

    public Image getImgPolyIn() {
        return imgPolyIn;
    }

    public ToggleButton getMove() {
        return move;
    }

    public Image getImgMove() {
        return imgMove;
    }

    public ToggleButton getPlanAvant() {
        return planAvant;
    }

    public Image getImgPlanAvant() {
        return imgPlanAvant;
    }

    public ToggleButton getPlanPremier() {
        return planPremier;
    }

    public Image getImgPlanPremier() {
        return imgPlanPremier;
    }

    public ToggleButton getPlanArriere() {
        return planArriere;
    }

    public Image getImgPlanArriere() {
        return imgPlanArriere;
    }

    public ToggleButton getPlanDernier() {
        return planDernier;
    }

    public Image getImgPlanDernier() {
        return imgPlanDernier;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Line getLine() {
        return line;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Circle getCercle() {
        return cercle;
    }

    public Ellipse getEllipse() {
        return ellipse;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setRootBP(BorderPane rootBP) {
        this.rootBP = rootBP;
    }

    public void setS(Scene s) {
        this.s = s;
    }

    public void setTopContainer(VBox topContainer) {
        this.topContainer = topContainer;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public void setFile(Menu file) {
        this.file = file;
    }

    public void setOpenFile(MenuItem openFile) {
        this.openFile = openFile;
    }

    public void setSave(MenuItem save) {
        this.save = save;
    }

    public void setSaveUnder(MenuItem saveUnder) {
        this.saveUnder = saveUnder;
    }

    public void setClose(MenuItem close) {
        this.close = close;
    }

    public void setQuitter(MenuItem quitter) {
        this.quitter = quitter;
    }

    public void setGroupCouleurs(ToggleGroup groupCouleurs) {
        this.groupCouleurs = groupCouleurs;
    }

    public void setGroupLines(ToggleGroup groupLines) {
        this.groupLines = groupLines;
    }

    public void setGroupBin(ToggleGroup groupBin) {
        this.groupBin = groupBin;
    }

    public void setGroupDraw(ToggleGroup groupDraw) {
        this.groupDraw = groupDraw;
    }

    public void setGroupMove(ToggleGroup groupMove) {
        this.groupMove = groupMove;
    }

    public void setGroupPlans(ToggleGroup groupPlans) {
        this.groupPlans = groupPlans;
    }

    public void setColorOut(ColorPicker colorOut) {
        this.colorOut = colorOut;
    }

    public void setColorIn(ColorPicker colorIn) {
        this.colorIn = colorIn;
    }

    public void setLineSmall(ToggleButton lineSmall) {
        this.lineSmall = lineSmall;
    }

    public void setImgLineSmall(Image imgLineSmall) {
        this.imgLineSmall = imgLineSmall;
    }

    public void setLineMed(ToggleButton lineMed) {
        this.lineMed = lineMed;
    }

    public void setImgLineMed(Image imgLineMed) {
        this.imgLineMed = imgLineMed;
    }

    public void setLineBig(ToggleButton lineBig) {
        this.lineBig = lineBig;
    }

    public void setImgLineBig(Image imgLineBig) {
        this.imgLineBig = imgLineBig;
    }

    public void setBbin(ToggleButton bbin) {
        this.bbin = bbin;
    }

    public void setImgBin(Image imgBin) {
        this.imgBin = imgBin;
    }

    public void setDrawFree(ToggleButton drawFree) {
        this.drawFree = drawFree;
    }

    public void setImgDrawFree(Image imgDrawFree) {
        this.imgDrawFree = imgDrawFree;
    }

    public void setDrawLine(ToggleButton drawLine) {
        this.drawLine = drawLine;
    }

    public void setImgDrawLine(Image imgDrawLine) {
        this.imgDrawLine = imgDrawLine;
    }

    public void setRectOut(ToggleButton rectOut) {
        this.rectOut = rectOut;
    }

    public void setImgRectOut(Image imgRectOut) {
        this.imgRectOut = imgRectOut;
    }

    public void setRectIn(ToggleButton rectIn) {
        this.rectIn = rectIn;
    }

    public void setImgRectIn(Image imgRectIn) {
        this.imgRectIn = imgRectIn;
    }

    public void setEllipseOut(ToggleButton ellipseOut) {
        this.ellipseOut = ellipseOut;
    }

    public void setImgEllipseOut(Image imgEllipseOut) {
        this.imgEllipseOut = imgEllipseOut;
    }

    public void setEllipseIn(ToggleButton ellipseIn) {
        this.ellipseIn = ellipseIn;
    }

    public void setImgEllipseIn(Image imgEllipseIn) {
        this.imgEllipseIn = imgEllipseIn;
    }

    public void setPolyOut(ToggleButton polyOut) {
        this.polyOut = polyOut;
    }

    public void setImgPolyOut(Image imgPolyOut) {
        this.imgPolyOut = imgPolyOut;
    }

    public void setPolyIn(ToggleButton polyIn) {
        this.polyIn = polyIn;
    }

    public void setImgPolyIn(Image imgPolyIn) {
        this.imgPolyIn = imgPolyIn;
    }

    public void setMove(ToggleButton move) {
        this.move = move;
    }

    public void setImgMove(Image imgMove) {
        this.imgMove = imgMove;
    }

    public void setPlanAvant(ToggleButton planAvant) {
        this.planAvant = planAvant;
    }

    public void setImgPlanAvant(Image imgPlanAvant) {
        this.imgPlanAvant = imgPlanAvant;
    }

    public void setPlanPremier(ToggleButton planPremier) {
        this.planPremier = planPremier;
    }

    public void setImgPlanPremier(Image imgPlanPremier) {
        this.imgPlanPremier = imgPlanPremier;
    }

    public void setPlanArriere(ToggleButton planArriere) {
        this.planArriere = planArriere;
    }

    public void setImgPlanArriere(Image imgPlanArriere) {
        this.imgPlanArriere = imgPlanArriere;
    }

    public void setPlanDernier(ToggleButton planDernier) {
        this.planDernier = planDernier;
    }

    public void setImgPlanDernier(Image imgPlanDernier) {
        this.imgPlanDernier = imgPlanDernier;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public void setCercle(Circle cercle) {
        this.cercle = cercle;
    }

    public void setEllipse(Ellipse ellipse) {
        this.ellipse = ellipse;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
}
