

import java.awt.Color;

public class Figure {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int type;
	private boolean plein;
	private Color couleur1;
	private Color couleur2;
	private Color couleur3;
	private int epaisseur;
	private String nom;
	
	public static final int RECTANGLE = 1;
	public static final int ELIPSE = 2;
	public static final int TRAIT = 3;
	public Figure(int type, int x1, int y1, int x2, int y2, boolean plein){
		this.type=type;
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.plein=plein;
		epaisseur =1;
		couleur1 = new Color(0,0,0);
		couleur2 = new Color(255,255,255);
		couleur3 = new Color(155,155,155);
		nom="";
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isPlein() {
		return plein;
	}
	public void setPlein(boolean plein) {
		this.plein = plein;
	}
	public Color getCouleur1() {
		return couleur1;
	}
	public void setCouleur1(Color couleur1) {
		this.couleur1 = couleur1;
	}
	public Color getCouleur2() {
		return couleur2;
	}
	public void setCouleur2(Color couleur2) {
		this.couleur2 = couleur2;
	}
	public Color getCouleur3() {
		return couleur3;
	}
	public void setCouleur3(Color couleur3) {
		this.couleur3 = couleur3;
	}
	public int getEpaisseur() {
		return epaisseur;
	}
	public void setEpaisseur(int epaisseur) {
		this.epaisseur = epaisseur;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	public String getNom(){
		return nom;
	}
	
	public boolean dessus(int x, int y){
		boolean res = false;
		if (type==RECTANGLE){
			int e =(epaisseur+1)/2;
			res = dansRectangle(x1-e,y1-e,x1+x2+e,y1+y2+e, x,y);
		}

		if (type==ELIPSE){
			int e =(epaisseur+1)/2;
			res = dansEllipse(x1-e,y1-e,x1+x2+e,y1+y2+e, x,y);
			//res = dansEllipse(x1-e,y1-e,x1+x2+e,y1+y2+e, x,y);
			
		}
		if (type==TRAIT){
			int e =(epaisseur+1)/2;
			res = dansRectangle(x1-e,y1-e,x1+x2+e,y1+e, x,y);
			res = res || dansRectangle(x1-e,y1-e,x1+e,y1+y2+e, x,y);
			res = res || dansRectangle(x1-e,y1+y2-e,x1+x2+e,y1+y2+e, x,y);
			res = res || dansRectangle(x1+x2-e,y1-e,x1+x2+e,y1+y2+e, x,y);
		}
		
		
		return res;
	}
	
	private boolean dansRectangle(int x1, int y1, int x2, int y2, int x, int y){
		if ((x1<=x && x<=x2) && (y1<=y && y<=y2)) return true;
		return false;
	}
	private boolean dansEllipse(int x1, int y1, int x2, int y2, int x, int y){
		
		int c1 = (x1+x2)/2;
		int c2 = (y1+y2)/2;
		
		
		
		
		if( Math.sqrt((c1-x2)*(c1-x2) + (c2-y2)*(c2-y2) ) >= Math.sqrt( (x1-x)*(x1-x) + (y1-y)*(y1-y) ))return true;
		return false;
	}
	public void deplacerDe(int dx, int dy){
		x1+=dx;
		y1+=dy;
	}
	public void modifier(int xa, int ya, int xb, int yb){
		x1 = Math.min(xa,xb);
		y1 = Math.min(ya,yb);
		x2 = Math.abs(xa-xb);
		y2 = Math.abs(ya-yb);
	}
	public void augmenterEpaisseur(int augm) {
		epaisseur = epaisseur+augm;
		if (epaisseur<1) epaisseur=1;
		if (epaisseur>20) epaisseur=20;
	}
	
	
	
}
