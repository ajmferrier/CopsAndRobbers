/**
 * Created by Adam Ferrier on 10/29/2017.
 */
public class SpriteMover implements Runnable {
    Model model;
    View view;

    SpriteMover(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void run(){
        //infinite loop used to update scene (wait 2 ms b/t iterations)
        while (true){
            model.updateScene(view.getWidth(),view.getHeight());
            view.repaint();

            try{
                Thread.sleep(2);
            } catch(InterruptedException e){

            }
        }
    }
}
