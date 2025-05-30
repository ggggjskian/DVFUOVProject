import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor{
   
    
    protected int width;
    protected int height;
    
    public Button(int width, int height) {
        this.width = width;
        this.height = height;
        setImage(new GreenfootImage(width, height));
    }
    public void execute(Runnable func){
        if(Greenfoot.mouseClicked(this)){
            func.run();        
        }
        
    }
}
