import greenfoot.*;

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

    int animationCounter = 0;
    int animationDelay = 10;
    int frameIndex = 0;
    int state = 0; // 0 - обычная анимация 1 - лопание

    public Boss() 
    {
        // Масштабирование
        raise0.scale(170, 170);
        raise1.scale(170, 170);
        raise2.scale(170, 170);
        raiseHand[0] = raise0;
        raiseHand[1] = raise1;
        raiseHand[2] = raise2;

        pop0.scale(190, 190);
        pop1.scale(190, 190);
        pop2.scale(190, 190);
        popAnim[0] = pop0;
        popAnim[1] = pop1;
        popAnim[2] = pop2;

        setImage(raiseHand[0]);
    }

    public void act() 
    {
        if (state == 1) 
        {
            animatePop();
        } else 
        {
            animateRaise();
            checkHeroCollision();
        }
    }

    public void animateRaise() 
    {
        animationCounter++;
        if (animationCounter % (animationDelay * 3) == 0) 
        {
            frameIndex = 0;
        } else if (animationCounter % animationDelay == 0) 
        {
            frameIndex = (frameIndex + 1) % 3;
            setImage(raiseHand[frameIndex]);
        }
    }

    
        public void checkHeroCollision() 
    {
        Hero hero = (Hero)getOneIntersectingObject(Hero.class);
        if (hero != null) 
        {
        int heroBottom = hero.getY() + hero.getImage().getHeight() / 2;
        int bossTop = getY() - getImage().getHeight() / 2;
        if (heroBottom < bossTop + 10) 
        {
            // Герой сверху —> лопаем босса
            state = 1;
            animationCounter = 0;
            frameIndex = 0;
        } 
        else 
        {
            // Герой сбоку/снизу — проигрыш
            getWorld().addObject(new ResultImage("lose.png"), getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.delay(100); 
            Greenfoot.setWorld(new GameWorld());
        }
        }
    }

    
    public void animatePop() 
    {
        if (animationCounter % animationDelay == 0 && frameIndex < 3) 
        {
        setImage(popAnim[frameIndex]);
        frameIndex++;
        }
        animationCounter++;
        if (frameIndex >= 3) 
        {
        getWorld().addObject(new ResultImage("win.png"), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        Greenfoot.delay(130); 
        Greenfoot.setWorld(new Menu()); 
        }
    }

}
