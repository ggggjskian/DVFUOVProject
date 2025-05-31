import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
/**
 * Write a description of class InfoAboutGameButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfoAboutGameButton extends Button{
       
    private String pathTxtFile = "AboutGame.txt";
    
    InfoAboutGameButton(){
        super(100, 100,"decor.png");
    }
    
    public void act(){
        execute(this::readFile);
    }
    
    
    public void readFile(){
         
        try(FileReader reader = new FileReader(pathTxtFile)){
            int c;
            while((c=reader.read())!=-1){
                System.out.print((char)c);
            } 
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
    
    
    }
}
