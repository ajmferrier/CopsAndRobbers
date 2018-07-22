import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


class Model
{
    private ArrayList<Sprite> sprite = new ArrayList<Sprite>();
    // use carCounter to keep track of # of cars for alternating spawns
    private int carCounter;

    Model() throws IOException {
        //first spawn Bank
        Bank bank = new Bank();
        synchronized (this){
            sprite.add(bank);
        }
        this.carCounter = 0;
    }

    public void initialize(){
        synchronized (this){
            ArrayList<Sprite> newList = new ArrayList<Sprite>();
            sprite = newList;
            Bank bank = new Bank();
            sprite.add(bank);
            this.carCounter = 0;
        }

        //create "dummy" robber car inorder to reset total counts
        RobberCar dummy = new RobberCar();
        dummy.setCaptured(0);
        dummy.setEscaped(0);
    }

    public void update(Graphics g) {
        synchronized (this){
            for (int i = 0; i < sprite.size(); i++){
                sprite.get(i).updateImage(g);
            }
        }
    }

    public void updateScene(int width, int height){
        synchronized (this){
            //remove escaped robbers
            for(Iterator it = sprite.iterator(); it.hasNext(); ){
                Sprite s = (Sprite) it.next();
                if (s instanceof RobberCar){
                    if(((RobberCar) s).hasEscaped()){
                        it.remove();
                    }
                }
            }

            for (int i = 0; i < sprite.size(); i++){
                //starting with first cop car, check if overlapping any robbers
                if(i >= 2 && (sprite.get(i) instanceof CopCar)){
                    for (int j = 1; j < sprite.size(); j++){
                        if (sprite.get(i).overlaps(sprite.get(j)) && (sprite.get(j) instanceof RobberCar)){
                            if(!((RobberCar)sprite.get(j)).isCaptured()){
                                ((RobberCar)sprite.get(j)).captured();
                            }
                        }
                    }
                }

                //perform update
                sprite.get(i).updateState(width, height);
            }
        }
    }

    //method to print number of robbers escaped/captured
    public void printScore(){
        RobberCar robber = new RobberCar();
        System.out.println("Robbers escaped: " + robber.getEscaped());
        System.out.println("Robbers captured: " + robber.getCaptured());
    }

    public void spawnCar(int x, int y){
        synchronized (this){
            // use carCounter to determine which car to spawn
            if (this.carCounter % 2 == 0){
                RobberCar robberCar = new RobberCar();
                robberCar.setX(300);
                robberCar.setY(300);
                sprite.add(robberCar);
                this.carCounter++;
            } else{
                CopCar copCar = new CopCar();
                copCar.setX(x);
                copCar.setY(y);
                sprite.add(copCar);
                this.carCounter++;
            }
        }

    }
}
