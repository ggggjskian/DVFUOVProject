import greenfoot.*;

public class Projectile extends Actor {
    private int speed = 8;

    public Projectile() {
        
        setImage("bullet.png"); 
        GreenfootImage img = getImage();
        img.scale(40, 40);
        img.rotate(270); 
        setImage(img);  
    }


    public void act() {
        setLocation(getX(), getY() + speed);
        if (isTouching(Hero.class)) {
            restartWithCamera(); 
        } 
        else if (getY() > getWorld().getHeight() - getImage().getHeight()) {
            getWorld().removeObject(this); // Удаляем снаряд за пределами мира
        }
    }

    private void restartWithCamera() {
        GameWorld newWorld = new GameWorld();
        Hero newHero = newWorld.getHero();
        Camera camera = new Camera(newWorld, newHero);
        Greenfoot.setWorld(camera);
    }
}
