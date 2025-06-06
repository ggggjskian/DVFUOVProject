import greenfoot.*; 

public class GameWorld extends MyWorld
{
    GreenfootImage PhoneImage;
    private Hero hero;
    private int cameraOffsetY = 100;
    
    public GameWorld() {
        PhoneImage = new GreenfootImage("backgroundGame.jpg");
        PhoneImage.scale(getWidth(), getHeight());
        setBackground(PhoneImage);
        hero = new Hero(false);
        addObject(hero, getWidth()/2 ,310);
        addObject(new BackButton(), 17, 15);
        addObject(new Platform(), 270, 255);
       
    }
    
    
}
