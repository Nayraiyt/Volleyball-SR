import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Main extends Application {
    @Override
    public void start(Stage stage) {

        stage.setTitle("Volleyball SR");

        /*:::Court View Page::: */
        Color pastelBlue = Color.web("#ADD8E6");

        Menu seams_menu_1 = new Menu("Seams");
        MenuItem left_1 = new MenuItem("Left");
        MenuItem right_1 = new MenuItem("Right");
        MenuItem jserver_1 = new MenuItem("Justify Server");
        MenuItem jlibero_1 = new MenuItem("Justify Libero");
        seams_menu_1.getItems().add(left_1);
        seams_menu_1.getItems().add(right_1);
        seams_menu_1.getItems().add(jserver_1);
        seams_menu_1.getItems().add(jlibero_1);
        MenuBar seams_bar_1 = new MenuBar();
        seams_bar_1.getMenus().add(seams_menu_1);

        Menu serve_menu_1 = new Menu("Serve");
        MenuItem underhand_1 = new MenuItem("Underhand");
        MenuItem float_1 = new MenuItem("Float");
        MenuItem spin_1 = new MenuItem("Spin");
        serve_menu_1.getItems().addAll(underhand_1,float_1,spin_1);
        MenuBar serve_bar_1 = new MenuBar();
        serve_bar_1.getMenus().add(serve_menu_1);

        Button players_bar_1 = new Button("Players");

        Button rotate_bar_1 = new Button("Rotate");


        ToolBar tool_bar_1 = new ToolBar();
        tool_bar_1.getItems().addAll(rotate_bar_1, serve_bar_1, seams_bar_1, players_bar_1);

        Pane court_1 = new Pane();
        court_1.setPrefSize(400,400);
        court_1.setStyle("-fx-border-color: #ADD8E6; -fx-border-width: 10; -fx-border-style: solid;");

        Rectangle attackLine = new Rectangle(450,10, pastelBlue);

        int player_icon_size = 125;

        Image setter_icon = new Image(getClass().getResource("/images/setter_icon.PNG").toExternalForm());
        ImageView p1 = new ImageView(setter_icon);
        p1.setFitWidth(player_icon_size);
        p1.setPreserveRatio(true);

        Image power_icon = new Image(getClass().getResource("/images/power_icon.PNG").toExternalForm());
        ImageView p2 = new ImageView(power_icon);
        p2.setFitWidth(player_icon_size);
        p2.setPreserveRatio(true);

        Image middle_icon = new Image(getClass().getResource("/images/middle_icon.PNG").toExternalForm());
        ImageView p3 = new ImageView(middle_icon);
        p3.setFitWidth(player_icon_size);
        p3.setPreserveRatio(true);

        Image opposite_icon = new Image(getClass().getResource("/images/opposite_icon.PNG").toExternalForm());
        ImageView p4 = new ImageView(opposite_icon);
        p4.setFitWidth(player_icon_size);
        p4.setPreserveRatio(true);

        ImageView p5 = new ImageView(power_icon);
        p5.setFitWidth(player_icon_size);
        p5.setPreserveRatio(true);

        ImageView p6 = new ImageView(middle_icon);
        p6.setFitWidth(player_icon_size);
        p6.setPreserveRatio(true);

        int starting [][] = {{385,285},{385,50},{210,50},{50,50},{50,285},{210,285}};
        ImageView positions[] = {p1,p2,p3,p4,p5,p6};
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

        court_1.getChildren().addAll(attackLine,p2,p3,p4,p5,p6,p1);

        BorderPane screan_1 = new BorderPane();
        screan_1.setTop(tool_bar_1);
        screan_1.setBottom(court_1);

        /*::::Player Statistics View:::: */
        Button court_bar_2 = new Button("court");
        Button addplayers_bar_2 = new Button("add player");
        Button remove_bar_2 = new Button("Remove All");

        ToolBar tool_bar_2 = new ToolBar();
        tool_bar_2.getItems().addAll(court_bar_2, addplayers_bar_2, remove_bar_2);

        BorderPane screan_2 = new BorderPane();
        screan_2.setTop(tool_bar_2);
        

        Scene court_view_1 = new Scene(screan_1, 450, 600);
        Scene player_stats_2 = new Scene(screan_2,450,600);

        /* :::Buttons::: */

        players_bar_1.setOnAction(event -> {
            stage.setScene(player_stats_2);
        });

        rotate_bar_1.setOnAction(event -> { 
            rotationNum.rotate();
            for(int i = 0; i<6; i++){
            int j = 0;
            positions[i].setLayoutX(five_one[rotationNum.get()][i][j]);
            positions[i].setLayoutY(five_one[rotationNum.get()][i][j+1]);
        }
        });

        court_bar_2.setOnAction(event -> {
            stage.setScene(court_view_1);
        });

        stage.setScene(court_view_1);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}