public class Engine {
    private String type;
    private int mpg;
    private int maxSpeed;

    Engine(String type, int mpg, int maxSpeed){
        if (type.length() == 0){
            this.type = "Generic engine";
        } else{
            this.type = type;
        }
        if (mpg < 0){
            this.mpg = 0;
        } else{
            this.mpg = mpg;
        }
        if (maxSpeed < 0){
            this.maxSpeed = 0;
        } else{
            this.maxSpeed = maxSpeed;
        }
    }

    public int getMPG(){
        return this.mpg;
    }

    public int getMaxSpeed(){
        return this.maxSpeed;
    }

    public String getDescription(){
        return this.type + " (MPG: " + this.mpg + ", Max speed: " + this.maxSpeed + ")";
    }
}
