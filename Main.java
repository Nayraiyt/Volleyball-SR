import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import java.util.concurrent.atomic.AtomicInteger;



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

        Button players_bar_1 = new Button("Players");

        Button rotate_bar_1 = new Button("Rotate");


        ToolBar tool_bar_1 = new ToolBar();
        tool_bar_1.getItems().addAll(rotate_bar_1, seams_bar_1, players_bar_1);

        Pane court_1 = new Pane();
        court_1.setPrefSize(450,400);
        court_1.setStyle("-fx-border-color: #ADD8E6; -fx-color: white; -fx-border-width: 10; -fx-border-style: solid;");

        Rectangle attackLine = new Rectangle(450,10, pastelBlue);

        /* Cutesy Icons for the player positions */
        int player_icon_size = 43;
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
        
        /* Serve Movement */
        Image under_serve_icon = new Image(getClass().getResource("/images/under_serve_icon.PNG").toExternalForm());
        ImageView server_icon = new ImageView(under_serve_icon);
        Image over_serve_icon = new Image(getClass().getResource("/images/over_serve_icon.PNG").toExternalForm());
        server_icon.setFitWidth(90);
        server_icon.setPreserveRatio(true);
        server_icon.setLayoutX(0);

        AtomicInteger num_server_toggles = new AtomicInteger(0);

        Pane serve_space_1 = new Pane();
        serve_space_1.setPrefSize(450,180);

        Rectangle serve_zone1_1 = new Rectangle(90,180,Color.WHITE);
        serve_zone1_1.setLayoutX(0);
        Rectangle serve_zone2_1 = new Rectangle(90,180,Color.WHITE);
        serve_zone2_1.setLayoutX(90);
        Rectangle serve_zone3_1 = new Rectangle(90,180,Color.WHITE);
        serve_zone3_1.setLayoutX(180);
        Rectangle serve_zone4_1 = new Rectangle(90,180,Color.WHITE);
        serve_zone4_1.setLayoutX(270);
        Rectangle serve_zone5_1 = new Rectangle(90,180,Color.WHITE);
        serve_zone5_1.setLayoutX(360);

        server_icon.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                num_server_toggles.incrementAndGet();

                if(num_server_toggles.get() > 1){
                    num_server_toggles.set(0);
                    server_icon.setImage(under_serve_icon);
                }
                else{
                    server_icon.setImage(over_serve_icon);
                }
            } 
        });

        serve_zone1_1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                server_icon.setLayoutX(0);
            } 
        });

        serve_zone1_1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                server_icon.setLayoutX(0);
            } 
        });
        serve_zone2_1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                server_icon.setLayoutX(90);
            } 
        });
        serve_zone3_1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                server_icon.setLayoutX(180);
            } 
        });
        serve_zone4_1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                server_icon.setLayoutX(270);
            } 
        });
        serve_zone5_1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                server_icon.setLayoutX(360);
            } 
        });




        serve_space_1.getChildren().addAll(serve_zone1_1,serve_zone2_1,serve_zone3_1,serve_zone4_1,serve_zone5_1,server_icon);
        court_1.getChildren().addAll(attackLine,p2,p3,p4,p5,p6,p1);

        BorderPane screan_1 = new BorderPane();
        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(tool_bar_1,serve_space_1);


        screan_1.setTop(topContainer);
        screan_1.setBottom(court_1);

        /*::::Player Statistics View:::: */
        Button court_bar_2 = new Button("court");
        Button addplayers_bar_2 = new Button("add player");
        Button remove_bar_2 = new Button("Remove All");

        ToolBar tool_bar_2 = new ToolBar();
        tool_bar_2.getItems().addAll(court_bar_2, addplayers_bar_2, remove_bar_2);

        BorderPane screan_2 = new BorderPane();
        screan_2.setTop(tool_bar_2);
        

        Scene court_view_1 = new Scene(screan_1, 450, 620, Color.WHITE);
        Scene player_stats_2 = new Scene(screan_2,450,620, Color.WHITE);

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