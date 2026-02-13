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
import java.util.ArrayList;
import javafx.scene.shape.Ellipse;


public class Main extends Application {
    @Override
    public void start(Stage stage) {

        stage.setTitle("Volleyball SR");

        ArrayList<Integer> player_start_pos = new ArrayList<>();
        ArrayList<String> player_names = new ArrayList<>();
        ArrayList<String> player_position = new ArrayList<>();
        ArrayList<Double> player_hand_stat = new ArrayList<>();
        ArrayList<Double> player_platform_stat = new ArrayList<>();

        /*:::Court View Page::: */
        Color pastelBlue = Color.web("#ADD8E6");


        /* 
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

        */

        Button players_bar_1 = new Button("Players");

        Button rotate_bar_1 = new Button("Rotate");


        ToolBar tool_bar_1 = new ToolBar();
        tool_bar_1.getItems().addAll(rotate_bar_1, players_bar_1);

        Pane court_1 = new Pane();
        court_1.setPrefSize(450,400);
        court_1.setStyle("-fx-border-color: #ADD8E6; -fx-background-color:  white; -fx-border-width: 10; -fx-border-style: solid;");

        Rectangle attackLine = new Rectangle(450,10, pastelBlue);

        /* Cutesy Icons for the player positions */

        Ellipse p1_range = new Ellipse(), p2_range = new Ellipse(),p3_range = new Ellipse(),p4_range = new Ellipse(),p5_range =new Ellipse(),p6_range = new Ellipse();
        p1_range.setRadiusX(0);
        p1_range.setRadiusY(0);

        Ellipse ranges[] = {p1_range,p2_range,p3_range,p4_range,p5_range,p6_range};
        for(int i =1; i<6; i++){
            ranges[i].setRadiusX(50);
            ranges[i].setRadiusY(50);
            ranges[i].setOpacity(0.5);
            ranges[i].setFill(Color.web("#5E819D"));
        }

        int player_icon_size = 43;
        Image setter_icon = new Image(getClass().getResource("/images/setter_icon.PNG").toExternalForm());
        ImageView p1 = new ImageView(setter_icon);
        p1.setFitWidth(player_icon_size);
        p1.setPreserveRatio(true);
        StackPane player1 = new StackPane();
        player1.getChildren().addAll(p1_range, p1);

        Image power_icon = new Image(getClass().getResource("/images/power_icon.PNG").toExternalForm());
        ImageView p2 = new ImageView(power_icon);
        p2.setFitWidth(player_icon_size);
        p2.setPreserveRatio(true);
        StackPane player2 = new StackPane();
        player2.getChildren().addAll(p2_range, p2);

        Image middle_icon = new Image(getClass().getResource("/images/middle_icon.PNG").toExternalForm());
        ImageView p3 = new ImageView(middle_icon);
        p3.setFitWidth(player_icon_size);
        p3.setPreserveRatio(true);
        StackPane player3 = new StackPane();
        player3.getChildren().addAll(p3_range, p3);

        Image opposite_icon = new Image(getClass().getResource("/images/opposite_icon.PNG").toExternalForm());
        ImageView p4 = new ImageView(opposite_icon);
        p4.setFitWidth(player_icon_size);
        p4.setPreserveRatio(true);
        StackPane player4 = new StackPane();
        player4.getChildren().addAll(p4_range, p4);

        ImageView p5 = new ImageView(power_icon);
        p5.setFitWidth(player_icon_size);
        p5.setPreserveRatio(true);
        StackPane player5 = new StackPane();
        player5.getChildren().addAll(p5_range, p5);

        ImageView p6 = new ImageView(middle_icon);
        p6.setFitWidth(player_icon_size);
        p6.setPreserveRatio(true);
        StackPane player6 = new StackPane();
        player6.getChildren().addAll(p6_range, p6);

        int starting [][] = {{385,285},{385,50},{210,50},{50,50},{50,285},{210,285}};
        StackPane[]  positions = {player1,player2,player3,player4,player5,player6};
        for(int i = 0; i<6; i++){
            int j = 0;
            positions[i].setLayoutX(starting[i][j]);
            ranges[i].setLayoutX(starting[i][j]);
            positions[i].setLayoutY(starting[i][j+1]);
            ranges[i].setLayoutY(starting[i][j+1]);
        }
        attackLine.relocate(0,150);
        //five one rotations, justified to setter postion.
        int five_one_one [][] = {{400,100},{385,50},{75,100},{40,240},{210,275},{385,240}};
        int five_one_two [][] = {{400,50},{40,120},{10,75},{50,245},{210,270},{385,245}};
        int five_one_three [][] = {{370,50},{25,125},{60,250},{230,275},{385,250},{405,100}};
        int five_one_four [][] = {{0,0},{50,260},{230,275},{385,215},{45,125},{25,60}};
        int five_one_five [][] = {{130,60},{210,275},{385,250},{395,135},{50,250},{20,35}};
        int five_one_six [][] = {{210,50},{385,300},{400,175},{300,30},{65,235},{220,275}};
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
        court_1.getChildren().addAll(attackLine,player2,player3,player4,player5,player6,player1);

        BorderPane screan_1 = new BorderPane();
        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(tool_bar_1,serve_space_1);


        screan_1.setTop(topContainer);
        screan_1.setBottom(court_1);

        /*::::Player Statistics View:::: */
        ArrayList<Player> players = new ArrayList<Player>();

        Button court_bar_2 = new Button("court");
        Button addplayers_bar_2 = new Button("add player");
        Button remove_bar_2 = new Button("Remove All");

        ToolBar tool_bar_2 = new ToolBar();
        tool_bar_2.getItems().addAll(court_bar_2, addplayers_bar_2, remove_bar_2);
        
        Label player_info = new Label("Add Players to See Their Information");

        Pane player_cards_2 = new Pane();
        player_cards_2.getChildren().addAll(player_info);
        player_cards_2.setPrefSize(450,550);

        BorderPane screan_2 = new BorderPane();
        screan_2.setTop(tool_bar_2);
        screan_2.setBottom(player_cards_2);
        
        Scene court_view_1 = new Scene(screan_1, 450, 620, Color.WHITE);
        Scene player_stats_2 = new Scene(screan_2,450,620, Color.WHITE);

        /*::::Add Player Screan:::: */
        Button save_player_bar_3 = new Button("Save Player");
        Button exit_bar_3 = new Button("Exit");

        ToolBar tool_bar_3 = new ToolBar();
        tool_bar_3.getItems().addAll(save_player_bar_3, exit_bar_3);

        Label get_position = new Label("Position (s,m,p,o):");
        TextField input_position = new TextField();
        Label output_position = new Label("");
        Label get_name = new Label("Player Name (3 letters or less):");
        Label output_name = new Label("");
        TextField input_name = new TextField();
        Label get_hand_pass = new Label("Hand Passing Stat (0.0-3.0):");
        Label output_hand_pass = new Label("");
        TextField input_hand_pass = new TextField();
        Label get_platform_pass = new Label("Platform Passing stat (0.0-3.0): ");
        Label output_platform_pass = new Label("");
        TextField input_platform_pass = new TextField();
        Label get_start_pos = new Label("Start Position (1-6, 0 if N/A): ");
        Label output_start_pos = new Label("");
        TextField input_start_pos = new TextField();

        save_player_bar_3.setOnAction(event ->{

            Player p = new Player();

            String position = input_position.getText().trim();
            boolean position_invalid = true;
            String [] valid_pos = {"s","m1","m2","p1","p2","o"};
            for(int i = 0; i< 6; i++){
                if(position == valid_pos[i]){
                    position_invalid = false;
                    break;
                }
            }
            if(position_invalid || position.isEmpty()){
                output_position.setText("⚠ Reqired Field, s: setter, m1, m2: middle, p1, p2: power, o:opposite)");
            }
            else{
                output_position.setText("");
                p.setPosition(position);
            }

            String name = input_name.getText().trim();
            if(name.length()>3 || name.isEmpty()){
                output_name.setText("⚠ Reqired Field, 3 Letters Max");
            }
            else{
                output_name.setText("");
                p.setName(name);
            }

            String hand_pass = input_hand_pass.getText().trim();
            try {
                if(hand_pass.isEmpty() || Double.parseDouble(hand_pass) > 3){
                    output_hand_pass.setText("⚠ Required Field, Only values 0-3");
                }
                else{
                    output_hand_pass.setText("");  
                    p.setHand_pass(Double.parseDouble(hand_pass));
                }
            } catch (NumberFormatException e) {
                output_hand_pass.setText("⚠ Numerical Values only");
            }

            String platform_pass = input_platform_pass.getText().trim();
            try {
                if(platform_pass.isEmpty() || Double.parseDouble(platform_pass) > 3){
                    output_platform_pass.setText("⚠ Required Field, Only values 0-3");
                }
                else{
                    output_platform_pass.setText("");
                    p.setPlatform_pass(Double.parseDouble(platform_pass));  
                }
            } catch (NumberFormatException e) {
                output_platform_pass.setText("⚠ Numerical Values only");
            }

            String start_pos = input_start_pos.getText().trim();

            boolean occupied = false;

            for(int i = 0; i< player_start_pos.size(); i++){
                if(player_start_pos.get(i) == Integer.parseInt(start_pos)){
                    occupied = true;
                }
            }

            boolean start_pos_verrified = false;
            if(start_pos.isEmpty() || Integer.parseInt(start_pos) > 6 || Integer.parseInt(start_pos) < 0){
                output_start_pos.setText("⚠ Required Field, only (1-6, 0 if N/A)");
            }
            else if(occupied){
                output_start_pos.setText("⚠ only one player can occupy at once");
            }
            else{
                start_pos_verrified = true;
            }

            if(p.getName().isEmpty() || p.getPosition().isEmpty() || p.getPlatformPass() == -1 || p.getHandPass() == -1 || start_pos_verrified == false){

            }
            else{
                player_names.add(name);
                player_position.add(position);
                player_platform_stat.add(Double.parseDouble(platform_pass));
                player_hand_stat.add(Double.parseDouble(hand_pass));
                player_start_pos.add(Integer.parseInt(start_pos));

                if(position.equals("s")){
                    int start = Integer.parseInt(start_pos);
                    switch(start){
                        case 1: 
                            rotationNum.setSetterPos(0);
                        case 2:
                            rotationNum.setSetterPos(5);
                        case 3:
                            rotationNum.setSetterPos(4);
                        case 4:
                            rotationNum.setSetterPos(3);
                        case 5:
                            rotationNum.setSetterPos(2);
                        case 6:
                            rotationNum.setSetterPos(1);
                        default:
                    }
                }

                updatePlayerInfo(player_info, player_names,player_position,player_platform_stat,player_hand_stat);
                stage.setScene(player_stats_2);
                p.clear();
                input_name.clear();
                input_position.clear();
                input_hand_pass.clear();
                input_platform_pass.clear();
                input_start_pos.clear();

            }
        });
        
        VBox inputs = new VBox();
        inputs.getChildren().addAll(get_name,output_name,input_name,get_position, output_position,input_position,
            get_hand_pass,output_hand_pass,input_hand_pass,get_platform_pass,output_platform_pass,input_platform_pass,
            get_start_pos, output_start_pos, input_start_pos);


        BorderPane screan_3 = new BorderPane();
        Scene add_player_3 = new Scene(screan_3, 450, 400, Color.WHITE);
        screan_3.setTop(tool_bar_3);
        screan_3.setBottom(inputs);

        /* :::Buttons::: */

        players_bar_1.setOnAction(event -> {
            stage.setScene(player_stats_2);
        });
        rotate_bar_1.setOnAction(event -> { 
            rotationNum.rotate();
            for(int i = 0; i<6; i++){
                int j = 0;
                positions[i].setLayoutX(five_one[rotationNum.get()][i][j]);
                ranges[i].setLayoutX(five_one[rotationNum.get()][i][j]);
                positions[i].setLayoutY(five_one[rotationNum.get()][i][j+1]);
                ranges[i].setLayoutY(five_one[rotationNum.get()][i][j+j]);
            }
        });

        court_bar_2.setOnAction(event -> {
            stage.setScene(court_view_1);
            for(int i = 0; i<6; i++){
                int j = 0;
                positions[i].setLayoutX(five_one[rotationNum.get()][i][j]);
                positions[i].setLayoutY(five_one[rotationNum.get()][i][j+1]);
                ranges[i].setLayoutX(five_one[rotationNum.get()][i][j]);
                ranges[i].setLayoutY(five_one[rotationNum.get()][i][j+1]);
            }

        });
        addplayers_bar_2.setOnAction(event -> {
            stage.setScene(add_player_3);
        });

        exit_bar_3.setOnAction(event ->{
            stage.setScene(player_stats_2);
        });

        remove_bar_2.setOnAction(event ->{
            players.clear();
            player_names.clear();
            player_position.clear();
            player_platform_stat.clear();
            player_hand_stat.clear();
            player_start_pos.clear();
            updatePlayerInfo(player_info, player_names,player_position,player_platform_stat,player_hand_stat);
        });


        stage.setScene(court_view_1);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void updatePlayerInfo(Label player_info, ArrayList<String> names,ArrayList<String> positions, ArrayList<Double> hands, ArrayList<Double> platform){
        StringBuilder player_s = new StringBuilder();
        
        for(int i = 0; i< names.size(); i++){
            player_s.append("Player: ");
            player_s.append(names.get(i));
            player_s.append(" Position: ");
            player_s.append(positions.get(i));
            player_s.append(" Hand Pass: ");
            player_s.append(hands.get(i));
            player_s.append(" Platform Pass: ");
            player_s.append(platform.get(i));
            player_s.append("\n");
        }
        player_info.setText(player_s.toString());

        if(names.size() < 1){
            player_info.setText("Add a player to see their information");
        }
    }
}