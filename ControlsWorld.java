import greenfoot.*;

public class ControlsWorld extends MyWorld {
    
    public ControlsWorld() {
        GreenfootImage bg = new GreenfootImage("ControlsBackground.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        addObject(new Hero(), 100, 310);
        
        addObject(new BackButton(), 17, 15);
        backgroundMusic = new GreenfootSound("mainLocaMusic.wav");
        backgroundMusic.playLoop();
        addObject(new KeyIcon("w", "W.png", "W_L.png", 66, 66), 201, 145);
        addObject(new KeyIcon("a", "A.png", "A_L.png", 66, 66), 131, 215);
        addObject(new KeyIcon("s", "S.png", "S_L.png", 66, 66), 201, 215);
        addObject(new KeyIcon("d", "D.png", "D_L.png", 66, 66), 270, 215);
        addObject(new KeyIcon("space", "SPACE.png", "SPACE_L.png", 177, 97), 439, 179);
        
        setPaintOrder(Hero.class, KeyIcon.class);
    }
}
