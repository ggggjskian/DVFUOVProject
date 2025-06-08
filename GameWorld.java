import greenfoot.*;

public class GameWorld extends MyWorld
{
    GreenfootImage PhoneImage;
    private Hero hero;
    private int cameraOffsetY = 100;
    
    public GameWorld() 
    {
        super(600, 3600, 1);
        PhoneImage = new GreenfootImage("allGame.png");
        setBackground(PhoneImage);
        
        hero = new Hero(false, 3510); 
        addObject(hero, getWidth() / 2 , 3510);

        addObject(new BackButton(), 17, 15);
        
        Portal portal = new Portal();
        portal.setSize(100, 100);
        addObject(portal, 300, 231);
        
    }
}
