import greenfoot.*;  

public class MyWorld extends World
{
    public GreenfootSound backgroundMusic = null;
    public MyWorld() {
        this(600, 400, 1);
    }

    
    public MyWorld(int width, int height, int cellSize) {
        super(width, height, cellSize);
    }
    
    
    public void stopped() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        } 
    }
}
