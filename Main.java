
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Serve Recieve");

        Menu Seams = new Menu("Seams");
        MenuItem m1 = new MenuItem("Left");
        MenuItem m2 = new MenuItem("Right");
        MenuItem m3 = new MenuItem("Justify Server");
        MenuItem m4 = new MenuItem("Justify Libero");
        Seams.getItems().add(m1);
        Seams.getItems().add(m2);
        Seams.getItems().add(m3);
        Seams.getItems().add(m4);
        MenuBar mb = new MenuBar();
        mb.getMenus().add(Seams);
        VBox vb = new VBox(mb);
        Scene sc = new Scene(vb, 500, 300);



        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
