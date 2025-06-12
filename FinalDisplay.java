import greenfoot.*; 

public class FinalDisplay extends MyWorld 
{
    GreenfootImage PhoneImage;
    public FinalDisplay() 
    {
        PhoneImage = new GreenfootImage("finalLoc.png");
        PhoneImage.scale(getWidth(), getHeight());
        setBackground(PhoneImage);
        addObject(new Hero(false), 100 ,310);
        
        addObject(new BackButton(), 17, 15);
        
        addObject(new Boss(), 440, 240);
        
        addObject(new Platform(), 240, 240);
        
         TimerDisplay timer = new TimerDisplay(30);
        addObject(timer, 560, 20);
    }
}
