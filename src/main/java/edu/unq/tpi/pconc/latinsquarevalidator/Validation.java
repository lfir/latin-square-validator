package edu.unq.tpi.pconc.latinsquarevalidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validation implements Runnable {
	private Integer lineIndex;
	private List<String> lineInputs;
	private SortedList validSquareIndexes;
	private Countdown countdown;
	private Integer dimension;
	
	public Validation(Integer lineIndex, String lineInputs, SortedList validSquareIndexes, Countdown countdown) {
		this.lineIndex = lineIndex;
		this.lineInputs = new ArrayList<String>(Arrays.asList(lineInputs.split(" ")));
		this.validSquareIndexes = validSquareIndexes;
		this.countdown = countdown;
		this.dimension = Integer.valueOf(this.lineInputs.get(0));
	}

	@Override
	public void run() {
		List<List<Integer>> latinSquare = this.parseLatinSquare();
		
		boolean validCells = this.validateCells(latinSquare);
		if (validCells) {
			boolean validRows = Validation.validateRows(latinSquare);
			if (validRows) {
				boolean validColumns = this.validateColumns(latinSquare);
				if (validColumns) {
					validSquareIndexes.add(this.lineIndex);
				}
			}
		}
		
		countdown.dec();
	}

	private boolean validateColumns(List<List<Integer>> latinSquare) {
		boolean res = true;
		
		for (int i = 0; i < this.dimension; i++) {
			if (!res) {
				break;
			}
			
			List<Integer> column = new ArrayList<Integer>();
			for (List<Integer> row : latinSquare) {
				column.add(row.get(i));
			}
			for (Integer k : column) {
				if (Validation.repeatedNumber(k, column)) {
					res = false;
					break;
				}
			}
		}
		
		return res;
	}

	private static boolean validateRows(List<List<Integer>> latinSquare) {
		boolean res = true;
		
		for (List<Integer> row : latinSquare) {
			if (!res) {
				break;
			}
			
			for (Integer n : row) {
				if (Validation.repeatedNumber(n, row)) {
					res = false;
					break;
				}
			}
		}
		
		return res;
	}

	private static boolean repeatedNumber(Integer n, List<Integer> row) {
		List<Integer> temp = new ArrayList<Integer>();
		temp.addAll(row);
		boolean res = false;
		
		for (Integer k : row) {
			temp.remove(k);
			if (temp.remove(k)) {
				res = true;
				break;
			}
		}
		
		return res;
	}

	private boolean validateCells(List<List<Integer>> latinSquare) {
		boolean res = true;
		
		for (List<Integer> row : latinSquare) {
			if (!res) {
				break;
			}
			
			for (Integer n : row) {
				if ((n > this.dimension) || (n < 1)) {
					res = false;
					break;
				}
			}
		}
		
		return res;
	}

	protected List<List<Integer>> parseLatinSquare() {
		List<List<Integer>> latinSquare = new ArrayList<List<Integer>>();
		
		Integer start = 1;
		for (int i = 0; i < this.dimension; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int z = 0; z < this.dimension; z++) {
				Integer cell = Integer.valueOf(this.lineInputs.get(start + z));
				row.add(cell);
			}
			start += this.dimension;
			latinSquare.add(row);
		}
		
		return latinSquare;
	}
}
