
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


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
        court.setPrefSize(450, 250);

        Circle p1 = new Circle(15, Color.BLUE);
        p1.relocate(100, 50);
        court.getChildren().add(p1);

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
