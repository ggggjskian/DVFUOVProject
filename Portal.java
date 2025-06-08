import greenfoot.*;

public class Portal extends Actor 
{
    public Portal() 
    {
        GreenfootImage portalImage = new GreenfootImage("portal.png");
        setImage(portalImage);
    }
    
    public void setSize(int width, int height) 
    {
        GreenfootImage img = getImage();
        img.scale(width, height);
        setImage(img);
    }
}