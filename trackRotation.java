public class trackRotation {
    private int setterPos;

    public trackRotation(int setterPos) {
        this.setterPos = setterPos;
    }

    public void rotate() {
        if(setterPos < 5){
            setterPos++;
        }
        else{
            setterPos = 0;
        }
    }
    public int get(){
        return setterPos;
    }

    public void setSetterPos(int pos){
        setterPos = pos;
    }
    public void unrotate(){
        if(setterPos>0){
            setterPos--;
        }
        else{
            setterPos = 6;
        }
    }
}