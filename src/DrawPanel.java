import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*; 

import javax.swing.*;

import java.io.*;

import javax.imageio.*;

public class DrawPanel extends JPanel{

  //Couleur du pointeur
  private Color pointerColor = Color.red;
  //Forme du pointeur
  private String pointerType = "CIRCLE";
  //Position X du pointeur
  static private int posX = 0;
  //Position Y du pointeur
  static private int posY = 0;
  //Pour savoir si on doit dessiner ou non
  private boolean erasing = true;
  //Taille du pointeur
  private int pointerSize = 20;
  
  private int largeur;
  //Collection de points ! 
  private ArrayList<Point> points = new ArrayList<Point>();  

  public DrawPanel(){
	  
	  
	Ligne l = new Ligne(); //-> lit tableau et charge valeur dans  liste
	
	points.add(new Point(l.getStation(0, 1), l.getStation(0, 2), pointerSize, pointerColor, pointerType));
   
	this.addMouseListener(new MouseAdapter(){
     
    	public void mousePressed(MouseEvent e){
        //points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
        repaint();
      }
    });

    this.addMouseMotionListener(new MouseMotionListener(){
      
    	public void mouseDragged(MouseEvent e) {
        //points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
        repaint();
      }

      public void mouseMoved(MouseEvent e) {}
    });

  }

  public void paintComponent(Graphics g) {
	  	
	
	  
	 
	  this.setFondColor(g);
	  this.setLargeur(2);

	 
	 points.add(new Point(getPosX(), getPosY(), pointerSize, pointerColor, pointerType));

   for(Point p : this.points)
      {
        g.setColor(p.getColor());

        if(p.getType().equals("SQUARE")){
          g.fillRect(p.getX(), p.getY(), p.getSize(), p.getSize());
          
        }
        else{
          g.setColor(Color.black);
          g.fillOval (p.getX()-getLargeur()/2, p.getY()-getLargeur()/2, getEpaisseur(p), getEpaisseur(p)); 
          g.setColor(pointerColor); 
          g.fillOval(p.getX(), p.getY(), p.getSize(), p.getSize());
        }
      }
    }        

  static public void setPosX(int x){
	    posX = x;
	  }
  
  static public void setPosY(int y){
	    posY = y;
	  }
  
  static public int getPosX(){
	    return posX;
	  }

  static public int getPosY(){
	    return posY;
	  }
  
  
  
  
  
  public void setPointerColor(Color c){
    this.pointerColor = c;
  }

  public void setPointerType(String str){
    this.pointerType = str;
  }      
  
  public void setLargeur(int value){
	    this.largeur = value;
  }  
  
  public int getLargeur(){
	    return this.largeur;
 } 

public int getEpaisseur(Point p){
	 int epaisseur = p.getSize() + largeur;
	 return epaisseur;
} 


public void setFondColor(Graphics g){
    g.setColor(Color.white);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
  }      
}

