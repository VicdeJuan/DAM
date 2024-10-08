package Prácticas.Ecosistema;

import java.util.ArrayList;
import java.util.Objects;

public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public ArrayList<Posicion> getAdyacentes(){
		ArrayList<Posicion> retval = new ArrayList<Posicion>();
		for (int i=-1;i<=1;i++) {
			for (int j=-1;j<=1;j++) {
				if (j==0 && i==0) {
					continue;
				}
				if (x+i >= 0 && y+j >=0) {
					retval.add(new Posicion(x+i,y+j));	
				}
				// TODO: sin pasarse de la posición máxima...
			}
		}
			
		return retval;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public int getX() { return x;}
	public int getY() { return y;}
	
	@Override
	public boolean equals(Object o) {
		Posicion pos = (Posicion) o;
		return (x == pos.getX() && y == pos.getY());
	}
	
	   @Override
	    public int hashCode() {
	        return Objects.hash(x, y);
	    }
	
}
