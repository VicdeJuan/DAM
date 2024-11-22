package ejercicio1;

public class MisionEspacial {

    public static void main(String[] args) {
        // Crear y lanzar los robots exploradores en paralelo
        Thread explorador1 = new Thread(new RobotExplorador("Curiosity", 3));
        Thread explorador2 = new Thread(new RobotExplorador("Perseverance", 2));
        Thread explorador3 = new Thread(new RobotExplorador("Opportunity", 5));
        Thread explorador4 = new Thread(new RobotExplorador("Spirit", 7));

        // Crear y operar los robots constructores secuencialmente
        
        explorador1.start();
        explorador2.start();
        explorador3.start();
        explorador4.start();

        
        
        try {
			explorador1.join();
			explorador2.join();
	        explorador3.join();
	        explorador4.join();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
        
   
        System.out.println("Terminada la misión espacial");
    }
}