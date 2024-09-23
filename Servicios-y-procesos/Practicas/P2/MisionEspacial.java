package hilos.Ejemplo2.robotsConstructores;

public class MisionEspacial {

    public static void main(String[] args) {
        // Crear y lanzar los robots exploradores en paralelo
        Thread explorador1 = new Thread(new RobotExplorador("Curiosity", 3));
        Thread explorador2 = new Thread(new RobotExplorador("Perseverance", 2));
        Thread explorador3 = new Thread(new RobotExplorador("Opportunity", 5));
        Thread explorador4 = new Thread(new RobotExplorador("Spirit", 7));

        // Crear y operar los robots constructores secuencialmente
        RobotConstructor constructor1 = new RobotConstructor("VCE", 8);
        RobotConstructor constructor2 = new RobotConstructor("Probe", 5);
        RobotConstructor constructor3 = new RobotConstructor("Drone", 2);
        
        
        explorador1.start();
        explorador2.start();
        explorador3.start();
        explorador4.start();

        
        /* Secuencial
        // Operar el primer constructor para construir 3 estructuras
        for (int i = 0; i < 3; i++) {
            constructor1.operar();
        }

        // Operar el segundo constructor para construir 5 estructuras
        for (int i = 0; i < 5; i++) {
            constructor2.operar();
        }

        // Operar el tercer constructor para construir 8 estructuras
        for (int i = 0; i < 8; i++) {
            constructor3.operar();
        }*/
        
        for (int i = 0; i < 100; i++) {
            new Thread(constructor1).start();
            new Thread(constructor2).start();
            new Thread(constructor3).start();
        }


        System.out.println(constructor1.getEstructurasConstruidas());
        System.out.println(constructor2.getEstructurasConstruidas());
        System.out.println(constructor3.getEstructurasConstruidas());
    }
}