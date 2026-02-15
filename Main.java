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
        // Stores all Player's created (their stats)
        ArrayList<Integer> player_start_pos = new ArrayList<>();
        ArrayList<String> player_names = new ArrayList<>();
        ArrayList<String> player_position = new ArrayList<>();
        ArrayList<Double> player_hand_stat = new ArrayList<>();
        ArrayList<Double> player_platform_stat = new ArrayList<>();

        /*:::Court View Page::: */
        Color pastelBlue = Color.web("#ADD8E6");

        /* 
        Unimplemented Menu Options

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

        //Buttons in Court Screan
        Button players_bar_1 = new Button("Players");

        Button rotate_bar_1 = new Button("Rotate");
        ToolBar tool_bar_1 = new ToolBar();
        tool_bar_1.getItems().addAll(rotate_bar_1, players_bar_1);

        //court pane(for all the player icons + the attack line)
        Pane court_1 = new Pane();
        court_1.setPrefSize(450,400);
        court_1.setStyle("-fx-border-color: #ADD8E6; -fx-background-color:  white; -fx-border-width: 10; -fx-border-style: solid;");
        Rectangle attackLine = new Rectangle(450,10, pastelBlue);

        /* Cutesy Icons for the player positions */

        //player range instantiated (the semi transparent oval that surrounds each player.
        //showing what amount of court they can comfortably cover. (based on their hand(height) and platform passing(width)))
        Ellipse p1_range = new Ellipse(), p2_range = new Ellipse(),p3_range = new Ellipse(),p4_range = new Ellipse(),p5_range =new Ellipse(),p6_range = new Ellipse();
        p1_range.setRadiusX(0); //setter doesn't need a serve receive range, as they dont pass
        p1_range.setRadiusY(0);

        Ellipse ranges[] = {p1_range,p2_range,p3_range,p4_range,p5_range,p6_range};
        for(int i =1; i<6; i++){                    //sets default stuff for the passing ranges
            ranges[i].setRadiusX(50);       //default range size is 50.
            ranges[i].setRadiusY(50);
            ranges[i].setOpacity(0.5);    
            ranges[i].setFill(Color.web("#5E819D")); 
        }

        int player_icon_size = 43;      
        //setter icon (starts in bottom right corner)
        Image setter_icon = new Image(getClass().getResource("/images/setter_icon.PNG").toExternalForm());
        ImageView p1 = new ImageView(setter_icon);
        p1.setFitWidth(player_icon_size);
        p1.setPreserveRatio(true);
        StackPane player1 = new StackPane();
        player1.getChildren().addAll(p1);

        //Power icons, starts in top right, and bottom left
        Image power_icon = new Image(getClass().getResource("/images/power_icon.PNG").toExternalForm());
        ImageView p2 = new ImageView(power_icon);
        p2.setFitWidth(player_icon_size);
        p2.setPreserveRatio(true);
        //stack pane used to combine the icon and their range into one entity for locations
        StackPane player2 = new StackPane();
        player2.getChildren().addAll(p2_range, p2);

        ImageView p5 = new ImageView(power_icon);
        p5.setFitWidth(player_icon_size);
        p5.setPreserveRatio(true);
        StackPane player5 = new StackPane();
        player5.getChildren().addAll(p5_range, p5);

        //middle icons, starts in middle top and bottom
        Image middle_icon = new Image(getClass().getResource("/images/middle_icon.PNG").toExternalForm());
        ImageView p3 = new ImageView(middle_icon);
        p3.setFitWidth(player_icon_size);
        p3.setPreserveRatio(true);
        StackPane player3 = new StackPane();
        player3.getChildren().addAll(p3_range, p3);

        ImageView p6 = new ImageView(middle_icon);
        p6.setFitWidth(player_icon_size);
        p6.setPreserveRatio(true);
        StackPane player6 = new StackPane();
        player6.getChildren().addAll(p6_range, p6);

        //Opposite icon, starts in top right corner
        Image opposite_icon = new Image(getClass().getResource("/images/opposite_icon.PNG").toExternalForm());
        ImageView p4 = new ImageView(opposite_icon);
        p4.setFitWidth(player_icon_size);
        p4.setPreserveRatio(true);
        StackPane player4 = new StackPane();
        player4.getChildren().addAll(p4_range, p4);

        //p[#] represents player, and the typical starting position. In volleyball, Setter typically
        //starts in 1, with the pattern, Setter, Power,Middle, Oposite, Power Middle, (counter-clockwize,)
        //(its the serving order)

        int starting [][] = { //starting positions (1-6)
            {350,255},
            {320,50},
            {180,50},
            {25,50},
            {25,250},
            {180,250}
        };
        StackPane[]  positions = {player1,player2,player3,player4,player5,player6};
        for(int i = 0; i<6; i++){
            int j = 0;
            positions[i].setLayoutX(starting[i][j]);
            positions[i].setLayoutY(starting[i][j+1]);
        }

        attackLine.relocate(0,150);
        //five one rotations, justified to setter postion. Five one is the typical serve receive 
        //set up for teams with 1 setter. 
        int five_one_one [][] = {
			{400,100},
			{375,50},
			{160,100},
			{50,230},
			{210,275},
			{385,240}
		};

		int five_one_two [][] = { 
			{400,50},
			{50,125},
			{30,75},
			{50,245},
			{210,270},
			{385,245}
		};

		int five_one_three [][] = {
			{370,50},
			{25,125},
			{60,250},
			{230,275},
			{385,250},
			{405,100}
		};

		int five_one_four [][] = {
			{20,30},
			{60,125},
			{230,275},
			{385,215},
			{50,260},
			{40,65}
		};

		int five_one_five [][] = {
			{130,60},
			{210,275},
			{385,250},
			{395,135},
			{50,250},
			{20,35}
		};

		int five_one_six [][] = {
			{210,50},
			{385,300},
			{400,175},
			{300,40},
			{65,235},
			{220,275}
		};

        int five_one[][][] = {five_one_one,five_one_six,five_one_five,
                             five_one_four,five_one_three,five_one_two};
        int setterPos = -1; //when setter's start position hasn't been set, its degault is -1,
        //which is where there is no rotations.

        trackRotation rotationNum = new trackRotation(setterPos);
        //This class is for keeping track of where the setter is, as rotations are based off this.
        
        /* Serve Movement */
        //Server icon is set. There is an under and over hand serving icon.
        Image under_serve_icon = new Image(getClass().getResource("/images/under_serve_icon.PNG").toExternalForm());
        ImageView server_icon = new ImageView(under_serve_icon);
        Image over_serve_icon = new Image(getClass().getResource("/images/over_serve_icon.PNG").toExternalForm());
        server_icon.setFitWidth(90);
        server_icon.setPreserveRatio(true);
        server_icon.setLayoutX(0);
        //atomic int used so it can be changed inside a event.
        AtomicInteger num_server_toggles = new AtomicInteger(0);
        
        //There are 5 main serving zones, each split into a seperate white rectangle on the serve area.
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

        //When you click the server, it toggles between under and overhand serve
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

        //when you click a serve zone, the server is moved to that zone.
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

        //adding the serve zone rectangles and server to the serve space.
        serve_space_1.getChildren().addAll(serve_zone1_1,serve_zone2_1,serve_zone3_1,serve_zone4_1,serve_zone5_1,server_icon);
        court_1.getChildren().addAll(attackLine,player2,player3,player4,player5,player6,player1);

        //combining the nav bar over serve space into one container
        BorderPane screan_1 = new BorderPane();
        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(tool_bar_1,serve_space_1);

        //setting the nav bar and serve space to the top of the screan, and the court to the bottom
        screan_1.setTop(topContainer);
        screan_1.setBottom(court_1);

        /*::::Player Statistics View:::: (its a new screan!!!)*/ 
        //keeps a array of all the players (only can keep track of the number of players, not their stats)
        ArrayList<Player> players = new ArrayList<>();

        //buttons for the player statistics screen. court goes back to court screan.
        Button court_bar_2 = new Button("court");
        Button addplayers_bar_2 = new Button("add player");
        Button remove_bar_2 = new Button("Remove All");
        Button override_bar_2 = new Button("Team Peers");
        ToolBar tool_bar_2 = new ToolBar();
        tool_bar_2.getItems().addAll(court_bar_2, addplayers_bar_2, remove_bar_2,override_bar_2);
        
        //when there are no players added yet, this is the text displayed on the screan
        Label player_info = new Label("Add Players to See Their Information");

        Pane player_cards_2 = new Pane();
        player_cards_2.getChildren().addAll(player_info);
        player_cards_2.setPrefSize(450,550);

        //adding the nav bar to the top, and the player info to the bottom.
        BorderPane screan_2 = new BorderPane();
        screan_2.setTop(tool_bar_2);
        screan_2.setBottom(player_cards_2);
        
        Scene court_view_1 = new Scene(screan_1, 450, 620, Color.WHITE);
        Scene player_stats_2 = new Scene(screan_2,450,620, Color.WHITE);

        /*::::Add Player Screan:::: */
        //buttons for the add player screen 
        Button save_player_bar_3 = new Button("Save Player");
        Button exit_bar_3 = new Button("Exit");
        ToolBar tool_bar_3 = new ToolBar();
        tool_bar_3.getItems().addAll(save_player_bar_3, exit_bar_3);

        //Questions, error checking, then a text box for the user to input the information prompted
        Label get_position = new Label("Position (s,m1,m2,p1,p2,o):");
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
            //After you save a player, there is error checking to see if the correct format of info was given.
            //If not, text is displayed that tells you what you did wrong.

            //This keeps track of the current player being made, and is checked at the end to see if it passes
            //all the error checks
            Player p = new Player();


            //takes the string given by the position text box, and evaluates it to see if its "ok"
            String position = input_position.getText().trim();
            boolean position_invalid = true;
            String [] valid_pos = {"s","m1","m2","p1","p2","o"}; //these are the only accepted inputs
            for(int i = 0; i< 6; i++){
                if(position.equals(valid_pos[i])){
                    position_invalid = false;
                    break;
                }
            }
            if(position_invalid || position.isEmpty()){
                output_position.setText("⚠ Reqired Field, s: setter, m1, m2: middle, p1, p2: power, o:opposite)");
            }
            else{ //if the position in valid, it gets put into the player object p
                output_position.setText("");
                p.setPosition(position);
            }

            //name is checked, then set into the player object if it passes.
            String name = input_name.getText().trim();
            if(name.length()>3 || name.isEmpty()){
                output_name.setText("⚠ Reqired Field, 3 Letters Max");
            }
            else{
                output_name.setText("");
                p.setName(name);
            }

            //hand pass has to be convereted into a double, then evaluated. (same format)
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

            //same as hand pass, uses try catch to see if it can be turned into a double
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

            //makes sure that only one player is put in each start position on the court.
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
            //checks to see if all of the error checks passed
            if(p.getName().isEmpty() || p.getPosition().isEmpty() || p.getPlatformPass() == -1 || p.getHandPass() == -1 || start_pos_verrified == false){

            }
            //if yes, all the information is put into the arrays, and stuff happens.
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
                            break;
                        case 2:
                            rotationNum.setSetterPos(5);
                            break;
                        case 3:
                            rotationNum.setSetterPos(4);
                            break;
                        case 4:
                            rotationNum.setSetterPos(3);
                            break;
                        case 5:
                            rotationNum.setSetterPos(2);
                            break;
                        case 6:
                            rotationNum.setSetterPos(1);
                            break;
                        default:
                    }
                }
                //sets the passing range of the player inputed
                setRange(ranges,position,Double.parseDouble(platform_pass), Double.parseDouble(hand_pass));
                updatePlayerInfo(player_info, player_names,player_position,player_platform_stat,player_hand_stat);
                //moves back to the old screen.
                stage.setScene(player_stats_2);
                //clears the information for the next potential player
                p.clear();
                input_name.clear();
                input_position.clear();
                input_hand_pass.clear();
                input_platform_pass.clear();
                input_start_pos.clear();

            }
        });
        
        //adds all of the questions, error messages, and text boxs to the screen
        VBox inputs = new VBox();
        inputs.getChildren().addAll(get_name,output_name,input_name,get_position, output_position,input_position,
            get_hand_pass,output_hand_pass,input_hand_pass,get_platform_pass,output_platform_pass,input_platform_pass,
            get_start_pos, output_start_pos, input_start_pos);
        BorderPane screan_3 = new BorderPane();
        Scene add_player_3 = new Scene(screan_3, 450, 400, Color.WHITE);
        screan_3.setTop(tool_bar_3);
        screan_3.setBottom(inputs);

        /* :::Buttons::: */
        //In the court screan, this sends the user to the player stats screan
        players_bar_1.setOnAction(event -> {
            stage.setScene(player_stats_2);
        });

        //In the court screan, this rotates the serve recieve rotations
        rotate_bar_1.setOnAction(event -> { 
            rotationNum.rotate();
            for(int i = 0; i<6; i++){
                positions[i].setLayoutX(five_one[rotationNum.get()][i][0] - (positions[i].getWidth()/2));
                positions[i].setLayoutY(five_one[rotationNum.get()][i][1] - (positions[i].getHeight()/2));

            }
        });
        //In the player stats screan, this sends the user to the court screan, and updates the court to any new player info
        court_bar_2.setOnAction(event -> {
            stage.setScene(court_view_1);
            rotationNum.rotate();
            rotationNum.unrotate();
            for(int i = 0; i<6; i++){
                positions[i].setLayoutX(five_one[rotationNum.get()][i][0] - (positions[i].getWidth()/2));
                positions[i].setLayoutY(five_one[rotationNum.get()][i][1] - (positions[i].getHeight()/2));
            }
        });
        //in the Player stats screan, this opens up the adding player screan
        addplayers_bar_2.setOnAction(event -> {
            stage.setScene(add_player_3);
        });
        //in the adding player screean, this brings the user back to the stats screan
        exit_bar_3.setOnAction(event ->{
            stage.setScene(player_stats_2);
        });
        //in the Player stats screan, this overrides all player information, and turns it into Naomi's volleyball team Stats (for my convience)
        override_bar_2.setOnAction(event -> {
            player_names.clear();
            player_position.clear();
            player_platform_stat.clear();
            player_hand_stat.clear();
            player_start_pos.clear();
            rotationNum.setSetterPos(0);
            String[] names = {"yu","nay","em","qio","kel","kar"};
            String[] pos = {"s","p1","m1","o","p2","m2"};
            double[] plat = {0,2.4,2.4,2.0,2.3,2.7};
            int[] start = {1,2,3,4,5,6};
            double[] hands = {0,3,2.1,1.5,2.0,2.5};

            for(int i = 0; i<6; i++){
                player_names.add(names[i]);
                player_position.add(pos[i]);
                player_platform_stat.add(plat[i]);
                player_hand_stat.add(hands[i]);
                player_start_pos.add(start[i]);
                setRange(ranges,pos[i],plat[i], hands[i]);
            }
            updatePlayerInfo(player_info, player_names,player_position,player_platform_stat,player_hand_stat);

        });
        // This removes all players from the player stats, and clears their information
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

    
    /*
    updates the player information displayed on the player stats screan

    Inputs: 
    - Player info (the information displated to the player stats screan)
    - All the names from the stats inputted (in order of time added)
    - All their respective positions (in order of time added)
    - Akk their respective hand passing stats (out of 3)(in order of time added)
    - all their respective platform passing stats (out of 3)(in order of time added)

    outputs: displays the updated statistics to the player stats screan.
    */
    private void updatePlayerInfo(Label player_info, ArrayList<String> names,ArrayList<String> positions, ArrayList<Double> hands, ArrayList<Double> platform){
        StringBuilder player_s = new StringBuilder(); //so that the string can be changed.
        
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

        if(names.size() < 1){ //used if there are no players yet added.
            player_info.setText("Add a player to see their information");
        }
    }
    
    /*
    sets the range of the players passing, to each position

    Inputs:
    - All court players ranges
    - The selected players position
    - Thier passing stats
    */
    private void setRange(Ellipse[] rang,String pos,double ps, double hs){
        int modifier = 60; //modifyer, all stats are multiplied by this to find their displayed range
                switch(pos){
                    case "s":
                        break;
                    case "p1":
                        rang[1].setRadiusX(modifier*ps);
                        rang[1].setRadiusY(modifier*hs);
                        break;
                    case "m1":
                        rang[2].setRadiusX(modifier*ps);
                        rang[2].setRadiusY(modifier*hs);
                        break;
                    case "o":
                        rang[3].setRadiusX(modifier*ps);
                        rang[3].setRadiusY(modifier*hs);
                        break;
                    case "p2":
                        rang[4].setRadiusX(modifier*ps);
                        rang[4].setRadiusY(modifier*hs);
                        break;
                    case "m2":
                        rang[5].setRadiusX(modifier*ps);
                        rang[5].setRadiusY(modifier*hs);
                        break;
                    }
    }
}