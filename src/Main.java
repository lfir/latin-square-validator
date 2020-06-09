import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    	Scanner in = new Scanner(System.in);
    	System.out.println(
    	    "Ingrese la ruta completa al archivo de entradas " +
            "(p. ej. /tmp/inputs.txt) y presione Enter:"
    	);
    	String rutaArchivoInputs = in.nextLine();
    	System.out.println("Ingrese la cantidad de Threads a usar y presione Enter:");
    	Integer nroThreads = new Integer(in.nextLine());
    	in.close();
    	
    	Instant start = Instant.now();
    	
    	ThreadPool threadPool = new ThreadPool(nroThreads);
    	SortedList indicesCuadradosPerfectos = new SortedList();
    	List<String> lineas = Files.readAllLines(Paths.get(rutaArchivoInputs));
    	Countdown countDown = new Countdown((new Integer(lineas.get(0))));
    	for (int i = 1; i < lineas.size(); i++) {
    		Validacion validacion = new Validacion(i, lineas.get(i), indicesCuadradosPerfectos, countDown);
    		threadPool.agregarParaValidar(validacion);
    	}
    	threadPool.descartarThreads();
    	
    	countDown.zero();
    	System.out.println("Índices cuadrados latinos válidos: " + indicesCuadradosPerfectos.getListaIndices());
    	
    	Instant end = Instant.now();
    	
    	System.out.println("Duración de la ejecución: " + Duration.between(start, end).getSeconds() + " segundos");
    }
}
