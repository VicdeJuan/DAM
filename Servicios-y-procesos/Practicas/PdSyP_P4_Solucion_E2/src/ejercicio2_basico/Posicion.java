package ejercicio2_basico;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Posicion {
	int x;
	int y;
	
	static final int max_x = Juego.MAX_X;
	final static int max_y = Juego.MAX_Y;
	
	private static Random r = new Random();
	
	public Posicion(int x,int y) {
		this.x = x;
		this.y = y;
		
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
		
	}
	
	public boolean esValida() {
		return posicionMenor(new Posicion(max_x,max_y));
	}
	public boolean posicionMenor(Posicion pos) {
		return this.x <= pos.x && this.y <=pos.y;
	}
	
	@Override 
	public boolean equals(Object obj) {
		Posicion pos = (Posicion) obj;
		return this.x == pos.getX() && this.y == pos.getY();
	}
	
   @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


   
   public ArrayList<Posicion> getAdyacentes() {	
		ArrayList<Posicion> retval = new ArrayList<Posicion>();
		for (int i=-1;i<=1;i++) {
			for (int j=-1;j<=1;j++) {
				if (j==0 && i==0) {
					continue;
				}
				if (0<= x+i && x+i <= max_x && 0<= y+j  && y+j <= max_y) {
					retval.add(new Posicion(x+i,y+j));
				}
			}
		}
			
		return retval;
	}

	public static Posicion getRandomPos() {
		return new Posicion(r.nextInt(max_x),r.nextInt(max_y));
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
}

