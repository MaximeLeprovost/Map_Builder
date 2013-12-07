import java.awt.Color;
import java.awt.Image;


public class Ligne {
	

	  private String nomLigne = "Ligne A";

	  static private int nombreStation = 0;

	  static private String[][] station = new String[10][3];;
	  //private String[][] data=new String[10][3];
	
	public Ligne() {
		
		
		String[][] station = new String[10][3];
		station[0][0] = "Jean Villard";
		station[0][1] = "50";
		station[0][2] = "30";
		
		
		
				
		
		
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println("tab["+i+"]["+j+"] =" +station[i][j]);
			}
		}

		System.out.println(station[0][1]);
		System.out.println(Integer.parseInt(station[0][1]));
		
	}

	public int getStation(int i, int j) {
		System.out.println("i ="+i+"/ j="+j);
		System.out.println(station[0][1]);
		return Integer.parseInt(station[0][1]);
	}

	public void setStation(String[][] station) {
		Ligne.station = station;
	}

}
