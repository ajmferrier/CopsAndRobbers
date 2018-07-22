public class GasTank {
    private int capacity;
    private double level;

    GasTank(int maxCapacity){
        if (maxCapacity < 0){
            this.capacity = 0;
        } else {
            this.capacity = maxCapacity;
        }

        this.level = 0;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public double getLevel(){
        return this.level;
    }

    public void setLevel(double levelIn){
        if (levelIn < 0.0){
            this.level = 0.0;
        } else if (levelIn > this.capacity){
            this.level = (double) this.capacity;
        } else {
            this.level = levelIn;
        }
    }
}
