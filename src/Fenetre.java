

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*; 
import java.io.*;
import javax.imageio.*;
import javax.*;


//import Modele.*;
//import Ressources.Mode;
//import Ressources.Point;

//BufferedImage img;


public class Fenetre extends JFrame {

	JButton square = new JButton(new ImageIcon("images/carré.jpg")),
    circle = new JButton(new ImageIcon("images/rond.jpg")),
    red = new JButton(new ImageIcon("images/rouge.jpg")),
    green = new JButton(new ImageIcon("images/vert.jpg")),
    blue = new JButton(new ImageIcon("images/bleu.jpg"));
  
  //LES ÉCOUTEURS
  private FormeListener fListener = new FormeListener();
  private CouleurListener cListener = new CouleurListener();

  //Notre zone de dessin
  private DrawPanel drawPanel = new DrawPanel();
  
  
  
  public Fenetre(){
    
    this.setTitle("Map Builder");
	this.setSize(1000, 700);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   

    String n1 = JOptionPane.showInputDialog("Metro (Coord X)", "0");
    String n2 = JOptionPane.showInputDialog("Metro (Coord Y)", "0");
    int x = new Integer(n1);
    DrawPanel.setPosX(x);
    int y = new Integer(n2);
    DrawPanel.setPosY(y);
    
    this.getContentPane().add(drawPanel, BorderLayout.CENTER);
    this.setVisible(true); 

    
  }

  //Initialise la barre d'outils
  

  //ÉCOUTEUR POUR LE CHANGEMENT DE FORME
  class FormeListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      if(e.getSource().getClass().getName().equals("javax.swing.JMenuItem")){
       // if(e.getSource()==carre) drawPanel.setPointerType("SQUARE");
        //else drawPanel.setPointerType("CIRCLE");
      }
      else{
        if(e.getSource()==square)drawPanel.setPointerType("SQUARE");
        else drawPanel.setPointerType("CIRCLE");        
      }
    }    
  }

  //ÉCOUTEUR POUR LE CHANGEMENT DE COULEUR
  class CouleurListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      System.out.println(e.getSource().getClass().getName());
      if(e.getSource().getClass().getName().equals("javax.swing.JMenuItem")){
        System.out.println("OK !");
        //if(e.getSource()==vert)drawPanel.setPointerColor(Color.green);
        //else if(e.getSource()==bleu)drawPanel.setPointerColor(Color.blue);
       // else drawPanel.setPointerColor(Color.red);
      }
      else{
        if(e.getSource()==green)drawPanel.setPointerColor(Color.green);
        else if(e.getSource()==blue)drawPanel.setPointerColor(Color.blue);
        else drawPanel.setPointerColor(Color.red);        
      }
    }    
  }

  public static void main(String[] args){
    Fenetre fen = new Fenetre();
  }    
}