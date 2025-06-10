import greenfoot.*; 

public class FinalDisplay extends MyWorld {
    
    GreenfootImage PhoneImage;
    
    public FinalDisplay() {
        PhoneImage = new GreenfootImage("finalLoc.png");
        PhoneImage.scale(getWidth(), getHeight());
        setBackground(PhoneImage);
        addObject(new Hero(true), 100 ,310);
        
        addObject(new BackButton(), 17, 15);
        
        addObject(new Boss(), 440, 270);
        
    }
}
