import java.awt.*;
import java.util.Random;

public class RobberCar extends Car {
    private int xRatio;
    private int yRatio;
    private boolean captureStatus;
    private boolean escapeStatus;
    private static int totalCaptured;
    private static int totalEscaped;

    RobberCar() {
        super("Robber Car", 5000, new Engine("v4", 20, 200), "red-car.jpg");
        Random r = new Random();
        this.xRatio = r.nextInt(10) - 5;
        this.yRatio = r.nextInt(10) - 5;

        //set captureStatus and escapeStatus to false
        captureStatus = false;
        escapeStatus = false;

        //fill up the tank
        this.fillUp();
    }

    public int getCaptured(){
        return totalCaptured;
    }

    public int getEscaped(){
        return totalEscaped;
    }

    public void setCaptured(int captured){
        this.totalCaptured = captured;
    }

    public void setEscaped(int escaped){
        this.totalEscaped = escaped;
    }

    //method to check if robber car has left window, using Rectangle objects
    private boolean hasLeft(int width, int height){
        Rectangle car = new Rectangle(this.getX(),this.getY(),60,60);
        Rectangle field = new Rectangle(0,0,width,height);

        //to see if car has entirely left the field, check if field contains or intersects the car
        if(field.contains(car) || field.intersects(car)){
            return false;
        } else{
            return true;
        }
    }

    //method to check if car has escaped
    public boolean hasEscaped(){
        if (this.escapeStatus == true){
            return true;
        } else{
            return false;
        }
    }

    public void updateState(int width, int height){
        //check if car has escaped field
        //make sure only checks this the first time
        if(!this.hasEscaped()){
            if (this.hasLeft(width,height)){
                this.escapeStatus = true;
                this.totalEscaped++;
            }
        }
        
        //only drive if not captured
        if(!this.isCaptured()){
            this.drive(4,xRatio,yRatio);
        }
    }

    public void updateImage(Graphics g){
        super.updateImage(g);
    }

    public void captured(){
        if(this.captureStatus == false){
            this.setImage("jail.jpg");
            this.captureStatus = true;
            this.totalCaptured++;
        }
    }

    public boolean isCaptured(){
        if (captureStatus == false){
            return false;
        } else{
            return true;
        }
    }

}
