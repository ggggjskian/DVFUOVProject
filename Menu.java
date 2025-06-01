import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends MyWorld
{
    GreenfootImage PhoneImage;
    public Menu(){
        PhoneImage = new GreenfootImage("MenuBackground.jpg");
        PhoneImage.scale(getWidth(), getHeight());
        setBackground(PhoneImage);
        addObject(new PlayButton(), 258,241);
        addObject(new ControlsButton(), 261, 332);
        addObject(new InfoAboutGameButton(), 80,118);
    }
}
