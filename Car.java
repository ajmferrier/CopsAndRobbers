import java.awt.*;
import java.text.DecimalFormat;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class Car extends Sprite{
    private String description;
    private GasTank tank;
    private Engine engine;

    Car(String description, int capacity, Engine engine, String jpgName){
        super(jpgName);
        //if description length 0 set "generic car"
        if (description.length() == 0){
            this.description = "Generic car";
        } else{
            this.description = description;
        }
        //check if engine object is null
        if (engine == null){
            this.engine = new Engine("",0,0);
        } else{
            this.engine = engine;
        }
        //intialize gas tank
        this.tank = new GasTank(capacity);
        //intialize location
        this.setX(0);
        this.setY(0);
    }

    public String getDescription(){
        String description = this.description + " [engine: " + this.engine.getDescription();
        description += ", fuel: " + String.format("%.2f",this.tank.getLevel()) + "/" + this.tank.getCapacity();
        description += ", location: (" + this.getX() + "," + this.getY() + ")]";

        return description;
    }

    public double getFuelLevel(){
        return this.tank.getLevel();
    }

    public int getMPG(){
        return this.engine.getMPG();
    }

    public void fillUp(){
        this.tank.setLevel((double) this.tank.getCapacity());
    }

    public int getMaxSpeed(){
        return this.engine.getMaxSpeed();
    }

    public boolean isEmpty(){
        if (this.tank.getLevel() <= 0){
            return true;
        } else{
            return false;
        }
    }

    public double drive(int distance, double xRatio, double yRatio){
        //Create "unit vector" from x/y ratios given
        double hyp = Math.sqrt(xRatio*xRatio + yRatio*yRatio);
        double xUnit = xRatio/hyp;
        double yUnit = yRatio/hyp;

        //create double from int distance given
        double distanceFloat = (double) distance;

        //check if car has enough gas for trip
        double travellable = this.getFuelLevel() * this.getMPG();

        //case where car does NOT run out of fuel
        if (travellable >= distanceFloat){
            //first remove used fuel from tank
            double gasUsed = distanceFloat/this.getMPG();
            this.tank.setLevel(this.getFuelLevel() - gasUsed);

            //set new location
            this.setX(this.getX() + (int) (distanceFloat*xUnit));
            this.setY(this.getY() + (int) (distanceFloat*yUnit));
            return distanceFloat;
        } else{
            //case where car DOES run out of fuel
            this.tank.setLevel(0.0);
            System.out.printf("Ran out of gas after %1$.2f miles.\n", travellable);

            //set new location
            this.setX(this.getX() + (int) (travellable*xUnit));
            this.setY(this.getY() + (int) (travellable*yUnit));
            return travellable;
        }
    }

    public void updateImage(Graphics g){
        super.updateImage(g);
    }
}
