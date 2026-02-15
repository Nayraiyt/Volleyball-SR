package com.volleyballsr;

/*
Keeps track of the setters position in Serve recieve
*/
public class TrackRotation {

    private int setterPos;

    public TrackRotation(int setterPos) {
        this.setterPos = setterPos;
    }
    //keeps the setter rotating around the court in the correct order.
    public void rotate() {
        if (setterPos < 5) setterPos++;
        else setterPos = 0;
    }

    //getter
    public int get() { 
        return setterPos; 
    }
    //setter
    public void setSetterPos(int pos) { 
        setterPos = pos; 
    }
    //This basically jiggles the screen for the range sizes, as when java fx does its thing and 
    //checks the sizes of ranges, it won't take the updated ones, only the ones that were on screan,
    //last time the court view was open.
    public void unrotate() {
        if (setterPos > 0) {
            setterPos--;
        }
        else {
            setterPos = 6;
        }
    }
}