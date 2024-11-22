package ejercicio2_2;

public class MisionEspacial {

	private final static boolean paralelo = true;
	
	
    public static void main(String[] args){
		
        // Cre[ar y lanzar los robots exploradores en paralelo
    	Thread exploradores[] = {
    			new Thread(new RobotExplorador("Curiosity", 3)),
    			new Thread(new RobotExplorador("Perseverance", 2)),
    			new Thread(new RobotExplorador("Opportunity", 5)),
    			new Thread(new RobotExplorador("Spirit", 7))
    	};

        // Crear y operar los robots constructores secuencialmente
    	RobotConstructor constructores[] = {
    		new RobotConstructor("VCE",8),
    		new RobotConstructor("Probe", 5),
    		new RobotConstructor("Drone", 2)	
    	};
    	
    	
        for (Thread t: exploradores) {
        	t.start();
        }
        
        // Para mantener el main no hay otra opción que utilizar un booleano o alguna solución similar (línea de comandos).
        // El motivo es que el proceso principal debe "esperar" a los hilos con un join. Y ese join se debe hacer desde el 
        // proceso principal, es decir, desde el main.
    	if (paralelo) {    	
	    	for (Thread t: constructores) {
	    		t.start();
	    	}
	    	for (Thread t: constructores) {
	    		try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
    	} else {
    		// Cada uno opera 10 veces secuencialmente.
    		for (RobotConstructor c : constructores) {
    			for(int i = 0; i< 10; i++) {
    				c.operar();
    			}
    		}
    		
    	}
    	
    	for (Thread t: exploradores) {
    		try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
        
        System.out.println("Estructuras construidas por cada Robot:");
    	for (RobotConstructor c : constructores) 
    		//Observa el warning de esta construcción. Aunque se entiende que se pueda acceder desde el objeto,
    		// lo recomendado es acceder desde la clase. RobotConstructor.getEstructurasConstruidas()
    		System.out.println("\t- " + c.nombre + ": " + c.getEstructurasConstruidas());

       
    }
}