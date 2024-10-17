package examen.repasandoPOO;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Evaluador {
	
    public static void main(String[] args) {
        // Preparar el archivo CSV
        try (FileWriter writer = new FileWriter("evaluacion.csv")) {
            writer.append("Requisito,¿Se cumple?,Observación\n");

            // Evaluación de los requisitos. 
            // NO CAMBIAR EL ORDEN DE LLAMADAS A LAS FUNCIONES PARA 
            // MANTENER EL BUEN FUNCIONAMIENTO DEL CONTADOR
            evaluarClasePersona(writer);
            System.out.println(Vehículo.getContador());
            evaluarClaseVehiculo(writer);
            System.out.println(Vehículo.getContador());
            evaluarClaseCoche(writer);
            System.out.println(Vehículo.getContador());
            evaluarClaseCamion(writer);
            System.out.println(Vehículo.getContador());
            evaluarFuncionamientoGeneralCon3Vehiculos(writer);
            System.out.println(Vehículo.getContador());
            evaluarFuncionamientoGeneralCon6Vehiculos(writer);

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de evaluación: " + e.getMessage());
        }
    }

    // Evaluar que no hay redeclaración de atributos heredados en Coche y Camión
    private static void evaluarClaseCoche(FileWriter writer) throws IOException {
        try {
            // Crear un coche sin propietario
            Coche coche1 = new Coche("Marca", "Modelo", 2020, 4);
            // Crear un coche con propietario
            Persona propietario = new Persona("Propietario");
            Coche coche2 = new Coche("Marca", "Modelo", 2021, 4, propietario);

            if (coche1.getNumeroPuertas() == 4 && coche2.getPropietario().getNombre().equals("Propietario")) {
                writer.append("Clase Coche hereda de Vehículo con número de puertas y constructor con/ sin propietario,2,Correctamente implementada\n");
            } else {
                writer.append("Clase Coche hereda de Vehículo con número de puertas y constructor con/ sin propietario,1,Implementación parcial o error en número de puertas o constructores\n");
            }
        } catch (Exception e) {
            writer.append("Clase Coche hereda de Vehículo con número de puertas y constructor con/ sin propietario,0,Fallo en la clase Coche: " + e.getMessage() + "\n");
        }

        try {
            // Verificar que Coche no tiene atributos heredados redeclarados
            Field[] camposCoche = Coche.class.getDeclaredFields();
            boolean tieneAtributosHeredados = false;
            for (Field campo : camposCoche) {
                if (campo.getName().equals("marca") || campo.getName().equals("modelo") || campo.getName().equals("año") || campo.getName().equals("propietario")) {
                    tieneAtributosHeredados = true;
                    break;
                }
            }
            if (!tieneAtributosHeredados) {
                writer.append("Clase Coche no redeclara atributos heredados de Vehículo,2,Atributos heredados no redeclarados\n");
            } else {
                writer.append("Clase Coche no redeclara atributos heredados de Vehículo,0,Error: Atributos heredados redeclarados\n");
            }

            // Verificar que no hay getters/setters para atributos heredados
            Method[] metodosCoche = Coche.class.getDeclaredMethods();
            boolean tieneGettersSettersHeredados = false;
            for (Method metodo : metodosCoche) {
                if (metodo.getName().startsWith("get") || metodo.getName().startsWith("set")) {
                    String atributo = metodo.getName().substring(3).toLowerCase();
                    if (atributo.equals("marca") || atributo.equals("modelo") || atributo.equals("año") || atributo.equals("propietario")) {
                        tieneGettersSettersHeredados = true;
                        break;
                    }
                }
            }
            if (!tieneGettersSettersHeredados) {
                writer.append("Clase Coche no tiene getters/setters para atributos heredados de Vehículo,2,No hay getters/setters para atributos heredados\n");
            } else {
                writer.append("Clase Coche no tiene getters/setters para atributos heredados de Vehículo,0,Error: Hay getters/setters para atributos heredados\n");
            }

            // Verificar que los atributos de Coche son private
            boolean atributosSonPrivate = true;
            for (Field campo : camposCoche) {
                if (!java.lang.reflect.Modifier.isPrivate(campo.getModifiers())) {
                    atributosSonPrivate = false;
                    break;
                }
            }
            if (atributosSonPrivate) {
                writer.append("Clase Coche tiene todos sus atributos como private,2,Atributos correctamente declarados como private\n");
            } else {
                writer.append("Clase Coche tiene todos sus atributos como private,0,Error: Atributos no son private\n");
            }

        } catch (Exception e) {
            writer.append("Evaluación de la clase Coche fallida,0,Error: " + e.getMessage() + "\n");
        }
    }

    
    // Evaluar clase Vehículo (verificaciones adicionales)
    private static void evaluarClaseVehiculo(FileWriter writer) throws IOException {
        try {
            // Crear un vehículo sin propietario
            Vehículo vehiculo1 = new Vehículo("Marca", "Modelo", 2020);
            // Crear un vehículo con propietario
            Persona persona = new Persona("Propietario");
            Vehículo vehiculo2 = new Vehículo("Marca", "Modelo", 2020, persona);

            if (Vehículo.getContador() == 2 && vehiculo1.getPropietario() == null && vehiculo2.getPropietario().getNombre().equals("Propietario")) {
                writer.append("Clase Vehículo con marca, modelo, año, propietario y contador estático,2,Correctamente implementada\n");
            } else {
                writer.append("Clase Vehículo con marca, modelo, año, propietario y contador estático,1,Implementación parcial o error en el contador, propietario o constructores\n");
            }
        } catch (Exception e) {
            writer.append("Clase Vehículo con marca, modelo, año, propietario y contador estático,0,Fallo en la clase Vehículo: " + e.getMessage() + "\n");
        }
    
    
        try {
            // Verificar que los atributos de Vehículo son private
            Field[] camposVehiculo = Vehículo.class.getDeclaredFields();
            boolean atributosSonPrivate = true;
            for (Field campo : camposVehiculo) {
                if (!java.lang.reflect.Modifier.isPrivate(campo.getModifiers())) {
                    atributosSonPrivate = false;
                    break;
                }
            }
            if (atributosSonPrivate) {
                writer.append("Clase Vehículo tiene todos sus atributos como private,2,Atributos correctamente declarados como private\n");
            } else {
                writer.append("Clase Vehículo tiene todos sus atributos como private,0,Error: Atributos no son private\n");
            }

        } catch (Exception e) {
            writer.append("Evaluación de la clase Vehículo fallida,0,Error: " + e.getMessage() + "\n");
        }
    }
    
    
    // Evaluar clase Persona
    private static void evaluarClasePersona(FileWriter writer) throws IOException {
        try {
            Persona persona = new Persona("Prueba");
            String nombre = persona.getNombre();
            
            if (nombre.equals("Prueba")) {
                writer.append("Clase Persona con nombre (String),2,Correctamente implementada\n");
            } else {
                writer.append("Clase Persona con nombre (String),1,Error parcial en la implementación del nombre\n");
            }
        } catch (Exception e) {
            writer.append("Clase Persona con nombre (String),0,Fallo en la clase Persona: " + e.getMessage() + "\n");
        }
    }
    
    // Evaluar el funcionamiento general con 3 vehículos
    private static void evaluarFuncionamientoGeneralCon3Vehiculos(FileWriter writer) throws IOException {
        try {
            // Crear personas
            Persona persona1 = new Persona("Juan");
            Persona persona2 = new Persona("Ana");

            // Crear vehículos y asignar propietarios
            Coche coche1 = new Coche("Toyota", "Corolla", 2020, 4);
            coche1.setPropietario(persona1);
            
            Coche coche2 = new Coche("Honda", "Civic", 2021, 4);
            coche2.setPropietario(persona2);
            
            Coche coche3 = new Coche("Ford", "Focus", 2022, 4); // Sin propietario

            // Verificar los propietarios
            if (coche1.getPropietario().getNombre().equals("Juan") &&
                coche2.getPropietario().getNombre().equals("Ana") &&
                coche3.getPropietario() == null) {
                
                writer.append("Funcionamiento general (3 vehículos): Asignación de propietarios,2,Asignación correcta\n");
            } else {
                writer.append("Funcionamiento general (3 vehículos): Asignación de propietarios,1,Error parcial en la asignación de propietarios\n");
            }

            // Verificar el contador de vehículos
            if (Vehículo.getContador() == 6+3) { //Se han creado 6 vehículos previamente
                writer.append("Funcionamiento general (3 vehículos): Contador estático de vehículos,2,Contador correcto\n");
            } else {
                writer.append("Funcionamiento general (3 vehículos): Contador estático de vehículos,1,Error en el manejo del contador\n");
            }

        } catch (Exception e) {
            writer.append("Funcionamiento general (3 vehículos): Asignación de propietarios y contador,0,Fallo en el funcionamiento general: " + e.getMessage() + "\n");
        }
    }

    // Evaluar el funcionamiento general con 6 vehículos
    private static void evaluarFuncionamientoGeneralCon6Vehiculos(FileWriter writer) throws IOException {
        try {
            // Crear personas
            Persona persona1 = new Persona("Juan");
            Persona persona2 = new Persona("Ana");

            // Crear vehículos y asignar propietarios
            Coche coche1 = new Coche("Toyota", "Corolla", 2020, 4);
            coche1.setPropietario(persona1);
            
            Coche coche2 = new Coche("Honda", "Civic", 2021, 4);
            coche2.setPropietario(persona2);
            
            Coche coche3 = new Coche("Ford", "Focus", 2022, 4); // Sin propietario

            Camión camion1 = new Camión("Mercedes", "Actros", 2019, 18);
            camion1.setPropietario(persona1);

            Camión camion2 = new Camión("Volvo", "FH", 2020, 20); // Sin propietario

            Coche coche4 = new Coche("Tesla", "Model 3", 2022, 4); // Sin propietario

            // Verificar los propietarios
            if (coche1.getPropietario().getNombre().equals("Juan") &&
                coche2.getPropietario().getNombre().equals("Ana") &&
                coche3.getPropietario() == null &&
                camion1.getPropietario().getNombre().equals("Juan") &&
                camion2.getPropietario() == null &&
                coche4.getPropietario() == null) {
                
                writer.append("Funcionamiento general (6 vehículos): Asignación de propietarios,2,Asignación correcta\n");
            } else {
                writer.append("Funcionamiento general (6 vehículos): Asignación de propietarios,1,Error parcial en la asignación de propietarios\n");
            }

            // Verificar el contador de vehículos
            if (Vehículo.getContador() == 6+9) { // Se han creado 9 vehículos antes de llamar esta fucnión.
                writer.append("Funcionamiento general (6 vehículos): Contador estático de vehículos,2,Contador correcto\n");
            } else {
                writer.append("Funcionamiento general (6 vehículos): Contador estático de vehículos,1,Error en el manejo del contador\n");
            }

        } catch (Exception e) {
            writer.append("Funcionamiento general (6 vehículos): Asignación de propietarios y contador,0,Fallo en el funcionamiento general: " + e.getMessage() + "\n");
        }
    }
    
    private static void evaluarClaseCamion(FileWriter writer) throws IOException {
        try {
            // Crear un camión sin propietario
            Camión camion1 = new Camión("Marca", "Modelo", 2020, 18);
            // Crear un camión con propietario
            Persona propietario = new Persona("Propietario");
            Camión camion2 = new Camión("Marca", "Modelo", 2021, 20, propietario);

            if (camion1.getCapacidadCarga() == 18 && camion2.getPropietario().getNombre().equals("Propietario")) {
                writer.append("Clase Camión hereda de Vehículo con capacidad de carga y constructor con/ sin propietario,2,Correctamente implementada\n");
            } else {
                writer.append("Clase Camión hereda de Vehículo con capacidad de carga y constructor con/ sin propietario,1,Implementación parcial o error en capacidad de carga o constructores\n");
            }
        } catch (Exception e) {
            writer.append("Clase Camión hereda de Vehículo con capacidad de carga y constructor con/ sin propietario,0,Fallo en la clase Camión: " + e.getMessage() + "\n");
        }
        
        try {
            // Verificar que Camión no tiene atributos heredados redeclarados
            Field[] camposCamion = Camión.class.getDeclaredFields();
            boolean tieneAtributosHeredados = false;
            for (Field campo : camposCamion) {
                if (campo.getName().equals("marca") || campo.getName().equals("modelo") || campo.getName().equals("año") || campo.getName().equals("propietario")) {
                    tieneAtributosHeredados = true;
                    break;
                }
            }
            if (!tieneAtributosHeredados) {
                writer.append("Clase Camión no redeclara atributos heredados de Vehículo,2,Atributos heredados no redeclarados\n");
            } else {
                writer.append("Clase Camión no redeclara atributos heredados de Vehículo,0,Error: Atributos heredados redeclarados\n");
            }

            // Verificar que no hay getters/setters para atributos heredados
            Method[] metodosCamion = Camión.class.getDeclaredMethods();
            boolean tieneGettersSettersHeredados = false;
            for (Method metodo : metodosCamion) {
                if (metodo.getName().startsWith("get") || metodo.getName().startsWith("set")) {
                    String atributo = metodo.getName().substring(3).toLowerCase();
                    if (atributo.equals("marca") || atributo.equals("modelo") || atributo.equals("año") || atributo.equals("propietario")) {
                        tieneGettersSettersHeredados = true;
                        break;
                    }
                }
            }
            if (!tieneGettersSettersHeredados) {
                writer.append("Clase Camión no tiene getters/setters para atributos heredados de Vehículo,2,No hay getters/setters para atributos heredados\n");
            } else {
                writer.append("Clase Camión no tiene getters/setters para atributos heredados de Vehículo,0,Error: Hay getters/setters para atributos heredados\n");
            }

            // Verificar que los atributos de Camión son private
            boolean atributosSonPrivate = true;
            for (Field campo : camposCamion) {
                if (!java.lang.reflect.Modifier.isPrivate(campo.getModifiers())) {
                    atributosSonPrivate = false;
                    break;
                }
            }
            if (atributosSonPrivate) {
                writer.append("Clase Camión tiene todos sus atributos como private,2,Atributos correctamente declarados como private\n");
            } else {
                writer.append("Clase Camión tiene todos sus atributos como private,0,Error: Atributos no son private\n");
            }

        } catch (Exception e) {
            writer.append("Evaluación de la clase Camión fallida,0,Error: " + e.getMessage() + "\n");
        }
    }
    
  }
