package ejercicio2_semaforo_sin_terminar;

import java.util.ArrayList;
import java.util.Objects;

public class Posicion {
	int x;
	int y;
	
	static final int max_x = 5;
	final static int max_y = 5;
	
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
}

