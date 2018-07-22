import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


class Sprite
{
    private String jpgName;
    private int locationX;
    private int locationY;
    private Image image;

    public Sprite(String jpgName)
    {
        setImage(jpgName);
        locationX = 0;
        locationY = 0;
    }

    public int getX() {	return locationX; }
    public int getY() {	return locationY; }
    public void setX(int x) { locationX = x; }
    public void setY(int y) { locationY = y; }

    public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ioe) {
            System.out.println("Unable to load image file.");
        }
    }
    public Image getImage() { return image; }

    public void updateState(int width, int height){

    }

    public void updateImage(Graphics g) {
        // Move the sprite
        g.drawImage(getImage(), getX(), getY(), 60, 60, null);
    }

    //method that uses java Rectangle object's intersect method to check for overlaps
    public boolean overlaps(Sprite s){
        Rectangle r = new Rectangle(s.getX(),s.getY(),60,60);
        Rectangle p = new Rectangle(this.getX(),this.getY(),60,60);

        if (r.intersects(p)){
            return true;
        } else{
            return false;
        }
    }
}