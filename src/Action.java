

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Action implements ActionListener,ChangeListener {

	private FenetreComposee fen;
	// initialise l action listener avec la fenetre, utile pour acceder a repaint()
	public Action(FenetreComposee fen){
		this.fen=fen;
	}
	public void actionPerformed(ActionEvent e) {
		// recupere le nom du composant qui a declanche l action
		String item = e.getActionCommand();
		if(item.equals("Rectangle vide")){                
			Dessin.RECTANGLE_VIDE=!Dessin.RECTANGLE_VIDE;  // changement d etat sur l affichage des rectangles vides
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nactivation rectangle vide : "+Dessin.RECTANGLE_VIDE);
		}
		else if(item.equals("Rectangle plein")){
			Dessin.RECTANGLE_PLEIN=!Dessin.RECTANGLE_PLEIN;
		FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nactivation rectangle plein : "+Dessin.RECTANGLE_PLEIN);
	}

		else if(item.equals("Oval vide")){
			Dessin.OVAL_VIDE=!Dessin.OVAL_VIDE;
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nactivation oval vide : "+Dessin.OVAL_VIDE);
		}
		else if(item.equals("Oval plein")){
			Dessin.OVAL_PLEIN=!Dessin.OVAL_PLEIN;
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nactivation oval plein : "+Dessin.OVAL_PLEIN);
		}
		else if(e.getSource().getClass()==JTextField.class){
			
			Dessin.NB_FIGURE=Integer.parseInt(item);  // recupere le nombre de figure a tracer
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\n"+Dessin.NB_FIGURE+" figures");
		}
		else if(item.equals("Couleur du fond ...")){
			
			Dessin.FOND=JColorChooser.showDialog(fen,"Palette",Color.black);  // ouvre une palette de couleur et recupere la couleur choisie par l utilisateur
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\ncouleur de fond : "+Dessin.FOND);
		}
		else if(item.equals("Un rectangle vide")){
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nAjouter un Rectangle vide");
			String nom = JOptionPane.showInputDialog("Nom de station", " ");
			fen.memoriserAjoutRectangleVide(nom);
		}
		else if(item.equals("Un rectangle plein")){
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nAjouter un Rectangle plein");
			String nom = JOptionPane.showInputDialog("Nom de station", " ");
			fen.memoriserAjoutRectanglePlein(nom);
		}
		
		else if(item.equals("Une station")){
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nAjouter une station");
			String nom = JOptionPane.showInputDialog("Nom de station", " ");
			fen.memoriserAjoutEllipse(nom);
		}
		else if(item.equals("Un trait")){
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nAjouter un trait");
			String nom = JOptionPane.showInputDialog("Nom de station", " ");
			fen.memoriserAjoutTrait(nom);
		}
		else if(item.equals("Quitter")){
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nQuitter");
			System.exit(0);
		}
		
		
		
		
		fen.repaint();  // appeler repaint() de fenetre va appeler automatiquement repaint() de Dessin
	}
	public void stateChanged(ChangeEvent e) {
		// pas besoin de savoir quel composant a declenche cette action car c est forcement slider
		// par contre conversion d objet
		JSlider s=(JSlider) e.getSource();
		// pour pouvoir utiliser la methode getValue() definie sur slider
		Dessin.MAX_SIZE=s.getValue();
		FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nTaille max des figures : "+Dessin.MAX_SIZE);
		fen.repaint();
	}
}
