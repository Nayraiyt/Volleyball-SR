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
        stage.setTitle("Volleyball SR");

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

        Circle p1 = new Circle(15,Color.BLUE);
        Circle p2 = new Circle(15,Color.RED);
        Circle p3 = new Circle(15,Color.RED);
        Circle p4 = new Circle(15,Color.RED);
        Circle p5 = new Circle(15,Color.RED);
        Circle p6 = new Circle(15,Color.RED);

        int starting [][] = {{385,285},{385,50},{210,50},{50,50},{50,285},{210,285}};
        Circle positions[] = {p1,p2,p3,p4,p5,p6};
        for(int i = 0; i<6; i++){
            int j = 0;
            positions[i].setLayoutX(starting[i][j]);
            positions[i].setLayoutY(starting[i][j+1]);
        }
        attackLine.relocate(0,150);
        //five one rotations, justified to setter postion.
        int five_one_one [][] = {{400,100},{385,50},{75,100},{50,275},{210,300},{385,275}};
        int five_one_two [][] = {{400,50},{45,125},{30,100},{50,275},{210,300},{385,275}};
        int five_one_three [][] = {{370,50},{25,150},{50,275},{230,300},{385,275},{410,100}};
        int five_one_four [][] = {{15,20},{50,275},{210,300},{385,235},{45,125},{20,50}};
        int five_one_five [][] = {{160,60},{210,300},{385,275},{415,135},{50,250},{20,40}};
        int five_one_six [][] = {{210,50},{385,300},{400,175},{300,30},{65,250},{220,310}};
        int five_one[][][] = {five_one_one,five_one_six,five_one_five,
                             five_one_four,five_one_three,five_one_two};

        int setterPos = -1;
        trackRotation rotationNum = new trackRotation(setterPos);

        rotate.setOnAction(event -> { 
            rotationNum.rotate();
            for(int i = 0; i<6; i++){
            int j = 0;
            positions[i].setLayoutX(five_one[rotationNum.get()][i][j]);
            positions[i].setLayoutY(five_one[rotationNum.get()][i][j+1]);
        }
        });

        court.getChildren().addAll(attackLine,p2,p3,p4,p5,p6,p1);

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
