import greenfoot.*;

public class ResultImage extends Actor 
{
    public ResultImage(String imagePath, int width, int height) 
    {
        GreenfootImage img = new GreenfootImage(imagePath);
        img.scale(width, height);
        setImage(img);
    }

    public ResultImage(String imagePath) 
    {
        this(imagePath, 600, 200);
    }
}
