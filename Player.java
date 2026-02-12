public class Player {
    
    private String name;
    private String position;
    private double hand_pass;
    private double platform_pass;
    
    public Player() {
        this.hand_pass = -1;
        this.platform_pass = -1;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPosition() {
        return position;
    }

    public Double getHandPass(){
        return hand_pass;
    }
    public Double getPlatformPass(){
        return platform_pass;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setHand_pass(double hand_pass) {
        if(hand_pass <= 3){
            this.hand_pass = hand_pass;
        }
    }
    
    public void setPlatform_pass(double platform_pass) {
        if(platform_pass <= 3){
            this.platform_pass = platform_pass;
        }
    }
    public void clear(){
        this.name = null;
        this.position = null;
        this.hand_pass = -1;
        this.platform_pass = -1;
    }

    @Override
    public String toString() {
        return "Player {" +
                "Name='" + (name != null ? name : "N/A") + '\'' +
                ", Position='" + (position != null ? position : "N/A") + '\'' +
                ", Hand Pass=" + (hand_pass >= 0 ? hand_pass : "Not set") +
                ", Platform Pass=" + (platform_pass >= 0 ? platform_pass : "Not set") +
                '}';
    }

}