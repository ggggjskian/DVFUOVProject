import greenfoot.*; 

public class KeyIcon extends Actor {
    private String keyName;
    //не нажата
    private GreenfootImage defaultImage;
    // при нажатии
    private GreenfootImage pressedImage;
    private int pressedTimer = 0;
    private static final int PRESSED_DURATION = 30; 

    
    public KeyIcon(String keyName, String defaultImgPath, String pressedImgPath, int width, int height) {
        this.keyName = keyName;
        
        defaultImage = new GreenfootImage(defaultImgPath);
        defaultImage.scale(width, height);

        pressedImage = new GreenfootImage(pressedImgPath);
        pressedImage.scale(width, height);

        setImage(defaultImage);
    }

    public void act() {

        if (Greenfoot.isKeyDown(keyName) && pressedTimer == 0) {
            setImage(pressedImage);      
            pressedTimer = PRESSED_DURATION;  
        }

        if (pressedTimer > 0) {
            pressedTimer--;  
            if (pressedTimer == 0) 
            {
                setImage(defaultImage); 
            }
        }
    }
}
