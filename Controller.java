import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.SwingUtilities;

class Controller implements MouseListener, KeyListener
{
    Model model;
    View view;

    Controller() throws IOException, Exception {
        model = new Model();
        view = new View(this);
    }

    public void update(Graphics g) {
        model.update(g);
    }

    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // Gets here is left mouse button was clicked
            model.spawnCar(e.getX(),e.getY());
            view.repaint();
        } else if (SwingUtilities.isRightMouseButton(e))  {
            // Gets here if right mouse button was clicked
            model.updateScene(view.getWidth(),view.getHeight());
            view.repaint();
        }
    }

    public void keyTyped(KeyEvent e){
        if (e.getKeyChar() == 'n'){
            // when 'n' is pressed, print out how many robbers captured and how many escaped
            model.printScore();
        }
        if (e.getKeyChar() == 'r'){
            // press 'r' to reset the game
            model.initialize();
            view.repaint();
        }
        if (e.getKeyChar() == 's'){
            // press 's' to speed up the game (start a new thread)
            SpriteMover spriteMover = new SpriteMover(model,view);
            Thread thread = new Thread(spriteMover);
            thread.start();
        }
    }
    public void keyPressed(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){ }

    public void mouseReleased(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) {    }


    public static void main(String[] args) throws Exception {
        //  Use the following line to determine which directory your program
        //  is being executed from, since that is where the image files will
        //  need to be.
        //System.out.println("cwd=" + System.getProperty("user.dir"));
        new Controller();
    }
}

