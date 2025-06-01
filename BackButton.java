import greenfoot.*;

public class BackButton extends Button
{
    public BackButton()
    {
        super(40, 35, "Back.png"); 
    }

    public void act()
    {
        execute(this::goBackToMenu);
    }

    public void goBackToMenu()
    {
        Greenfoot.setWorld(new Menu());
    }
}
