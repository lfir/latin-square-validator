package edu.unq.tpi.pconc.latinsquarevalidator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConcurrentLatinSquareValidationTest {
	private ThreadPool threadPool;
	private SortedList validIndexes;
	private List<String> inputs;
	private Countdown countDown;
	
	@Before
	public void setUp() throws InterruptedException {
		this.inputs = this.getInputs();
		this.threadPool = new ThreadPool(2);
		this.countDown = new Countdown(Integer.valueOf(this.inputs.get(0)));
		this.validIndexes = new SortedList();
		
		for (int i = 1; i < this.inputs.size(); i++) {
    		Validation validation = new Validation(
    				i, this.inputs.get(i), this.validIndexes, this.countDown
    		);
    		this.threadPool.addTask(validation);
    	}
    	this.threadPool.discardThreads();
    	this.countDown.zero(); // Wait until all threads have finished
	}
	
	@Test
	public void outputArrayContainsIndexesOfValidSquares() {
		List<Integer> validIndexes = this.validIndexes.getListaIndices();

		assertTrue(validIndexes.contains(1));
		assertTrue(validIndexes.contains(4));
		assertTrue(validIndexes.contains(7));
		assertTrue(validIndexes.contains(9));
	}
	
	@Test
	public void outputArrayContainsIndexesOfValidSquaresInAscendingOrder() {
		List<Integer> validIndexes = this.validIndexes.getListaIndices();
		
		assertEquals(0, validIndexes.indexOf(1));
		assertEquals(1, validIndexes.indexOf(4));
		assertEquals(2, validIndexes.indexOf(7));
		assertEquals(3, validIndexes.indexOf(9));
	}

	/*
	 * Returns a list of inputs with the following structure:
	 * Line 0: Number of cases to process.
	 * Lines 1-n: Each line has one Latin square.
	 * First integer is the square's dimension and the
	 * rest are the cell values, from left to right. 
	 */
	private List<String> getInputs() {
		List<String> inputs = new ArrayList<String>();
		inputs.add("10");
		inputs.add("2 1 2 2 1");
		inputs.add("2 1 1 1 2");
	    inputs.add("3 1 2 3 3 1 2 4 3 1");
		inputs.add("3 1 2 3 2 3 1 3 1 2");
		inputs.add("5 1 2 3 4 5 5 4 3 2 1 3 4 5 1 2 3 2 1 4 5 4 2 3 5 1");
		inputs.add("5 3 4 1 2 5 4 1 2 5 3 4 2 3 1 5 1 5 4 3 2 2 3 5 4 1");
	    inputs.add("5 3 4 1 2 5 4 1 2 5 3 5 2 3 1 4 1 5 4 3 2 2 3 5 4 1");
		inputs.add("5 1 2 3 4 5 2 3 4 5 1 3 4 5 1 2 4 5 1 3 2 5 1 2 3 4");
		inputs.add("15 7 5 8 9 6 1 14 3 11 13 2 10 12 4 15 15 9 3 12 14 11 8 5 2 6 1 7 13 10 4 1 4 15 6 11 7 13 14 10 8 5 2 3 9 12 13 2 6 4 3 8 5 11 14 15 10 1 7 12 9 4 14 1 2 13 6 12 9 3 10 7 11 15 5 8 8 3 9 15 2 13 4 12 1 5 6 14 10 11 7 6 1 2 10 12 3 7 8 9 11 14 15 4 13 5 2 11 7 3 5 4 15 6 13 9 12 8 1 14 10 3 7 12 13 9 2 10 15 5 14 8 4 11 6 1 11 6 10 5 1 12 2 4 15 3 13 9 8 7 14 12 8 14 11 4 15 1 10 6 7 3 5 9 2 13 5 13 11 8 7 10 3 1 4 12 9 6 14 15 2 9 10 5 1 15 14 6 7 12 4 11 13 2 8 3 14 12 4 7 10 9 11 13 8 2 15 3 5 1 6 10 15 13 14 8 5 9 2 7 1 4 12 6 3 11");
		inputs.add("15 8 6 9 5 2 9 12 10 8 10 7 9 6 6 10 9 10 5 13 11 14 10 14 12 6 8 4 4 10 7 4 5 14 10 7 6 3 12 7 12 1 14 14 5 8 7 4 14 8 4 7 8 6 3 14 9 3 4 14 10 5 8 3 10 10 6 12 11 11 11 11 4 3 8 12 9 13 1 8 12 7 9 14 11 5 1 9 2 6 1 13 1 5 3 3 5 2 1 12 8 8 10 14 2 3 7 11 7 12 7 9 9 11 6 10 7 10 11 5 10 10 11 12 6 4 6 12 14 10 3 13 3 14 12 6 1 10 9 12 14 6 1 8 11 10 10 4 6 6 10 10 7 1 11 10 9 3 3 4 14 10 10 7 5 9 3 9 2 7 8 6 13 1 6 3 5 14 1 11 1 10 2 12 4 4 12 11 8 5 14 7 4 4 9 4 6 8 1 1 11 6 8 5 1 13 7 5 14 2 9 5 5 5 14 5 6 1 10 4 6 7 2 8 14 10");
		return inputs;
	} 
}
