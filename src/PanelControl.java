

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelControl extends JPanel {
	private JLabel info1;
	public PanelControl(Action act){
		// panneau de controle de la zone de dessin
		// creation d une bordure
		setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.black));
		// ajout des composants les uns sous les autres
		setLayout(new GridLayout(0,1));
		JLabel lab=new JLabel("Nombres de figures : ");
		// zone de texte editable
		JTextField tf=new JTextField(""+Dessin.NB_FIGURE);
		tf.addActionListener(act);
		JLabel lab2=new JLabel("Taille : ");
		JSlider s1=new JSlider();
		// fixe la valeur max et initiale du slider avec la dimension de la zone de dessin
		s1.setMaximum(Dessin.MAX_SIZE);
		s1.setValue(Dessin.MAX_SIZE);
		// ajout de l ecouteur d evenement changeListener
		// c est le changement de valeur du slider qui declanche un evenement
		// pas d actionListener sur slider
		s1.addChangeListener(act);
		JButton b1=new JButton("Dessiner");
		b1.addActionListener(act);
		JCheckBox cb1=new JCheckBox("Rectangle vide");
		cb1.setSelected(Dessin.RECTANGLE_VIDE);
		cb1.addActionListener(act);
		JCheckBox cb2=new JCheckBox("Rectangle plein");
		cb2.setSelected(Dessin.RECTANGLE_PLEIN);
		cb2.addActionListener(act);
		JCheckBox cb3=new JCheckBox("Oval vide");
		cb3.setSelected(Dessin.OVAL_VIDE);
		cb3.addActionListener(act);
		JCheckBox cb4=new JCheckBox("Oval plein");
		cb4.setSelected(Dessin.OVAL_PLEIN);
		cb4.addActionListener(act);
		// ajout d un panneau pour le choix du nombre de figure
		// l etiquette et la zone de texte editable sont cote a cote
		JPanel pan=new JPanel();
		pan.add(lab);
		pan.add(tf);
		add(pan);
		// "taille" et slider cote a cote dans un panneau, puis ajout du panneau
		JPanel pan2=new JPanel();
		pan2.add(lab2);
		pan2.add(s1);
		// ajout de tous les elements les uns sous les autres
		add(pan2);
		add(cb1);
		add(cb2);
		add(cb3);
		add(cb4);
		add(b1);
		info1 = new JLabel("Rien");
		add(info1);
		FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nPanneau controle initialise");
	}
	public void changeInfo1(String mess){
		info1.setText(mess);
	}
}
