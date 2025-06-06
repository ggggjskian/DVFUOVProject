import greenfoot.*;

public class Hero extends Actor 
{
    //анимации
    GreenfootImage StaticHero; 
    GreenfootImage StaticHeroHorizont; 
    GreenfootImage[] walkRight = new GreenfootImage[3]; 
    GreenfootImage[] walkLeft = new GreenfootImage[3]; 
    boolean facingRight = true;  
    int HereWeGo = 1; 
    int animationDelay = 5; 
    int animationCounter = 0; 

    //механика движения
    int speedH = 5; 
    int gravity = 1;
    int speedV = 0; 
    final int Jump = -15;
    
    boolean FinalLoca;

    
    public Hero(boolean flag) {
        //анимации ходьбы
        for (int i = 0; i < 3; i++) {
            walkRight[i] = new GreenfootImage("spriteWalk" + i + ".png");
            walkLeft[i] = new GreenfootImage("spriteWalk" + i + ".png");
            walkLeft[i].mirrorHorizontally(); 
            walkRight[i].scale(75, 75);
            walkLeft[i].scale(75, 75);
        }

        //статичное изобр
        StaticHero = new GreenfootImage("staticHero.png");
        StaticHero.scale(75, 75);
        StaticHeroHorizont = new GreenfootImage(StaticHero);
        StaticHeroHorizont.mirrorHorizontally();
        setImage(StaticHero);
        this.FinalLoca = flag;
    }

    public void act() {
        if (!FinalLoca){
            fall();  //применение гравитации
            checkLandLeft();//проверка на приземление
            checkLandRight();
            checkLandUnderLeft();
            checkLandUnderRight();
            
        }
        move();
        animate();
    }
    
    public void move() {
         
        if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && getX() > getImage().getWidth() / 2 - 15) 
        {
            setLocation(getX() - speedH, getY());
            HereWeGo = 0;
            facingRight = false;
        }
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && getX() < getWorld().getWidth() - getImage().getWidth() / 2) 
        {
            setLocation(getX() + speedH, getY());
            HereWeGo = 2;
            facingRight = true;
        }
        else {
            HereWeGo = 1;
        }
        
        if (FinalLoca) {
            if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {
                setLocation(getX(), getY() - speedH);}
            else if ((Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) && getY() < getWorld().getHeight() - getImage().getHeight() / 2) {
                setLocation(getX(), getY() + speedH);
            }
        }
        else {
             if (Greenfoot.isKeyDown("space") && check()) {
                speedV = Jump;
            } 
        }
    }

    public void animate() {
        ++animationCounter;
        int frame = (animationCounter / animationDelay) % 3;

        if (HereWeGo == 2) 
        { 
            setImage(walkRight[frame]);
        } else if (HereWeGo == 0) 
        { 
            setImage(walkLeft[frame]);
        } else 
        {
            if (facingRight)
                setImage(StaticHero);
            else
                setImage(StaticHeroHorizont);
        }
    }

    public boolean check() {
        return (isTouching(Platform.class) || getY() >= 310) && speedV == 0;
    }

    public void checkLandLeft() {
        Actor platform = getOneObjectAtOffset(10, getImage().getHeight() / 2 + 5, Platform.class);
        if (platform != null && speedV >= 0) {
            int platformTop = platform.getY() - platform.getImage().getHeight() / 2;
            int heroHalfHeight = getImage().getHeight() / 2;
            setLocation(getX(), platformTop - heroHalfHeight); 
            speedV = 0; 
        } 
        else if (getY() >= 310 && speedV >= 0) {
            setLocation(getX(), 310);
            speedV = 0;
        }
    }
    
    
    
    public void checkLandRight() {
        Actor platform = getOneObjectAtOffset(-10, getImage().getHeight() / 2 + 5, Platform.class);
        if (platform != null && speedV >= 0) {
            int platformTop = platform.getY() - platform.getImage().getHeight() / 2;
            int heroHalfHeight = getImage().getHeight() / 2;
            setLocation(getX(), platformTop - heroHalfHeight); 
            speedV = 0; 
        } 
        else if (getY() >= 310 && speedV >= 0) {
            setLocation(getX(), 310);
            speedV = 0;
        }
    }
    
    public void checkLandUnderLeft() {
        Actor platform = getOneObjectAtOffset(30, -getImage().getHeight() / 2 - 5, Platform.class);
        if (platform != null && speedV < 0) {
            int platformTop = platform.getY() + platform.getImage().getHeight() / 2;
            int heroHalfHeight = getImage().getHeight() / 2;
            setLocation(getX(), platformTop + heroHalfHeight); 
            speedV = 0; 
        } 
       
    }
    
    public void checkLandUnderRight() {
        Actor platform = getOneObjectAtOffset(-30, -getImage().getHeight() / 2 - 5, Platform.class);
        if (platform != null && speedV < 0) {
            int platformTop = platform.getY() + platform.getImage().getHeight() / 2;
            int heroHalfHeight = getImage().getHeight() / 2;
            setLocation(getX(), platformTop + heroHalfHeight); 
            speedV = 0; 
        } 
       
    }

    public void fall() {
        speedV += gravity; 
        setLocation(getX(), getY() + speedV); 
    }
}
