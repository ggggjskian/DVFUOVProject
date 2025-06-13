import greenfoot.*;
import java.util.Random;

public class Boss extends Actor 
{
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
    int frameIndex = 0;
    int DelayMove = 20;
    
    int shootDelay = 60;
    int shotTimer = shootDelay;

    int state = 0;

    TimerDisplay timerRef;

    public Boss(TimerDisplay timer) {
        this.timerRef = timer;

        raise0.scale(190, 190);
        raise1.scale(190, 190);
        raise2.scale(190, 190);
        raiseHand[0] = raise0;
        raiseHand[1] = raise1;
        raiseHand[2] = raise2;

        pop0.scale(220, 220);
        pop1.scale(220, 220);
        pop2.scale(220, 220);
        popAnim[0] = pop0;
        popAnim[1] = pop1;
        popAnim[2] = pop2;

        setImage(raiseHand[0]);
    }

    public void act()  {
        if (state == 0) {
            animateRaise();
            checkHeroCollision();
            updatePhaseAndShoot();
            moveBoss(5);
        } else if (state == 1) {
            animatePop();
        }
    }

    public void animateRaise() {
        animationCounter++;
        if (animationCounter % (animationDelay * 3) == 0) {
            frameIndex = 0;
            setImage(raiseHand[frameIndex]);
        } else if (animationCounter % animationDelay == 0) {
            frameIndex = (frameIndex + 1) % 3;
            setImage(raiseHand[frameIndex]);
        }
    }

    public void checkHeroCollision() {
        Hero hero = (Hero)getOneIntersectingObject(Hero.class);
        if (hero != null) {
            int heroBottom = hero.getY() + hero.getImage().getHeight() / 2;
            int bossTop = getY() - getImage().getHeight() / 2;
            int currentPhase = timerRef.getPhase();

            if (currentPhase == 4 && heroBottom < bossTop + 10) {
                state = 1;
                animationCounter = 0;
                frameIndex = 0;} 
            else {
                getWorld().addObject(new ResultImage("lose.png"), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                Greenfoot.delay(100);
                restartWithCamera();
            }
        }
    }

    public void animatePop() {
        animationCounter++;
        if (frameIndex < popAnim.length && animationCounter >= animationDelay) {
            setImage(popAnim[frameIndex]);
            frameIndex++;
            animationCounter = 0; 
        }

        if (frameIndex >= popAnim.length) {
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

    private void walk(int offset) {
        int currentX = getX();
        int currentY = getY();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                if (currentX + offset < getWorld().getWidth() - getImage().getWidth() / 2) {
                    setLocation(currentX + offset, currentY);
                }
                break;
            case 1:
                if (currentX - offset > getImage().getWidth() / 2) {
                    setLocation(currentX - offset, currentY);
                }
                break;
            case 2:
                if (currentY - offset > getImage().getHeight() / 2) {
                    setLocation(currentX, currentY - offset);
                }
                break;
            case 3:
                if (currentY + offset < getWorld().getHeight() - getImage().getHeight() / 2) {
                    setLocation(currentX, currentY + offset);
                }
                break;
        }
    }

    private void moveBoss(int offset) {
        DelayMove--;
        if (DelayMove <= 0) {
            walk(offset);
            DelayMove = 20;
        }
    }

    private void updatePhaseAndShoot() {
        int currentPhase = timerRef.getPhase();

        if (currentPhase >= 4) return; 
        
        shotTimer--;
        if (shotTimer <= 0) {
            int[] bulletHeights = {290, 170};
            int selectedY = bulletHeights[Greenfoot.getRandomNumber(2)];
            int angleToShoot = 180;
            int spacing = 80;

            if (currentPhase == 1) {
                getWorld().addObject(new BosBullet(angleToShoot), getX(), selectedY);
            } else if (currentPhase == 2) {
                getWorld().addObject(new BosBullet(angleToShoot), getX() - spacing / 2, selectedY);
                getWorld().addObject(new BosBullet(angleToShoot), getX() + spacing / 2, selectedY);
            } else if (currentPhase == 3) {
                for (int i = -1; i <= 1; i++) {
                    int offsetX = i * spacing;
                    getWorld().addObject(new BosBullet(angleToShoot), getX() + offsetX, selectedY);
                }
            }
            shotTimer = shootDelay;
        }
    }
}
