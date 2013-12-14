

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

// notre fentre comprend une zone de dessin, un panneau de controle et une console qui affiche des messages
@SuppressWarnings("serial")
public class FenetreComposee extends JFrame{

	// acces statique a la lecture et l'ecriture de la console
	public static JTextArea TA;
	public static PanelControl ct;
	public static Dessin des;
	// ecouteur d evenements
	private Action act;
	
	public FenetreComposee(){
		
		super("Fenetre Composee");
		
		act=new Action(this);  // definition de l ecouteur d evenements
		
		TA=new JTextArea();  // initialisation de la console
		
		JScrollPane sp=new JScrollPane(TA,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // la zone de texte est affcihe dans un composant qui gere le defilement horizontal et vertical
		
		TA.setEditable(false);   // l utilisateur ne peut pas ecrire dans la console
		sp.setPreferredSize(new Dimension(400,200));   // creation de la barre de menu
		
		createMenu();  // zone de dessin
		
		des=new Dessin();   // panneau de controle
		
		//ct=new PanelControl(act);  
		 
		setLayout(new BorderLayout()); // gestion geographique des composants
		 
		add(des,BorderLayout.CENTER); // la zone de dessin au milieu 
	
		
		add(sp,BorderLayout.SOUTH);   // la console en bas
		
		pack();  // redimensionnement automatique de la fenetre au contenu
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		// affichage d un nouveau message dans la console : penser a garder les messages deja afficher
		// => setText initialise tout le texte de la zone de texte
		TA.setText(TA.getText()+"\nfenetre initialisee");
	}
	private void createMenu(){
		int toucheRaccourcis = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		JMenuBar mb=new JMenuBar();
		// un menu parametre
		JMenu m1=new JMenu("MapBuilder");
		// qui contient un element permettant de modifier la couleur du fond
		JMenuItem mi1=new JMenuItem("Couleur du fond ...");
		mi1.addActionListener(act);
		JMenuItem mi2 = new JMenuItem("Quitter",'Q');
		mi2.addActionListener(act);
		mi2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, toucheRaccourcis));	
		
		
		
		m1.add(mi1);
		m1.add(mi2);
		mb.add(m1);
		JMenu m2=new JMenu("Ajouter");
		JMenuItem mi10=new JMenuItem("Un rectangle vide");
		mi10.addActionListener(act);
		JMenuItem mi11=new JMenuItem("Un rectangle plein");
		mi11.addActionListener(act);
		JMenuItem mi12 = new JMenuItem("Une station");
		mi12.addActionListener(act);
		JMenuItem mi13 = new JMenuItem("Un trait");
		mi13.addActionListener(act);
		m2.add(mi10);
		m2.add(mi11);
		m2.add(mi12);
		m2.add(mi13);
		mb.add(m2);
		// definition de la barre de menus
		setJMenuBar(mb);
		TA.setText(TA.getText()+"\nmenu cree");
	}
	public static void main(String[] args) {
		new FenetreComposee();
	}
	public void memoriserAjoutRectangleVide(String nom) {
		des.prochaineCreation(Figure.RECTANGLE,false, nom);	
	}
	public void memoriserAjoutRectanglePlein(String nom) {
		des.prochaineCreation(Figure.RECTANGLE,true, nom);
	}
	public void memoriserAjoutEllipse(String nom) {
		des.prochaineCreation(Figure.ELIPSE,true, nom);
	}
	public void memoriserAjoutTrait(String nom) {
		des.prochaineCreation(Figure.TRAIT,true, nom);
	}
	
	

}
