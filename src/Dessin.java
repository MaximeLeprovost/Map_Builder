

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Dessin extends JPanel {
	
	// plusieurs variables statiques pour faciliter la gestion lecture ecriture
	// comme il y a une seule zone de dessin dans l application : pas de probleme
	// il faut juste verifier de bien creer Dessin avant d'appeler les variables statiques
	public static int NB_FIGURE=10;
	public static int MAX_SIZE=600;// taille de la zone de dessin et des figures (maximum)
	public static boolean RECTANGLE_VIDE=true;// indicateur dessiner rectangle vide ?
	public static boolean RECTANGLE_PLEIN=true;// indicateur dessiner rectangle plein ?
	public static boolean OVAL_VIDE=true;// indicateur dessiner oval vide ?
	public static boolean OVAL_PLEIN=true;// indicateur dessiner oval plein ?
	public static Color FOND=Color.black;// couleur de l arriere plan de la zone de dessin
	public static Color TEXT=Color.white;//
	private Vector<Figure> liste_figure = new Vector<Figure>();
	public int prochaineCreationFigureType;
	public boolean prochaineCreationFigureRemplir;
	public String prochaineCreationFigurename ;
	private ActionsSouris  actionSouris;
	public Dessin(){
		// setPreferredSize indique au conteneur qui contient ce composant la taille par défaut
		// => setSize ne permet pas de definir une taille par defaut
		setPreferredSize(new Dimension(MAX_SIZE,MAX_SIZE));
		FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nTablette dessin initialisee");
		
		Figure f1 = new Figure(Figure.RECTANGLE,200,100,100,50,true);
		f1.setNom("Ici");
		f1.setEpaisseur(4);
		liste_figure.add(f1);
		
		Figure f2 = new Figure(Figure.RECTANGLE,150,300,200,50,false);
		f2.setNom("Autre");
		f2.setEpaisseur(6);
		f2.setCouleur2(Color.GREEN);
		liste_figure.add(f2);
		
		Figure f3 = new Figure(Figure.ELIPSE,350,400,50,50,true);
		f3.setNom("Jean Vilar");
		f3.setCouleur2(Color.RED);
		f3.setEpaisseur(10);
		liste_figure.add(f3);
		
		Figure f4 = new Figure(Figure.RECTANGLE,300,200,50,50,true);
		f4.setNom("Jean Vilar");
		f4.setCouleur2(Color.RED);
		f4.setEpaisseur(20);
		liste_figure.add(f4);
		
		Figure f5 = new Figure(Figure.TRAIT,30,10,50,50,true);
		f5.setNom("trait");
		f5.setCouleur2(Color.BLACK);
		f5.setEpaisseur(5);
		liste_figure.add(f5);
		
		actionSouris = new ActionsSouris(this);
		addMouseListener(actionSouris);
		addMouseMotionListener(actionSouris);
		addMouseWheelListener(actionSouris);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(FOND);
		g.fillRect(0,0,getWidth(),getHeight());
		
		for(int i=0;i<liste_figure.size();i++){
			dessineFigure(g, liste_figure.get(i));
		}
		FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\nnouveau dessin Fait");
	}
	//
	public void dessineFigure(Graphics g, Figure fig){
		// choix aleatoire de la couleur
		Graphics2D g2D=(Graphics2D)g;
		g2D.setColor(TEXT);
		Figure figDessus = actionSouris.getAuDessusFigure();
		if (figDessus!=null){
			addText("drawString");
			g2D.drawString(figDessus.getNom(), figDessus.getX1()-10, figDessus.getY1()-10);		
		}
		 RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        rh.put(RenderingHints.KEY_RENDERING,
	               RenderingHints.VALUE_RENDER_QUALITY);

	    g2D.setRenderingHints(rh);
		Shape shape = null; 
		switch(fig.getType()){
		case Figure.RECTANGLE:
			BasicStroke bs1 = new BasicStroke(fig.getEpaisseur(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		    g2D.setStroke(bs1);
			Rectangle2D rec2D = new Rectangle2D.Float(fig.getX1(), fig.getY1(), fig.getX2(), fig.getY2());
		
			shape = rec2D;
			
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\n dessiner un rectangle");
			break;
		
		case Figure.ELIPSE:
			BasicStroke bs2 = new BasicStroke(fig.getEpaisseur(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		    g2D.setStroke(bs2);
			Ellipse2D eli2D = new Ellipse2D.Float(fig.getX1(), fig.getY1(), fig.getX2(), fig.getY2());
		
			shape = eli2D;
		
		
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\n dessiner une elipse");
			break;
			
		case Figure.TRAIT:
			BasicStroke bs3 = new BasicStroke(fig.getEpaisseur(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		    g2D.setStroke(bs3);
			Line2D trai2D = new Line2D.Float(fig.getX1(), fig.getY1(), fig.getX2(), fig.getY2());
		
			shape = trai2D;
			FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\n dessiner un trait");
			break;
	}
	
		if (fig.isPlein()) {
			g2D.setColor(fig.getCouleur3());
			boolean pas_tracer = (fig.getType()==Figure.RECTANGLE) && !RECTANGLE_PLEIN;
			pas_tracer = pas_tracer || (fig.getType()==Figure.ELIPSE) && !OVAL_PLEIN; 
			if (!pas_tracer) g2D.fill(shape);
		}
		boolean pas_tracer = (fig.getType()==Figure.RECTANGLE) && !RECTANGLE_VIDE;
		pas_tracer = pas_tracer || (fig.getType()==Figure.ELIPSE) && !OVAL_VIDE; 
		g2D.setColor(fig.getCouleur2());
		if (!pas_tracer) g2D.draw(shape);
	}
	//
	int nombreAleatoire(int max){
		return (int)Math.floor(Math.random()*max);
	}
	//
	public  Vector<Figure> getFigures(){
		return liste_figure;
	}
	
	public void addText(String text){
		FenetreComposee.TA.setText(FenetreComposee.TA.getText()+"\n "+text);
		
	}
	public void prochaineCreation(int type, boolean remplir, String nom) {
		prochaineCreationFigurename = nom;
		prochaineCreationFigureType=type;
		prochaineCreationFigureRemplir=remplir;
		actionSouris.setModeAjout();
		
	}
	public Figure realiserAjout(int sx, int sy) {
		addText(">> RealiserAjout <<<");
		Figure fig = new Figure(prochaineCreationFigureType,sx,sy,5,5,prochaineCreationFigureRemplir);
		fig.setEpaisseur(3);
		fig.setNom(prochaineCreationFigurename);
		fig.setCouleur2(Color.RED);
		liste_figure.add(fig);
		this.addText("done");
		return fig;
	}
	
}
