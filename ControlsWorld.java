import greenfoot.*;

public class ControlsWorld extends MyWorld 
{
    public ControlsWorld() 
    {
        GreenfootImage bg = new GreenfootImage("ControlsBackground.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        addObject(new Hero(), 100, 310);
        
    }
}
