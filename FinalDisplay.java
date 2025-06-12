import greenfoot.*; 

public class FinalDisplay extends MyWorld {
    
    int deleteImagetime = 180;
    GreenfootImage PhoneImage;
    private GreenfootSound backgroundMusic;
    public FinalDisplay() {
        PhoneImage = new GreenfootImage("finalLoc.png");
        PhoneImage.scale(getWidth(), getHeight());
        setBackground(PhoneImage);
        addObject(new Hero(), 100 ,310);
        
        addObject(new BackButton(), 17, 15);
        
        
        addObject(new Platform(), 240, 240);
        showText("По окончанию таймера нужно прыгать на босса", 240, 22);
        TimerDisplay timer = new TimerDisplay(20);
        addObject(timer, 560, 20);
        backgroundMusic = new GreenfootSound("musicBoss.wav");
        backgroundMusic.playLoop();
        Boss boss = new Boss(timer); 
        addObject(boss, 440, 240);
    }
    
    public void act(){
         deletetTimer();
    }
    
    public void deletetTimer(){
        deleteImagetime--;
        if (deleteImagetime <= 0){
            showText(" ", 240, 22);
        }
    
    }
    
    public void stopped() {
        backgroundMusic.stop(); 
    }
    
  
}
