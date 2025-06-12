import greenfoot.*;
import java.util.Random;
public class Boss extends Actor {
    GreenfootImage raise0 = new GreenfootImage("staticBoss.png");
    GreenfootImage raise1 = new GreenfootImage("handBoss.png");
    GreenfootImage raise2 = new GreenfootImage("muscleBoss.png");

    GreenfootImage pop0 = new GreenfootImage("bamBoss1.png");
    GreenfootImage pop1 = new GreenfootImage("bamBoss2.png");
    GreenfootImage pop2 = new GreenfootImage("bamBoss3.png");

    GreenfootImage[] raiseHand = new GreenfootImage[3];
    GreenfootImage[] popAnim = new GreenfootImage[3];
    Random random = new Random();
    int animationCounter = 0;
    int animationDelay = 10;
    int DelayMove = 20;
    int frameIndex = 0;
    int BossHealth = 3;
    int shootDelay = 60;
    int shotTimer = 0;
    int state = 0; // 0 - обычная анимация 1 - лопание

    public Boss() {
        // Масштабирование
        raise0.scale(190, 190);
        raise1.scale(190, 190);
        raise2.scale(190, 190);
        raiseHand[0] = raise0;
        raiseHand[1] = raise1;
        raiseHand[2] = raise2;

        pop0.scale(140, 140);
        pop1.scale(140, 140);
        pop2.scale(140, 140);
        popAnim[0] = pop0;
        popAnim[1] = pop1;
        popAnim[2] = pop2;


        setImage(raiseHand[0]);
    }

    public void act() {
     
        animateRaise();
        checkHeroCollision();
        checkDefeatBoss();  
        //moveBoss(10);
        shoot();
    
    }

    public void animateRaise() {
        animationCounter++;
        if (animationCounter % (animationDelay * 3) == 0) {
            frameIndex = 0;} 
        else if (animationCounter % animationDelay == 0) {
            frameIndex = (frameIndex + 1) % 3;
            setImage(raiseHand[frameIndex]);
        }
    }

    public void pushHero(){
        Hero hero = getWorld().getObjects(Hero.class).get(0);
        hero.setLocation(hero.getX() - 50, hero.getY() - 50);
    
    }
    
    public void checkHeroCollision() {
        Hero hero = (Hero)getOneIntersectingObject(Hero.class);
        if (hero != null) {
            int heroBottom = hero.getY() + hero.getImage().getHeight() / 2;
            int bossTop = getY() - getImage().getHeight() / 2;
            if (heroBottom < bossTop + 10) {
                getHurtBoss();
                pushHero();
                frameIndex = 0;
            } 
            else {
                // Герой сбоку/снизу — проигрыш
                getWorld().addObject(new ResultImage("lose.png"), getWorld().getWidth()/2, getWorld().getHeight()/2);
                Greenfoot.delay(100); 
                restartWithCamera();
            }
        }
    }

    
    public void animatePop() {
        if (animationCounter % animationDelay == 0 && frameIndex < 3) {
            setImage(popAnim[frameIndex]);
            frameIndex++;
        }
        animationCounter++;
        if (frameIndex >= 3) {
            getWorld().addObject(new ResultImage("win.png"), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.delay(130); 
            Greenfoot.setWorld(new Menu()); 
        }
        
    }
    
    private void restartWithCamera() {
        GameWorld newWorld = new GameWorld();
        Hero newHero = newWorld.getHero();
        Camera camera = new Camera(newWorld, newHero);
        Greenfoot.setWorld(camera);
    }
    
    
    private void walk(int offset){
        
        int currentX = getX();
        int currentY = getY();
        int direction = random.nextInt(4);
        switch(direction){
            case 0:
                if (currentX + offset < getWorld().getWidth() - getImage().getWidth()/2){
                    setLocation(getX() + offset, getY());
                }
                break;
            case 1:
                 if (currentX - offset > getImage().getWidth()/2){
                    setLocation(getX() - offset, getY());
                }
                break;
            case 2:
                if (currentY - offset > getImage().getHeight()/2){
                    setLocation(getX(), getY() - offset);
                }
                break;
            case 3:
                 if (currentY + offset < getWorld().getHeight() -getImage().getHeight()/2){
                    setLocation(getX(), getY() + offset);
                }
                break;
        }
    }
    
    private void moveBoss(int offset){
        DelayMove--;
        if (DelayMove == 0){
            walk(offset);
            DelayMove = 20;
        }
    
    }
    

    private void checkDefeatBoss(){
        if (this.BossHealth == 0){
            animatePop();
            Greenfoot.stop();
        }
    }
    
    private void getHurtBoss(){
        this.BossHealth -= 1;
    }
    
    public void shoot() 
    {
        shotTimer--;
        if (shotTimer <= 0) 
        {

            int[] bulletHeights = {290, 170};
            int selectedIndex = Greenfoot.getRandomNumber(2);
            int selectedY = bulletHeights[selectedIndex];

            int angleToShoot = 180; 

            int spacing = 80;

            for (int i = -1; i <= 1; i++) 
            {
            int offsetX = i * spacing;
            BosBullet bullet = new BosBullet(angleToShoot);
            getWorld().addObject(bullet, getX() + offsetX, selectedY);
            }

            shotTimer = shootDelay;
        }
    }
}

