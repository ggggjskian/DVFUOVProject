import greenfoot.*;

public class GameWorld extends MyWorld {
    GreenfootImage PhoneImage;
    private Hero hero;
    private int projectileTimer = 0;
    private int spawnInterval = 70;
    private GreenfootSound backgroundMusic;
    public GameWorld() {
        super(600, 3600, 1);
        PhoneImage = new GreenfootImage("allGamev2.png");
        setBackground(PhoneImage);
        
        hero = new Hero(3510); 
        addObject(hero, getWidth() / 2 , 3510);
        backgroundMusic = new GreenfootSound("mainLocaMusic.wav");
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.playLoop();
        }
        generatePlatforms();
        generatePlatforms();
        Portal portal = new Portal();
        portal.setSize(100, 100);
        addObject(portal, 300, 240);
    }

    public void act() {
        projectileTimer++;
        if (projectileTimer >= spawnInterval) 
        {
            spawnProjectile();
            projectileTimer = 0;
        }

    }

    private void spawnProjectile() {   
        Projectile p = new Projectile();
        int x = Greenfoot.getRandomNumber(getWidth() - p.getImage().getWidth()/2) + p.getImage().getWidth()/2 ;
        addObject(p, x, 0);
    }

    private void generatePlatforms() {
        int startY = 3450;          // Стартовая высота
        int stepY = 123;            // Вертикальное расстояние между платформами
        int platformCount = 26;     // Кол-во платформ
        int fixedStepX = 150;        // горизонтальное смещение
        int minXBoundary = 60;
        int maxXBoundary = getWidth() - 60;

        int previousX = getWidth() / 2;  

        for (int i = 0; i < platformCount; i++) {
            int y = startY - i * stepY;

            int direction = Greenfoot.getRandomNumber(2) == 0 ? -1 : 1;
            int nextX = previousX + direction * fixedStepX;

            // Проверяем, не выходит ли nextX за границы
            if (nextX < minXBoundary || nextX > maxXBoundary) {
                // Если выходит — меняем направление на противоположное
                nextX = previousX - direction * fixedStepX;

                // Если и так выходит — оставляем previousX без изменения 
                if (nextX < minXBoundary || nextX > maxXBoundary) {
                    nextX = previousX;
                }
            }
            addObject(new Platform(), nextX, y);
            previousX = nextX;
        }
    }

    public Hero getHero() {
        return hero;
    }
    
    public void stopped() {
        backgroundMusic.stop(); 
    }
}
