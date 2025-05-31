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
    GreenfootImage buttonImage;
    
    public Button(int width, int height, String path) {
        this.width = width;
        this.height = height;
        buttonImage = new GreenfootImage(path);
        buttonImage.scale(width, height);
        setImage(buttonImage);
    }
    
    public void execute(Runnable func){
        if(Greenfoot.mouseClicked(this)){
            func.run();        
        }
        
    }
}
