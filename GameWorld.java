import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends MyWorld{
    
    GreenfootImage PhoneImage;
    public GameWorld(){
        PhoneImage = new GreenfootImage("backgroundGame.jpg");
        PhoneImage.scale(getWidth(), getHeight());
        setBackground(PhoneImage);
        addObject(new Hero(), 100 ,310);
    }
}
