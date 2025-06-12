import greenfoot.*;

public class TimerDisplay extends Actor 
{
    private int timeLeft;  
    private int tickCounter = 0;  
    private GreenfootImage image;

    public TimerDisplay(int seconds) 
    {
        timeLeft = seconds;
        updateImage();
    }

    public void act() 
    {
        tickCounter++;
        if (tickCounter >= 60) 
        {
            tickCounter = 0;
            timeLeft--;

            if (timeLeft < 0) 
            {
                timeLeft = 0;
                // Можно добавить действие по окончании таймера
            }

            updateImage();
        }
    }

    private void updateImage() 
    {
        image = new GreenfootImage(150, 30); 
        image.setColor(new Color(0, 0, 0, 0));  
        image.fill();

        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", 24));
        image.drawString("Time: " + timeLeft, 10, 24);  

        setImage(image);
    }
}
