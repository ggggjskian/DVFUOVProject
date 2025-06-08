import greenfoot.*;

public class Camera extends World {
    private int x, y, n;
    private double s;
    private World w;
    private Actor[] h;


    public Camera(World world, Actor host) {
        super(600, 400, world.getCellSize(), false);
        x = 600;
        y = 400;
        n = 1;
        w = world;
        h = new Actor[1];
        h[0] = host;
        s = 1;

        setGhosts();
        setBackground();
        addObject(new BackButton(), 17, 15);
    }


    public void act(){
        actWorld();
        setGhosts();
        setBackground();
    }

    private void setGhosts(){
        removeObjects(getObjects(Ghost.class));
        addObject(new Ghost(h[0], s), h[0].getX(), y/2);
        for(Object o : w.getObjects(null)){
            if(o!=h[0]){
                addObject(new Ghost((Actor)o, s), ((Actor)o).getX(), ((Actor)o).getY()-h[0].getY()+y/2);
            }
        }
    }
    


    private void setBackground(){
        setBackground(w.getBackground());
        GreenfootImage temp = new GreenfootImage(getBackground().getWidth(), getBackground().getWidth());
        temp.setColor(greenfoot.Color.DARK_GRAY);
        temp.fill();
        temp.drawImage(w.getBackground(), 0, y/2-h[0].getY());
        setBackground(temp);
    }

    private void actWorld(){
        for(Object o : w.getObjects(null)){
            ((Actor)o).act();
        }
        w.act();
    }

    private class Ghost extends Actor {

        public Ghost(Actor h){
            setImage(h.getImage());
            setRotation(h.getRotation());
        }

        public Ghost(Actor h, double scale){
            GreenfootImage temp = h.getImage();
            temp.scale((int)((double)temp.getWidth()*scale), (int)((double)temp.getHeight()*scale));
            setImage(temp);
            setRotation(h.getRotation());
        }

        public Ghost(GreenfootImage i){
            setImage(i);
        }
    }
}
