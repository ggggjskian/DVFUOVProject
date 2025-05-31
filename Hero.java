import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor{
    
    
    /* Для анимашки **/
    GreenfootImage StaticHero;
    GreenfootImage StaticHeroHorizont;
    GreenfootImage[] walkRight = new GreenfootImage[3];
    GreenfootImage[] walkLeft = new GreenfootImage[3];
    boolean facingRight = true;  /* True вправо/left влево  **/
    int HereWeGo = 1;
    int animationDelay = 5;
    int animationCounter = 0;
    
    /* Для игровой механики платоформера **/
    int speedH = 5;
    int gravity = 1;
    int speedV = 0;
    final int Jump = -15;

    
    public Hero (){
        
        for(int i = 0; i < 3; i++) {
            walkRight[i] = new GreenfootImage("spriteWalk" + i + ".png");
            walkLeft[i] = new GreenfootImage("spriteWalk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
            walkRight[i].scale(75,75);
            walkLeft[i].scale(75,75);
        }
        
        StaticHero = new GreenfootImage("staticHero.png");
        StaticHero.scale(75,75);
        StaticHeroHorizont = new GreenfootImage(StaticHero);
        StaticHeroHorizont.mirrorHorizontally();
        setImage(StaticHero);
    
    }
    
    public void act(){
        moveV();
        animate();
        fall();
        checkLand();
    }
    
    public void moveV(){
        
        if (Greenfoot.isKeyDown("space") && check()) {
            speedV = Jump;}
            
        if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && getX() > getImage().getWidth()/ 2 - 15){
            setLocation(getX() - speedH, getY());
            HereWeGo = 0;
            facingRight = false;
        }else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && (getX() < getWorld().getWidth() - getImage().getWidth()/2)){
            setLocation(getX() + speedH, getY());
            HereWeGo = 2;
            facingRight = true;
        }else{
            HereWeGo = 1;
        }
    } 
    
    public void animate(){
        ++animationCounter;
        int frame = (animationCounter / animationDelay) % 3;
        if (HereWeGo == 2){
            setImage(walkRight[frame]);
        }else if(HereWeGo == 0){
            setImage(walkLeft[frame]);
        }else{
            if(facingRight)setImage(StaticHero);
            else setImage(StaticHeroHorizont);
            
        }
    
    }
    public boolean check(){
        if (speedV == 0)return true;
        return false;
    }
    public void checkLand() {
        if (getY() >= 310  && speedV > 0) {
            setLocation(getX(), 310);
            speedV = 0;
        }
    }
    
    public void fall(){
            speedV += gravity;
            setLocation(getX(), getY() + speedV);
    }
}

