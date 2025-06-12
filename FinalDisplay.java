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
        
        
        addObject(new Platform(), 240, 240);
        
        TimerDisplay timer = new TimerDisplay(5);
        addObject(timer, 560, 20);
        
        Boss boss = new Boss(timer); 
        addObject(boss, 440, 240);
    }
}
