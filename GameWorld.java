import greenfoot.*;

public class GameWorld extends MyWorld
{
    GreenfootImage PhoneImage;
    private Hero hero;
    private int cameraOffsetY = 100;
    
    public GameWorld() 
    {
        super(600, 3600, 1);
        PhoneImage = new GreenfootImage("allGamev2.png");
        setBackground(PhoneImage);
        
        hero = new Hero(false, 3510); 
        addObject(hero, getWidth() / 2 , 3510);
        
        generatePlatforms();
        
    
        Portal portal = new Portal();
        portal.setSize(100, 100);
        addObject(portal, 300, 240);
    }
    
   private void generatePlatforms() 
    {
        int startY = 3450;          // Стартовая высота
        int stepY = 125;            // Вертикальное расстояние между платформами
        int platformCount = 26;     // Кол-во платформ
        int fixedStepX = 120;        // горизонтальное смещение
        int minXBoundary = 60;
        int maxXBoundary = getWidth() - 60;

        int previousX = getWidth() / 2;  

        for (int i = 0; i < platformCount; i++) 
        {
            int y = startY - i * stepY;

            int direction = Greenfoot.getRandomNumber(2) == 0 ? -1 : 1;
            int nextX = previousX + direction * fixedStepX;

            // Проверяем, не выходит ли nextX за границы
            if (nextX < minXBoundary || nextX > maxXBoundary) 
            {
                // Если выходит — меняем направление на противоположное
                nextX = previousX - direction * fixedStepX;

                // Если и так выходит — оставляем previousX без изменения 
                if (nextX < minXBoundary || nextX > maxXBoundary) 
                {
                    nextX = previousX;
                }
            }
            addObject(new Platform(), nextX, y);
            previousX = nextX;
        }
    }


    public Hero getHero() 
    {
        return hero;
    }
}
