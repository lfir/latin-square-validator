package edu.unq.tpi.pconc.latinsquarevalidator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LatinSquareValidationTest {
	private SortedList validIndexes;
	private Countdown cd;
	
	@Before
	public void setUp() {
		this.validIndexes = new SortedList();
		this.cd = new Countdown(1);
	}
	
	@Test
	public void indexOfValidSquareIsAddedToIndexList() {
		String input = "2 1 2 2 1";
		Validation validation = new Validation(0, input, this.validIndexes, this.cd);
		validation.run();
		
		assertTrue(this.validIndexes.getList().contains(0));
		assertEquals(1, this.validIndexes.getList().size());
	}
	
	@Test
	public void indexOfSquareWithInvalidCellIsNotAddedToIndexList() {
		String input = "2 1 2 2 8";
		Validation validation = new Validation(0, input, this.validIndexes, this.cd);
		validation.run();
		
		assertTrue(this.validIndexes.getList().isEmpty());
	}
	
	@Test
	public void indexOfSquareWithInvalidRowIsNotAddedToIndexList() {
		String input = "3 1 2 2 2 3 1 3 1 2";
		Validation validation = new Validation(0, input, this.validIndexes, this.cd);
		validation.run();
		
		assertTrue(this.validIndexes.getList().isEmpty());
	}
	
	@Test
	public void indexOfSquareWithInvalidColumnIsNotAddedToIndexList() {
		String input = "3 1 2 3 1 3 2 2 3 1";
		Validation validation = new Validation(0, input, this.validIndexes, this.cd);
		validation.run();
		
		assertTrue(this.validIndexes.getList().isEmpty());
	}
	
	@Test
	public void inputStringIsCorrectlyConvertedToListOfLists() {
		String input = "3 1 2 3 1 3 2 2 3 1";
		Validation validation = new Validation(0, input, this.validIndexes, this.cd);
		List<Integer> row0 = new ArrayList<Integer>();
		row0.add(1);
		row0.add(2);
		row0.add(3);
		List<Integer> row1 = new ArrayList<Integer>();
		row1.add(1);
		row1.add(3);
		row1.add(2);
		List<Integer> row2 = new ArrayList<Integer>();
		row2.add(2);
		row2.add(3);
		row2.add(1);
		
		List<List<Integer>> parsedLatinSquare = validation.parseLatinSquare();
		
		assertEquals(row0, parsedLatinSquare.get(0));
		assertEquals(row1, parsedLatinSquare.get(1));
		assertEquals(row2, parsedLatinSquare.get(2));
		assertEquals(3, parsedLatinSquare.size());
	}
}
