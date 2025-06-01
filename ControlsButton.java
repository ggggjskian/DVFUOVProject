import greenfoot.*;

public class ControlsButton extends Button
{
    public ControlsButton()
    {
        super(263, 82, "controls.png");
    }

    public void act()
    {
        execute(this::goToControls);
    }

    public void goToControls()
    {
        Greenfoot.setWorld(new ControlsWorld());
    }
}
