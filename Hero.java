import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor{
    int speed = 5;
    public void act(){
        moveV();
    }
    
    public void moveV(){
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            setLocation(getX() - speed, getY());
        }else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
            setLocation(getX() + speed, getY());
        }
    }
    
    
}
