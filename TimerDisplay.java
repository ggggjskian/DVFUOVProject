import greenfoot.*;

public class TimerDisplay extends Actor {
    private int timeLeft;
    private int tickCounter = 0;
    private GreenfootImage image;
    private int phaseCount = 1;
    private int maxPhases = 4; 

    public TimerDisplay(int seconds) {
        timeLeft = seconds;
        updateImage();
    }

    public void act() {
        
        if (phaseCount >= maxPhases) {
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
            return;
        }

        tickCounter++;
        if (tickCounter >= 60) {
            tickCounter = 0;
            timeLeft--;

            if (timeLeft < 0) {
                timeLeft = 20;  
                phaseCount++;  
            }
            updateImage();
        }
    }

    private void updateImage() {
        image = new GreenfootImage(150, 30);
        image.setColor(new Color(0, 0, 0, 0));
        image.fill();

        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", 24));
        image.drawString("Time: " + timeLeft, 10, 24);
        setImage(image);
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getPhase() {
        if (phaseCount >= maxPhases) {
            return maxPhases;
        }
        return phaseCount;
    }
}
