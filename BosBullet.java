import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BosBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BosBullet extends Actor {
    /**
     * Act - do whatever the BosBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int speed = 6;
    int rotation;
    
    
    public BosBullet(int angle){
        this.rotation = angle;
        GreenfootImage BulletImage = new GreenfootImage("bullet.png");
        BulletImage.scale(50,50);
        setImage(BulletImage);

    }
    
    public void act()
    {
        checkCollision();
        move();
          if (isAtEdge()) {
            getWorld().removeObject(this); 
        }
    }
    
    public void move() 
    {
        double angleInRadians = Math.toRadians(rotation);
        int dx = (int) Math.round(speed * Math.cos(angleInRadians));
        int dy = (int) Math.round(speed * Math.sin(angleInRadians));
        setLocation(getX() + dx, getY() + dy);
    }

    
    
    public void checkCollision() 
    {
        if (isTouching(Hero.class)) 
        {
        World world = getWorld();
        if (world != null) 
        {
            ResultImage loseImage = new ResultImage("lose.png");
            world.addObject(loseImage, world.getWidth() / 2, world.getHeight() / 2);
            Greenfoot.delay(70);  
            restartWithCamera();
        }
    }
}
    
    private void restartWithCamera() 
    {
        GameWorld newWorld = new GameWorld();
        Hero newHero = newWorld.getHero();
        Camera camera = new Camera(newWorld, newHero);
        Greenfoot.setWorld(camera);
    }
}
