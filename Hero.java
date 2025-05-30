import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor{
    GreenfootImage StaticHero;
    int speed = 5;
    
    
    public Hero (){
        StaticHero = new GreenfootImage("staticHero.png");
        StaticHero.scale(75,75);
        setImage(StaticHero);
    
    }
    
    public void act(){
        moveV();
    }
    
    public void moveV(){
        
        if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && getX() > getImage().getWidth()/ 2 - 15){
            setLocation(getX() - speed, getY());
        }else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && (getX() < getWorld().getWidth() - getImage().getWidth()/2)){
            setLocation(getX() + speed, getY());
        }
    }

}
