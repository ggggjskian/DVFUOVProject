import greenfoot.*;

public class Hero extends Actor {
    // Анимации
    GreenfootImage StaticHero; 
    GreenfootImage StaticHeroHorizont; 
    GreenfootImage[] walkRight = new GreenfootImage[3]; 
    GreenfootImage[] walkLeft = new GreenfootImage[3]; 
    boolean facingRight = true;  
    int HereWeGo = 1; 
    int animationDelay = 5; 
    int animationCounter = 0; 

    // Механика движения
    int speedH = 5; 
    int gravity = 1;
    int speedV = 0; 
    final int Jump = -18;

    boolean FinalLoca;
    private int groundLevel = 310;

    // Конструктор по умолчанию (оставляем для других миров)
    public Hero() {
        setupImages();
    }

    // Конструктор с указанием уровня земли
    public Hero(int groundLevel) {
        this.groundLevel = groundLevel;
        setupImages();
    }

    private void setupImages() {
        for (int i = 0; i < 3; i++) {
            walkRight[i] = new GreenfootImage("spriteWalk" + i + ".png");
            walkLeft[i] = new GreenfootImage("spriteWalk" + i + ".png");
            walkLeft[i].mirrorHorizontally(); 
            walkRight[i].scale(75, 75);
            walkLeft[i].scale(75, 75);
        }

        StaticHero = new GreenfootImage("staticHero.png");
        StaticHero.scale(75, 75);
        StaticHeroHorizont = new GreenfootImage(StaticHero);
        StaticHeroHorizont.mirrorHorizontally();
        setImage(StaticHero);
    }

    public void act() {
        if (!FinalLoca){
            fall();
            checkLandLeft();
            checkLandRight();
            checkLandUnderLeft();
            checkLandUnderRight();
        }
        move();
        animate();
        checkPortal();  // Проверка касания портала для телепортации
    }

    public void move() {
        if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && getX() > getImage().getWidth() / 2 - 15) {
            setLocation(getX() - speedH, getY());
            HereWeGo = 0;
            facingRight = false;
        }
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && getX() < getWorld().getWidth() - getImage().getWidth() / 2) {
            setLocation(getX() + speedH, getY());
            HereWeGo = 2;
            facingRight = true;
        }
        else {
            HereWeGo = 1;
        }
        
        if (Greenfoot.isKeyDown("space") && check()) {
                speedV = Jump;
                Greenfoot.playSound("jumpSound.wav");
            } 
        
    }

    public void animate() {
        ++animationCounter;
        int frame = (animationCounter / animationDelay) % 3;

        if (HereWeGo == 2) { 
            setImage(walkRight[frame]);
        } else if (HereWeGo == 0) { 
            setImage(walkLeft[frame]);
        } else {
            if (facingRight)
                setImage(StaticHero);
            else
                setImage(StaticHeroHorizont);
        }
    }

    public boolean check() {
        return (isTouching(Platform.class) || getY() >= groundLevel) && speedV == 0;
    }

    public void checkLandLeft() {
        Actor platform = getOneObjectAtOffset(10, getImage().getHeight() / 2 + 5, Platform.class);
        if (platform != null && speedV >= 0) {
            int platformTop = platform.getY() - platform.getImage().getHeight() / 2;
            int heroHalfHeight = getImage().getHeight() / 2;
            setLocation(getX(), platformTop - heroHalfHeight); 
            speedV = 0; 
        } 
        else if (getY() >= groundLevel && speedV >= 0) {
            setLocation(getX(), groundLevel);
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
        else if (getY() >= groundLevel && speedV >= 0) {
            setLocation(getX(), groundLevel);
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
    
    public void checkPortal() {
        if (isTouching(Portal.class)) {
            getWorld().stopped();
            Greenfoot.setWorld(new FinalDisplay());
        }
    }
}
