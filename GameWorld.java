import greenfoot.*;

public class GameWorld extends MyWorld
{
    GreenfootImage PhoneImage;
    private Hero hero;
    private int cameraOffsetY = 100;
    
    public GameWorld() {
        super(600, 3600, 1);
        PhoneImage = new GreenfootImage("allGamev2.png");
        setBackground(PhoneImage);
        
        hero = new Hero(false, 3510); 
        addObject(hero, getWidth() / 2 , 3510);
        
        addObject(new Platform(), getWidth() / 4 , 3470);
        addObject(new Platform(), 390 , 3400);

        Portal portal = new Portal();
        portal.setSize(100, 100);
        addObject(portal, 300, 231);
        Greenfoot.setWorld(new Camera(this, hero));
        
    }
}
