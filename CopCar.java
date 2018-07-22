import java.awt.*;
import java.util.Random;

public class CopCar extends Car{

    private static int xRatio;
    private static int yRatio;
    private boolean signX;
    private boolean signY;
//    private int xDir;
//    private int yDir;

    CopCar(){
        super("Cop Car", 5000, new Engine("v4",30,100),"cop-car.jpg");
        Random r = new Random();
        this.xRatio = r.nextInt(10) - 5;
        this.yRatio = r.nextInt(10) - 5;
        this.signX = true;
        this.signY = true;
//        this.xDir = xRatio;
//        this.yDir = yRatio;

        // fill up the tank
        this.fillUp();
    }

    //method to help change drive direction
    public void driveHelper(){
        if(signX && signY){
            this.drive(2,xRatio,yRatio);
        } else if(!signX && signY){
            this.drive(2,-xRatio,yRatio);
        } else if(signX && !signY){
            this.drive(2,xRatio,-yRatio);
        } else{
            this.drive(2,-xRatio,-yRatio);
        }
    }

    public void updateState(int width, int height){
        Rectangle carRect = new Rectangle(this.getX(),this.getY(),60,60);
        Rectangle boundary = new Rectangle(0,0,width,height);
        if(boundary.contains(carRect)){
            this.driveHelper();
        } else{
            if(this.getX() > 0 && this.getX() < width && (this.getY() < 0 || this.getY() + 60 > height)){
                //first check North/South boundary
                this.signY = !this.signY;
                this.driveHelper();
            } else if(this.getY() > 0 && this.getY() < height && (this.getX() < 0 || this.getX() + 60 > width)){
                //next check East/West boundary
                this.signX = !this.signX;
                this.driveHelper();
            } else if((this.getX() < 0 && this.getY() < 0) ||(this.getX() > width && this.getY() < 0)
                    || (this.getX() < 0 && this.getY() > height) || (this.getX() > width && this.getY() > height)){
                //check corners
                this.signX = !this.signX;
                this.signY = !this.signY;
                this.driveHelper();
            } 
        }

    }

    public void updateImage(Graphics g){
        super.updateImage(g);
    }
}
