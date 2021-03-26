package edu.unq.tpi.pconc.latinsquarevalidator;
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
    	    "Type the full path to the inputs file " +
            "(i. e. /tmp/inputs.txt) and press Enter:"
    	);
    	String inputsFilePath = in.nextLine();
    	System.out.println("Indicate the number of Threads to use and press Enter:");
    	Integer numberOfThreads = Integer.valueOf(in.nextLine());
    	in.close();
    	
    	Instant start = Instant.now();
    	
    	ThreadPool threadPool = new ThreadPool(numberOfThreads);
    	SortedList validSquareIndexes = new SortedList();
    	List<String> lines = Files.readAllLines(Paths.get(inputsFilePath));
    	Countdown countDown = new Countdown((Integer.valueOf(lines.get(0))));
    	for (int i = 1; i < lines.size(); i++) {
    		Validation validation = new Validation(i, lines.get(i), validSquareIndexes, countDown);
    		threadPool.addTask(validation);
    	}
    	threadPool.discardThreads();
    	
    	countDown.zero();
    	System.out.println("Index of the valid latin squares: " + validSquareIndexes.getListaIndices());
    	
    	Instant end = Instant.now();
    	
    	System.out.println("Runtime: " + Duration.between(start, end).getSeconds() + " seconds");
    }
}
