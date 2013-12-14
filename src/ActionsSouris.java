

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ActionsSouris implements MouseListener, MouseMotionListener, MouseWheelListener {
	private Dessin refDessin;
	private int mode;
	private static final int LIBRE=1;
	private static final int AJOUT=2;
	private Figure dessusFig = null;
	private int actual_sx;
	private int actual_sy;
	
	ActionsSouris(Dessin dessin){
		refDessin = dessin;
		mode=LIBRE;
	}
	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		int sx = me.getX();
		int sy = me.getY();
		if (dessusFig!=null){
			if (mode==LIBRE){
				int dx = sx-actual_sx;
				int dy = sy-actual_sy;
				dessusFig.deplacerDe(dx,dy);
				actual_sx = sx;
				actual_sy = sy;
				refDessin.repaint();
			}
			if (mode==AJOUT){
				dessusFig.modifier(sx, sy, actual_sx, actual_sy);
				//dessusFig.modifier(sx, sy, 2, 2);
				refDessin.repaint();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub
		int sx = me.getX();
		int sy = me.getY();
		if (mode==LIBRE){
			Figure fig = surQuoi(sx,sy);
			dessusFig = fig;
			String mess = "Rien";
			if (fig!=null) mess = "Au dessus de "+fig.getNom();	
			refDessin.repaint();
		}
	}

	private Figure surQuoi(int sx, int sy){
		for(Figure fig : refDessin.getFigures()){
			if (fig.dessus(sx,sy)){
				
				return fig;
				// refDessin.addText("Dessus "+fig.getNom());
			}
		}
		return null;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent me) {
		int sx = me.getX();
		int sy = me.getY();
		
		dessusFig=surQuoi(sx, sy);
		refDessin.addText("mousePressed"+(mode==AJOUT ? "AJOUT" : "LIBRE") );
		if (mode==AJOUT){
			//dessusFig= refDessin.realiserAjout(sx/2,sy/2);
			dessusFig= refDessin.realiserAjout(sx,sy);
			refDessin.repaint();
		}
		if (dessusFig!=null){
			actual_sx = sx;
			actual_sy = sy;
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dessusFig=null;
		mode=LIBRE;
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent me) {
		int sx = me.getX();
		int sy = me.getY();
		int augm = me.getWheelRotation();
		Figure fig = surQuoi(sx,sy);
		if (fig!=null) {
			fig.augmenterEpaisseur(augm);
			refDessin.repaint();
		}
	    refDessin.addText("augmentation: " + augm);
		
	}
	
	public void setModeAjout(){
		mode=AJOUT;
	}
	
	public Figure getAuDessusFigure(){
		return dessusFig;
	}

}
