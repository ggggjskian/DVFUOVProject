import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayButton extends Button{
    GameWorld world;
    
    
    public PlayButton() {
        super(200, 80);
 
    }
    
    public void act(){
        execute(this::beginGame);
    }
    
    public void beginGame(){
        GameWorld world = new GameWorld();
        Greenfoot.setWorld(world);
    
    }
    
    
}
