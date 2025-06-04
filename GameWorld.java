import greenfoot.*; 

public class GameWorld extends MyWorld
{
    GreenfootImage PhoneImage;
    public GameWorld()
    {
        PhoneImage = new GreenfootImage("backgroundGame.jpg");
        PhoneImage.scale(getWidth(), getHeight());
        setBackground(PhoneImage);
        addObject(new Hero(), 100 ,310);
        
        addObject(new BackButton(), 17, 15);
        addObject(new Platform(), 120,270);
    }
}
