
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Serve Recieve");

        Menu Seams = new Menu("Seams");
        MenuItem l = new MenuItem("Left");
        MenuItem r = new MenuItem("Right");
        MenuItem js = new MenuItem("Justify Server");
        MenuItem jl = new MenuItem("Justify Libero");
        Seams.getItems().add(l);
        Seams.getItems().add(r);
        Seams.getItems().add(js);
        Seams.getItems().add(jl);
        MenuBar seam = new MenuBar();
        seam.getMenus().add(Seams);

        Menu Serve = new Menu("Serve");
        MenuItem uh = new MenuItem("Underhand");
        MenuItem f = new MenuItem("Float");
        MenuItem s = new MenuItem("Spin");
        Serve.getItems().addAll(uh,f,s);
        MenuBar serve = new MenuBar();
        serve.getMenus().add(Serve);

        Button players = new Button("Players");
        Button rotate = new Button("Rotate");

        ToolBar toggles = new ToolBar();
        toggles.getItems().addAll(rotate, serve, seam, players);

        Pane court = new Pane();
        court.setPrefSize(400,400);
        court.setStyle("-fx-border-color: PINK; -fx-border-width: 10; -fx-border-style: solid;");

        Rectangle attackLine = new Rectangle(450,10, Color.PINK);

        Circle p1 = new Circle(15, Color.BLUE);
        Circle p2 = new Circle (15, Color.PURPLE);
        Circle p3 = new Circle(15, Color.RED);
        Circle p4 = new Circle(15, Color.BLUE);
        Circle p5 = new Circle(15, Color.PURPLE);
        Circle p6 = new Circle(15, Color.RED);
        p1.relocate(385, 185);
        p2.relocate(385, 50);
        p3.relocate(210,50);
        p4.relocate(50,50);
        p5.relocate(50,185);
        p6.relocate(210,185);
        attackLine.relocate(0,150);
        court.getChildren().addAll(p1,p2,p3,p4,p5,p6,attackLine);

        BorderPane screan = new BorderPane();
        screan.setTop(toggles);
        screan.setBottom(court);

        Scene sc = new Scene(screan, 450, 600);
        stage.setScene(sc);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
