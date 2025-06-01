import greenfoot.*; 

public class PlayButton extends Button
{
    GameWorld world;
    
    public PlayButton() 
    {
        super(250, 80, "play.png");
    }
    
    public void act()
    {
        execute(this::beginGame);
    }
    
    public void beginGame()
    {
        GameWorld world = new GameWorld();
        Greenfoot.setWorld(world);
    
    }
    
    
}
