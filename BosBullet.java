import greenfoot.*;

public class BosBullet extends Actor 
{
    int speed = 8;
    int rotation;
    public BosBullet(int angle)
    {
        this.rotation = angle;
        GreenfootImage BulletImage = new GreenfootImage("bullet.png");
        BulletImage.scale(50,50);
        setImage(BulletImage);
    }

    public void act() 
    {
        checkCollision();
        move();
        if (isAtEdge()) 
        {
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

    private void restartWithCamera() {
        getWorld().stopped();
        GameWorld newWorld = new GameWorld();
        Hero newHero = newWorld.getHero();
        Camera camera = new Camera(newWorld, newHero);
        Greenfoot.setWorld(camera);
    }
}
